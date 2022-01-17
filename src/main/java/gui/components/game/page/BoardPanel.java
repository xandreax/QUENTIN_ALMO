package gui.components.game.page;

import entities.Coordinate2D;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import gui.GUICoordinate;
import gui.components.GameFrame;
import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JLayeredPane {
    public static final int TWO = 2;
    public static final int DIMENSION = 13;
    public static final int PAD_1 = 14;
    public static final int PAD_2 = 12;
    public static final int RATIO_NUM = 9;
    public static final int RATIO_DEN = 10;
    public static final double PIECE_UNIT_DENOM = 1.5;
    public static final int THREE = 3;
    public static final int SIX = 6;
    public static final int NINE = 9;
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
        this.side = RATIO_NUM * gameFrame.getSide() / RATIO_DEN;
        this.setMinimumSize(new Dimension(side, side));
        this.setSize(new Dimension(side, side));
        this.setPreferredSize(new Dimension(side, side));
        this.setOpaque(true);
        this.setVisible(true);
        this.setLayout(null);

        this.boardSide = RATIO_NUM*this.side/RATIO_DEN;
        this.cellSide = this.boardSide / PAD_1;      //int of division (ROUNDING). 14 not 12 to have some padding then...
        this.innerBoardSide = this.cellSide * PAD_2;
        this.padding = (this.side - this.boardSide) / TWO;
        this.innerPadding = (this.boardSide - this.innerBoardSide) / TWO;
        this.pieceUnit = (int) (this.cellSide / PIECE_UNIT_DENOM);
        this.guiCoordinates = new GUICoordinate[DIMENSION][DIMENSION];

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

        g.setColor(Color.WHITE);
        //east
        g.fillPolygon(new int[]{this.getWidth(), this.getWidth(), this.getWidth()/TWO},
                new int[]{0, this.getHeight(), this.getHeight()/ TWO},
                THREE);
        //west
        g.fillPolygon(new int[]{0, 0, this.getWidth()/TWO},
                new int[]{0, this.getHeight(), this.getHeight()/TWO},
                THREE);
        g.setColor(Color.BLACK);
        //north
        g.fillPolygon(new int[]{0, this.getWidth()/TWO, this.getWidth()},
                new int[]{0, this.getHeight()/TWO, 0},
                THREE);
        //south
        g.fillPolygon(new int[]{0, this.getWidth()/TWO, this.getWidth()},
                new int[]{this.getHeight(), this.getHeight()/TWO, this.getHeight()},
                THREE);

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
                    button.setBounds(this.getGuiCoordinates()[row][col].getRow() - (this.cellSide/TWO), this.getGuiCoordinates()[row][col].getColumn() - (this.cellSide/TWO), this.cellSide, this.cellSide);
                    button.addMouseListener(new HoverButtonMouseListener(this.currentGameFrame, row, col));
                    this.add(button);
                }
            }
        }
    }

    //AUXILIARY METHODS
    private static void drawLittleRectsForHelp(Graphics graphics, GUICoordinate[][] guiCoordinates) {
        int lenRects = SIX;
        graphics.fillRect(guiCoordinates[THREE][THREE].getRow()-(lenRects/TWO), guiCoordinates[THREE][THREE].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[SIX][THREE].getRow()-(lenRects/TWO), guiCoordinates[SIX][THREE].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[NINE][THREE].getRow()-(lenRects/TWO), guiCoordinates[NINE][THREE].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[THREE][SIX].getRow()-(lenRects/TWO), guiCoordinates[THREE][SIX].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[SIX][SIX].getRow()-(lenRects/TWO), guiCoordinates[SIX][SIX].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[NINE][SIX].getRow()-(lenRects/TWO), guiCoordinates[NINE][SIX].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[THREE][NINE].getRow()-(lenRects/TWO), guiCoordinates[THREE][NINE].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[SIX][NINE].getRow()-(lenRects/TWO), guiCoordinates[SIX][NINE].getColumn()-(lenRects/TWO), lenRects, lenRects);
        graphics.fillRect(guiCoordinates[NINE][NINE].getRow()-(lenRects/TWO), guiCoordinates[NINE][NINE].getColumn()-(lenRects/TWO), lenRects, lenRects);
    }

    private static void drawPiece(Graphics g, Coordinate2D coordinate, Pieces piece, int pieceUnit) {
        if (piece == Pieces.BLACK) g.setColor(Color.BLACK);
        else if (piece == Pieces.WHITE) g.setColor(Color.WHITE);
        else return;
        g.fillOval(coordinate.getRow()-pieceUnit/TWO, coordinate.getColumn()-pieceUnit/TWO, pieceUnit, pieceUnit);
    }
}
