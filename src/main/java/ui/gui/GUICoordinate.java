package ui.gui;

import entities.Coordinate2D;
import exceptions.InvalidCoordinateException;
import utils.IntUtils;

/**
 * This class represents a single coordinate defined as a screen pixel.
 *
 */
public class GUICoordinate implements Coordinate2D {
    //FIELDS
    private final int x;
    private final int y;

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
}

