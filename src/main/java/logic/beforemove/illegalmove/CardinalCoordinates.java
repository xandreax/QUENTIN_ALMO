package logic.beforemove.illegalmove;

import entities.BoardCoordinate;
import exceptions.InvalidCoordinateException;


public class CardinalCoordinates {
    protected BoardCoordinate southWestCell;
    protected BoardCoordinate northWestCell;
    protected BoardCoordinate northEastCell;
    protected BoardCoordinate southEastCell;
    protected BoardCoordinate northCell;
    protected BoardCoordinate southCell;
    protected BoardCoordinate eastCell;
    protected BoardCoordinate westCell;

    /**
     * This constructor defines all the possible cardinal cells of a given coordinate on the board.
     *
     * @param coordinate
     * @param boardDimension
     * @throws InvalidCoordinateException
     */
    //TODO: Refactoring o no di questa classe?
    public CardinalCoordinates(BoardCoordinate coordinate, int boardDimension) throws InvalidCoordinateException {
        if(coordinate.getRow() != 0){
            northCell = new BoardCoordinate(coordinate.getRow()-1, coordinate.getColumn());
            if(coordinate.getColumn() != 0) {
                northWestCell = new BoardCoordinate(coordinate.getRow() - 1, coordinate.getColumn() - 1);
                westCell = new BoardCoordinate(coordinate.getRow(), coordinate.getColumn() - 1);
            }
            if(coordinate.getColumn() != boardDimension - 1) {
                northEastCell = new BoardCoordinate(coordinate.getRow() - 1, coordinate.getColumn() + 1);
                eastCell = new BoardCoordinate(coordinate.getRow(), coordinate.getColumn() + 1);
            }
        }
        if(coordinate.getRow() != boardDimension - 1) {
            southCell = new BoardCoordinate(coordinate.getRow() + 1, coordinate.getColumn());
            if (coordinate.getColumn() != 0) {
                southWestCell = new BoardCoordinate(coordinate.getRow() + 1, coordinate.getColumn() - 1);
                if(westCell == null)
                    westCell = new BoardCoordinate(coordinate.getRow(), coordinate.getColumn() - 1);
            }
            if (coordinate.getColumn() != boardDimension - 1) {
                southEastCell = new BoardCoordinate(coordinate.getRow() + 1, coordinate.getColumn() + 1);
                if(eastCell == null)
                    eastCell = new BoardCoordinate(coordinate.getRow(), coordinate.getColumn() + 1);
            }
        }
    }
}
