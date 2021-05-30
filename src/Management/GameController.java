package Management;

import Opponents.Opponent;

import java.util.ArrayList;
import java.util.Arrays;

import Management.Driver.MOVE;
import Management.Driver.OUTCOME;
import static Management.Driver.MODE;
import static Management.Driver.in;

public class GameController {

    // ------------------------------ VARIABLES ------------------------------ //

    private Opponent opponent;
    private ArrayList<MOVE> moveList;
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
                Arrays.asList(MOVE.NONE, MOVE.NONE, MOVE.NONE, MOVE.NONE)
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
            String input = in.nextLine();
            if (input.equals("")) {
                choice = 2;
                break;
            } else if (Driver.willExit(input)) {
                System.exit(1);
            } else if (Driver.isNum(input)){
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
            MOVE oppMove = opponent.getMove(moveList);
            if (MODE != 3) System.out.println("Opponent will play: " + oppMove.toString());
            MOVE playerMove = getChoice();

            System.out.println("Opponent played: " + oppMove.toString());
            System.out.println("You played: " + playerMove.toString());

            moveList.remove(3);
            moveList.add(0, playerMove);

            if (MODE != 1) {
                opponent.processOutcome(oppMove, playerMove, moveList);
            }


            OUTCOME outcome = Driver.outcome(playerMove, oppMove);

            if (outcome == OUTCOME.TIE) {
                System.out.println("You tied!");
                System.out.println(tieSeperator);
            } else {
                if (outcome == OUTCOME.WIN) {
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

    private MOVE getChoice() {
        System.out.print("Rock, Paper, Scissors, Shoot!");
        while (true) {
            System.out.print("> ");
            String input = in.nextLine().toLowerCase();
            if (input.equals("")) continue;
            if (Driver.willExit(input)) System.exit(1);

            char choice = input.charAt(0);
            if (choice == 'r' || choice == '1') {
                return MOVE.ROCK;
            } else if (choice == 'p' || choice == '2') {
                return MOVE.PAPER;
            } else if (choice == 's' || choice == '3') {
                return MOVE.SCISSORS;
            }

            System.out.println("Please enter rock/r/1, paper/p/2, or scissors/s/3");
        }

    }

}
