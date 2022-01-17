package logic.aftermove.territories;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import exceptions.InvalidCoordinateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CounterColourTerritory {

    private final List<BoardCoordinate> countedPieces = new ArrayList<>();
    private final Board board;

    public CounterColourTerritory(Board board) {
        this.board = board;
    }

    /**
     * This method counts how many pieces and of which type are adjacent to
     * the coordinate passed as input
     *
     * @param coordinate
     * @return a map with the type of the piece and its respective number
     */
    public Map<Pieces, Integer> countAdjacentPieces(BoardCoordinate coordinate) throws InvalidCoordinateException {
        Map<Pieces, Integer> piecesIntegerMap = new HashMap<>();
        int countWhite, countBlack;
        int row = coordinate.getRow();
        int col = coordinate.getColumn();

        BlackAndWhite bn1 = countBnW(row -1, col);
        BlackAndWhite bn2 = countBnW(row +1, col);
        BlackAndWhite bn3 = countBnW(row, col-1);
        BlackAndWhite bn4 = countBnW(row, col+1);
        countBlack = bn1.getnBlack() + bn2.getnBlack() + bn3.getnBlack() + bn4.getnBlack();
        countWhite = bn1.getnWhite() + bn2.getnWhite() + bn3.getnWhite() + bn4.getnWhite();
        piecesIntegerMap.put(Pieces.BLACK, countBlack);
        piecesIntegerMap.put(Pieces.WHITE, countWhite);
        return piecesIntegerMap;
    }

    /**
     * This method is called by "countAdjacentPieces" and it returns a structure
     * that keeps count of the number of black and white pieces in the given coordinate
     *
     * @param row
     * @param col
     * @return a structure with the number of white and black pieces for the given coordinate
     */
    private BlackAndWhite countBnW(int row, int col) throws InvalidCoordinateException {
        int countWhite = 0, countBlack = 0;
        if (board.isNotEdge(row) && board.isNotEdge(col)){
            BoardCoordinate coordinate = new BoardCoordinate(row,col);
            if (board.getMatrix()[row][col].equals(Pieces.WHITE) && !countedPieces.contains(coordinate)){
                countWhite++;
                countedPieces.add(coordinate);
            }
            else if (board.getMatrix()[row][col].equals(Pieces.BLACK) && !countedPieces.contains(coordinate)) {
                countBlack++;
                countedPieces.add(coordinate);
            }
        }
        return new BlackAndWhite(countBlack, countWhite);
    }

    private class BlackAndWhite {
        private final int nBlack;
        private final int nWhite;

        public BlackAndWhite(int nBlack, int nWhite) {
            this.nBlack = nBlack;
            this.nWhite = nWhite;
        }

        public int getnBlack() {
            return nBlack;
        }

        public int getnWhite() {
            return nWhite;
        }
    }
}



