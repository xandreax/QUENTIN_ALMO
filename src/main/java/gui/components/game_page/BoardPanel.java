package gui.components.game_page;

import entities.Coordinate2D;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import gui.GUICoordinate;
import gui.components.GameFrame;
import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JLayeredPane {
    //FIELDS
    protected GameFrame currentGameFrame;
    protected int side;
    protected int boardSide;
    protected int padding;
    protected int innerBoardSide;
    protected int innerPadding;
    protected int cellSide;
    protected int pieceUnit;
    protected GUICoordinate[][] guiCoordinates;

    //CONSTRUCTORS
    public BoardPanel(GameFrame gameFrame) {
        super();
        this.currentGameFrame = gameFrame;
        this.side = 9 * gameFrame.getSide() / 10;
        //this.side = gameFrame.getSide();
        this.setMinimumSize(new Dimension(side, side));
        this.setSize(new Dimension(side, side));
        this.setPreferredSize(new Dimension(side, side));
        this.setOpaque(true);
        this.setVisible(true);
        this.setLayout(null);


        this.boardSide = 9*this.side/10;
        this.cellSide = this.boardSide / 14;      //int of division (ROUNDING). 14 not 12 to have some padding then...
        this.innerBoardSide = this.cellSide *12;      //dimensionWithoutPadding <= boardSide
        this.padding = (this.side - this.boardSide) / 2;
        this.innerPadding = (this.boardSide - this.innerBoardSide) / 2;
        this.pieceUnit = (int) (this.cellSide / 1.5);
        this.guiCoordinates = new GUICoordinate[13][13];

        // set guiCoordinates and creates hover buttons
        this.setAndCreateHoverButtons();
    }

    //METHODS

    public GameFrame getCurrentGameFrame() {
        return currentGameFrame;
    }

    public GUICoordinate[][] getGuiCoordinates() {
        return guiCoordinates;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2D = (Graphics2D) g;

        g.setColor(Color.WHITE);
        //east
        g.fillPolygon(new int[]{this.getWidth(), this.getWidth(), this.getWidth()/2},
                new int[]{0, this.getHeight(), this.getHeight()/2},
                3);
        //west
        g.fillPolygon(new int[]{0, 0, this.getWidth()/2},
                new int[]{0, this.getHeight(), this.getHeight()/2},
                3);
        g.setColor(Color.BLACK);
        //north
        g.fillPolygon(new int[]{0, this.getWidth()/2, this.getWidth()},
                new int[]{0, this.getHeight()/2, 0},
                3);
        //south
        g.fillPolygon(new int[]{0, this.getWidth()/2, this.getWidth()},
                new int[]{this.getHeight(), this.getHeight()/2, this.getHeight()},
                3);

        // draw board green background
        g.setColor(GameFrame.BOARD_COLOR);
        g.fillRect(this.padding, this.padding, this.boardSide, this.boardSide);

        // draw board lines
        int paddingSum = this.padding + this.innerPadding;
        g.setColor(Color.BLACK);
        for (int i = 0; i < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); i++) {
            g.drawLine(paddingSum +i*this.cellSide, paddingSum, paddingSum +i*this.cellSide, paddingSum +this.innerBoardSide);
            g.drawLine(paddingSum, paddingSum +i*this.cellSide, paddingSum +this.innerBoardSide, paddingSum +i*this.cellSide);
        }

        // draw little rectangles
        drawLittleRectsForHelp(g, this.guiCoordinates);

        // draw pieces of the board
        for (int i = 0; i < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); i++) {
            for (int j = 0; j < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); j++) {
                drawPiece(g, this.guiCoordinates[i][j], this.getCurrentGameFrame().getGame().getBoard().getMatrix()[i][j], this.pieceUnit);
            }
        }
    }

    public void setAndCreateHoverButtons() {
        int paddingSum = this.padding + this.innerPadding;
        for (int row = 0; row < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); row++) {
            for (int col = 0; col < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); col++) {
                try {
                    guiCoordinates[row][col] = new GUICoordinate(paddingSum +col * this.cellSide, paddingSum +row * this.cellSide);
                }
                catch (InvalidCoordinateException e) {
                    e.printStackTrace();
                }
                if (this.getCurrentGameFrame().getGame().getBoard().getMatrix()[row][col] == Pieces.NONE) {
                    HoverPieceButton button = new HoverPieceButton();
                    button.setBounds(this.getGuiCoordinates()[row][col].getRow() - (this.cellSide /2), this.getGuiCoordinates()[row][col].getColumn() - (this.cellSide /2), this.cellSide, this.cellSide);
                    button.addMouseListener(new HoverButtonMouseListener(this.currentGameFrame, row, col));
                    this.add(button);
                }
            }
        }
    }

    public void updateHoverButtons() {
        this.removeAll();
        for (int row = 0; row < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); row++) {
            for (int col = 0; col < this.getCurrentGameFrame().getGame().getBoard().getDIMENSION(); col++) {
                if (this.getCurrentGameFrame().getGame().getBoard().getMatrix()[row][col] == Pieces.NONE) {
                    HoverPieceButton button = new HoverPieceButton();
                    button.setBounds(this.getGuiCoordinates()[row][col].getRow() - (this.cellSide/2), this.getGuiCoordinates()[row][col].getColumn() - (this.cellSide/2), this.cellSide, this.cellSide);
                    button.addMouseListener(new HoverButtonMouseListener(this.currentGameFrame, row, col));
                    this.add(button);
                }
            }
        }
    }

    //AUXILIARY METHODS
    private static void drawLittleRectsForHelp(Graphics graphics, GUICoordinate[][] guiCoordinates) {
        int lenRects = 6;
        graphics.fillRect(guiCoordinates[3][3].getRow()-(lenRects/2), guiCoordinates[3][3].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[6][3].getRow()-(lenRects/2), guiCoordinates[6][3].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[9][3].getRow()-(lenRects/2), guiCoordinates[9][3].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[3][6].getRow()-(lenRects/2), guiCoordinates[3][6].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[6][6].getRow()-(lenRects/2), guiCoordinates[6][6].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[9][6].getRow()-(lenRects/2), guiCoordinates[9][6].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[3][9].getRow()-(lenRects/2), guiCoordinates[3][9].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[6][9].getRow()-(lenRects/2), guiCoordinates[6][9].getColumn()-(lenRects/2), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[9][9].getRow()-(lenRects/2), guiCoordinates[9][9].getColumn()-(lenRects/2), lenRects, lenRects);
    }

    private static void drawPiece(Graphics g, Coordinate2D coordinate, Pieces piece, int pieceUnit) {
        if (piece == Pieces.BLACK) g.setColor(Color.BLACK);
        else if (piece == Pieces.WHITE) g.setColor(Color.WHITE);
        else return;
        g.fillOval(coordinate.getRow()-pieceUnit/2, coordinate.getColumn()-pieceUnit/2, pieceUnit, pieceUnit);
    }
}
