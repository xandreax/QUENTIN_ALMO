package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Player;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import ui.gui.components.GameFrame;
import ui.gui.components.gamepage.PanelGamePage;

import java.util.Arrays;
import java.util.List;

public class UIGame implements Game {

    private Player player1;
    private Player player2;
    private final GameFrame frame;
    private final Board board;
    private Controller controller;

    public UIGame() {
        this.board = new Board();
        frame = new GameFrame(this);
        frame.setVisible(true);
    }

    @Override
    public void startGame() {
        List<Player> players = frame.getPlayers();
        player1 = players.get(0);
        player2 = players.get(1);
        controller = new Controller(board, new Player[]{player1, player2});
        frame.initPanelGamePage();
    }

    public void handleMove(BoardCoordinate move, PanelGamePage p) {
        try {
            controller.checkIfMoveIsPossible(move);
            controller.makeMove(move);
            frame.renderMove();
            p.getHeader().highlightTurn(controller.getCurrentPlayer());
        } catch (PositionAlreadyOccupiedException e) {
            frame.toggleAlertDialog("Invalid coordinate. This position is already occupied by another piece");
        } catch (InvalidCoordinateException e) {
            frame.toggleAlertDialog("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
        } catch (IllegalMoveException e) {
            frame.toggleAlertDialog("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
        }
        if (controller.checkIfIsTimeToPieRule()) {
            if (frame.togglePieRuleDialog(controller.getCurrentPlayer())) {
                controller.applyPieRule();
                p.getHeader().swapPieces();
                p.getBoardPanel().repaint();
                p.getHeader().highlightTurn(controller.getCurrentPlayer());
            }
        }
        if (!controller.checkIfThereAreAvailableMoves()) {
            frame.toggleNoAvailableMovesDialog(controller.getCurrentPlayer());
            controller.changeTurn();
            p.getHeader().highlightTurn(controller.getCurrentPlayer());
        }
        if (controller.endOfGame()) {
            Player winner = controller.getWinnerPlayer();
            frame.toggleVictoryDialog(winner);
        }
    }

    public List<Player> getPlayers() {
        return Arrays.asList(player1, player2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return controller.getBoard();
    }
}
