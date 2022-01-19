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
     * @param coordinate: the current coordinate
     * @return a map with the type of the piece and its respective number
     */
    public Map<Pieces, Integer> countAdjacentPieces(BoardCoordinate coordinate) throws InvalidCoordinateException {
        Map<Pieces, Integer> piecesIntegerMap = new HashMap<>();
        int countWhite, countBlack;
        BlackAndWhite bn1 = countBnW(coordinate.getUp());
        BlackAndWhite bn2 = countBnW(coordinate.getDown());
        BlackAndWhite bn3 = countBnW(coordinate.getLeft());
        BlackAndWhite bn4 = countBnW(coordinate.getRight());
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
     * @param coordinate: the coordinate from which starts counting
     * @return a structure with the number of white and black pieces for the given coordinate
     */
    private BlackAndWhite countBnW(BoardCoordinate coordinate) {
        int countWhite = 0, countBlack = 0;
        int row = coordinate.getRow();
        int col = coordinate.getColumn();
        if (board.isNotEdge(row) && board.isNotEdge(col)){
            if (board.getPieceByCoordinate(coordinate).equals(Pieces.WHITE) && !countedPieces.contains(coordinate)){
                countWhite++;
                countedPieces.add(coordinate);
            }
            else if (board.getPieceByCoordinate(coordinate).equals(Pieces.BLACK) && !countedPieces.contains(coordinate)) {
                countBlack++;
                countedPieces.add(coordinate);
            }
        }
        return new BlackAndWhite(countBlack, countWhite);
    }

    private static class BlackAndWhite {
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



