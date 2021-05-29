package Opponents;

import Management.Driver;

public interface Opponent {

    public Driver.MOVE getMove();

    public void processOutcome(Driver.MOVE oppMove);

}
