package logic.BeforeMove;

import entities.Board;
import entities.Move;
import entities.Pieces;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import logic.BeforeMove.IllegalMoveLogic.CardinalCoordinates;

import static logic.BeforeMove.IllegalMoveLogic.IllegalMoveLogic.checkIfExistDiagonalIllegalPiece;

public class BeforeMoveChecker {
    protected Move move;
    protected Board board;

    public BeforeMoveChecker(Move move, Board board) {
        this.move = move;
        this.board = board;
    }

    public void checkIfMoveIsPossible() throws PositionAlreadyOccupiedException, IllegalMoveException, InvalidCoordinateException {
        checkIfPositionIsOccupied();
        checkIfMoveIsLegal();
    }

    private void checkIfPositionIsOccupied() throws PositionAlreadyOccupiedException {
        if (!board.getMatrix()[move.getCoordinate().getX()][move.getCoordinate().getY()].equals(Pieces.NONE)) {
            throw new PositionAlreadyOccupiedException("Move not allowed, this position is already occupied by another piece");
        }
    }

    private void checkIfMoveIsLegal() throws IllegalMoveException, InvalidCoordinateException {
        CardinalCoordinates cardinals = new CardinalCoordinates(move.getCoordinate(), board.getDIMENSION());
        if(checkIfExistDiagonalIllegalPiece(cardinals, board, move.getPlayer().getPieces()))
            throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
    }
}
