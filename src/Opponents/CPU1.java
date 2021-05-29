package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU1 extends Opponent {

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU1() {
        this.filename = "src\\Data\\CPU1.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        return Driver.MOVE.values()[r.nextInt(3)];
    }
}
