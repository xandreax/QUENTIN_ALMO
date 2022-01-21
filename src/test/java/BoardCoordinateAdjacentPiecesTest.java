import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardCoordinateAdjacentPiecesTest {

    public static BoardCoordinate bc;

    @Before
    public void initBoardCoordinate() throws InvalidCoordinateException {
        bc = new BoardCoordinate("c3");
    }

    @Test
    public void getLeft() throws InvalidCoordinateException {
        assertEquals(bc.getLeft(), new BoardCoordinate("c2"));
    }

    @Test
    public void getRight() throws InvalidCoordinateException {
        assertEquals(bc.getRight(), new BoardCoordinate("c4"));
    }

    @Test
    public void getUp() throws InvalidCoordinateException {
        assertEquals(bc.getUp(), new BoardCoordinate("b3"));
    }

    @Test
    public void getDown() throws InvalidCoordinateException {
        assertEquals(bc.getDown(), new BoardCoordinate("d3"));
    }

    @Test
    public void getUpRight() throws InvalidCoordinateException {
        assertEquals(bc.getUpRight(), new BoardCoordinate("b4"));
    }

    @Test
    public void getUpLeft() throws InvalidCoordinateException {
        assertEquals(bc.getUpLeft(), new BoardCoordinate("b2"));
    }

    @Test
    public void getDownLeft() throws InvalidCoordinateException {
        assertEquals(bc.getDownLeft(), new BoardCoordinate("d2"));
    }

    @Test
    public void getDownRight() throws InvalidCoordinateException {
        assertEquals(bc.getDownRight(), new BoardCoordinate("d4"));
    }

    @Test
    public void hasAtLeastTwoAdjacentPieces() throws InvalidCoordinateException {
        Board board = new Board();
        board.setPieceByCoordinate(bc.getRight(), Pieces.BLACK);
        board.setPieceByCoordinate(bc.getDown(), Pieces.WHITE);
        assertTrue(bc.hasAtLeastTwoAdjacentPieces(board));
        assertFalse(bc.getUp().hasAtLeastTwoAdjacentPieces(board));
    }
}
