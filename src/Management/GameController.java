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
                    "===";

    private final String tieSeperator = "-----------------------------------";

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public GameController(Opponent opponent) {
        this.opponent = opponent;
    }

    // ------------------------------ METHODS ------------------------------ //

    public void startGame() {
        System.out.println(roundSeparator);
        System.out.println("");
        System.out.println(tieSeperator);
    }

    private void getChoice() {

    }
}
