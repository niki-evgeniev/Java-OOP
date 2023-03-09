package ExercisesInterfacesАndAbstractionFoodShortage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Buyer> map = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("\\s+");

            if (data.length == 4) {
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String id = data[2];
                String birthDay = data[3];
                Citizen citizen = new Citizen(name, age, id, birthDay);
                map.put(name, citizen);
            } else {
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String group = data[2];
                Rebel rebel = new Rebel(name, age, group);
                map.put(name, rebel);
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            Buyer buyer = map.get(command);

            if (buyer != null) {
                buyer.buyFood();
            }
            command = scanner.nextLine();
        }
//        int sumFood = 0;
//        for (Map.Entry<String, Buyer> entry : map.entrySet()) {
//            sumFood += entry.getValue().getFood();
//        }
//        System.out.println(sumFood);
        int sumFood = map.values().stream().mapToInt(Buyer::getFood).sum();
        System.out.println(sumFood);
    }
}