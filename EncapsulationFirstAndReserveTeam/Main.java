package EncapsulationFirstAndReserveTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                String[] data = scanner.nextLine().split(" ");
                String firstName = data[0];
                String lastName = data[1];
                int age = Integer.parseInt(data[2]);
                double salary = Double.parseDouble(data[3]);

                Person newPerson = new Person(firstName, lastName, age, salary);
                list.add(newPerson);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Team team = new Team("Black Eagles");
        for (Person person : list) {
            team.addPlayer(person);
        }

        System.out.println("First team have " + team.getFirstTeam().size() + " players");
        System.out.println("Reserve team have " + team.getReserveTeam().size() + " players");


    }
}
