package ExceptionsandErrorHandling;

import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.nextLine();
        try {
            double sqr = getSqr(number);
            System.out.printf("%.2f%n",sqr);
        }catch (Exception e){
            System.out.println("Invalid");
        }finally {
            System.out.println("Goodbye");
        }
    }

    private static double getSqr(String number) throws Exception {
        int n = Integer.parseInt(number);
        if (n < 0){
            throw new Exception();
        }
        double sqr = Math.sqrt(n);
        return sqr;
    }
}
