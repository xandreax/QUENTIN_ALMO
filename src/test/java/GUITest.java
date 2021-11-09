import entities.*;
import exceptions.InvalidCoordinateException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import gui.BoardShellPrinter;
import gui.components.GameFrame;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class GUITest {
    //FIELDS
    public static Board board;
    public static GameFrame frame;
    public static Player p1;
    public static Player p2;
    public static Game game;
/*
    //METHODS
    @BeforeClass
    public static void init() throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        board = new Board();
        frame = new GameFrame(board);
        p1 = new Player("giocatore1", Pieces.WHITE);
        p2 = new Player("giocatore2", Pieces.BLACK);
        game = new Game(p1, p2);
    }

    @Test
    public void testGUI() throws InvalidCoordinateException {
        Move temp = new Move(p1, new BoardCoordinate(0, 0));
        board.doMove(temp);
        temp = new Move(p2, new BoardCoordinate(12, 12));
        board.doMove(temp);
        temp = new Move(p1, new BoardCoordinate(3, 3));
        board.doMove(temp);
        temp = new Move(p2, new BoardCoordinate(5, 2));
        board.doMove(temp);
        temp = new Move(p1, new BoardCoordinate(4, 3));
        board.doMove(temp);
        temp = new Move(p2, new BoardCoordinate(5, 3));
        board.doMove(temp);
        temp = new Move(p1, new BoardCoordinate(5, 3));
        board.doMove(temp);
        temp = new Move(p2, new BoardCoordinate(6, 3));
        board.doMove(temp);
        temp = new Move(p1, new BoardCoordinate(6, 3));
        board.doMove(temp);
        temp = new Move(p2, new BoardCoordinate(7, 3));
        board.doMove(temp);
        frame.setVisible(true);
    }

 */
}
