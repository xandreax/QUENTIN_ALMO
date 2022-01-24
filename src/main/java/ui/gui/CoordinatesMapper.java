package ui.gui;

import entities.Board;

public class CoordinatesMapper {
    //FIELDS
    private Board board;
    private GUICoordinate[][] mapper;

    //CONSTRUCTORS
    public CoordinatesMapper(Board board) {
        this.board = board;
        this.mapper = new GUICoordinate[board.getDIMENSION()][board.getDIMENSION()];
    }

    //METHODS
    public GUICoordinate pixelAt(int row, int col) {
        return this.mapper[row][col];
    }

    public void setPixelAt(int row, int col, GUICoordinate guiCoordinate) {
        this.mapper[row][col] = guiCoordinate;
    }
}
