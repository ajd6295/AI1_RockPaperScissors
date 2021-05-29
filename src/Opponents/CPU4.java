package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU4 extends Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU4() {
        this.filename = "src\\Data\\CPU4.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        if (moveList.get(0) == Driver.MOVE.NONE) return Driver.MOVE.values()[r.nextInt(3)];
        return moveList.get(0);
    }
}
