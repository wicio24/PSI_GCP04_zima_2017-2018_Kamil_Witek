package Perceptron;

import java.util.Random;

public class Perceptron {

    private int liczbaWejsc; //ilość wejść
    private double[] w; //wagi

    public Perceptron ( int iloscWejsc ) {
        liczbaWejsc = iloscWejsc;
        w = new double[liczbaWejsc];

        for ( int i = 0; i < liczbaWejsc; i++ )
            w[i] = new Random().nextDouble(); //nadanie wag poczatkowych, losowo
    }

    //Funkcja aktywacji
    public int activation(double output) {
        if (output < 0) return 0;
        else return 1;
    }


    public int suma ( int[] x ) {
        double y_p = 0;
        for ( int i = 0; i < liczbaWejsc; i++ )
            y_p += x[i] * w[i];

        return activation( y_p );
    }

    //nauka
    public void learn ( int[] x, double y, double lr ) {
        double y_p = suma( x );

        for ( int i = 0; i < liczbaWejsc; i++ )
            w[i] += ( y - y_p ) * lr * x[i]; //modyfikacja wag
    }
}