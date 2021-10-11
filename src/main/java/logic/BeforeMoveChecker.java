package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Move;
import entities.Pieces;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.List;

public class BeforeMoveChecker {
    protected Move move;
    protected Board board;

    public BeforeMoveChecker(Move move, Board board) {
        this.move = move;
        this.board = board;
    }

    public void checkIfMoveIsPossible() throws PositionAlreadyOccupiedException, IllegalMoveException {
        checkIfPositionIsOccupied();
        checkIfMoveIsLegal();
    }

    private void checkIfPositionIsOccupied () throws PositionAlreadyOccupiedException {
        if(!board.getMatrix()[move.getCoordinate().getX()][move.getCoordinate().getY()].equals(Pieces.NONE)){
            throw new PositionAlreadyOccupiedException("Move not allowed, this position is already occupied by another piece");
        }
    }

    private void checkIfMoveIsLegal () throws IllegalMoveException {
        List<BoardCoordinate> boardCoordinateList = new ArrayList<>();
        try {
            if(board.getMovesHistory().size() < 2)
                return;
            if(move.getCoordinate().getX() != 0) {
                BoardCoordinate north = new BoardCoordinate(move.getCoordinate().getX() - 1, move.getCoordinate().getY());
                boardCoordinateList.add(north);
            }
            if(move.getCoordinate().getX() != 12) {
                BoardCoordinate south = new BoardCoordinate(move.getCoordinate().getX() + 1, move.getCoordinate().getY());
                boardCoordinateList.add(south);
            }
            if(move.getCoordinate().getY() != 0) {
                BoardCoordinate east = new BoardCoordinate(move.getCoordinate().getX(), move.getCoordinate().getY() - 1);
                boardCoordinateList.add(east);
            }if(move.getCoordinate().getY() != 12) {
                BoardCoordinate west = new BoardCoordinate(move.getCoordinate().getX(), move.getCoordinate().getY() + 1);
                boardCoordinateList.add(west);
            }
        } catch (InvalidCoordinateException e) {
            e.printStackTrace();
        }
        for (BoardCoordinate coordinate: boardCoordinateList) {
            if(board.getPieceByCoordinate(coordinate).equals(move.getPlayer().getPieces()))
                return;
        }
        throw new IllegalMoveException("Move not allowed, this position doesn't share any other orthogonal piece of your color");
    }
}
