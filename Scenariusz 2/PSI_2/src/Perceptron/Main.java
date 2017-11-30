package Perceptron;

import java.util.Arrays;

public class Main{

    public static void main ( String[] args ) {

        int liczbaWejsc = 7;        //ilość wejść
        int liczbaLiter = 13;       //ilość liter MAX 26
        int epoka = 0;    //licznik ilości epok uczenia się
        double wspolczynnikNauki = 0.1;    //krok uczenia się

        Perceptron[] perceptron = new Perceptron[liczbaWejsc];
        for ( int i = 0; i < liczbaWejsc; i++ )
            perceptron[i] = new Perceptron( liczbaWejsc );

        int[] y = new int[liczbaLiter * 2];     //0 - duża litera, 1 - mała litera
        Arrays.fill( y, 0, liczbaLiter, 0 );
        Arrays.fill( y, liczbaLiter, liczbaLiter * 2, 1 );

        int[] wyj = new int[liczbaLiter * 2];   //tablica przechowująca wyniki testowania perceptronu
        Arrays.fill( wyj, 0, liczbaLiter * 2, 0 );
        int licznik;
        while ( ! Arrays.equals( y, wyj ) ) {
            licznik=0;
            for ( int i = 0; i < 2; i++ )       //0 - wielkie litery, 1 - małe litery
                for ( int j = 0; j < liczbaLiter; j++ )
                    learn( perceptron, liczbaWejsc, wspolczynnikNauki, i, j );

            wyj = test( perceptron, liczbaLiter, liczbaWejsc );

            for (int i = 0; i < liczbaLiter * 2; i++) {
                if (wyj[i] != y[i])
                    licznik++;
            }

            System.out.println((licznik * 100) / (2 * liczbaLiter)+",00%");
            epoka++;
        }

        System.out.println( "Ilość kroków do nauczenia się = " + epoka );
    }


    public static void learn ( Perceptron[] perceptron, int liczbaWejsc, double wspolczynnikNauki, int i, int j ) {
        int[] vector;                   //tablica przechowująca wektor sygnałów wejściowych do uczenia pierwszej warstwy sieci
        vector = Alphabet.getLetter( i, j );

        int[] vector_p = new int[liczbaWejsc];  //tablica przechowująca wektor sygnałów wyjściowych pierwszej warstwy sieci
        vector_p[0] = 1; //bias

        for ( int k = 0; k < liczbaWejsc - 1; k++ ) {               //uczenie pierwszej warstwy
            perceptron[k].learn( vector, i, wspolczynnikNauki );
            vector_p[k + 1] = perceptron[k].suma( vector );    //pobranie sygnału wyjściowego
        }
        perceptron[liczbaWejsc - 1].learn( vector_p, i, wspolczynnikNauki );             //uczenie perceptronu wynikowego na podstawie sygnałów wyjściowych pierwszej warstwy
    }

    public static int[] test ( Perceptron[] perceptron, int liczbaLiter, int liczbaWejsc ) {
        int[] wyj = new int[liczbaLiter * 2];
        int[] vector;                   //tablica przechowująca wektor sygnałów wejściowych do testowania pierwszej warstwy sieci
        int[] vector_p = new int[liczbaWejsc];  //tablica przechowująca wektor sygnałów wyjściowych pierwszej warstwy sieci
        vector_p[0] = 1;                //bias

        for ( int i = 0; i < 2; i++ ) { //testowanie, celem upewnienia się, czy sieć już nauczona
            for ( int j = 0; j < liczbaLiter; j++ ) {
                vector = Alphabet.getLetter( i, j );
                for ( int k = 0; k < liczbaWejsc - 1; k++ )
                    vector_p[k + 1] = perceptron[k].suma( vector );

                wyj[i * liczbaLiter + j] = perceptron[liczbaWejsc - 1].suma( vector_p );
            }
        }
        return wyj;
    }
}