package ExercisesInterfacesАndAbstractionBirthdayCelebrations;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, Birthable> map = new LinkedHashMap<>();

        while (!input.equals("End")) {
            String[] data = input.split(" ");
            String name = data[1];


            switch (data[0]) {
                case "Citizen":
                    int age = Integer.parseInt(data[2]);
                    String id = data[3];
                    String birthDate = data[4];
                    Birthable citizen = new Citizen(name,age, id, birthDate);
                    map.put(name, citizen);
                    break;
                case "Pet":
                    String birthDates = data[2];
                    Birthable pet = new Pet(name, birthDates);
                    map.put(name,pet);
                    break;
            }
            input = scanner.nextLine();
        }
        String day = scanner.nextLine();
        for (Map.Entry<String, Birthable> entry : map.entrySet()) {
            String current = entry.getValue().getBirthDate();
            if (current.contains(day)){
                System.out.println(current);
            }
        }

    }
}
