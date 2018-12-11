package dice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static void welcomeMsg() {
        System.out.println("Let's play a game.\nDescribe what to throw");
    }

    private static String readAndValidateInput() {
        Scanner scanner = new Scanner(System.in);
        Pattern validPattern = Pattern.compile("\\d*d\\d+(([-+])\\d+)?");

        String input = scanner.next().toLowerCase();
        Matcher matcher = validPattern.matcher(input);

        while (!matcher.matches()) {
            System.out.println("Invalid format");
            input = scanner.next().toLowerCase();
            matcher = validPattern.matcher(input);
        }
        return input;
    }

    private static int[] parseInput(String input) {
        int[] parsedNumbers = new int[3];
        String[] s1 = input.split("d");
        if (s1[0].equals("")) {
            parsedNumbers[0] = 1;
        } else {
            parsedNumbers[0] = Integer.parseInt(s1[0]);
        }

        if (input.contains("+")) {
            String[] s2 = s1[1].split("\\+");
            parsedNumbers[1] = Integer.parseInt(s2[0]);
            parsedNumbers[2] = Integer.parseInt(s2[1]);
        } else if (input.contains("-")) {
            String[] s2 = s1[1].split("-");
            parsedNumbers[1] = Integer.parseInt(s2[0]);
            parsedNumbers[2] = -Integer.parseInt(s2[1]);
        } else {
            parsedNumbers[1] = Integer.parseInt(s1[1]);
        }
        return parsedNumbers;
    }

    private static void resultMsg(int[] parsedNumbers) {
        Random random = new Random();
        int score = 0;
        for (int i = 0; i < parsedNumbers[0]; i++) {
            score += random.nextInt(parsedNumbers[1])+1;
        }
        System.out.println("Score: " + (score + parsedNumbers[2]));
    }

    private static char repeatPrompt() {
        char repeat;
        do {
            System.out.println("Play again? y / n");
            Scanner scanner = new Scanner(System.in);
            repeat = scanner.next().toLowerCase().charAt(0);
        } while (repeat != 'y' && repeat != 'n');
        return repeat;
    }

    public static void main(String[] args) {

        char repeat;
        do {
            welcomeMsg();
            String input = readAndValidateInput();
            int[] parsedNumbers = parseInput(input);
            resultMsg(parsedNumbers);
            repeat = repeatPrompt();
        } while (repeat == 'y');
    }
}

