package dice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static void welcomeMsg(){
        System.out.println("Let's play a game.\nDescribe what to throw");
    }

    public static void main(String[] args) {



        welcomeMsg();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().toLowerCase();
        Pattern validPattern = Pattern.compile("\\d*d\\d+((-|\\+)\\d+)?");
        Matcher matcher = validPattern.matcher(input);
        Random random = new Random();
        int a;
        int b;
        int c;


        while (!matcher.matches()) {
            System.out.println("Invalid format");
            scanner.next();
        }

        String[] s1 = input.split("d");
        if (s1[0].equals("")) {
            a = 1;
        } else {
            a = Integer.parseInt(s1[0]);
        }

        if (input.contains("+")) {
            String[] s2 = s1[1].split("\\+");
            b = Integer.parseInt(s2[0]);
            c = Integer.parseInt(s2[1]);
        } else if (input.contains("-")) {
            String[] s2 = s1[1].split("-");
            b = Integer.parseInt(s2[0]);
            c = -Integer.parseInt(s2[1]);
        } else {
            b = Integer.parseInt(s1[1]);
            c = 0;
        }

        System.out.println(a + random.nextInt(b + 1) + c);



    }


}

