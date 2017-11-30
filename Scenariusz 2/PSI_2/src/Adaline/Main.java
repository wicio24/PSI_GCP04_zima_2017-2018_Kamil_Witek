package Adaline;

import java.util.Arrays;

public class Main{

    public static void main ( String[] args ) {

        int liczbaWejsc = 7;        //ilość wejść
        int liczbaLiter = 13;       //ilość liter MAX 26
        int epoka = 0;    //licznik ilości epok uczenia się
        double wspolczynnikNauki = 0.1;    //krok uczenia się

        Adaline[] adaline = new Adaline[liczbaWejsc];
        for ( int i = 0; i < liczbaWejsc; i++ )
            adaline[i] = new Adaline( liczbaWejsc );

        int[] y = new int[liczbaLiter * 2];     //-1 - duża litera, 1 - mała litera
        Arrays.fill( y, 0, liczbaLiter, - 1 );
        Arrays.fill( y, liczbaLiter, liczbaLiter * 2, 1 );

        int[] wyj = new int[liczbaLiter * 2];   //tablica przechowująca wyniki testowania adalineline
        Arrays.fill( wyj, 0, liczbaLiter * 2, 0 );
        int licznik = 0;
        while ( ! Arrays.equals( y, wyj ) ) {
            licznik=0;
            for ( int i = 0; i < 2; i++ ) {     //-1 - wielkie litery, 1 - małe litery
                for ( int j = 0; j < liczbaLiter; j++ )
                    learn( adaline, liczbaWejsc, wspolczynnikNauki, i, j );
            }

            wyj = test( adaline, liczbaLiter, liczbaWejsc );
            for (int i = 0; i < liczbaLiter * 2; i++) {
                if (wyj[i] != y[i])
                    licznik++;
                }

            System.out.println((licznik * 100) / (2 * liczbaLiter)+",00%");
            epoka++;
        }

        System.out.println( "Ilość kroków do nauczenia się = " + epoka );
    }


    private static void learn ( Adaline[] adaline, int liczbaWejsc, double wspolczynnikNauki, int i, int j ) {
        int[] vector;                   //tablica przechowująca wektor sygnałów wejściowych do uczenia pierwszej warstwy sieci
        vector = Alphabet.getLetter( i, j );
        format( vector );

        int[] vector_p = new int[liczbaWejsc];  //tablica przechowująca wektor sygnałów wyjściowych pierwszej warstwy sieci
        vector_p[0] = 1; //bias

        int letter_size;
        if ( i == 0 ) letter_size = - 1;
        else letter_size = 1;

        for ( int k = 0; k < liczbaWejsc - 1; k++ ) {               //uczenie pierwszej warstwy
            adaline[k].learn( vector, letter_size, wspolczynnikNauki );
            vector_p[k + 1] = adaline[k].test( vector );        //pobranie sygnału wyjściowego
        }
        adaline[liczbaWejsc - 1].learn( vector_p, letter_size, wspolczynnikNauki );    //uczenie perceptronu wynikowego na podstawie sygnałów wyjściowych pierwszej warstwy
    }

    private static int[] test ( Adaline[] adaline, int liczbaLiter, int liczbaWejsc ) {
        int[] wyj = new int[liczbaLiter * 2];
        int[] vector;                   //tablica przechowująca wektor sygnałów wejściowych do testowania pierwszej warstwy sieci
        int[] vector_p = new int[liczbaWejsc];  //tablica przechowująca wektor sygnałów wyjściowych pierwszej warstwy sieci
        vector_p[0] = 1;                //bias

        for ( int i = 0; i < 2; i++ ) { //testowanie, celem upewnienia się, czy sieć już nauczona
            for ( int j = 0; j < liczbaLiter; j++ ) {
                vector = Alphabet.getLetter( i, j );
                format( vector );

                for ( int k = 0; k < liczbaWejsc - 1; k++ )
                    vector_p[k + 1] = adaline[k].test( vector );

                wyj[i * liczbaLiter + j] = adaline[liczbaWejsc - 1].test( vector_p );
            }
        }
        return wyj;
    }

    //w przypadku adalineline sygnały wejściowe = 0 muszą być zamienione na sygnały -1
    private static void format( int[] vector ){
        for ( int k = 0; k < vector.length; k++ )
            if ( vector[k] == 0 ) vector[k] = -1;
    }
}