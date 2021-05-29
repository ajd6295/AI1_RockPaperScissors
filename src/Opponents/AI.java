package Opponents;

import Management.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Management.Driver.MOVE;
import Management.Driver.OUTCOME;

public class AI extends Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public AI() {
        this.filename = "src\\Data\\AI.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public MOVE getMove(ArrayList<MOVE> moveList) {
        return null;
    }

    @Override
    public void processOutcome(MOVE CPUMove, MOVE playerMove, ArrayList<MOVE> moveList) {

        OUTCOME outcome = Driver.outcome(CPUMove, playerMove);
        String fileContent = "";

        // Open file
        File file = new File(filename);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert reader != null;

        // Get each individual datapoint from first line
        String[] data = reader.nextLine().split(" \\| ");

        // Update outcome-specific information
        if (outcome == OUTCOME.WIN) {
            data[0] = String.valueOf(Integer.parseInt(data[0]) + 1);
        } else if (outcome == OUTCOME.TIE) {
            data[1] = String.valueOf(Integer.parseInt(data[1]) + 1);
        } else {
            data[2] = String.valueOf(Integer.parseInt(data[2]) + 1);
        }

        // Update outcome-independent information
        data[3] = String.valueOf(Integer.parseInt(data[3]) + 1);
        data[4] = (Float.parseFloat(data[0]) / Float.parseFloat(data[3]))*100 + "%";

        for (int i = 0; i < 5; i++) {
            fileContent += (data[i] + " | ");
        }

        // Formulate specific case to look for
        String thisCase = "";
        for (int i = 0; i < 3; i++) {
            MOVE move = moveList.get(i);
            if (move == MOVE.ROCK) {

            }
        }

        // Find line for this specific case & update
        while (true) {
            break;
        }


        // Write to file and close
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
