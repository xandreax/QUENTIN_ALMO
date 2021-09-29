import entities.*;
import exceptions.InvalidCoordinateException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import gui.components.GameFrame;

public class TestGUI {
    //METHODS
    public static void main(String[] args) throws UnsupportedPiecesForPlayerException, UsernameTooShortException, InvalidCoordinateException {
        Board board = new Board();
        GameFrame frame = new GameFrame(board);

        Player p1 = new Player("lemmed", Pieces.BLACK);
        Player p2 = new Player("jack", Pieces.WHITE);

        Move temp = new Move(p1, new BoardCoordinate(0, 0));
        board.doMove(temp);
        temp = new Move(p2, new BoardCoordinate(12, 12));
        board.doMove(temp);

        frame.setVisible(true);
    }
}
