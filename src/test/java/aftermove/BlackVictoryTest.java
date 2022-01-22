package aftermove;

import entities.*;
import exceptions.*;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;
import utils.TestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlackVictoryTest {

    //FIELDS
    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        List<String> coordinateList = Arrays.asList("a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5");
        TestUtils.setPiecesOnBoard(coordinateList, player1.getPieces(), myBoard);
    }

    @Test
    public void testBlackVictory(){
        Controller controller = new Controller(myBoard, new Player[]{player1, player2});
        assertTrue(controller.endOfGame());
        assertEquals(controller.getWinnerPlayer(), player1);
    }
}
