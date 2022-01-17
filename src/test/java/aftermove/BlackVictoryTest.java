package aftermove;

import entities.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class BlackVictoryTest {

    //FIELDS
    public static Game game;
    public static Player player1;
    public static Player player2;

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
    }

    @Test
    public void testBlackVictory() {
        Assertions.assertThrows(VictoryException.class, () -> {
            game = new Game(player1, player2);
            List<String> coordinateList = Arrays.asList("a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5");
            doMoves(coordinateList, game, player1);
        });
    }

    private void doMoves(List<String> coordinateList, Game game, Player player) throws InvalidCoordinateException, PositionAlreadyOccupiedException, VictoryException, IllegalMoveException {
        for (String coordinateString: coordinateList) {
            BoardCoordinate coordinate = new BoardCoordinate(coordinateString);
            Move move = new Move(player, coordinate);
            game.move(move);
        }
    }
}
