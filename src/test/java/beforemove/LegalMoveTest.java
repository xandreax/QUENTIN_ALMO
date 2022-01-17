package beforemove;

import entities.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class LegalMoveTest {

    //FIELDS
    public static Game game;
    public static Player player1;
    public static Player player2;

    @Before
    public void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
    }

    //METHODS
    @Test
    public void checkIfMoveIsLegal() {
        Assertions.assertDoesNotThrow(() -> {
            game = new Game(player1, player2);
            List<String> coordinateList = Arrays.asList("d8", "d9", "c9");
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
