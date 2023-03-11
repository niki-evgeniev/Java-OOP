package ExercisesWorkingwithAbstraction;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Card Suits:");
//        for (CardSuits value : CardSuits.values()) {
//            System.out.printf("Ordinal value: %d; Name value: %s%n",value.ordinal(),value.name());
//
//        }
//        System.out.println("Card Ranks:");
//        for (CardRank value : CardRank.values()) {
//            System.out.printf("Ordinal value: %d; Name value: %s%n", value.ordinal(), value);
//        }
//
//        CardRank cardRank = CardRank.valueOf(scanner.nextLine());
//        CardSuits cardSuits = CardSuits.valueOf(scanner.nextLine());
//        int sum = cardRank.getPower() + cardSuits.getPower();
//
//        System.out.printf("Card name: %s of %s; Card power: %d",cardRank,cardSuits,sum);
//    }
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(scanner.nextLine());

        List<LightChange> light = Arrays.stream(input).map(Light::valueOf)
                .map(LightChange::new)
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            for (LightChange lightChange : light) {
                lightChange.changeLight();
                System.out.print(lightChange.getLight() + " ");
            }
            System.out.println();
        }

    }
}
