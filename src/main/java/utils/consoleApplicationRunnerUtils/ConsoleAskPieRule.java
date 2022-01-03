package utils.consoleApplicationRunnerUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleAskPieRule {

    /**
     * This method is for console mode of the game and its functionality is to ask if player2 want to make use of the Pie rule
     * @param username
     * @param br
     * @return true if the pie rule is accepted, false otherwise.
     */
    public static boolean askForPieRule(String username, BufferedReader br){
        while (true) {
            System.out.println("\nPlayer "+ username+ ", make use of the Pie rule?(0) or continue(1)?\n");
            try {
                String temp = br.readLine();
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
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
    }
}
