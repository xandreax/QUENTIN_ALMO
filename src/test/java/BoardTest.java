import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    //FIELDS
    private static Board board = new Board();

    @Test
    public void is13x13() {
        assertEquals(board.getMatrix().length, 13);
        assertEquals(board.getMatrix()[0].length, 13);
        assertEquals(board.getMatrix()[6].length, 13);
        assertEquals(board.getMatrix()[12].length, 13);
    }

    @Test
    public void isInitialized() {
        for (Pieces[] row : board.getMatrix()) {
            for (Pieces p : row) {
                assertNotNull(p);
            }
        }
    }

    @Test
    public void hasNoWhitePieces() {
        board = new Board();
        assertTrue(board.hasNoWhitePieces());
        try {
            board.setPieceByCoordinate(new BoardCoordinate("c4"), Pieces.WHITE);
        } catch (InvalidCoordinateException ignored) {
        }
        assertFalse(board.hasNoWhitePieces());
    }

    @Test
    public void isFull() throws InvalidCoordinateException {
        for (int row = 0; row < board.getDIMENSION(); row++) {
            for (int col = 0; col < board.getDIMENSION(); col++) {
                board.setPieceByCoordinate(new BoardCoordinate(row, col), Pieces.BLACK);
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    public void checkIfTwoPointsIsNotOccupiedBySamePiece() throws InvalidCoordinateException {
        BoardCoordinate bc1 = new BoardCoordinate("c4");
        BoardCoordinate bc2 = new BoardCoordinate("c2");
        BoardCoordinate bc3 = new BoardCoordinate("c3");
        board.setPieceByCoordinate(bc1, Pieces.WHITE);
        board.setPieceByCoordinate(bc2, Pieces.WHITE);
        board.setPieceByCoordinate(bc3, Pieces.BLACK);
        assertFalse(board.checkIfTwoPointsIsNotOccupiedBySamePiece(bc1, bc2));
        assertTrue(board.checkIfTwoPointsIsNotOccupiedBySamePiece(bc1, bc3));
    }

}
