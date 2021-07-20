package entities;

import gui.BoardPrinter;

public class Board {
    //FIELDS
    protected Pieces[][] matrix;        //each cell represents an intersection(!)
    protected final int DIMENSION = 13;      //number of intersection, not boxes. 13 intersection ==> 12 boxes

    //CONSTRUCTORS
    public Board() {
        this.matrix = new Pieces[DIMENSION][DIMENSION];
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

    public BoardPrinter getPrinter() {
        return new BoardPrinter(this);
    }
}
