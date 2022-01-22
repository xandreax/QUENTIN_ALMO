package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;
import java.util.*;

public class BoardUpdater {

    private final Board board;
    private final Player player;
    private final List<BoardCoordinate> countedPieces = new ArrayList<>();

    public BoardUpdater(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    /**
     * This method counts the number of pieces adjacent to the territory
     * and then fills the territory with th right kind. If the number of
     * pieces are the same, the territory is filled with the opposite color
     * of the last player move.
     *
     * @param territory: list of coordinates
     * @return the board updated with the pieces of the right colour
     */
    public Board updateBoardWithTerritory(LinkedList<BoardCoordinate> territory) throws InvalidCoordinateException {
        int counterBlack = 0, counterWhite = 0;
        //Counts the total number of white and black pieces adjacent to the territory
        for (BoardCoordinate coordinate : territory){
            Map<Pieces, Integer> adjacentPiecesNumber = countAdjacentPieces(coordinate);
            Integer nBlack = adjacentPiecesNumber.get(Pieces.BLACK);
            Integer nWhite = adjacentPiecesNumber.get(Pieces.WHITE);
            counterBlack = counterBlack + nBlack;
            counterWhite = counterWhite + nWhite;
        }
        if (counterBlack > counterWhite){
            fillTerritory(territory, Pieces.BLACK);
        }
        else if (counterBlack < counterWhite){
            fillTerritory(territory, Pieces.WHITE);
        }
        else{
            if (player.isBlackPlayer()){
                fillTerritory(territory, Pieces.WHITE);
            }
            else if (player.isWhitePlayer()) {
                fillTerritory(territory, Pieces.BLACK);
            }
        }
        return board;
    }

    /**
     * This method fills the territory with the specified type of piece as argument
     *
     * @param territory :list of BoardCoordinate
     * @param blackOrWhite is the type of piece
     */
    private void fillTerritory(LinkedList<BoardCoordinate> territory, Pieces blackOrWhite) {
        for (BoardCoordinate coordinate : territory){
            board.setPieceByCoordinate(coordinate, blackOrWhite);
        }
    }

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
     * @param row: index of the row
     * @param col: index of the column
     * @return a structure with the number of white and black pieces for the given coordinate
     */
    private BlackAndWhite countBnW(int row, int col) throws InvalidCoordinateException {
        int countWhite = 0, countBlack = 0;
        if (board.isNotEdge(row) && board.isNotEdge(col)){
            BoardCoordinate coordinate = new BoardCoordinate(row,col);
            if (board.isPieceWhite(coordinate) && !countedPieces.contains(coordinate)){
                countWhite++;
                countedPieces.add(coordinate);
            }
            else if (board.isPieceBlack(coordinate) && !countedPieces.contains(coordinate)) {
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
