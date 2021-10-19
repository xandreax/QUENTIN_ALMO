import entities.*;
import exceptions.*;
import logic.AfterMoveChecker;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TerritoriesTest {

    public static Game game;
    public static Player player1;
    public static Player player2;
    public static Board myBoard;
    public static Board updatedBoard;

    @BeforeClass
    public static void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException, PositionAlreadyOccupiedException, VictoryException, IllegalMoveException {
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
        Move m1b = new Move(player1, c1);
        Move m1w = new Move(player2, c2);
        Move m2b = new Move(player1, c3);
        Move m2w = new Move(player2, c4);
        Move m3b = new Move(player1, c5);
        Move m3w = new Move(player2, c6);
        Move m4b = new Move(player1, c7);
        game.move(m1b);
        game.move(m1w);
        game.move(m2b);
        game.move(m2w);
        game.move(m3b);
        game.move(m3w);
        game.move(m4b);
        AfterMoveChecker afterMoveChecker = new AfterMoveChecker(game.getBoard(), player1);
        updatedBoard = afterMoveChecker.checkAndUpdateBoardAfterMove();

        Pieces[][] matrix = new Pieces[13][13];
        matrix[1][3] = Pieces.BLACK;
        matrix[1][5] = Pieces.WHITE;
        matrix[2][3] = Pieces.BLACK;
        matrix[2][5] = Pieces.WHITE;
        matrix[1][6] = Pieces.WHITE;
        matrix[3][3] = Pieces.BLACK;
        matrix[3][4] = Pieces.BLACK;
        //These next two are the filled pieces
        matrix[1][4] = Pieces.BLACK;
        matrix[2][4] = Pieces.BLACK;
        myBoard.setMatrix(matrix);
    }

    @Test
    void testBoardAfterMove() {
        Assertions.assertEquals(myBoard, updatedBoard);
    }
}