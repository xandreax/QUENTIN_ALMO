package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Player;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;

public class BeforeMoveChecker {
    private final BoardCoordinate moveCoordinate;
    private final Board board;
    private final Player player;

    public BeforeMoveChecker(BoardCoordinate moveCoordinate, Board board, Player player) {
        this.moveCoordinate = moveCoordinate;
        this.board = board;
        this.player = player;
    }

    /**
     * This method verifies that the inserted move is possible with two others methods: the first checks
     * if the position indicated by the move is not already occupied, the second if that position is legal.
     *
     * @throws PositionAlreadyOccupiedException
     * @throws IllegalMoveException
     */
    public void checkIfMoveIsPossible() throws PositionAlreadyOccupiedException, IllegalMoveException {
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
        if (!board.isCoordinateEmpty(moveCoordinate)) {
            throw new PositionAlreadyOccupiedException("Move not allowed, this position is already occupied by another piece");
        }
    }

    /**
     * This method finds the cardinal coordinates of the position and call another method to check if there is a
     * illegal piece on a diagonal corner.
     *
     * @throws IllegalMoveException
     */
    private void checkIfMoveIsLegal() throws IllegalMoveException {
        if (checkIfExistDiagonalIllegalPiece())
            throw new IllegalMoveException("Move not allowed, " + moveCoordinate.getRow() + moveCoordinate.getColumn() + " this position doesn't share any other orthogonal piece of your color");
    }

    /**
     * This method checks for each diagonal corner if exists (points near the border doesn't have all the corner) and
     * if is occupied by a piece of the same colour of the player that doesn't share any adjacent and orthogonal
     * piece with the position of the move.
     *
     * @return true only if there is a piece of the same colour in a corner that doesn't share any adjacent and orthogonal
     * piece with the position of the move.
     */
    private boolean checkIfExistDiagonalIllegalPiece() {
        try {
            if (board.getPieceByCoordinate(moveCoordinate.getUpRight()).equals(player.getPieces())) {
                if (board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getUpRight(), moveCoordinate.getRight()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getUpRight(), moveCoordinate.getUp()))
                    return true;
            }
        } catch (InvalidCoordinateException ignored) {
        }
        try {
            if (board.getPieceByCoordinate(moveCoordinate.getUpLeft()).equals(player.getPieces())) {
                if (board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getUpLeft(), moveCoordinate.getLeft()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getUpLeft(), moveCoordinate.getUp()))
                    return true;
            }
        } catch (InvalidCoordinateException ignored) {
        }
        try {
            if (board.getPieceByCoordinate(moveCoordinate.getDownLeft()).equals(player.getPieces())) {
                if (board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getDownLeft(), moveCoordinate.getLeft()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getDownLeft(), moveCoordinate.getDown()))
                    return true;
            }
        } catch (InvalidCoordinateException ignored) {
        }
        try {
            if (board.getPieceByCoordinate(moveCoordinate.getDownRight()).equals(player.getPieces())) {
                if (board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getDownRight(), moveCoordinate.getRight()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(moveCoordinate.getDownRight(), moveCoordinate.getDown()))
                    return true;
            }
        } catch (InvalidCoordinateException ignored) {
        }
        return false;
    }
}
