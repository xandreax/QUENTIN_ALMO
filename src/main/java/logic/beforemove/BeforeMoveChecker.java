package logic.beforemove;

import entities.Board;
import entities.Move;
import entities.Pieces;
import entities.Player;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import logic.beforemove.illegalmove.CardinalCoordinates;
import logic.beforemove.illegalmove.IllegalMoveLogic;

public class BeforeMoveChecker {
    private final Move move;
    private final Board board;

    public BeforeMoveChecker(Move move, Board board) {
        this.move = move;
        this.board = board;
    }

    /**
     * This method verifies that the inserted move is possible with two others methods: the first checks
     * if the position indicated by the move is not already occupied, the second if that position is legal.
     *
     * @throws PositionAlreadyOccupiedException
     * @throws IllegalMoveException
     * @throws InvalidCoordinateException
     */
    public void checkIfMoveIsPossible() throws PositionAlreadyOccupiedException, IllegalMoveException, InvalidCoordinateException {
        checkIfPositionIsOccupied();
        checkIfMoveIsLegal();
    }

    /**
     * This method checks if the position indicated in the move is already occupied by another piece. If this is true,
     * an exception will be thrown.
     *
     * @throws PositionAlreadyOccupiedException
     */
    private void checkIfPositionIsOccupied() throws PositionAlreadyOccupiedException {
        if (!board.getPieceByCoordinate(move.getCoordinate()).equals(Pieces.NONE)) {
            throw new PositionAlreadyOccupiedException("Move not allowed, this position is already occupied by another piece");
        }
    }

    /**
     * This method finds the cardinal coordinates of the position and call another method to check if there is a
     * illegal piece on a diagonal corner.
     *
     * @throws IllegalMoveException
     * @throws InvalidCoordinateException
     */
    private void checkIfMoveIsLegal() throws IllegalMoveException, InvalidCoordinateException {
        CardinalCoordinates cardinals = new CardinalCoordinates(move.getCoordinate(), board.getDIMENSION());
        Player player = move.getPlayer();
        if(IllegalMoveLogic.checkIfExistDiagonalIllegalPiece(cardinals, board, player.getPieces()))
            throw new IllegalMoveException("Move not allowed, " +move.getCoordinate().getRow()+move.getCoordinate().getColumn()+" this position doesn't share any other orthogonal piece of your color");
    }
}
