package Management;

import java.util.Scanner;

public class Driver {

    private static String introMessage =
            "******************************\n" +
                    "    Rock Paper Scissors AI    \n" +
                    "    by Anthony DelPrincipe    \n" +
                    "******************************";

    private static Scanner in = new Scanner(System.in);

    public enum CPU_TYPE {
        CPU1,
        CPU2,
        CPU3,
        AI
    }

    public static void main(String[] args) {

        System.out.println(introMessage);

        CPU_TYPE opponent = getCPUType();
        System.out.println("Playing opponent " + opponent.toString());

        // Make instance of opponent and GameController
        // Pass control to GameController
    }

    public static CPU_TYPE getCPUType() {
        System.out.println("Which CPU do you want to play against?");
        System.out.println("    1) CPU1 (Random)");
        System.out.println("    2) CPU2 (Plays winning move to your previous choice)");
        System.out.println("    3) CPU3 (Randomly chooses different behavioral patterns)");
        System.out.println("    4) AI (AI that learns with every game)");
        System.out.print("\n> ");

        String input;
        while (true) {
            input = in.nextLine();

            if (input.toLowerCase().charAt(0) == 'e' || input.toLowerCase().charAt(0) == 'q') {
                System.exit(1);
            } else {
                int choice;
                try {
                    choice = Integer.parseInt(input);
                    if (0 < choice && choice < 5) {
                        return CPU_TYPE.values()[choice-1];
                    } else {
                        System.out.println("Please choose a number between 1 and 4 (inclusive)");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid numerical choice!");
                }
            }
        }
    }

}
