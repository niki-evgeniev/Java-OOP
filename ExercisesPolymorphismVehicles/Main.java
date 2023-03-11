package ExercisesPolymorphismVehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicles> map = new LinkedHashMap<>();

        String[] data = scanner.nextLine().split(" ");
        while (data.length != 1) {
            double fuelQuantity = Double.parseDouble(data[1]);
            double fuelConsumption = Double.parseDouble(data[2]);
            double maxFuel = Double.parseDouble(data[3]);

            Vehicles vehicles = null;
            switch (data[0]){
                case"Car":
                    vehicles = new Car(fuelQuantity, fuelConsumption,maxFuel);
                    break;
                case"Truck":
                    vehicles = new Truck(fuelQuantity,fuelConsumption, maxFuel);
                    break;
                case"Bus":
                    vehicles = new Bus(fuelQuantity,fuelConsumption,maxFuel);
            }
            map.put(data[0], vehicles );
            data = scanner.nextLine().split(" ");
        }
        int n = Integer.parseInt(data[0]);

        for (int i = 0; i < n; i++) {
            data = scanner.nextLine().split(" ");
            String command = data[0];
            String vehicle = data[1];
            double distance = Double.parseDouble(data[2]);


            switch (command){
                case"Drive":
                    System.out.println(map.get(vehicle).drive(distance));
                    break;
                case "Refuel":
                    try {
                        map.get(vehicle).refuel(distance);
                    }catch (IllegalArgumentException exception){
                        System.out.println(exception.getMessage());
                    }

                    break;
                case"DriveEmpty":
                    Bus bus = (Bus) map.get(vehicle);
                    System.out.println(bus.driveEmpty(distance));
                    break;
            }
        }
//        System.out.println(map.get("Car").getQuantity());
//        System.out.println(map.get("Truck").getQuantity());
//        System.out.printf("Car: %.2f%n",map.get("Car").getQuantity());
//        System.out.printf("Truck: %.2f%n",map.get("Truck").getQuantity());
        map.forEach((s, vehicles) -> System.out.println(vehicles));
    }
}
