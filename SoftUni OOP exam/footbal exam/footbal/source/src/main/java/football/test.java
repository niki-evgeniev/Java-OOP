package football;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<String, List<String>> mapList = new HashMap<>();

        mapList.putIfAbsent("a",new ArrayList<>());

        extracted(mapList);

        printMapList(mapList);


    }


    private static void printMapList(Map<String, List<String>> mapList) {
        for (Map.Entry<String, List<String>> entry : mapList.entrySet()) {
            System.out.println("This is key: " + entry.getKey());
            System.out.print("Value is: ");
            for (String s : entry.getValue()) {
                System.out.print(s + " ");
            }
        }
    }

    private static void extracted(Map<String, List<String>> mapList) {
        mapList.get("a").add("b");
        mapList.get("a").add("hui");
        mapList.get("a").add("hsi");
        mapList.get("a").add("hosi");
        mapList.get("a").add("hosi");
        mapList.get("a").add("hoawi");
    }
}
