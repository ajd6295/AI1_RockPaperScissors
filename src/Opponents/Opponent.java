package Opponents;

import Management.Driver;

public interface Opponent {

    // ------------------------------ METHODS ------------------------------ //

    /**
     * getMove: Returns the move that this opponent will make
     *
     * @return      This opponent's move
     */
    public Driver.MOVE getMove();

    /**
     * processOutcome: Implementation dependent processing of outcome of round
     *
     * @param oppMove       The move that the opponent (human) made
     */
    public void processOutcome(Driver.MOVE oppMove);

}
