import entities.Game;
import entities.Pieces;
import entities.Player;
import exceptions.UnsupportedPiecesForPlayerException;
import gui.BoardShellPrinter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class GameTest {
    //FIELDS
    public static Player player1;
    public static Player player2;
    public static Player player3;
    public static Game game1;
    public static Game game2;
    public static Game game3;
    public static BoardShellPrinter printer1;
    public static BoardShellPrinter printer2;
    public static BoardShellPrinter printer3;

    //TODO: refactor these tests
    //METHODS
    @Before
    public void initGame() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                player1 = new Player("1mnbvboekmg9", Pieces.WHITE);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                player2 = new Player("239f9rugf", Pieces.BLACK);
            }
        });
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                player3 = new Player("mnbcv92340", Pieces.NONE);
            }
        });
        game1 = new Game(player1, player2);
        game2 = new Game(player1, player3);
        game3 = new Game(player2, player3);
        printer1 = game1.getBoard().getPrinter();
        printer2 = game2.getBoard().getPrinter();
        printer3 = game3.getBoard().getPrinter();
    }

    @Test
    public void testGameEquality() {
        Assertions.assertNotEquals(game2, game1);
        Assertions.assertNotEquals(game1, game2);
        Assertions.assertNotEquals(game1, game3);
        Assertions.assertNotEquals(game3, game1);
        Assertions.assertNotEquals(game2, game3);
        Assertions.assertNotEquals(game3, game2);
        Assertions.assertEquals(game1, game1);
        Assertions.assertEquals(game2, game2);
        Assertions.assertEquals(game3, game3);
    }
}
