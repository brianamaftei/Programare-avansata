package Bonus;

public class Main {
    /**
     * the method creates an object c through which the functions of the Mat1 class are called
     * @param args is empty
     */
    public static void main(String[] args){

        int value = 5; //the size of matrix
        Mat1 c = new Mat1(value);
        c.setMatrices();
        c.multiplication();
        c.print();
    }

}