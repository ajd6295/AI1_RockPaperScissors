package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class AI implements Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public AI() {}

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        return null;
    }

    @Override
    public void processOutcome(Driver.MOVE oppMove) {

    }
}
