package gui.components;

import entities.Board;
import entities.Coordinate2D;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import gui.GUICoordinate;
import javax.swing.*;
import java.awt.*;

public class InnerBoardPanel extends JLayeredPane {
    //FIELDS
    protected Board board;
    protected int squareUnit;
    protected int pieceUnit;
    protected int padding;
    protected int dimensionWithoutPadding;
    protected int dimensionWithPadding;
    protected GUICoordinate[][] guiCoordinates;

    //CONSTRUCTORS
    public InnerBoardPanel(Board board, int dimensionCandidate) {
        this.board = board;
        this.squareUnit = dimensionCandidate / 12;      //int of division (ROUNDING)
        this.dimensionWithoutPadding = this.squareUnit*12;      //dimensionWithoutPadding <= dimensionCandidate
        this.padding = dimensionCandidate / 15;
        this.dimensionWithPadding = dimensionWithoutPadding+2*this.padding;
        this.pieceUnit = (int) (this.squareUnit / 1.5);
        this.guiCoordinates = new GUICoordinate[13][13];
        this.setLayout(null);
    }

    //METHODS
    public GUICoordinate[][] getGuiCoordinates() {
        return guiCoordinates;
    }

    public int getDimensionWithoutPadding() {
        return this.dimensionWithoutPadding;
    }

    public int getDimensionWithPadding() {
        return this.dimensionWithPadding;
    }

    public int getSquareUnit() {
        return this.squareUnit;
    }

    public int getPieceUnit() {
        return this.pieceUnit;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2D = (Graphics2D) g;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 13; i++) {
            g.drawLine(this.padding+i*this.squareUnit, this.padding, this.padding+i*this.squareUnit, this.padding+this.dimensionWithoutPadding);
            g.drawLine(this.padding, this.padding+i*this.squareUnit, this.padding+this.dimensionWithoutPadding, this.padding+i*this.squareUnit);
            for (int j = 0; j < 13; j++) {
                try {
                    guiCoordinates[i][j] = new GUICoordinate(padding+i * this.squareUnit, padding+j * this.squareUnit);
                }
                catch (InvalidCoordinateException e) {
                    e.printStackTrace();
                }
            }
        }
        drawLittleRectsForHelp(g, this.guiCoordinates);
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                drawPiece(g, this.guiCoordinates[i][j], this.board.getMatrix()[i][j], this.pieceUnit);
            }
        }
        //setInvisibleButtonForHoverEffect(this, this.invisibleButtonSideLength, this.invisibleButtons, this.guiCoordinates);
        //this.renderInvisibleButtons();
    }

    //AUXILIARY METHODS
    private static void drawLittleRectsForHelp(Graphics graphics, GUICoordinate[][] guiCoordinates) {
        int lenRects = 4;
        graphics.fillRect(guiCoordinates[3][3].getX()-(lenRects/2), guiCoordinates[3][3].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[6][3].getX()-(lenRects/2), guiCoordinates[6][3].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[9][3].getX()-(lenRects/2), guiCoordinates[9][3].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[3][6].getX()-(lenRects/2), guiCoordinates[3][6].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[6][6].getX()-(lenRects/2), guiCoordinates[6][6].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[9][6].getX()-(lenRects/2), guiCoordinates[9][6].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[3][9].getX()-(lenRects/2), guiCoordinates[3][9].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[6][9].getX()-(lenRects/2), guiCoordinates[6][9].getY()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[9][9].getX()-(lenRects/2), guiCoordinates[9][9].getY()-(lenRects/2), lenRects, lenRects);
    }

    private static void drawPiece(Graphics g, Coordinate2D coordinate, Pieces piece, int pieceUnit) {
        if (piece == Pieces.BLACK) g.setColor(Color.BLACK);
        else if (piece == Pieces.WHITE) g.setColor(Color.WHITE);
        else return;
        g.fillOval(coordinate.getX()-pieceUnit/2, coordinate.getY()-pieceUnit/2, pieceUnit, pieceUnit);
    }
}
