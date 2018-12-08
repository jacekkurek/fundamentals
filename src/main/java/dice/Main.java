package dice;

import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.next();
        int dPosition = 0;
        while (input.toLowerCase().indexOf('d') < 0) {
            System.out.println(dPosition);
            System.out.println("\"D\" is missing");
            scanner.next();
        }
        dPosition = input.toLowerCase().indexOf('d');

        int plusPosition = input.indexOf("+");
        int minusPosition = input.indexOf("-");
        int modifierPosition;
        int result = 0;
        int throwNumber;
        Random random = new Random();

        if (plusPosition > -1) {
            modifierPosition = plusPosition;
            result += Integer.parseInt(input.substring(modifierPosition + 1, input.length()));
        } else if (minusPosition > -1) {
            modifierPosition = minusPosition;
            result -= Integer.parseInt(input.substring(modifierPosition + 1, input.length()));
        } else {
            modifierPosition = input.length();
        }
        if (dPosition > 0) {
            throwNumber = Integer.parseInt(input.substring(0, dPosition));
        } else {
            throwNumber = 1;
        }
        int diceType = Integer.parseInt(input.substring(dPosition + 1, modifierPosition));

        if (throwNumber > 0 && diceType > 0) {
            for (int i = 0; i < throwNumber; i++) {
                result += random.nextInt(diceType);
            }
        }

        System.out.println("result " + result);
    }
}
