package aftermove;

import entities.*;
import exceptions.*;
import logic.aftermove.AfterMoveChecker;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class EdgeTerritoryTest {

    public static Game game;
    public static Player player1;
    public static Player player2;
    public static Board myBoard = new Board();
    public static Board updatedBoard = new Board();

    @Before
    public void playGame2() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException, PositionAlreadyOccupiedException, VictoryException, IllegalMoveException {
        player1 = new Player("adfq", Pieces.BLACK);
        player2 = new Player("fwqf", Pieces.WHITE);

        game = new Game(player1, player2);
        List<String> coordinateBlackList = Arrays.asList("a1", "b1");
        List<String> coordinateWhiteList = List.of("b0");
        doMoves(coordinateBlackList, game, player1);
        doMoves(coordinateWhiteList, game, player2);
        AfterMoveChecker afterMoveChecker = new AfterMoveChecker(game, player1);
        updatedBoard = afterMoveChecker.checkAndUpdateBoardAfterMove();

        coordinateBlackList.add("a0");
        setPiecesOnBoard(coordinateWhiteList, Pieces.WHITE);
        setPiecesOnBoard(coordinateBlackList, Pieces.BLACK);
    }

    private void doMoves(List<String> coordinateList, Game game, Player player) throws InvalidCoordinateException, PositionAlreadyOccupiedException, VictoryException, IllegalMoveException {
        for (String coordinateString: coordinateList) {
            BoardCoordinate coordinate = new BoardCoordinate(coordinateString);
            Move move = new Move(player, coordinate);
            game.move(move);
        }
    }

    private void setPiecesOnBoard(List<String> coordinateList, Pieces piece) throws InvalidCoordinateException {
        for (String coordinateString: coordinateList) {
            myBoard.setPieceByCoordinate(new BoardCoordinate(coordinateString), piece);
        }
    }

    @Test
    public void testBoardAfterMove() {
        Assertions.assertEquals(myBoard, updatedBoard);
    }
}
