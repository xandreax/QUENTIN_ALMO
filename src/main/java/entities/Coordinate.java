package entities;

import exceptions.InvalidCoordinateException;
import utils.CharUtils;
import utils.IntUtils;
import utils.StringUtils;

public class Coordinate {
    //FIELDS
    protected int x;    //column
    protected int y;    //row

    //CONSTRUCTORS
    public Coordinate(int x, int y) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForCoordinate(x) && IntUtils.isValidIntForCoordinate(y)) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    public Coordinate(char x, char y) throws InvalidCoordinateException {
        if (CharUtils.isValidCharForCoordinate(x) && CharUtils.isValidCharForCoordinate(y)) {
            this.x = CharUtils.mapInputCharToInputInt(x);
            this.y = CharUtils.mapInputCharToInputInt(y);
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    public Coordinate(char x, int y) throws InvalidCoordinateException {
        if (CharUtils.isValidCharForCoordinate(x) && IntUtils.isValidIntForCoordinate(y)) {
            this.x = CharUtils.mapInputCharToInputInt(x);
            this.y = y;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    public Coordinate(int x, char y) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForCoordinate(x) && CharUtils.isValidCharForCoordinate(y)) {
            this.x = x;
            this.y = CharUtils.mapInputCharToInputInt(y);
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    //Format: literal from a to m, and right after a number from 0 to 12
    public Coordinate(String s) throws InvalidCoordinateException {
        String temp = s.toUpperCase();
        if (s.length() == 2) {
            if (StringUtils.is2LiteralsValidCoordinate(s)) {
                try {
                    this.x = CharUtils.mapInputCharToInputInt(temp.charAt(0));
                }
                catch (IndexOutOfBoundsException e) {
                    throw new InvalidCoordinateException("1st literal is char that exceed the correct interval [a-m].");
                }
                try {
                    int parsed = Integer.parseInt(temp.substring(1));
                    this.y = parsed;
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
                    this.x = CharUtils.mapInputCharToInputInt(temp.charAt(0));
                }
                catch (IndexOutOfBoundsException e) {
                    throw new InvalidCoordinateException("1st literal is char that exceed the correct interval [a-m].");
                }
                try {
                    int parsed = Integer.parseInt(temp.substring(1));
                    this.y = parsed;
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
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate param = (Coordinate) obj;
            return ((this.getX() == param.getX()) && (this.getY() == param.getY()));
        }
        else {
            return false;
        }
    }
}
