package Management;

import java.util.Scanner;

public class Driver {

    private final static String introMessage =
            "******************************\n" +
                    "    Rock Paper Scissors AI    \n" +
                    "    by Anthony DelPrincipe    \n" +
                    "******************************";

    private static final Scanner in = new Scanner(System.in);

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

        GameController controller = new GameController(opponent);

        // Make instance of opponent and GameController
        // Pass control to GameController
    }

    public static CPU_TYPE getCPUType() {
        System.out.println("Which CPU do you want to play against?");
        System.out.println("    1) CPU1 (Random)");
        System.out.println("    2) CPU2 (Plays winning move to your previous choice)");
        System.out.println("    3) CPU3 (Randomly chooses different behavioral patterns)");
        System.out.println("    4) AI (AI that learns with every game)\n");

        String input;
        while (true) {
            System.out.print("> ");
            input = in.nextLine();

            if (willExit(input)) {
                System.exit(1);
            } else {
                int choice;
                if (isNum(input)) {
                    choice = toNum(input);
                    if (0 < choice && choice < 5) {
                        return CPU_TYPE.values()[choice-1];
                    } else {
                        System.out.println("Please choose a number between 1 and 4 (inclusive)");
                    }
                }
            }
        }
    }

    public static boolean willExit(String input) {
        return (input.toLowerCase().charAt(0) == 'e' || input.toLowerCase().charAt(0) == 'q');
    }

    public static boolean isNum(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid numerical choice!");
            return false;
        }
    }

    public static int toNum(String input) {
        return Integer.parseInt(input);
    }

}
