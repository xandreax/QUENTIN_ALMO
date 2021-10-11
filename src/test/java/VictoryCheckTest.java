import entities.*;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import exceptions.VictoryException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

public class VictoryCheckTest {
    //FIELDS
    public static Game game;
    public static Player player1;
    public static Player player2;

    @BeforeClass
    public static void initGame() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        player1 = new Player("hjgutcgju", Pieces.BLACK);
        player2 = new Player("saiubvfswvb", Pieces.WHITE);
    }

    @Test
    public void testVictoryCondition(){
        Assertions.assertThrows(VictoryException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                List<BoardCoordinate> coordinateList = new ArrayList<>();
                coordinateList.add(new BoardCoordinate("a5"));
                coordinateList.add(new BoardCoordinate("b5"));
                coordinateList.add(new BoardCoordinate("c5"));
                coordinateList.add(new BoardCoordinate("d5"));
                coordinateList.add(new BoardCoordinate("e5"));
                coordinateList.add(new BoardCoordinate("f5"));
                coordinateList.add(new BoardCoordinate("g5"));
                coordinateList.add(new BoardCoordinate("h5"));
                coordinateList.add(new BoardCoordinate("I5"));
                coordinateList.add(new BoardCoordinate("J5"));
                coordinateList.add(new BoardCoordinate("K5"));
                coordinateList.add(new BoardCoordinate("L5"));
                coordinateList.add(new BoardCoordinate("m5"));
                List<Move> moveList = new ArrayList<>();
                for (BoardCoordinate coordinate: coordinateList) {
                    moveList.add(new Move(player1, coordinate));
                }
                for (Move move: moveList) {
                    game.move(move);
                }
            }
        });
        Assertions.assertThrows(VictoryException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                List<BoardCoordinate> coordinateList = new ArrayList<>();
                coordinateList.add(new BoardCoordinate("a0"));
                coordinateList.add(new BoardCoordinate("a1"));
                coordinateList.add(new BoardCoordinate("a2"));
                coordinateList.add(new BoardCoordinate("a3"));
                coordinateList.add(new BoardCoordinate("a4"));
                coordinateList.add(new BoardCoordinate("a5"));
                coordinateList.add(new BoardCoordinate("a6"));
                coordinateList.add(new BoardCoordinate("a7"));
                coordinateList.add(new BoardCoordinate("a8"));
                coordinateList.add(new BoardCoordinate("a9"));
                coordinateList.add(new BoardCoordinate("a10"));
                coordinateList.add(new BoardCoordinate("a11"));
                coordinateList.add(new BoardCoordinate("a12"));
                List<Move> moveList = new ArrayList<>();
                for (BoardCoordinate coordinate: coordinateList) {
                    moveList.add(new Move(player2, coordinate));
                }
                for (Move move: moveList) {
                    game.move(move);
                }
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                game = new Game(player1, player2);
                List<BoardCoordinate> coordinateList = new ArrayList<>();
                coordinateList.add(new BoardCoordinate("a0"));
                coordinateList.add(new BoardCoordinate("a1"));
                coordinateList.add(new BoardCoordinate("a2"));
                coordinateList.add(new BoardCoordinate("a3"));
                coordinateList.add(new BoardCoordinate("a4"));
                coordinateList.add(new BoardCoordinate("a5"));
                coordinateList.add(new BoardCoordinate("a6"));
                coordinateList.add(new BoardCoordinate("a7"));
                coordinateList.add(new BoardCoordinate("a8"));
                coordinateList.add(new BoardCoordinate("a9"));
                coordinateList.add(new BoardCoordinate("a10"));
                coordinateList.add(new BoardCoordinate("a11"));
                coordinateList.add(new BoardCoordinate("a12"));
                List<Move> moveList = new ArrayList<>();
                for (BoardCoordinate coordinate: coordinateList) {
                    moveList.add(new Move(player1, coordinate));
                }
                for (Move move: moveList) {
                    game.move(move);
                }
            }
        });
    }
}
