package entities;

import exceptions.InvalidCoordinateException;
import utils.CharUtils;
import utils.IntUtils;
import utils.StringUtils;
import java.util.Objects;

/**
 * This class represents a single coordinate defined as a line intersection in the context of the table board.
 *
 */
public class BoardCoordinate implements Coordinate2D {
    //FIELDS
    private int row;
    private int column;

    //CONSTRUCTORS
    public BoardCoordinate() {
    }

    public BoardCoordinate(int row, int column) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForBoardCoordinate(row) && IntUtils.isValidIntForBoardCoordinate(column)) {
            this.row = row;
            this.column = column;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    //Format: literal from a to m, and right after a number from 0 to 12
    public BoardCoordinate(String s) throws InvalidCoordinateException {
        String temp = s.toUpperCase();
        if (s.length() == 2) {
            if (StringUtils.is2LiteralsValidCoordinate(s)) {
                try {
                    this.row = CharUtils.mapInputCharToInputInt(temp.charAt(0));
                }
                catch (IndexOutOfBoundsException e) {
                    throw new InvalidCoordinateException("1st literal is char that exceed the correct interval [a-m].");
                }
                try {
                    this.column = Integer.parseInt(temp.substring(1));
                }
                catch (NumberFormatException e) {
                    throw new InvalidCoordinateException("2nd literal is not a number.");
                }
            }
            else {
                throw new InvalidCoordinateException("Coordinate is not a 2 literals valid coordinate.");
            }
        }
        else if (s.length() == 3) {
            if (StringUtils.is3LiteralsValidCoordinate(s)) {
                try {
                    this.row = CharUtils.mapInputCharToInputInt(temp.charAt(0));
                }
                catch (IndexOutOfBoundsException e) {
                    throw new InvalidCoordinateException("1st literal is char that exceed the correct interval [a-m].");
                }
                try {
                    this.column = Integer.parseInt(temp.substring(1));
                }
                catch (NumberFormatException e) {
                    throw new InvalidCoordinateException("2nd literal is not a number.");
                }
            }
            else {
                throw new InvalidCoordinateException("Coordinate is not a 3 literals valid coordinate.");
            }
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    //METHODS
    @Override
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    //TODO: Override anche di hashcode
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardCoordinate) {
            BoardCoordinate param = (BoardCoordinate) obj;
            return ((this.getRow() == param.getRow()) && (this.getColumn() == param.getColumn()));
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    /**
     * This method checks if a coordinate has at least two adjacent pieces
     *
     * @param board
     * @return true if the coordinate has at least two adjacent pieces, false otherwise
     */
    protected boolean hasAtLeastTwoAdjacentPieces(Board board) {
        boolean down, up, right, left;
        //First checks the limits of the board, then if row and column are not edges checks all 4 directions
        if (row == 0 && column == 0){
            down = !board.getMatrix()[row + 1][column].equals(Pieces.NONE);
            right = !board.getMatrix()[row][column + 1].equals(Pieces.NONE);
            return down && right;
        }
        if (row == board.getDIMENSION()-1 && column == 0){
            up = !board.getMatrix()[row - 1][column].equals(Pieces.NONE);
            right = !board.getMatrix()[row][column + 1].equals(Pieces.NONE);
            return up && right;
        }
        if (column == board.getDIMENSION()-1 && row == 0){
            down = !board.getMatrix()[row + 1][column].equals(Pieces.NONE);
            left = !board.getMatrix()[row][column - 1].equals(Pieces.NONE);
            return left && down;
        }
        if (row == board.getDIMENSION()-1 && column == board.getDIMENSION() -1){
            up = !board.getMatrix()[row - 1][column].equals(Pieces.NONE);
            left = !board.getMatrix()[row][column - 1].equals(Pieces.NONE);
            return up && left;
        }
        if (row == board.getDIMENSION() - 1) {
            up = !board.getMatrix()[row - 1][column].equals(Pieces.NONE);
            right = !board.getMatrix()[row][column + 1].equals(Pieces.NONE);
            left = !board.getMatrix()[row][column - 1].equals(Pieces.NONE);
            return (up && right) || (up && left) || (right && left);
        }
        if (row == 0) {
            down = !board.getMatrix()[row + 1][column].equals(Pieces.NONE);
            right = !board.getMatrix()[row][column + 1].equals(Pieces.NONE);
            left = !board.getMatrix()[row][column - 1].equals(Pieces.NONE);
            return (down && right) || (down && left) || (right && left);
        }
        if (column == 0) {
            down = !board.getMatrix()[row + 1][column].equals(Pieces.NONE);
            up = !board.getMatrix()[row - 1][column].equals(Pieces.NONE);
            right = !board.getMatrix()[row][column + 1].equals(Pieces.NONE);
            return (down && up) || (down && right) || (right && up);
        }
        if (column == board.getDIMENSION() - 1) {
            down = !board.getMatrix()[row + 1][column].equals(Pieces.NONE);
            up = !board.getMatrix()[row - 1][column].equals(Pieces.NONE);
            left = !board.getMatrix()[row][column - 1].equals(Pieces.NONE);
            return (down && up) || (down && left) || (left && up);
        }

        down = !board.getMatrix()[row + 1][column].equals(Pieces.NONE);
        up = !board.getMatrix()[row - 1][column].equals(Pieces.NONE);
        right = !board.getMatrix()[row][column + 1].equals(Pieces.NONE);
        left = !board.getMatrix()[row][column - 1].equals(Pieces.NONE);
        return (down && up) || (down && right) || (down && left) || (up && right) || (left && up) || (left && right);
    }
}
