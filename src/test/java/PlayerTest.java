import entities.Pieces;
import entities.Player;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class PlayerTest {
    //METHODS
    @Test
    public void testPlayer1() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Exception {
                Player player = new Player("injf93", Pieces.BLACK);
            }
        });
    }
    @Test
    public void testPlayer2() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Exception {
                Player player = new Player("adsgff0", Pieces.WHITE);
            }
        });
    }

    @Test
    public void testPlayer3() {
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, new Executable() {
            @Override
            public void execute() throws Exception {
                Player player = new Player("abine13", Pieces.NONE);
            }
        });
    }

    @Test
    public void testPlayer4() {
        Assertions.assertThrows(UsernameTooShortException.class, new Executable() {
            @Override
            public void execute() throws Exception {
                Player player = new Player("ab", Pieces.BLACK);
            }
        });
    }

    @Test
    public void testPlayer5() {
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, new Executable() {
            @Override
            public void execute() throws Exception {
                Player player = new Player("ab", Pieces.NONE);
            }
        });
    }

}
