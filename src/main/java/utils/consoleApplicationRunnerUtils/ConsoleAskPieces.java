package utils.consoleApplicationRunnerUtils;

import entities.Pieces;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleAskPieces {
    /**
     * This method is for console mode of the game and its functionality is to ask and read the color of the pieces in input
     * @param br
     * @param username1 username of player 1
     * @return an array of the two pieces: (result[0] is player 1 and result[1] is player 2)
     */
    public static Pieces[] askForPieces(BufferedReader br, String username1) {
        Pieces[] result = new Pieces[2];
        boolean done = false;
        while (!done) {
            System.out.print("Player '" + username1 + "' please select a color: 0 for BLACK, 1 for WHITE: ");
            try {
                String temp = br.readLine();
                int i = Integer.parseInt(temp);
                if (i == 0) {
                    result[0] = Pieces.BLACK;
                    done = true;
                } else if (i == 1) {
                    result[0] = Pieces.WHITE;
                    done = true;
                } else {
                    System.out.println("Invalid number inserted. Please insert 0 for BLACK, 1 for WHITE.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format inserted.");
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        if (result[0] == Pieces.BLACK) result[1] = Pieces.WHITE;
        else result[1] = Pieces.BLACK;
        return result;
    }
}
