package gui;

import entities.Coordinate2D;
import exceptions.InvalidCoordinateException;
import utils.coordinate.IntUtils;

/**
 * This class represents a single coordinate defined as a screen pixel.
 *
 */
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
        return this.y;
    }

    @Override
    public int getColumn() {
        return this.x;
    }
}

