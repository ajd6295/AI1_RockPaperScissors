package Management;

import Opponents.Opponent;

import java.util.ArrayList;
import java.util.Arrays;

public class GameController {

    // ------------------------------ VARIABLES ------------------------------ //

    private Opponent opponent;
    private ArrayList<Driver.MOVE> moveList;
    private int oppWins;
    private int playerWins;

    private final String roundSeparator =
            "===\n" +
                    "==================================================\n" +
                    "===\n";

    private final String tieSeperator = "-----------------------------------";

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public GameController(Opponent opponent) {
        this.opponent = opponent;
        this.moveList = new ArrayList<>(
                Arrays.asList(Driver.MOVE.NONE, Driver.MOVE.NONE, Driver.MOVE.NONE)
        );
        this.oppWins = 0;
        this.playerWins = 0;
    }

    // ------------------------------ METHODS ------------------------------ //

    public void startGame() {

        System.out.println("First to? (<1 goes forever)");
        int choice;
        while (true) {
            System.out.print("> ");
            String input = Driver.in.nextLine();
            if (input.equals("")) {
                choice = -1;
                break;
            } else if (Driver.isNum(input)) {
                choice = Driver.toNum(input);
                if (choice == 0) choice --;
                break;
            }
        }

        if (choice < 1) {
            System.out.println("Playing indefinitely!\n");
        } else {
            System.out.println("Playing first to " + choice + " (Best of " + ((choice*2)-1) + ")!\n");
        }

        System.out.println(roundSeparator);

        while (true){
            doRound();
            if (oppWins == choice) {
                System.out.println("You lost the game :(");
            } else if (playerWins == choice) {
                System.out.println("You won the game!");
            } else {
                continue;
            }

            System.out.println("You: " + playerWins + " | CPU: " + oppWins);
            System.exit(0);
        }

    }

    private void doRound() {
        while (true) {
            Driver.MOVE oppMove = opponent.getMove(moveList);
            if (Driver.DEBUG) System.out.println("Opponent will play: " + oppMove.toString());
            Driver.MOVE playerMove = getChoice();

            System.out.println("Opponent played: " + oppMove.toString());
            System.out.println("You played: " + playerMove.toString());

            moveList.remove(2);
            moveList.add(0, playerMove);
            if (!Driver.DEBUG) {
                opponent.processOutcome(oppMove, playerMove);
            }


            Driver.OUTCOME outcome = Driver.outcome(playerMove, oppMove);

            if (outcome == Driver.OUTCOME.TIE) {
                System.out.println("You tied!");
                System.out.println(tieSeperator);
            } else {
                if (outcome == Driver.OUTCOME.WIN) {
                    playerWins++;
                } else {
                    oppWins++;
                }
                System.out.println("You " + outcome.toString() + "!");
                break;
            }
        }
        System.out.println("\n" + roundSeparator);
    }

    private Driver.MOVE getChoice() {
        System.out.print("Rock, Paper, Scissors, Shoot!");
        while (true) {
            System.out.print("> ");
            String input = Driver.in.nextLine().toLowerCase();
            if (input.equals("")) continue;
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
