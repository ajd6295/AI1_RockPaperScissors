package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU1 implements Opponent {

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU1() {}

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        return Driver.MOVE.values()[r.nextInt(3)];
    }

    @Override
    public void processOutcome(Driver.MOVE oppMove) {}
}
