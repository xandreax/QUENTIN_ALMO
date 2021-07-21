import entities.Coordinate;
import entities.Move;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MoveTest {
    //FIELDS
    public static Player player1;
    public static Coordinate coordinate1;
    public static Move move1;
    public static Player player2;
    public static Coordinate coordinate2;
    public static Move move2;

    //METHODS
    @BeforeClass
    public static void initMoves() throws UnsupportedPiecesForPlayerException, InvalidCoordinateException, UsernameTooShortException {
        player1 = new Player("jnv8498bg", Pieces.WHITE);
        coordinate1 = new Coordinate("k11");
        move1 = new Move(player1, coordinate1);
        player2 = new Player("9082374", Pieces.WHITE);
        coordinate2 = new Coordinate("d4");
        move2 = new Move(player2, coordinate2);
    }

    @Test
    public void testMoveEquality() {
        Assertions.assertEquals(move1, move1);
        Assertions.assertEquals(move2, move2);
        Assertions.assertNotEquals(move1, move2);
        Assertions.assertNotEquals(move2, move1);
    }
}