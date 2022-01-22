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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieRuleTest {

    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();
    public static Controller controller;

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        controller = new Controller(myBoard, new Player[]{player1, player2});
    }

    @Test
    public void checkIfIsTimeToPieRule() throws InvalidCoordinateException {
        controller.makeMove(new BoardCoordinate("c3"));
        assertTrue(controller.checkIfIsTimeToPieRule());
    }

    @Test
    public void applyPieRule() {
        controller.applyPieRule();
        assertEquals(player1.getPieces(), Pieces.WHITE);
        assertEquals(player2.getPieces(), Pieces.BLACK);
    }
}
