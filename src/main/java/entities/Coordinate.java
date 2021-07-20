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

    public Coordinate(String s) throws InvalidCoordinateException {
        if (StringUtils.isCoordinate(s)) {
            String temp = s.toUpperCase();
            try {
                int parsed = Integer.parseInt(temp.substring(0, 1));
                this.x = parsed;
            }
            catch (NumberFormatException e) {
                this.x = CharUtils.mapInputCharToInputInt(temp.charAt(0));
            }
            try {
                int parsed = Integer.parseInt(temp.substring(1));
                this.x = parsed;
            }
            catch (NumberFormatException e) {
                this.x = CharUtils.mapInputCharToInputInt(temp.charAt(1));
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
}
