package logic.aftermove;

import entities.Board;
import entities.Game;
import entities.Player;
import exceptions.InvalidCoordinateException;
import exceptions.VictoryException;
import logic.aftermove.victory.VictoryCondition;

public class AfterMoveChecker {
    private final Player player;
    private final Game game;

    public AfterMoveChecker(Game game, Player player) {
        this.game = game;
        this.player = player;
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
        Board board = game.getBoard();
        VictoryCondition victoryCondition = new VictoryCondition();
        //If there have been at least 2 moves it makes sense to check the territories
        if (board.getMovesHistory().size() > 1) {
            board = game.checkTerritories(player);
        }
        victoryCondition.checkVictoryCondition(board, player);
        return board;
    }
}
