package logic;

import entities.*;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;

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
        if (!board.getPieceByCoordinate(move).equals(Pieces.NONE)) {
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
        if(checkIfExistDiagonalIllegalPiece())
            throw new IllegalMoveException("Move not allowed, " +move.getRow()+move.getColumn()+" this position doesn't share any other orthogonal piece of your color");
    }

    /**
     * This method checks for each diagonal corner if exists (points near the border doesn't have all the corner) and
     * if is occupied by a piece of the same colour of the player that doesn't share any adjacent and orthogonal
     * piece with the position of the move.
     *
     * @return true only if there is a piece of the same colour in a corner that doesn't share any adjacent and orthogonal
     *         piece with the position of the move.
     */
    //TODO: ho refactorizzato anche qui eliminando le classi cardinalCoordinate e IllegalMoveLogic che conteneva un metodo statico che non serviva che lo sia
    private boolean checkIfExistDiagonalIllegalPiece(){
        try{
            if(board.getPieceByCoordinate(move.getUpRight()).equals(player.getPieces())){
                if(board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getUpRight(), move.getRight()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getUpRight(), move.getUp()))
                    return true;
            }
        }
        catch(InvalidCoordinateException ignored){}
        try {
            if (board.getPieceByCoordinate(move.getUpLeft()).equals(player.getPieces())) {
                if (board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getUpLeft(), move.getLeft()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getUpLeft(), move.getUp()))
                    return true;
            }
        }
        catch(InvalidCoordinateException ignored){}
        try {
            if (board.getPieceByCoordinate(move.getDownLeft()).equals(player.getPieces())) {
                if (board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getDownLeft(), move.getLeft()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getDownLeft(), move.getDown()))
                    return true;
            }
        }
        catch(InvalidCoordinateException ignored){}
        try{
            if(board.getPieceByCoordinate(move.getDownRight()).equals(player.getPieces())){
                if(board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getDownRight(), move.getRight()) &&
                        board.checkIfTwoPointsIsNotOccupiedBySamePiece(move.getDownRight(), move.getDown()))
                    return true;
            }
        }
        catch(InvalidCoordinateException ignored){}
        return false;
    }
}
