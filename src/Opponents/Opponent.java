package Opponents;

import Management.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    Random r = new Random();
    String filename;

    // ------------------------------ METHODS ------------------------------ //

    /**
     * getMove: Returns the move that this opponent will make
     *
     * @param moveList  List of latest three moves
     * @return          This opponent's move
     */
    abstract public Driver.MOVE getMove(ArrayList<Driver.MOVE> moveList);

    /**
     * processOutcome: Implementation dependent processing of outcome of round
     *                 Default: Record latest win/tie/loss into .txt file
     *
     * @param CPUMove       The move that the CPU made
     * @param playerMove       The move that the opponent (human) made
     */
    public void processOutcome(Driver.MOVE CPUMove, Driver.MOVE playerMove) {

        // Open file
        File file = new File(filename);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert reader != null;

        // Get each individual datapoint
        String[] data = reader.nextLine().split(" | ");

        // Update outcome-specific information
        Driver.OUTCOME outcome = Driver.outcome(CPUMove, playerMove);
        if (outcome == Driver.OUTCOME.WIN) {
            data[0] = String.valueOf(Integer.parseInt(data[0]) + 1);
        } else if (outcome == Driver.OUTCOME.WIN) {
            data[1] = String.valueOf(Integer.parseInt(data[1]) + 1);
        } else {
            data[2] = String.valueOf(Integer.parseInt(data[2]) + 1);
        }

        // Update outcome-independent information
        data[3] = String.valueOf(Integer.parseInt(data[3]) + 1);
        data[4] = Integer.parseInt(data[0]) / Integer.parseInt(data[3]) + "%";

        String fileContent = "";
        for (int i = 0; i < 5; i++) {
            fileContent += (data[i] + " | ");
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
