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
import static Management.Driver.in;

public class AI extends Opponent {

    // ------------------------------ VARIABLES ------------------------------ //

    // ------------------------------ CONSTRUCTORS ------------------------------ //

    public AI() {
        this.filename = "src\\Data\\AI.txt";
    }

    // ------------------------------ METHODS ------------------------------ //

    @Override
    public MOVE getMove(ArrayList<MOVE> moveList) {
        return MOVE.ROCK;
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
        fileContent += "\n";

        // Formulate specific case to look for
        String thisCase = "";
        for (int i = 0; i < 3; i++) {
            MOVE move = moveList.get(i);
            if (move == MOVE.ROCK) {
                thisCase += "R ";
            } else if (move == MOVE.PAPER) {
                thisCase += "P ";
            } else if (move == MOVE.SCISSORS) {
                thisCase += "S ";
            } else {
                thisCase += "N ";
            }
        }

        // Find line for this specific case & update
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            if (line.contains(thisCase)) {
                data = line.split(" \\| ");
                fileContent += data[0] + " | ";

                // Update 2nd chunk
                float total = 0;
                String[] chunk = data[1].split(" ");
                if (outcome == OUTCOME.WIN) {
                    chunk[0] = String.valueOf(Integer.parseInt(chunk[0]) + 1);
                } else if (outcome == OUTCOME.TIE) {
                    chunk[1] = String.valueOf(Integer.parseInt(chunk[1]) + 1);
                } else {
                    chunk[2] = String.valueOf(Integer.parseInt(chunk[2]) + 1);
                }
                for (int i = 0; i < 3; i++) {
                    fileContent += chunk[i] + " ";
                    total += Integer.parseInt(chunk[i]);
                }

                fileContent += "| ";

                // Update 3rd chunk
                for (int i = 0; i < 3; i++) {
                    fileContent += (Float.parseFloat(chunk[i])/total) + " ";
                }
                fileContent += "\n";
            } else {
                fileContent += line + "\n";
            }

            if (line.contains("General")) {
                break;
            }
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
