import entities.*;
import exceptions.*;
import logic.afterMove.AfterMoveChecker;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class TerritoriesTest {

    public static Game game;
    public static Game game2;
    public static Player player1;
    public static Player player2;
    public static Player player3;
    public static Player player4;
    public static Board myBoard = new Board();
    public static Board updatedBoard = new Board();
    public static Board myBoard2 = new Board();
    public static Board updatedBoard2 = new Board();

    @BeforeClass
    public static void playGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException, PositionAlreadyOccupiedException, VictoryException, IllegalMoveException {
        player1 = new Player("pluto", Pieces.BLACK);
        player2 = new Player("pippo", Pieces.WHITE);

        game = new Game(player1, player2);
        BoardCoordinate c1 = new BoardCoordinate("a3");
        BoardCoordinate c2 = new BoardCoordinate("a5");
        BoardCoordinate c3 = new BoardCoordinate("b3");
        BoardCoordinate c4 = new BoardCoordinate("b5");
        BoardCoordinate c5 = new BoardCoordinate("c3");
        BoardCoordinate c6 = new BoardCoordinate("a6");
        BoardCoordinate c7 = new BoardCoordinate("c4");
        game.move(new Move(player1, c1));
        game.move(new Move(player2, c2));
        game.move(new Move(player1, c3));
        game.move(new Move(player2, c4));
        game.move(new Move(player1, c5));
        game.move(new Move(player2, c6));
        game.move(new Move(player1, c7));
        AfterMoveChecker afterMoveChecker = new AfterMoveChecker(game.getBoard(), player1);
        updatedBoard = afterMoveChecker.checkAndUpdateBoardAfterMove();

        myBoard.setPieceByCoordinate(new BoardCoordinate("a3"), Pieces.BLACK);
        myBoard.setPieceByCoordinate(new BoardCoordinate("a5"), Pieces.WHITE);
        myBoard.setPieceByCoordinate(new BoardCoordinate("b3"), Pieces.BLACK);
        myBoard.setPieceByCoordinate(new BoardCoordinate("b5"), Pieces.WHITE);
        myBoard.setPieceByCoordinate(new BoardCoordinate("c3"), Pieces.BLACK);
        myBoard.setPieceByCoordinate(new BoardCoordinate("a6"), Pieces.WHITE);
        myBoard.setPieceByCoordinate(new BoardCoordinate("c4"), Pieces.BLACK);
        //These next two are the filled pieces
        myBoard.setPieceByCoordinate(new BoardCoordinate("a4"), Pieces.BLACK);
        myBoard.setPieceByCoordinate(new BoardCoordinate("b4"), Pieces.BLACK);
    }

    @BeforeClass
    public static void playGame2() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException, PositionAlreadyOccupiedException, VictoryException, IllegalMoveException {
        player3 = new Player("adfq", Pieces.BLACK);
        player4 = new Player("fwqf", Pieces.WHITE);

        game2 = new Game(player3, player4);
        BoardCoordinate c1 = new BoardCoordinate("a1");
        BoardCoordinate c2 = new BoardCoordinate("b0");
        BoardCoordinate c3 = new BoardCoordinate("b1");
        game2.move(new Move(player3, c1));
        game2.move(new Move(player4, c2));
        game2.move(new Move(player3, c3));
        AfterMoveChecker afterMoveChecker = new AfterMoveChecker(game2.getBoard(), player3);
        updatedBoard2 = afterMoveChecker.checkAndUpdateBoardAfterMove();

        myBoard2.setPieceByCoordinate(new BoardCoordinate("a1"), Pieces.BLACK);
        myBoard2.setPieceByCoordinate(new BoardCoordinate("b0"), Pieces.WHITE);
        myBoard2.setPieceByCoordinate(new BoardCoordinate("b1"), Pieces.BLACK);
        //Filled piece of the formed territory
        myBoard2.setPieceByCoordinate(new BoardCoordinate("a0"), Pieces.WHITE);
    }
    @Test
    public void testBoardAfterMove() {
        Assertions.assertEquals(myBoard, updatedBoard);
        Assertions.assertEquals(myBoard2, updatedBoard2);
    }
}