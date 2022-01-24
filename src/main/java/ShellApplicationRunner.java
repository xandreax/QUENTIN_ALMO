import entities.Pieces;
import entities.Player;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import logic.ConsoleGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellApplicationRunner {
    //METHODS
    public static void main(String[] args) {
        System.out.println("---------- LET'S PLAY QUENTIN! ----------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] usernames = askForUsernames(br);
        Pieces[] pieces = askForPieces(br, usernames[0]);
        Player player1 = null;
        Player player2 = null;
        try {
            player1 = new Player(usernames[0], pieces[0]);
            player2 = new Player(usernames[1], pieces[1]);
        } catch (UnsupportedPiecesForPlayerException e) {
            System.err.println("Somehow a player choose NONE as Pieces. Aborted.");
            e.printStackTrace();
        } catch (UsernameTooShortException e) {
            System.err.println("Somehow player's too short username exception went through all controls. Aborted.");
            e.printStackTrace();
        }
        System.out.println("\n******************** GAME STARTING ********************");
        System.out.println("\nCoordinate input format should be: a literal from a to m, and right after a number from 0 to 12.");
        new ConsoleGame(player1, player2, br);
    }

    /**
     * This method is for console mode of the game and its functionality is to ask and read the usernames in input
     *
     * @param br
     * @return an array of the two usernames
     */
    public static String[] askForUsernames(BufferedReader br) {
        String[] result = new String[2];
        String username;
        int contatore = 1;
        while (contatore < 3) {
            System.out.print("Enter Player " + contatore + " username: ");
            try {
                username = br.readLine().trim();
                if (username.equals("")) {
                    System.out.println("Empty string is not a valid name. Must be at least 3 characters long.");
                } else if (username.length() <= 2) {
                    System.out.println("Username must be at least 3 characters long.");
                } else {
                    result[contatore - 1] = username;
                    contatore++;
                }
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * This method is for console mode of the game and its functionality is to ask and read the color of the pieces in input
     *
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