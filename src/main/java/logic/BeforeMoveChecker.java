package logic;

import entities.Board;
import entities.Move;
import entities.Pieces;
import exceptions.PositionAlreadyOccupiedException;

public class BeforeMoveChecker {
    protected Move move;
    protected Board board;

    public BeforeMoveChecker(Move move, Board board) {
        this.move = move;
        this.board = board;
    }

    public void checkIfMoveIsPossible() throws PositionAlreadyOccupiedException {
        checkIfPositionIsOccupied();
    }

    private void checkIfPositionIsOccupied () throws PositionAlreadyOccupiedException {
        if(!board.getMatrix()[move.getCoordinate().getX()][move.getCoordinate().getY()].equals(Pieces.NONE)){
            throw new PositionAlreadyOccupiedException("Move not allowed, this position is already occupied by another piece");
        }
    }

    private void checkIfMoveIsLegal () {

    }
}
