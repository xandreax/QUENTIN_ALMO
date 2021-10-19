package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Move;
import entities.Pieces;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;

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
        if (checkIfSouthEastCellIsIllegal() || checkIfSouthWestCellIsIllegal() || checkIfNorthEastCellIsIllegal() || checkIfNorthWestCellIsIllegal())
            throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
    }

    private boolean checkIfSouthWestCellIsIllegal() throws InvalidCoordinateException {
        if (move.getCoordinate().getX() != board.getDIMENSION() - 1 && move.getCoordinate().getY() != board.getDIMENSION() - 1) {
            BoardCoordinate southWestCoordinate = new BoardCoordinate(move.getCoordinate().getX() + 1, move.getCoordinate().getY() + 1);
            if (board.getPieceByCoordinate(southWestCoordinate).equals(move.getPlayer().getPieces())) {
                return !checkIfNorthIsOccupiedBySamePiece(southWestCoordinate) && !checkIfEastIsOccupiedBySamePiece(southWestCoordinate);
            }
        }
        return false;
    }

    private boolean checkIfSouthEastCellIsIllegal() throws InvalidCoordinateException {
        if (move.getCoordinate().getX() != board.getDIMENSION() - 1 && move.getCoordinate().getY() != 0) {
            BoardCoordinate southEastCoordinate = new BoardCoordinate(move.getCoordinate().getX() + 1, move.getCoordinate().getY() - 1);
            if (board.getPieceByCoordinate(southEastCoordinate).equals(move.getPlayer().getPieces())) {
                return !checkIfNorthIsOccupiedBySamePiece(southEastCoordinate) && !checkIfWestIsOccupiedBySamePiece(southEastCoordinate);
            }
        }
        return false;
    }

    private boolean checkIfNorthEastCellIsIllegal() throws InvalidCoordinateException {
        if (move.getCoordinate().getX() != 0 && move.getCoordinate().getY() != 0) {
            BoardCoordinate northEastCoordinate = new BoardCoordinate(move.getCoordinate().getX() - 1, move.getCoordinate().getY() - 1);
            if (board.getPieceByCoordinate(northEastCoordinate).equals(move.getPlayer().getPieces())) {
                return !checkIfSouthIsOccupiedBySamePiece(northEastCoordinate) && !checkIfWestIsOccupiedBySamePiece(northEastCoordinate);
            }
        }
        return false;
    }

    private boolean checkIfNorthWestCellIsIllegal() throws InvalidCoordinateException {
        if (move.getCoordinate().getX() != 0 && move.getCoordinate().getY() != board.getDIMENSION() - 1) {
            BoardCoordinate northWestCoordinate = new BoardCoordinate(move.getCoordinate().getX() - 1, move.getCoordinate().getY() + 1);
            if (board.getPieceByCoordinate(northWestCoordinate).equals(move.getPlayer().getPieces())) {
                return !checkIfSouthIsOccupiedBySamePiece(northWestCoordinate) && !checkIfEastIsOccupiedBySamePiece(northWestCoordinate);
            }
        }
        return false;
    }

    private boolean checkIfNorthIsOccupiedBySamePiece(BoardCoordinate coordinate) throws InvalidCoordinateException {
        return board.getPieceByCoordinate(new BoardCoordinate(coordinate.getX() - 1, coordinate.getY())).equals(move.getPlayer().getPieces());
    }

    private boolean checkIfEastIsOccupiedBySamePiece(BoardCoordinate coordinate) throws InvalidCoordinateException {
        return board.getPieceByCoordinate(new BoardCoordinate(coordinate.getX(), coordinate.getY() - 1)).equals(move.getPlayer().getPieces());
    }

    private boolean checkIfWestIsOccupiedBySamePiece(BoardCoordinate coordinate) throws InvalidCoordinateException {
        return board.getPieceByCoordinate(new BoardCoordinate(coordinate.getX(), coordinate.getY() + 1)).equals(move.getPlayer().getPieces());
    }

    private boolean checkIfSouthIsOccupiedBySamePiece(BoardCoordinate coordinate) throws InvalidCoordinateException {
        return board.getPieceByCoordinate(new BoardCoordinate(coordinate.getX() + 1, coordinate.getY())).equals(move.getPlayer().getPieces());
    }
}
