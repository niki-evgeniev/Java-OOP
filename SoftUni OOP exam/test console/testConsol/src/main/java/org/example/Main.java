package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLoginPass = "1";


        System.out.println("Welcome to your Notes");

        String data = scanner.nextLine();
        while (!firstLoginPass.equals(data)) {
            System.out.println("Wrong password");
            data = scanner.nextLine();
        }
        System.out.println("Password Accept");



    }
}