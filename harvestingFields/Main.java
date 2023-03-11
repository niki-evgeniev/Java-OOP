package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> clazz = RichSoilLand.class;

        Field[] declaredField = clazz.getDeclaredFields();

        Consumer<Field> printer = field -> System.out.printf(
                "%s %s %s%n",
                Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getName());

        String data = scanner.nextLine();

        while (!data.equals("HARVEST")){
            switch (data){
                case"private":
                    Arrays.stream(declaredField)
                            .filter(field -> Modifier.isPrivate(field.getModifiers()))
                            .forEach(printer);
                    break;
                case "public":
                    Arrays.stream(declaredField).filter(field -> Modifier.isPublic(field.getModifiers()))
                            .forEach(printer);
                    break;
                case "protected":
                    Arrays.stream(declaredField).filter(field -> Modifier.isProtected(field.getModifiers()))
                            .forEach(printer);
                    break;
                case"all":
                    Arrays.stream(declaredField).forEach(printer);

            }


            data = scanner.nextLine();
        }

    }
}
