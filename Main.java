public class Main {

    public static void main ( String[] args ) {

        Perceptron perc = new Perceptron();

        double learningRate = 0.3; //krok uczenia się

        int x0 = 1; //bias

        //dane wejściowe
        int[] x1 = { 0, 0, 1, 1 };
        int[] x2 = { 0, 1, 0, 1 };

        int[] y = { 0, 0, 0, 1 }; //AND

        //nauka
       for ( int j = 0; j < 1; j++ ) {  //ilosc powtorzen
            for (int i = 0; i < 4; i++) {
                perc.learn(new int[] {x0, x1[i], x2[i]}, y[i], learningRate);
            }
        }



        System.out.println( "WAGI:" );
        for ( int i = 0; i < 3; i++ )
            System.out.println( "w" + i + " = " + perc.getW( i ) );



        System.out.println();

        //test
        System.out.println(x1[0] + " " + x2[0] + "   " + perc.process( new int[] { x0, x1[0], x2[0] } ) );
        System.out.println(x1[1] + " " + x2[1] + "   " + perc.process( new int[] { x0, x1[1], x2[1] } ) );
        System.out.println(x1[2] + " " + x2[2] + "   " + perc.process( new int[] { x0, x1[2], x2[2] } ) );
        System.out.println(x1[3] + " " + x2[3] + "   " + perc.process( new int[] { x0, x1[3], x2[3] } ) );
    }
}