package beforemove;

import entities.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class PositionNotOccupiedTest {

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
    public void checkIfPositionIsNotOccupied (){
        Assertions.assertDoesNotThrow(() -> {
            game = new Game(player1, player2);
            BoardCoordinate coordinate1 = new BoardCoordinate("d5");
            BoardCoordinate coordinate2 = new BoardCoordinate("d6");
            Move moveBlack = new Move(player1, coordinate1);
            Move moveWhite = new Move(player2, coordinate2);
            game.move(moveBlack);
            game.move(moveWhite);
        });
    }
}
