package Homework;

public class Main {
    /**
     * The main method starts by checking the arguments from the command line
     * and then creates an object square through which the functions of the Latin class are called
     * @param args contains the command line arguments
     */
    public static void main(String[] args) {

        long start = System.nanoTime();//this predefined function give the time that the program starts to run

        /**
         * We can find out if the number of parameters entered in the command line is correct by comparing the length of the args vector
         * According to the requirement, the number of arguments must be 1, so if it is different from this value
         * it is necessary to signal with a message and then to stop the running program with the System.exit predefined method
         */
        if (args.length != 1 ) {
            System.out.println("Wrong number of arguments");
            System.exit(-1);
        }

        /**
         * We store the first argument in the value variable
         * and parse the string into an integer value
         * Also, with try and catch we handle the code that might throw an exception: the argument is not an integer
         */
        int value = 0; //n from requirement
        try {
            value = Integer.parseInt(args[0]);
        } catch (NumberFormatException error) {
            System.out.println("Error: not an integer");
            System.exit(-1);
        }
        System.out.println("Value = " + value);

        /**
         * If the program passes the tests above, we declare square object from LatinSquare class
         * and then print the time of running if the value is bigger than 30000 or call its functions otherwise
         * If we want to adjust the JVM Heap Space it can be write in terminal java -Xms8G -Xmx8G Main 31000 (if we want to adjust at 8G and the value is 31000)
         */
        LatinSquare square = new LatinSquare(value);
        if (value > 30_000) {
            System.out.println((System.nanoTime() - start) / 1_000_000 + "ms");
        } else {
            square.Fill();
            square.Print();
            square.Display();
        }
    }


}