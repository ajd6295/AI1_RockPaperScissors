package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU3 extends Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU3() {
        this.filename = "CPU3.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        return null;
    }

    @Override
    public void processOutcome(Driver.MOVE playerMove) {

    }
}
