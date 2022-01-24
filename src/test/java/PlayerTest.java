import entities.Pieces;
import entities.Player;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void testInvertPlayer() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        Player player1 = new Player("pippo", Pieces.BLACK);
        Player player2 = new Player("pluto", Pieces.WHITE);
        Player[] players = new Player[]{player1, player2};
        assertEquals(player1, player2.invertPlayer(players));
        assertEquals(player2, player1.invertPlayer(players));
        assertNotEquals(player1, player1.invertPlayer(players));
    }
}
