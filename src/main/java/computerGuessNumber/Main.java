package computerGuessNumber;

import java.util.Scanner;

public class Main {


    private static void welcomeMsg() {
        System.out.println("Choose number from 1 - 1000 and let me guess in max 10 steps");
    }

    private static int guess(int min, int max) {
        return ((max - min) / 2) + min;
    }

    private static void guessMsg(int answer, int step) {
        System.out.println("Step " + step + ". Is the number " + answer + "?");
        System.out.println("c - correct?\nl - lower?\nh - higher?");
    }

    private static void winMsg() {
        System.out.println("Thanks for play. Play again? y / n");
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char input, repeat;

        do {
            int min = 1;
            int max = 1000;
            int step = 1;
            welcomeMsg();
            int answer;
            do {
                answer = guess(min, max);
                guessMsg(answer,step++);
                input = scanner.next().toLowerCase().charAt(0);
                if (input == 'l') {
                    max = answer;
                } else if (input == 'h') {
                    min = answer;
                }
            } while (input != 'c');

            do {
                winMsg();
                repeat = scanner.next().toLowerCase().charAt(0);
            } while (repeat != 'y' && repeat != 'n');

        } while (repeat == 'y');

    }
}
