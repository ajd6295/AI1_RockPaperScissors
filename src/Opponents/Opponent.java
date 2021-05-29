package Opponents;

import Management.Driver;

import java.util.ArrayList;
import java.util.Random;

public interface Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    Random r = new Random();

    // ------------------------------ METHODS ------------------------------ //

    /**
     * getMove: Returns the move that this opponent will make
     *
     * @param moveList  List of latest three moves
     * @return          This opponent's move
     */
    public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList);

    /**
     * processOutcome: Implementation dependent processing of outcome of round
     *
     * @param playerMove       The move that the opponent (human) made
     */
    public void processOutcome(Driver.MOVE playerMove);

}
