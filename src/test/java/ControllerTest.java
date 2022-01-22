import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private static Player player1;
    private static Player player2;
    private static final Board myBoard = new Board();
    private static Controller controller;

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        controller = new Controller(myBoard, new Player[]{player1, player2});
    }

    @Test
    public void getCurrentPlayer() {
        assertEquals(controller.getCurrentPlayer(), player1);
    }

    @Test
    public void changeTurn() {
        controller.changeTurn();
        assertEquals(controller.getCurrentPlayer(), player2);
    }

    @Test
    public void makeMove() throws InvalidCoordinateException {
        controller = new Controller(myBoard, new Player[]{player1, player2});
        BoardCoordinate bc = new BoardCoordinate("c4");
        controller.makeMove(bc);
        assertEquals(myBoard.getPieceByCoordinate(bc), Pieces.BLACK);
        assertEquals(controller.getCurrentPlayer(), player2);
    }

    @Test
    public void checkIfThereAreAvailableMoves() {
        assertTrue(controller.checkIfThereAreAvailableMoves());
        Arrays.stream(myBoard.getMatrix()).forEach(array -> Arrays.fill(array, Pieces.BLACK));
        assertFalse(controller.checkIfThereAreAvailableMoves());
    }
}
