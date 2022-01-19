package aftermove;

import entities.*;
import exceptions.*;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NotVictoryTest {

    //FIELDS
    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        List<String> coordinateList = Arrays.asList("a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12");
        setPiecesOnBoard(coordinateList, player1.getPieces());
    }

    @Test
    public void testNoVictory() {
        Controller controller = new Controller(myBoard, new Player[]{player1, player2});
        assertFalse(controller.endOfGame());
    }

    private void setPiecesOnBoard(List<String> coordinateList, Pieces piece) throws InvalidCoordinateException {
        for (String coordinateString: coordinateList) {
            myBoard.setPieceByCoordinate(new BoardCoordinate(coordinateString), piece);
        }
    }
}
