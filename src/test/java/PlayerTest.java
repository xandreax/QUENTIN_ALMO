import entities.Pieces;
import entities.Player;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PlayerTest {
    //METHODS
    @Test
    public void testPlayer1() {
        Assertions.assertDoesNotThrow(() -> {
            new Player("injf93", Pieces.BLACK);
        });
    }
    @Test
    public void testPlayer2() {
        Assertions.assertDoesNotThrow(() -> {
             new Player("adsgff0", Pieces.WHITE);
        });
    }

    @Test
    public void testPlayer3() {
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, () -> new Player("abine13", Pieces.NONE));
    }

    @Test
    public void testPlayer4() {
        Assertions.assertThrows(UsernameTooShortException.class, () -> new Player("ab", Pieces.BLACK));
    }

    @Test
    public void testPlayer5() {
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, () -> new Player("ab", Pieces.NONE));
    }

}
