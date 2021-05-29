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

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public GameController(Opponent opponent) {
        this.opponent = opponent;
    }

    // ------------------------------ METHODS ------------------------------ //

    public void startGame() {

    }
}
