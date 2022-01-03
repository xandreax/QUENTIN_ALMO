package utils.consoleApplicationRunnerUtils;

import entities.BoardCoordinate;
import entities.Game;
import entities.Move;
import entities.Player;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import exceptions.VictoryException;

import java.io.BufferedReader;

import static utils.consoleApplicationRunnerUtils.ConsoleAskMove.askForMove;

public class ConsoleMoveLoop {

    /**
     * This method is for console mode of the game and its functionality is to capture the coordinate in input and
     * get the respective move.
     * @param game
     * @param br
     * @param player
     * @return true if the move is correct, and it's done.
     * @throws VictoryException if a player win the game
     */
    public static boolean playerMoves(Game game, BufferedReader br, Player player) throws VictoryException {
        try {
            BoardCoordinate newBoardCoordinate = askForMove(br, player);
            Move newMove = new Move(player, newBoardCoordinate);
            game.move(newMove);
            return true;
        } catch (InvalidCoordinateException e) {
            System.out.println("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
            return false;
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println("Invalid coordinate. This position is already occupied by another piece");
            return false;
        } catch (IllegalMoveException e) {
            System.out.println("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
            return false;
        } catch (VictoryException e) {
            throw  new VictoryException();
        }
    }
}
