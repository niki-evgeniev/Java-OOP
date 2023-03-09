package ExercisesInterfacesАndAbstractionTelephony;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> numbers = List.of(scanner.nextLine().split(" "));
        List<String> urls = List.of(scanner.nextLine().split(" "));

        Smartphone smartphone = new Smartphone(numbers, urls);

        System.out.print(smartphone.call());
        System.out.print(smartphone.browse());
    }
}
