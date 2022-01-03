package utils.consoleApplicationRunnerUtils;

import entities.BoardCoordinate;
import entities.Player;
import exceptions.InvalidCoordinateException;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleAskMove {
    /**
     * This method is for console mode of the game and its functionality is to ask and read the move in input
     *
     * @param br
     * @param player
     * @return the BoardCoordinate respectively of the input
     */
    public static BoardCoordinate askForMove(BufferedReader br, Player player) {
        boolean done = false;
        BoardCoordinate newBoardCoordinate = null;
        while (!done) {
            System.out.print("\n" + player.getUsername() + "[" + player.getPieces().getName() + "] has to move. Insert a valid coordinate: ");
            try {
                String temp = br.readLine().trim();
                newBoardCoordinate = new BoardCoordinate(temp);
                done = true;
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            } catch (InvalidCoordinateException e) {
                System.out.println("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
            }
        }
        return newBoardCoordinate;
    }
}
