package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU2 extends Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU2() {
        this.filename = "src\\Data\\CPU2.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        if (moveList.get(0) == Driver.MOVE.NONE) return Driver.MOVE.values()[r.nextInt(3)];
        return Driver.losesAgainst(moveList.get(0));
    }
}
