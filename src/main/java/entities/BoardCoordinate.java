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

    public BoardCoordinate getLeft() throws InvalidCoordinateException {
        return new BoardCoordinate(row, column-1);
    }

    public BoardCoordinate getRight() throws InvalidCoordinateException {
        return new BoardCoordinate(row, column+1);
    }

    public BoardCoordinate getUp() throws InvalidCoordinateException {
        return new BoardCoordinate(row -1, column);
    }

    public BoardCoordinate getDown() throws InvalidCoordinateException {
        return new BoardCoordinate(row +1, column);
    }

    public BoardCoordinate getUpRight() throws InvalidCoordinateException {
        return new BoardCoordinate(row-1, column+1);
    }

    public BoardCoordinate getUpLeft() throws InvalidCoordinateException {
        return new BoardCoordinate(row-1, column-1);
    }

    public BoardCoordinate getDownLeft() throws InvalidCoordinateException {
        return new BoardCoordinate(row+1, column-1);
    }

    public BoardCoordinate getDownRight() throws InvalidCoordinateException {
        return new BoardCoordinate(row+1, column+1);
    }

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
     * @param board: the board
     * @return true if the coordinate has at least two adjacent pieces, false otherwise
     */

    //TODO: ho provato a refactorizzare così, può andar bene?
    public boolean hasAtLeastTwoAdjacentPieces(Board board) {
        boolean down = false, up = false, right = false, left = false;
        //First checks the limits of the board, then if row and column are not edges checks all 4 directions
        try {
            down = !board.getPieceByCoordinate(getDown()).equals(Pieces.NONE);
        }
        catch (InvalidCoordinateException ignored){}
        try {
            up = !board.getPieceByCoordinate(getUp()).equals(Pieces.NONE);
        }
        catch (InvalidCoordinateException ignored){}
        try {
            right = !board.getPieceByCoordinate(getRight()).equals(Pieces.NONE);
        }
        catch (InvalidCoordinateException ignored){}
        try{
            left = !board.getPieceByCoordinate(getLeft()).equals(Pieces.NONE);
        }catch (InvalidCoordinateException ignored){}
        return (down && up) || (down && right) || (down && left) || (up && right) || (left && up) || (left && right);
    }
}
