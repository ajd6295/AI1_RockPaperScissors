package Opponents;

import Management.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        // TODO: clean up so there's less duplicated code

        // Open file
        File file = new File(filename);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert reader != null;

        // Formulate specific case to look for
        String specificCase = "";
        for (int i = 0; i < 3; i++) {
            MOVE move = moveList.get(i);
            if (move == MOVE.ROCK) {
                specificCase += "R ";
            } else if (move == MOVE.PAPER) {
                specificCase += "P ";
            } else if (move == MOVE.SCISSORS) {
                specificCase += "S ";
            } else {
                specificCase += "N ";
            }
        }

        // Formulate general case to look for
        String generalCase = "1 ";
        MOVE first = moveList.get(1);
        MOVE second = null;
        if (moveList.get(1) == MOVE.NONE) {
            generalCase += "N ";
        } else if (moveList.get(1) == moveList.get(0)) {
            generalCase += "1 ";
        } else {
            second = moveList.get(1);
            generalCase += "2 ";
        }
        if (moveList.get(2) == MOVE.NONE) {
            generalCase += "N ";
        } else if (moveList.get(2) == moveList.get(0)) {
            generalCase += "1 ";
        } else if (moveList.get(2) == moveList.get(1)){
            generalCase += "2 ";
        } else {
            if (moveList.get(1) == moveList.get(0)) {
                generalCase += "2 ";
            } else {
                generalCase += "3 ";
            }

        }

        float[] choiceArray = {0, 0, 0};
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            if (line.contains(specificCase)) {
                String[] data = line.split(" \\| ")[2].split(" ");
                for (int i = 0; i < 3; i++) {
                    choiceArray[i] += Float.parseFloat(data[i]);
                }
            } else if (line.contains(generalCase) && moveList.get(2) != MOVE.NONE) {
                String[] data = line.split(" \\| ")[2].split(" ");
                for (int i = 0; i < 3; i++) {
                    // Fill first
                    if (first == MOVE.ROCK) {
                        choiceArray[0] += Float.parseFloat(data[0]);
                    } else if (second == MOVE.ROCK || (second == null && first == MOVE.SCISSORS)) {
                        choiceArray[0] += Float.parseFloat(data[1]);
                    } else {
                        choiceArray[0] += Float.parseFloat(data[2]);
                    }

                    // Fill second
                    if (first == MOVE.PAPER) {
                        choiceArray[1] += Float.parseFloat(data[0]);
                    } else if (second == MOVE.PAPER || (second == null && first == MOVE.ROCK)) {
                        choiceArray[1] += Float.parseFloat(data[1]);
                    } else {
                        choiceArray[1] += Float.parseFloat(data[2]);
                    }

                    // Fill third
                    // Fill second
                    if (first == MOVE.SCISSORS) {
                        choiceArray[2] += Float.parseFloat(data[0]);
                    } else if (second == MOVE.SCISSORS || (second == null && first == MOVE.PAPER)) {
                        choiceArray[2] += Float.parseFloat(data[1]);
                    } else {
                        choiceArray[2] += Float.parseFloat(data[2]);
                    }
                }
            }
        }

        MOVE[] choices = {MOVE.NONE, MOVE.NONE, MOVE.NONE};
        int total = 0;
        if (choiceArray[0] > choiceArray[1] && choiceArray[0] > choiceArray[2]) {
            choices[0] = MOVE.ROCK;
            total = 1;
        } else if (choiceArray[1] > choiceArray[0] && choiceArray[1] > choiceArray[2]) {
            choices[0] = MOVE.PAPER;
            total = 1;
        } else if (choiceArray[2] > choiceArray[1] && choiceArray[2] > choiceArray[0]) {
            choices[0] = MOVE.SCISSORS;
            total = 1;
        } else if ((choiceArray[0] == choiceArray[1]) && choiceArray[0] > choiceArray[2]) {
            choices[0] = MOVE.ROCK;
            choices[1] = MOVE.PAPER;
            total = 2;
        } else if ((choiceArray[0] == choiceArray[2]) && choiceArray[0] > choiceArray[1]) {
            choices[0] = MOVE.ROCK;
            choices[1] = MOVE.SCISSORS;
            total = 2;
        } else if ((choiceArray[1] == choiceArray[2]) && choiceArray[1] > choiceArray[0]) {
            choices[0] = MOVE.PAPER;
            choices[1] = MOVE.SCISSORS;
            total = 2;
        } else {
            choices[0] = MOVE.ROCK;
            choices[1] = MOVE.PAPER;
            choices[2] = MOVE.SCISSORS;
            total = 3;
        }

        return choices[r.nextInt(total)];

    }

    @Override
    public void processOutcome(MOVE CPUMove, MOVE playerMove, ArrayList<MOVE> origMoveList) {
        // TODO: clean up so there's less duplicated code

        List<MOVE> moveList =  origMoveList.subList(1,4);

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
        String specificCase = "";
        for (int i = 0; i < 3; i++) {
            MOVE move = moveList.get(i);
            if (move == MOVE.ROCK) {
                specificCase += "R ";
            } else if (move == MOVE.PAPER) {
                specificCase += "P ";
            } else if (move == MOVE.SCISSORS) {
                specificCase += "S ";
            } else {
                specificCase += "N ";
            }
        }

        // Find line for this specific case & update
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            if (line.contains(specificCase)) {
                data = line.split(" \\| ");
                fileContent += data[0] + " | ";

                // Update 2nd chunk [what we *should* have played]
                float total = 0;
                String[] chunk = data[1].split(" ");
                if (Driver.losesAgainst(playerMove) == MOVE.ROCK) {
                    chunk[0] = String.valueOf(Integer.parseInt(chunk[0]) + 2);
                    chunk[2] = String.valueOf(Integer.parseInt(chunk[0]) + 1);
                } else if (Driver.losesAgainst(playerMove) == MOVE.PAPER) {
                    chunk[1] = String.valueOf(Integer.parseInt(chunk[1]) + 2);
                    chunk[0] = String.valueOf(Integer.parseInt(chunk[0]) + 1);
                } else {
                    chunk[2] = String.valueOf(Integer.parseInt(chunk[2]) + 2);
                    chunk[1] = String.valueOf(Integer.parseInt(chunk[0]) + 1);
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

        // Formulate general case to look for
        String generalCase = "1 ";
        MOVE first = moveList.get(1);
        MOVE second = null;
        if (moveList.get(1) == MOVE.NONE) {
            generalCase += "N ";
        } else if (moveList.get(1) == moveList.get(0)) {
            generalCase += "1 ";
        } else {
            second = moveList.get(1);
            generalCase += "2 ";
        }
        if (moveList.get(2) == MOVE.NONE) {
            generalCase += "N ";
        } else if (moveList.get(2) == moveList.get(0)) {
            generalCase += "1 ";
        } else if (moveList.get(2) == moveList.get(1)){
            generalCase += "2 ";
        } else {
            if (moveList.get(1) == moveList.get(0)) {
                generalCase += "2 ";
            } else {
                generalCase += "3 ";
            }
        }

        // Find line for this general case & update
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            if (!(moveList.get(1) == MOVE.NONE)) {
                if (line.contains(generalCase)) {
                    data = line.split(" \\| ");
                    fileContent += data[0] + " | ";

                    // Update 3rd chunk
                    float total = 0;
                    String[] chunk = data[1].split(" ");
                    if (Driver.losesAgainst(playerMove) == first) {
                        chunk[0] = String.valueOf(Integer.parseInt(chunk[0]) + 2);
                    } else if (Driver.losesAgainst(playerMove) == second || second == null) {
                        chunk[1] = String.valueOf(Integer.parseInt(chunk[1]) + 2);
                    } else {
                        chunk[2] = String.valueOf(Integer.parseInt(chunk[2]) + 2);
                    }

                    if (playerMove == first) {
                        chunk[0] = String.valueOf(Integer.parseInt(chunk[0]) + 1);
                    } else if (Driver.losesAgainst(playerMove) == second || second == null) {
                        chunk[1] = String.valueOf(Integer.parseInt(chunk[1]) + 1);
                    } else {
                        chunk[2] = String.valueOf(Integer.parseInt(chunk[1]) + 1);
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
