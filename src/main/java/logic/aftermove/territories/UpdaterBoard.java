package logic.aftermove.territories;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;

import java.util.LinkedList;
import java.util.Map;

public class UpdaterBoard {

    private final Board board;
    private final Player player;

    public UpdaterBoard(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    /**
     * This method counts the number of pieces adjacent to the territory
     * and then fills the territory with th right kind. If the number of
     * pieces are the same, the territory is filled with the opposite color
     * of the last player move.
     *
     * @param territory
     * @return the board updated with the pieces of the right colour
     */
    public Board updateBoardWithTerritory(LinkedList<BoardCoordinate> territory) throws InvalidCoordinateException {
        int counterBlack = 0, counterWhite = 0;
        //Counts the total number of white and black pieces adjacent to the territory
        for (BoardCoordinate coordinate : territory){
            CounterColourTerritory counterColourTerritory = new CounterColourTerritory(board);
            Map<Pieces, Integer> adjacentPiecesNumber = counterColourTerritory.countAdjacentPieces(coordinate);
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
            if (player.getPieces().equals(Pieces.BLACK)){
                fillTerritory(territory, Pieces.WHITE);
            }
            else if (player.getPieces().equals(Pieces.WHITE)) {
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
            board.getMatrix()[coordinate.getRow()][coordinate.getColumn()] = blackOrWhite;
        }
    }
}
