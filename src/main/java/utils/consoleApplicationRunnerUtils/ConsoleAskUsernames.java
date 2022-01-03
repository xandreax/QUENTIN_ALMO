package utils.consoleApplicationRunnerUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleAskUsernames {
    /**
     * This method is for console mode of the game and its functionality is to ask and read the usernames in input
     * @param br
     * @return an array of the two usernames
     */
    public static String[] askForUsernames(BufferedReader br) {
        String[] result = new String[2];
        String username;
        int contatore = 1;
        while (contatore <3) {
            System.out.print("Enter Player "+contatore+" username: ");
            try {
                username = br.readLine().trim();
                if (username.equals("")) {
                    System.out.println("Empty string is not a valid name. Must be at least 3 characters long.");
                } else if (username.length() <= 2) {
                    System.out.println("Username must be at least 3 characters long.");
                } else {
                    result[contatore-1] = username;
                    contatore++;
                }
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        return result;
    }
}
