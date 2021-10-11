import entities.*;
import exceptions.PositionAlreadyOccupiedException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class BeforeMoveCheckerTest {
    //FIELDS
    public static Game game;
    public static Player player1;
    public static Player player2;

    @BeforeClass
    public static void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
    }

    //METHODS
    @Test
    public void testMoveExceptions() {
        Assertions.assertThrows(PositionAlreadyOccupiedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinates = new BoardCoordinate("d5");
                Move moveBlack = new Move(player1, coordinates);
                Move moveWhite = new Move(player2, coordinates);
                game.move(moveBlack);
                game.move(moveWhite);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinates = new BoardCoordinate("d5");
                BoardCoordinate coordinates2 = new BoardCoordinate("d6");
                Move moveBlack = new Move(player1, coordinates);
                Move moveWhite = new Move(player2, coordinates2);
                game.move(moveBlack);
                game.move(moveWhite);
            }
        });
    }
}
