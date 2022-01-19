package beforemove;

import entities.*;
import exceptions.*;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class IllegalMoveTest {

    //FIELDS
    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
        List<String> coordinateList = Arrays.asList("l4", "m4");
        setPiecesOnBoard(coordinateList, player1.getPieces());
    }

    //METHODS
    @Test
    public void checkIfMoveIsIllegal() {
        Assertions.assertThrows(IllegalMoveException.class, () -> {
            Controller controller = new Controller(myBoard, new Player[]{player1, player2});
            controller.checkIfMoveIsPossible(new BoardCoordinate("k5"));
        });
    }

    private void setPiecesOnBoard(List<String> coordinateList, Pieces piece) throws InvalidCoordinateException {
        for (String coordinateString: coordinateList) {
            myBoard.setPieceByCoordinate(new BoardCoordinate(coordinateString), piece);
        }
    }
}
