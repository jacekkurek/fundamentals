package userGuessNumber;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static void welcomeMsg() {
        System.out.println("Guess number from 1 - 100");
    }

    private static void incorrectInputMsg() {
        System.out.println("Please provide number from 1 - 100");
    }

    private static void higherMsg() {
        System.out.println("The number is higher");
    }

    private static void lowerMsg() {
        System.out.println("The number is lower");
    }

    private static void winMsg() {
        System.out.println("Congratulations");
    }


    public static void main(String[] args) {


        int randNumber = new Random().nextInt(100) + 1;
        System.out.println(randNumber);
        int userNumber = 0;
        welcomeMsg();
        Scanner scanner = new Scanner(System.in);
        do {
            while (!scanner.hasNextInt()) {
                incorrectInputMsg();
                scanner.next();
            }
            userNumber = scanner.nextInt();
            if (userNumber > 0 && userNumber < 101) {
                if (userNumber > randNumber) {
                    lowerMsg();
                } else {
                    higherMsg();
                }
            } else {
                incorrectInputMsg();
            }
        } while (userNumber != randNumber);
        winMsg();
    }
}
