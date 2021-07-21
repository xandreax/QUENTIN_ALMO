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
    public void testPlayer() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Player player = new Player("injf93", Pieces.BLACK);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Player player = new Player("adsgff0", Pieces.WHITE);
            }
        });
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Player player = new Player("abine13", Pieces.NONE);
            }
        });
        Assertions.assertThrows(UsernameTooShortException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Player player = new Player("ab", Pieces.BLACK);
            }
        });
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Player player = new Player("ab", Pieces.NONE);
            }
        });
    }
}
