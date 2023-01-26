package ExceptionsandErrorHandling;

import java.util.Scanner;

public class NumberinRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split(" ");
        int firstNumber = Integer.parseInt(data[0]);
        int secondNumber = Integer.parseInt(data[1]);
        boolean isNumber = false;

        System.out.printf("Range: [%d...%d]%n", firstNumber,secondNumber);
        while (!isNumber) {
            String number = scanner.nextLine();
            try {
                int n = Integer.parseInt(number);
                if (n >= firstNumber && n <= secondNumber) {
                    System.out.println("Valid number: " + n);
                    isNumber = true;
                    continue;
                }
            } catch (IllegalArgumentException ignored) {
            }
            System.out.println("Invalid number: " + number);
        }
    }
}
