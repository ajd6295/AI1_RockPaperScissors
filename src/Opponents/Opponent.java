package Opponents;

import Management.Driver;

import java.util.ArrayList;

public interface Opponent {

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
     * @param oppMove       The move that the opponent (human) made
     */
    public void processOutcome(Driver.MOVE oppMove);

}
