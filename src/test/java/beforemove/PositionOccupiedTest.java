package beforemove;

import entities.*;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utils.TestUtils;

import java.util.List;

public class PositionOccupiedTest {

    //FIELDS
    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        List<String> coordinateList = List.of("d5");
        TestUtils.setPiecesOnBoard(coordinateList, player1.getPieces(), myBoard);
    }

    //METHODS
    @Test
    public void checkIfPositionIsOccupied() {
        Assertions.assertThrows(PositionAlreadyOccupiedException.class, () -> {
            Controller controller = new Controller(myBoard, new Player[]{player1, player2});
            controller.checkIfMoveIsPossible(new BoardCoordinate("d5"));
        });
    }
}
