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
        checkSouthEastCell();
        checkSouthWestCell();
        checkNorthEastCell();
        checkNorthWestCell();
    }

    private void checkSouthWestCell() throws InvalidCoordinateException, IllegalMoveException {
        if (!(move.getCoordinate().getX() == board.getDIMENSION()) || !(move.getCoordinate().getY() == board.getDIMENSION())) {
            BoardCoordinate southWestCoordinate = new BoardCoordinate(move.getCoordinate().getX() + 1, move.getCoordinate().getY() + 1);
            if (board.getPieceByCoordinate(southWestCoordinate).equals(move.getPlayer().getPieces())) {
                if (!checkIfNorthIsOccupiedBySamePiece(southWestCoordinate) && !checkIfEastIsOccupiedBySamePiece(southWestCoordinate))
                    throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
            }
        }
    }

    private void checkSouthEastCell() throws IllegalMoveException, InvalidCoordinateException {
        if (!(move.getCoordinate().getX() == board.getDIMENSION()) || !(move.getCoordinate().getY() == 0)) {
            BoardCoordinate southEastCoordinate = new BoardCoordinate(move.getCoordinate().getX() + 1, move.getCoordinate().getY() - 1);
            if (board.getPieceByCoordinate(southEastCoordinate).equals(move.getPlayer().getPieces())) {
                if (!checkIfNorthIsOccupiedBySamePiece(southEastCoordinate) && !checkIfWestIsOccupiedBySamePiece(southEastCoordinate))
                    throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
            }
        }
    }

    private void checkNorthEastCell() throws IllegalMoveException, InvalidCoordinateException {
        if (!(move.getCoordinate().getX() == 0) || !(move.getCoordinate().getY() == 0)) {
            BoardCoordinate northEastCoordinate = new BoardCoordinate(move.getCoordinate().getX() - 1, move.getCoordinate().getY() - 1);
            if (board.getPieceByCoordinate(northEastCoordinate).equals(move.getPlayer().getPieces())) {
                if (!checkIfSouthIsOccupiedBySamePiece(northEastCoordinate) && !checkIfWestIsOccupiedBySamePiece(northEastCoordinate))
                    throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
            }
        }
    }

    private void checkNorthWestCell() throws IllegalMoveException, InvalidCoordinateException {
        if (!(move.getCoordinate().getX() == 0) || !(move.getCoordinate().getY() == board.getDIMENSION())) {
            BoardCoordinate northWestCoordinate = new BoardCoordinate(move.getCoordinate().getX() - 1, move.getCoordinate().getY() + 1);
            if (board.getPieceByCoordinate(northWestCoordinate).equals(move.getPlayer().getPieces())) {
                if (!checkIfSouthIsOccupiedBySamePiece(northWestCoordinate) && !checkIfEastIsOccupiedBySamePiece(northWestCoordinate))
                    throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
            }
        }
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
