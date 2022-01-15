package logic.aftermove;

import entities.Board;
import entities.Game;
import entities.Player;
import exceptions.InvalidCoordinateException;
import exceptions.VictoryException;

import static logic.aftermove.victory.VictoryCondition.checkVictoryCondition;

public class AfterMoveChecker {
    private Board board;
    private Player player;
    private Game game;

    public AfterMoveChecker(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public AfterMoveChecker(Game game){
        this.game = game;
    }

    /**
     * This method calls 2 other methods: one checks if new territories have been formed,
     * the other one if the victory condition has been satisfied.
     *
     * @return the updated board if a new territory has been found, the current board otherwise
     * @throws VictoryException
     * @throws InvalidCoordinateException
     */
    public Board checkAndUpdateBoardAfterMove() throws VictoryException, InvalidCoordinateException {
        Board boardChecked = board;
        //If there have been at least 3 moves it makes sense to check the territories
        if (board.getMovesHistory().size() > 2) {
            boardChecked = game.checkTerritories(board, player);
        }
        checkVictoryCondition(boardChecked, player);
        return boardChecked;
    }
}
