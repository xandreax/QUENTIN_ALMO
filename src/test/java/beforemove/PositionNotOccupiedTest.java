package beforemove;

import entities.*;
import exceptions.*;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utils.TestUtils;

import java.util.List;


public class PositionNotOccupiedTest {

    //FIELDS
    private static Player player1;
    private static Player player2;
    private static final Board myBoard = new Board();

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        List<String> coordinateList = List.of("d5");
        TestUtils.setPiecesOnBoard(coordinateList, player1.getPieces(), myBoard);
    }

    //METHODS
    @Test
    public void checkIfPositionIsNotOccupied (){
        Assertions.assertDoesNotThrow(() -> {
            Controller controller = new Controller(myBoard, new Player[]{player1, player2});
            controller.checkIfMoveIsPossible(new BoardCoordinate("d6"));
        });
    }
}
