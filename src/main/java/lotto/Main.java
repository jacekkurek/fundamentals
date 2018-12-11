package lotto;

import java.util.*;

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

    private static void displayNumbers(Set<Integer> userNumbers, Set<Integer> randNumbers) {
        System.out.println("Your numbers: " + userNumbers);
        System.out.println("Drawn numbers: " + randNumbers);
    }

    private static void looseMsg(int result) {
        System.out.println(result + " struck numbers. It is not enough to win");
    }

    private static void againMsg() {
        System.out.println("Play again? y / n");
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Set<Integer> userNumbers = new TreeSet<>();
        Set<Integer> randNumbers = new TreeSet<>();
        char again;

        do {
            userNumbers.clear();
            randNumbers.clear();
            int count = 0;
            int userNumber;
            int result = 0;

            welcomeMsg();
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

            while (randNumbers.size() < 6) {
                randNumbers.add(random.nextInt(50) + 1);
            }

            for (int uNumber : userNumbers) {
                for (int rNumber : randNumbers) {
                    if (uNumber == rNumber) {
                        result++;
                    }
                }
            }

            displayNumbers(userNumbers, randNumbers);
            if (result > 2) {
                winMsg(result);
            } else {
                looseMsg(result);
            }

            do {
                againMsg();
                again = scanner.next().toLowerCase().charAt(0);
                if (again != 'y' && again != 'n') {
                    again = 'r';
                }
            } while (again == 'r');

        } while (again == 'y');

    }
}
