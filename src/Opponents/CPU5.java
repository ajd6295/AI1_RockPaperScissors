package Opponents;

import Management.Driver;

import java.util.ArrayList;

public class CPU5 extends Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public CPU5() {
        this.filename = "src\\Data\\CPU5.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList) {
        return null;
    }
}
