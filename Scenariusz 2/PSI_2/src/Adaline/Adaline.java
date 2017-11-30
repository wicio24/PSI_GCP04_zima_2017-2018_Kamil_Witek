package Adaline;

import java.util.Random;

public class Adaline {

    private int liczbaWejsc; //ilość wejść
    private double[] w; //wagi

    public Adaline ( int iloscWejsc ) {
        liczbaWejsc = iloscWejsc;
        w = new double[liczbaWejsc];

        for ( int i = 0; i < liczbaWejsc; i++ )
            w[i] = new Random().nextDouble(); //wagi początkowe sa losowane
    }

    //Funkcja aktywacji
    public int activation(double output) {
        if (output < 0) return -1;
        else return 1;
    }

    //suma
    public double suma ( int[] x ) {
        double y_p = 0;
        for ( int i = 0; i < liczbaWejsc; i++ )
            y_p += x[i] * w[i];

        return y_p;
    }

    //nauka
    public void learn ( int[] x, double y, double lr ) {
        double y_p = suma( x );

        for ( int i = 0; i < liczbaWejsc; i++ )
            w[i] += ( y - y_p ) * lr * x[i]; //modyfikacja wag
    }

    //testowanie
    public int test ( int[] x )
    {
        return ( activation( suma( x ) ) );
    }
}