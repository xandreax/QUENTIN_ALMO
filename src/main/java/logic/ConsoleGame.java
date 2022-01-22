package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Player;
import exceptions.*;
import ui.shell.BoardShellPrinter;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static ui.shell.ConsoleInputMethods.askForMove;
import static ui.shell.ConsoleInputMethods.askForPieRule;

public class ConsoleGame implements Game{
    //FIELDS
    private final String uuid;
    private final Date beginTime;
    private final Player player1;
    private final Player player2;
    private final Board board;
    private Controller controller;

    //CONSTRUCTORS
    public ConsoleGame(Player player1, Player player2/*, BufferedReader br*/) {
        //this.br = br;
        this.uuid = UUID.randomUUID().toString();
        this.beginTime = new Date();

        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        startGame();
    }

    //METHODS

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public void startGame(){
        System.out.println("ID game: "+ uuid);
        System.out.println("Begin time: "+ beginTime);
        controller = new Controller(board, new Player[]{player1, player2});
        BoardShellPrinter bp = board.getPrinter();
        bp.printOnStdOut();
        while(!controller.endOfGame()){
            if(controller.checkIfIsTimeToPieRule()){
                if(askForPieRule(controller.getCurrentPlayer()/*, br*/)) {
                    controller.applyPieRule();
                }
            }
            boolean hasMoved = false;
            if(controller.checkIfThereAreAvailableMoves()) {
                while (!hasMoved) {
                    BoardCoordinate move = askForMove(controller.getCurrentPlayer()/*, br*/);
                    try{
                        controller.checkIfMoveIsPossible(move);
                        controller.makeMove(move);
                        hasMoved = true;
                    } catch (PositionAlreadyOccupiedException e) {
                        System.out.println("Invalid coordinate. This position is already occupied by another piece");
                    } catch (InvalidCoordinateException e) {
                        System.out.println("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
                    } catch (IllegalMoveException e) {
                        System.out.println("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
                    }
                }
            }
            else{
                System.out.println("Player " + controller.getCurrentPlayer().getUsername() + " has no available moves");
                controller.changeTurn();
            }
            bp.printOnStdOut();
        }
        Player winner = controller.getWinnerPlayer();
        System.out.println("Victory! " + winner.getUsername() + " has won the game!");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj instanceof ConsoleGame) {
            ConsoleGame param = (ConsoleGame) obj;
            return (uuid.equals(param.uuid));
        } else {
            return false;
        }
    }

    //TODO: è CORRETTO COSì?
    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
