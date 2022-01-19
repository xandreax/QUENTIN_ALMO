package ui.shell;

import entities.BoardCoordinate;
import entities.Player;
import exceptions.InvalidCoordinateException;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInputMethods {
    /**
     * This method is for console mode of the game and its functionality is to ask and read the move in input
     *
     * @param player
     * @return the BoardCoordinate respectively of the input
     */
    public static BoardCoordinate askForMove(Player player/*, BufferedReader br*/) {
        boolean done = false;
        BoardCoordinate newBoardCoordinate = null;
        while (!done) {
            System.out.print("\n" + player.getUsername() + "[" + player.getPieces().getName() + "] has to move. Insert a valid coordinate: ");
            try {
                String temp = System.console().readLine().trim();
                //String temp = br.readLine().trim();
                newBoardCoordinate = new BoardCoordinate(temp);
                done = true;
            } catch (InvalidCoordinateException e) {
                System.out.println("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
            } /*catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        return newBoardCoordinate;
    }

    /**
     * This method is for console mode of the game and its functionality is to ask if player2 want to make use of the Pie rule
     * @param player
     * @return true if the pie rule is accepted, false otherwise.
     */
    public static boolean askForPieRule(Player player/*, BufferedReader br*/){
        while (true) {
            System.out.println("\nPlayer "+ player.getUsername() + ", make use of the Pie rule?(0) or continue(1)?\n");
            try {
                String temp = System.console().readLine().trim();
                //String temp = br.readLine().trim();
                int i = Integer.parseInt(temp);
                if (i == 0) {
                    return true;
                } else if (i == 1) {
                    return false;
                } else {
                    System.out.println("Invalid number inserted. Please insert 0 for PieRule, 1 for continue.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format inserted.");
            } /*catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
