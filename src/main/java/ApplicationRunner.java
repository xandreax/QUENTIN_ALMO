import entities.Game;
import entities.Pieces;
import entities.Player;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import gui.BoardShellPrinter;
import utils.ConsoleApplicationRunnerUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApplicationRunner {
    //ENTRY POINT
    public static void main(String[] args) {
        System.out.println("---------- LET'S PLAY QUENTIN! ----------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] usernames = ConsoleApplicationRunnerUtils.askForUsernames(br);
        Pieces[] pieces = ConsoleApplicationRunnerUtils.askForPieces(br, usernames[0]);
        Player player1 = null;
        Player player2 = null;
        try {
            player1 = new Player(usernames[0], pieces[0]);
            player2 = new Player(usernames[1], pieces[1]);
        }
        catch (UnsupportedPiecesForPlayerException e) {
            System.err.println("Somehow a player choose NONE as Pieces. Aborted.");
            e.printStackTrace();
        }
        catch (UsernameTooShortException e) {
            System.err.println("Somehow player's too short username exception went through all controls. Aborted.");
            e.printStackTrace();
        }
        Game game = new Game(player1, player2);
        BoardShellPrinter bp = game.getBoard().getPrinter();
        System.out.println("\n******************** GAME STARTING ********************");
        System.out.println("ID game: "+game.getUuid());
        System.out.println("Begin time: "+game.getBeginTime());
        System.out.println("\nCoordinate input format should be: a literal from a to m, and right after a number from 0 to 12.");
        ConsoleApplicationRunnerUtils.startGame(br, game, bp, player1, player2);
    }
}