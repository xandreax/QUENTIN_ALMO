package entities;

import exceptions.InvalidCoordinateException;
import utils.CharUtils;
import utils.IntUtils;
import utils.StringUtils;

public class BoardCoordinate implements Coordinate2D {
    //FIELDS
    protected int row;
    protected int column;

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

    public BoardCoordinate(char x, char y) throws InvalidCoordinateException {
        if (CharUtils.isValidCharForCoordinate(x) && CharUtils.isValidCharForCoordinate(y)) {
            this.row = CharUtils.mapInputCharToInputInt(x);
            this.column = CharUtils.mapInputCharToInputInt(y);
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    public BoardCoordinate(char x, int y) throws InvalidCoordinateException {
        if (CharUtils.isValidCharForCoordinate(x) && IntUtils.isValidIntForBoardCoordinate(y)) {
            this.row = CharUtils.mapInputCharToInputInt(x);
            this.column = y;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    public BoardCoordinate(int x, char y) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForBoardCoordinate(x) && CharUtils.isValidCharForCoordinate(y)) {
            this.row = x;
            this.column = CharUtils.mapInputCharToInputInt(y);
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
}
