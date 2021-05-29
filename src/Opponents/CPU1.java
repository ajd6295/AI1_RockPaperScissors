package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU1 implements Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU1() {}

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        return null;
    }

    @Override
    public void processOutcome(Driver.MOVE oppMove) {

    }
}
