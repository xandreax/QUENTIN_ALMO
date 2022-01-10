import entities.*;
import exceptions.IllegalMoveException;
import exceptions.PositionAlreadyOccupiedException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class BeforeMoveCheckerTest {
    //FIELDS
    public static Game game;
    public static Player player1;
    public static Player player2;

    @BeforeClass
    public static void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
    }

    //METHODS
    @Test
    public void testMoveExceptions() {
        Assertions.assertThrows(PositionAlreadyOccupiedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinate = new BoardCoordinate("d5");
                Move moveBlack = new Move(player1, coordinate);
                Move moveWhite = new Move(player2, coordinate);
                game.move(moveBlack);
                game.move(moveWhite);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinate1 = new BoardCoordinate("d5");
                BoardCoordinate coordinate2 = new BoardCoordinate("d6");
                Move moveBlack = new Move(player1, coordinate1);
                Move moveWhite = new Move(player2, coordinate2);
                game.move(moveBlack);
                game.move(moveWhite);
            }
        });
        Assertions.assertThrows(IllegalMoveException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinate1 = new BoardCoordinate("d8");
                BoardCoordinate coordinate2 = new BoardCoordinate("c9");
                Move moveBlack = new Move(player1, coordinate1);
                Move moveBlack2 = new Move(player1, coordinate2);
                game.move(moveBlack);
                game.move(moveBlack2);
            }
        });
        Assertions.assertThrows(IllegalMoveException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinate1 = new BoardCoordinate("k5");
                BoardCoordinate coordinate2 = new BoardCoordinate("m4");
                BoardCoordinate coordinate3 = new BoardCoordinate("l4");
                Move moveBlack = new Move(player1, coordinate1);
                Move moveBlack2 = new Move(player1, coordinate2);
                Move moveBlack3 = new Move(player1, coordinate3);
                game.move(moveBlack);
                game.move(moveBlack2);
                game.move(moveBlack3);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinate1 = new BoardCoordinate("d8");
                BoardCoordinate coordinate2 = new BoardCoordinate("d9");
                BoardCoordinate coordinate3 = new BoardCoordinate("c9");
                Move moveBlack = new Move(player1, coordinate1);
                Move moveBlack2 = new Move(player1, coordinate2);
                Move moveBlack3 = new Move(player1, coordinate3);
                game.move(moveBlack);
                game.move(moveBlack2);
                game.move(moveBlack3);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                BoardCoordinate coordinate1 = new BoardCoordinate("a0");
                BoardCoordinate coordinate2 = new BoardCoordinate("a12");
                BoardCoordinate coordinate3 = new BoardCoordinate("a6");
                BoardCoordinate coordinate4 = new BoardCoordinate("c12");
                Move moveBlack = new Move(player1, coordinate1);
                Move moveBlack2 = new Move(player1, coordinate2);
                Move moveBlack3 = new Move(player1, coordinate3);
                Move moveBlack4 = new Move(player1, coordinate4);
                game.move(moveBlack);
                game.move(moveBlack2);
                game.move(moveBlack3);
                game.move(moveBlack4);
            }
        });
    }
}
