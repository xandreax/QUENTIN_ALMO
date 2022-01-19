package aftermove;

import entities.*;
import exceptions.*;
import logic.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EdgeTerritoryTest {

    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();
    public static Board moveBoard = new Board();

    @Before
    public void playGame2() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        player1 = new Player("adfq", Pieces.BLACK);
        player2 = new Player("fwqf", Pieces.WHITE);
        List<String> coordinateBlackList = new LinkedList<>(Arrays.asList("a1", "b1"));
        List<String> coordinateWhiteList = new LinkedList<>(Collections.singleton("b0"));
        Controller controller = new Controller(moveBoard, new Player[]{player1, player2});
        doMoves(controller, coordinateBlackList, coordinateWhiteList);

        coordinateBlackList = Arrays.asList("a1", "b1", "a0");
        coordinateWhiteList = List.of("b0");
        setPiecesOnBoard(coordinateWhiteList, Pieces.WHITE);
        setPiecesOnBoard(coordinateBlackList, Pieces.BLACK);
    }

    private void doMoves(Controller controller, List<String> coordinateBlackList, List<String> coordinateWhiteList) throws InvalidCoordinateException{
        while (!coordinateBlackList.isEmpty() || !coordinateWhiteList.isEmpty()){
            BoardCoordinate blackMove = new BoardCoordinate(coordinateBlackList.remove(0));
            controller.makeMove(blackMove);
            if(!coordinateWhiteList.isEmpty()){
                BoardCoordinate whiteMove = new BoardCoordinate(coordinateWhiteList.remove(0));
                controller.makeMove(whiteMove);
            }
        }
    }

    private void setPiecesOnBoard(List<String> coordinateList, Pieces piece) throws InvalidCoordinateException {
        for (String coordinateString: coordinateList) {
            myBoard.setPieceByCoordinate(new BoardCoordinate(coordinateString), piece);
        }
    }

    @Test
    public void testBoardAfterMove() {
        Assertions.assertEquals(myBoard, moveBoard);
    }
}
