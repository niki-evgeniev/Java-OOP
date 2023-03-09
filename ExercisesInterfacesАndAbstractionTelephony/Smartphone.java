package ExercisesInterfacesАndAbstractionTelephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(isDigit(url)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            sb.append(iSLetter(number)).append(System.lineSeparator());

        }
        return sb.toString();
    }

    private String isDigit(String url) {
        char[] urlsChar = url.toCharArray();
        for (char c : urlsChar) {
            if (Character.isDigit(c)) {
                return "Invalid URL!";
            }

        }
        return "Browsing: " + url + "!";
    }

    private String iSLetter(String numb) {
        char[] character = numb.toCharArray();
        for (char c : character) {
            if (Character.isLetter(c)) {
                return "Invalid number!";
            }
        }
        return "Calling... " + numb;
    }
}
