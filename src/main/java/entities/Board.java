package entities;

import gui.BoardShellPrinter;
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
        return this.getMatrix()[xy.getX()][xy.getY()];
    }

    public void setPieceByCoordinate(BoardCoordinate xy, Pieces piece) {
        this.getMatrix()[xy.getX()][xy.getY()] = piece;
    }

    public BoardShellPrinter getPrinter() {
        return new BoardShellPrinter(this);
    }
}
