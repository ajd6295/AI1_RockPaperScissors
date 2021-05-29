package Management;

import Opponents.Opponent;

import java.util.ArrayList;
import java.util.Arrays;

public class GameController {

    // ------------------------------ VARIABLES ------------------------------ //

    private Opponent opponent;

    private ArrayList<Driver.MOVE> moveList = new ArrayList<>(
            Arrays.asList(Driver.MOVE.NONE, Driver.MOVE.NONE, Driver.MOVE.NONE)
    );

    private final String roundSeparator =
            "===\n" +
                    "==================================================\n" +
                    "===\n";

    private final String tieSeperator = "-----------------------------------";

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public GameController(Opponent opponent) {
        this.opponent = opponent;
    }

    // ------------------------------ METHODS ------------------------------ //

    public void startGame() {
        System.out.println(roundSeparator);
        while (true){
            doRound();
        }

    }

    private void doRound() {
        while (true) {
            Driver.MOVE oppMove = opponent.getMove(moveList);
            //
            // System.out.println("Opponent will play: " + oppMove.toString());
            //
            Driver.MOVE playerMove = getChoice();

            System.out.println("Opponent played: " + oppMove.toString());
            System.out.println("You played: " + playerMove.toString());

            Driver.OUTCOME outcome = Driver.outcome(playerMove, oppMove);

            if (outcome == Driver.OUTCOME.TIE) {
                System.out.println("You tied!");
                System.out.println(tieSeperator);
            } else {
                System.out.println("You " + outcome.toString() + "!");
                break;
            }
        }
        System.out.println("\n" + roundSeparator);
    }

    private Driver.MOVE getChoice() {
        while (true) {
            System.out.print("Rock, Paper, Scissors, Shoot!\n> ");

            String input = Driver.in.nextLine().toLowerCase();
            Driver.willExit(input);

            char choice = input.charAt(0);
            if (choice == 'r' || choice == '1') {
                return Driver.MOVE.ROCK;
            } else if (choice == 'p' || choice == '2') {
                return Driver.MOVE.PAPER;
            } else if (choice == 's' || choice == '3') {
                return Driver.MOVE.SCISSORS;
            }

            System.out.println("Please enter rock/r/1, paper/p/2, or scissors/s/3");
        }

    }

}
