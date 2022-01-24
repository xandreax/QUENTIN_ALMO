package aftermove;

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
import org.junit.jupiter.api.Assertions;
import utils.TestUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TerritoryTest {

    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();
    public static Board moveBoard = new Board();

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("pluto", Pieces.BLACK);
        player2 = new Player("pippo", Pieces.WHITE);
        List<String> coordinateBlackList = new LinkedList<>(Arrays.asList("a3", "b3", "c3", "c4"));
        List<String> coordinateWhiteList = new LinkedList<>(Arrays.asList("a5", "b5", "a6"));
        Controller controller = new Controller(moveBoard, new Player[]{player1, player2});
        doMoves(controller, coordinateBlackList, coordinateWhiteList);

        coordinateBlackList = Arrays.asList("a3", "b3", "c3", "c4", "a4", "b4");
        coordinateWhiteList = Arrays.asList("a5", "b5", "a6");
        TestUtils.setPiecesOnBoard(coordinateWhiteList, Pieces.WHITE, myBoard);
        TestUtils.setPiecesOnBoard(coordinateBlackList, Pieces.BLACK, myBoard);
    }

    private void doMoves(Controller controller, List<String> coordinateBlackList, List<String> coordinateWhiteList) throws InvalidCoordinateException {
        while (!coordinateBlackList.isEmpty() || !coordinateWhiteList.isEmpty()) {
            BoardCoordinate blackMove = new BoardCoordinate(coordinateBlackList.remove(0));
            controller.makeMove(blackMove);
            if (!coordinateWhiteList.isEmpty()) {
                BoardCoordinate whiteMove = new BoardCoordinate(coordinateWhiteList.remove(0));
                controller.makeMove(whiteMove);
            }
        }
    }

    @Test
    public void testBoardAfterMove() {
        Assertions.assertEquals(myBoard, moveBoard);
    }
}