package aftermove;

import entities.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class WhiteVictoryTest {

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
    public void testWhiteVictory() {
        Assertions.assertThrows(VictoryException.class, () -> {
            game = new Game(player1, player2);
            List<String> coordinateList = Arrays.asList("a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12");
            doMoves(coordinateList, game, player2);
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
