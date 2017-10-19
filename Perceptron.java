
public class Perceptron {

    private double[] w;

    public Perceptron () {

        w = new double[3];
        for ( int i = 0; i < 3; i++ )
            w[i] = 0.4;
    }



    //sumator
    public int process ( int[] x ) {
        double yEnd = 0;
        for ( int i = 0; i < 3; i++ )
            yEnd += x[i] * w[i];

        return activeFunction( yEnd);
    }

    //funkcja aktywująca
    private int activeFunction ( double yEnd ) {
        if(yEnd< 0) return 0;
        else return 1;
    }

    public void learn ( int[] x, double y, double lr ) {
        double yEnd = process( x );

        for ( int i = 0; i < 3; i++ )
            w[i] +=  x[i]*( y - yEnd ) * lr;
    }

    public double getW ( int i ) {
        return w[i];
    }
}