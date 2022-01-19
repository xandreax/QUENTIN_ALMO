package logic.beforemove;

import entities.*;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import logic.beforemove.illegalmove.CardinalCoordinates;
import logic.beforemove.illegalmove.IllegalMoveLogic;

public class BeforeMoveChecker {
    private final BoardCoordinate move;
    private final Board board;
    private final Player player;

    public BeforeMoveChecker(BoardCoordinate move, Board board, Player player) {
        this.move = move;
        this.board = board;
        this.player = player;
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
        if (!board.getPieceByCoordinate(move).equals(Pieces.NONE)) {
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
        CardinalCoordinates cardinals = new CardinalCoordinates(move, board.getDIMENSION());
        if(IllegalMoveLogic.checkIfExistDiagonalIllegalPiece(cardinals, board, player.getPieces()))
            throw new IllegalMoveException("Move not allowed, " +move.getRow()+move.getColumn()+" this position doesn't share any other orthogonal piece of your color");
    }
}
