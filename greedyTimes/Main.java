
package greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long vhod = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        var bag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();
        long gold = 0;
        long gems = 0;
        long money = 0;

        for (int i = 0; i < safe.length; i += 2) {
            String name = safe[i];
            long count = Long.parseLong(safe[i + 1]);

            String whatIsIt = "";

            whatIsIt = getIsIt(name, whatIsIt);

            if (whatIsIt.equals("")) {
                continue;
            } else if (vhod < bag.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + count) {
                continue;
            }

            switch (whatIsIt) {
                case "Gem":
                    if (!bag.containsKey(whatIsIt)) {
                        if (bag.containsKey("Gold")) {
                            if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(whatIsIt).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(whatIsIt)) {
                        if (bag.containsKey("Gem")) {
                            if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(whatIsIt).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!bag.containsKey(whatIsIt)) {
                bag.put((whatIsIt), new LinkedHashMap<String, Long>());
            }

            if (!bag.get(whatIsIt).containsKey(name)) {
                bag.get(whatIsIt).put(name, 0L);
            }


            bag.get(whatIsIt).put(name, bag.get(whatIsIt).get(name) + count);
            if (whatIsIt.equals("Gold")) {
                gold += count;
            } else if (whatIsIt.equals("Gem")) {
                gems += count;
            } else if (whatIsIt.equals("Cash")) {
                money += count;
            }
        }

        for (var x : bag.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

    private static String getIsIt(String name, String whatIsIt) {
        if (name.length() == 3) {
            whatIsIt = "Cash";
        } else if (name.toLowerCase().endsWith("gem")) {
            whatIsIt = "Gem";
        } else if (name.toLowerCase().equals("gold")) {
            whatIsIt = "Gold";
        }
        return whatIsIt;
    }
}