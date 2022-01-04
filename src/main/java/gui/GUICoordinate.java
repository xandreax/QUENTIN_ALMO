package gui;

import entities.Coordinate2D;
import exceptions.InvalidCoordinateException;
import utils.coordinate.IntUtils;

public class GUICoordinate implements Coordinate2D {
    //FIELDS
    protected int x;
    protected int y;

    //CONSTRUCTORS
    public GUICoordinate(int x, int y) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForGUICoordinate(x) && IntUtils.isValidIntForGUICoordinate(y)) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    //METHODS
    @Override
    public int getRow() {
        return this.x;
    }

    @Override
    public int getColumn() {
        return this.y;
    }

    public void setX(int x) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForGUICoordinate(x)) {
            this.x = x;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }

    public void setY(int y) throws InvalidCoordinateException {
        if (IntUtils.isValidIntForBoardCoordinate(y)) {
            this.y = y;
        }
        else {
            throw new InvalidCoordinateException();
        }
    }
}

