package Management;

import Opponents.*;

import java.util.Scanner;

public class Driver {

    // ------------------------------ VARIABLES ------------------------------ //

    public static int MODE;
    private final static String introMessage =
            "******************************\n" +
                    "    Rock Paper Scissors AI    \n" +
                    "    by Anthony DelPrincipe    \n" +
                    "******************************";

    public static final Scanner in = new Scanner(System.in);

    public enum CPU_TYPE {
        CPU1,
        CPU2,
        CPU3,
        CPU4,
        CPU5,
        AI
    }

    public enum MOVE {
        ROCK,
        PAPER,
        SCISSORS,
        NONE
    }

    public enum OUTCOME {
        WIN,
        TIE,
        LOSE
    }

    // ------------------------------ METHODS ------------------------------ //

    /**
     * main: Greet user, get the opponent they want to play, and start game
     *
     * @param args  none
     */
    public static void main(String[] args) {

        System.out.println(introMessage);

        System.out.print("Mode?\n1=Debug | 2=Cheat | 3=Normal\n> ");
        String input = in.nextLine();
        if (input.equals("") || !(input.charAt(0) == '2' | input.charAt(0) == '3')) {
            if (!input.equals("")) {
                if (willExit(input)) System.exit(1);
            }

            MODE = 1;
            System.out.println("Proceeding in DEBUG mode\n");
        } else if (input.charAt(0) == '2') {
            MODE = 2;
            System.out.println("Proceeding in CHEAT mode\n");
        } else if (input.charAt(0) == '3') {
            MODE = 3;
            System.out.println("Proceeding in NORMAL mode\n");
        }

        CPU_TYPE opponentType = getCPUType();
        System.out.println("Playing opponent " + opponentType.toString() + "\n");

        Opponent opponent;
        if (opponentType == CPU_TYPE.CPU1) {
            opponent = new CPU1();
        } else if (opponentType == CPU_TYPE.CPU2) {
            opponent = new CPU2();
        } else if (opponentType == CPU_TYPE.CPU3) {
            opponent = new CPU3();
        } else if (opponentType == CPU_TYPE.CPU4) {
            opponent = new CPU4();
        } else if (opponentType == CPU_TYPE.CPU5) {
            opponent = new CPU5();
        } else {
            opponent = new AI();
        }

        GameController controller = new GameController(opponent);
        controller.startGame();
    }

    /**
     * getCPUType: Get what opponent the user wants to play against
     *
     * @return  CPU_TYPE of desired opponent
     */
    public static CPU_TYPE getCPUType() {
        System.out.println("Which CPU do you want to play against?");
        System.out.println("    1) CPU1 (Random)");
        System.out.println("    2) CPU2 (Plays what would win against your previous move)");
        System.out.println("    3) CPU3 (Plays what would lose against your previous move)");
        System.out.println("    4) CPU4 (Plays what would tie against your previous move)");
        System.out.println("    5) CPU5 (Randomly chooses different behavioral patterns)");
        System.out.println("    6) AI (AI that learns with every game)");

        String input;
        while (true) {
            System.out.print("> ");
            input = in.nextLine();
            if (input.equals("")) continue;

            if (willExit(input)) {
                System.exit(1);
            } else {
                int choice;
                if (isNum(input)) {
                    choice = toNum(input);
                    if (0 < choice && choice < 7) {
                        return CPU_TYPE.values()[choice-1];
                    } else {
                        System.out.println("Please choose a number between 1 and 4 (inclusive)");
                    }
                }
            }
        }
    }

    /**
     * willExit: Determine if user wants to exit game
     *
     * @param input     User's input
     * @return          Boolean denoting if they want to exit game
     */
    public static boolean willExit(String input) {
        return (input.toLowerCase().charAt(0) == 'e' || input.toLowerCase().charAt(0) == 'q');
    }

    /**
     * isNum: Determines if the input is a number. If not,
     *        prints out an error message
     *
     * @param input     User's input
     * @return          Boolean denoting if it's a number or not
     */
    public static boolean isNum(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid numerical choice!");
            return false;
        }
    }

    /**
     * toNum: Returns integer form of string
     *
     * @param input     String to change to Integer
     * @return          Integer form of String
     */
    public static int toNum(String input) {
        return Integer.parseInt(input);
    }

    /**
     * outcome: Returns the outcome of the round
     *          (from the perspective of player who made move1)
     *
     * @param move1     First move to consider
     * @param move2     Second move to consider
     * @return          Outcome of round
     */
    public static OUTCOME outcome(MOVE move1, MOVE move2) {
        if (move1 == move2) {
            return OUTCOME.TIE;
        }

        if (winsAgainst(move1) == move2) return OUTCOME.WIN;
        return OUTCOME.LOSE;
    }

    public static MOVE winsAgainst(MOVE move) {
        if (move == MOVE.ROCK) return MOVE.SCISSORS;
        if (move == MOVE.PAPER) return MOVE.ROCK;
        return MOVE.PAPER;
    }

    public static MOVE losesAgainst(MOVE move) {
        if (move == MOVE.ROCK) return MOVE.PAPER;
        if (move == MOVE.PAPER) return MOVE.SCISSORS;
        return MOVE.ROCK;
    }

}
