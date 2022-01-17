package beforemove;

import entities.*;
import exceptions.PositionAlreadyOccupiedException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PositionOccupiedTest {

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
    public void checkIfPositionIsOccupied (){
        Assertions.assertThrows(PositionAlreadyOccupiedException.class, () -> {
            game = new Game(player1, player2);
            BoardCoordinate coordinate = new BoardCoordinate("d5");
            Move moveBlack = new Move(player1, coordinate);
            Move moveWhite = new Move(player2, coordinate);
            game.move(moveBlack);
            game.move(moveWhite);
        });
    }
}
