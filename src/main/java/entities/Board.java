package entities;

import exceptions.InvalidCoordinateException;
import gui.BoardShellPrinter;
import logic.aftermove.territories.UpdaterBoard;

import java.util.LinkedList;
import java.util.List;

public class Board {
    //FIELDS
    protected Pieces[][] matrix;        //each cell represents an intersection(!)
    protected final int DIMENSION = 13;      //number of intersection, not boxes. 13 intersection ==> 12 boxes
    protected List<Move> movesHistory;

    //CONSTRUCTORS
    public Board() {
        this.matrix = new Pieces[DIMENSION][DIMENSION];
        this.movesHistory = new LinkedList<>();
        for (int i = 0; i < this.DIMENSION; i++) {
            for (int j = 0; j < this.DIMENSION; j++) {
                this.matrix[i][j] = Pieces.NONE;
            }
        }
    }

    //METHODS
    public Pieces[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Pieces[][] matrix) {
        this.matrix = matrix;
    }

    public int getDIMENSION() {
        return DIMENSION;
    }

    public void doMove(Move move) {
        this.setPieceByCoordinate(move.getCoordinate(), move.getPlayer().getPieces());
        this.movesHistory.add(move);
    }

    public Pieces getPieceByCoordinate(BoardCoordinate xy) {
        return this.getMatrix()[xy.getRow()][xy.getColumn()];
    }

    public void setPieceByCoordinate(BoardCoordinate xy, Pieces piece) {
        this.getMatrix()[xy.getRow()][xy.getColumn()] = piece;
    }

    public BoardShellPrinter getPrinter() {
        return new BoardShellPrinter(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            Board board = (Board) obj;
            for (int row = 0; row < this.getDIMENSION(); row++){
                for (int col = 0; col < this.getDIMENSION(); col ++){
                    if (!board.getMatrix()[row][col].name.equals(this.matrix[row][col].name))
                        return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the index is in the board range
     *
     * @param index
     * @return true if the index is in range of the board dimension, false otherwise
     */
    public boolean isNotEdge(int index) {
        return (index >= 0 && index < DIMENSION);
    }

    /**
     * This method checks if the coordinates are in range of the board dimension
     *
     * @param row
     * @param col
     * @return the coordinate in the right range if the indexes are out of bounds, the old coordinates otherwise
     * @throws InvalidCoordinateException
     */
    public BoardCoordinate checkEdgesOfBoard(int row, int col) throws InvalidCoordinateException {
        if (col >= DIMENSION) {
            row++;
            col = 0;
            if (row >= DIMENSION){
                row = 0;
            }
        }
        return new BoardCoordinate(row, col);
    }

    public List<Move> getMovesHistory() {
        return movesHistory;
    }


}
