package lotto;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private static void welcomeMsg() {
        System.out.println("Type 6 unique numbers from 1 - 49");
    }

    private static void notNumberMsg() {
        System.out.println("This is not a number. Type number from 1 - 49");
    }

    private static void repeatedNumberMsg() {
        System.out.println("You already entered this number");
    }

    private static void numberOutOfRangeMsg() {
        System.out.println("This number is not between 1 - 49. Type number from 1 - 49");
    }

    private static void winMsg(int result) {
        System.out.println("Congratulations, you struck " + result + "numbers!");
    }

    private static void looseMsg(int result) {
        System.out.println(result + " struck numbers. It is not enough to win");
    }

    private static void displayNumbers(Set<Integer> userNumbers, Set<Integer> randNumbers) {
        System.out.println("Your numbers: " + userNumbers);
        System.out.println("Drawn numbers: " + randNumbers);
    }

    private static Set<Integer> drawNumbers(Random random) {
        Set<Integer> randNumbers = new TreeSet<>();
        while (randNumbers.size() < 6) {
            randNumbers.add(random.nextInt(50) + 1);
        }
        return randNumbers;
    }

    private static char repeatPrompt(Scanner scanner) {
        char repeat;
        do {
            System.out.println("Play again? y / n");
            repeat = scanner.next().toLowerCase().charAt(0);
        } while (repeat != 'y' && repeat != 'n');
        return repeat;
    }

    private static int compareNumbers(Set<Integer> userNumbers, Set<Integer> randNumbers) {
        int result = 0;
        for (int uNumber : userNumbers) {
            for (int rNumber : randNumbers) {
                if (uNumber == rNumber) {
                    result++;
                }
            }
        }
        return result;
    }

    private static Set<Integer> readUserNumbers(Scanner scanner) {
        Set<Integer> userNumbers = new TreeSet<>();
        int count = 0;
        int userNumber;
        while (userNumbers.size() < 6) {
            System.out.println("Type " + (count + 1) + ". number");
            while (!scanner.hasNextInt()) {
                notNumberMsg();
                scanner.next();
            }
            userNumber = scanner.nextInt();
            if (userNumber >= 1 && userNumber <= 49) {
                if (!userNumbers.add(userNumber)) {
                    repeatedNumberMsg();
                } else {
                    count++;
                }
            } else {
                numberOutOfRangeMsg();
            }
        }

        return userNumbers;
    }

    private static void resultMsg(int result) {
        if (result > 2) {
            winMsg(result);
        } else {
            looseMsg(result);
        }
    }


    public static void main(String[] args) {

        char again;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        do {
            welcomeMsg();
            Set<Integer> userNumbers = readUserNumbers(scanner);
            Set<Integer> randNumbers = drawNumbers(random);
            int result = compareNumbers(userNumbers, randNumbers);
            displayNumbers(userNumbers, randNumbers);
            resultMsg(result);
            again = repeatPrompt(scanner);

        } while (again == 'y');

    }
}
