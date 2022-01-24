package entities;

import exceptions.InvalidCoordinateException;
import ui.shell.BoardShellPrinter;

import java.util.Arrays;
import java.util.Objects;

public class Board {
    //FIELDS
    private final Pieces[][] matrix;        //each cell represents an intersection(!)
    private final int DIMENSION = 13;      //number of intersection, not boxes. 13 intersection ==> 12 boxes

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

    public int getDIMENSION() {
        return DIMENSION;
    }

    public Pieces getPieceByCoordinate(BoardCoordinate xy) {
        return matrix[xy.getRow()][xy.getColumn()];
    }

    public void setPieceByCoordinate(BoardCoordinate xy, Pieces piece) {
        matrix[xy.getRow()][xy.getColumn()] = piece;
    }

    public BoardShellPrinter getPrinter() {
        return new BoardShellPrinter(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            Board board = (Board) obj;
            for (int row = 0; row < this.getDIMENSION(); row++) {
                for (int col = 0; col < this.getDIMENSION(); col++) {
                    try {
                        BoardCoordinate bc = new BoardCoordinate(row, col);
                        if (!board.getPieceByCoordinate(bc).equals(this.matrix[row][col]))
                            return false;
                    } catch (InvalidCoordinateException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (int row = 0; row < this.getDIMENSION(); row++) {
            for (int col = 0; col < this.getDIMENSION(); col++) {
                res += Objects.hash(matrix[row][col]);
            }
        }
        return res;
    }

    /**
     * Checks if the index is in the board range
     *
     * @param index: index of the board
     * @return true if the index is in range of the board dimension, false otherwise
     */
    public boolean isNotEdge(int index) {
        return (index >= 0 && index < DIMENSION);
    }

    /**
     * This method checks if the coordinates are in range of the board dimension
     *
     * @param row: row of the board
     * @param col: col of the board
     * @return the coordinate in the right range if the indexes are out of bounds, the old coordinates otherwise
     * @throws InvalidCoordinateException: if coordinate is invalid throws an exception
     */
    public BoardCoordinate getCheckedCoordinate(int row, int col) throws InvalidCoordinateException {
        if (col >= DIMENSION) {
            row++;
            col = 0;
            if (row >= DIMENSION) {
                row = 0;
            }
        }
        return new BoardCoordinate(row, col);
    }

    public boolean hasNoWhitePieces() {
        return Arrays.stream(matrix).flatMap(Arrays::stream).noneMatch(x -> x.equals(Pieces.WHITE));
    }

    public boolean isFull() {
        return Arrays.stream(matrix).flatMap(Arrays::stream).noneMatch(x -> x.equals(Pieces.NONE));
    }

    public boolean checkIfTwoPointsIsNotOccupiedBySamePiece(BoardCoordinate bc1, BoardCoordinate bc2) {
        return !getPieceByCoordinate(bc1).equals(getPieceByCoordinate(bc2));
    }

    public boolean isPieceWhite(BoardCoordinate boardCoordinate) {
        return this.getPieceByCoordinate(boardCoordinate).equals(Pieces.WHITE);
    }

    public boolean isPieceBlack(BoardCoordinate boardCoordinate) {
        return this.getPieceByCoordinate(boardCoordinate).equals(Pieces.BLACK);
    }

    public boolean isCoordinateEmpty(BoardCoordinate boardCoordinate) {
        return this.getPieceByCoordinate(boardCoordinate).equals(Pieces.NONE);
    }

    public int countBlackPieces() {
        return (int) Arrays.stream(matrix).flatMap(Arrays::stream).filter(x -> x.equals(Pieces.BLACK)).count();
    }

    public int countWhitePieces() {
        return (int) Arrays.stream(matrix).flatMap(Arrays::stream).filter(x -> x.equals(Pieces.WHITE)).count();
    }
}
