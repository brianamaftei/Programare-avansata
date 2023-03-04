package Compulsory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n = n * 3;
        n = n + 0b10101;
        n = n + 0xFF;
        n = n * 6;

        int index = digitsSum(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[index]);
    }

    /**
     * the method returns
     * @param n is the number we want to calculate the sum of its digits
     * @return the digits sum of a number
     */
    public static int digitsSum(int n){
        if (n % 9 == 0) n = 9;
        else n = n % 9;
        return  n;
    }


}
