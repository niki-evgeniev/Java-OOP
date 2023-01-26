package ExceptionsandErrorHandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> validNumber = new ArrayList<>();
        int startNumber = 1;
        int endNumber = 100;

        while (validNumber.size() < 10) {

            try {
                int num = getNum(scanner, startNumber, endNumber);
                String str = String.valueOf(num);
                validNumber.add(str);
                startNumber = num;
            } catch (NumberFormatException e ) {
                System.out.println("Invalid Number!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

        }
        System.out.println(String.join(", ",validNumber));
    }

    private static int getNum(Scanner scanner, int startNumber, int endNumber) {
        int num = Integer.parseInt(scanner.nextLine());

        if (startNumber >= num || endNumber <= num) {
            throw new IllegalArgumentException("Your number is not in range " + startNumber + " - 100!");
        }
        return num;
    }
}
