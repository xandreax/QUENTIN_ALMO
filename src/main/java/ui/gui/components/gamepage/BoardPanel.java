package ui.gui.components.gamepage;

import entities.Board;
import entities.BoardCoordinate;
import entities.Coordinate2D;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import ui.gui.GUICoordinate;
import ui.gui.components.GameFrame;

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
    private final GameFrame currentGameFrame;
    private final Board board;
    private final int boardSide;
    private final int padding;
    private final int innerBoardSide;
    private final int innerPadding;
    private final int cellSide;
    private final int pieceUnit;
    private final GUICoordinate[][] guiCoordinates;

    //CONSTRUCTORS
    public BoardPanel(GameFrame gameFrame) {
        super();
        this.currentGameFrame = gameFrame;
        int side = RATIO_NUM * gameFrame.getSide() / RATIO_DEN;
        this.setMinimumSize(new Dimension(side, side));
        this.setSize(new Dimension(side, side));
        this.setPreferredSize(new Dimension(side, side));
        this.setOpaque(true);
        this.setVisible(true);
        this.setLayout(null);

        this.boardSide = RATIO_NUM* side /RATIO_DEN;
        this.cellSide = this.boardSide / PAD_1;      //int of division (ROUNDING). 14 not 12 to have some padding then...
        this.innerBoardSide = this.cellSide * PAD_2;
        this.padding = (side - this.boardSide) / TWO;
        this.innerPadding = (this.boardSide - this.innerBoardSide) / TWO;
        this.pieceUnit = (int) (this.cellSide / PIECE_UNIT_DENOM);
        this.guiCoordinates = new GUICoordinate[DIMENSION][DIMENSION];
        this.board = gameFrame.getBoard();

        this.setAndCreateHoverButtons();
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
        for (int i = 0; i < board.getDIMENSION(); i++) {
            g.drawLine(paddingSum +i*this.cellSide, paddingSum, paddingSum +i*this.cellSide, paddingSum +this.innerBoardSide);
            g.drawLine(paddingSum, paddingSum +i*this.cellSide, paddingSum +this.innerBoardSide, paddingSum +i*this.cellSide);
        }

        // draw little rectangles
        drawLittleRectsForHelp(g, this.guiCoordinates);

        // draw pieces of the board
        for (int i = 0; i < board.getDIMENSION(); i++) {
            for (int j = 0; j < board.getDIMENSION(); j++) {
                drawPiece(g, this.guiCoordinates[i][j], board.getMatrix()[i][j], this.pieceUnit);
            }
        }
    }

    public void setAndCreateHoverButtons() {
        int paddingSum = this.padding + this.innerPadding;
        for (int row = 0; row < board.getDIMENSION(); row++) {
            for (int col = 0; col < board.getDIMENSION(); col++) {
                try {
                    BoardCoordinate bc = new BoardCoordinate(row, col);
                    guiCoordinates[row][col] = new GUICoordinate(paddingSum +col * this.cellSide, paddingSum +row * this.cellSide);
                    if (board.getPieceByCoordinate(bc) == Pieces.NONE) {
                        HoverPieceButton button = new HoverPieceButton();
                        button.setBounds(this.getGuiCoordinates()[row][col].getRow() - (this.cellSide /2), this.getGuiCoordinates()[row][col].getColumn() - (this.cellSide /2), this.cellSide, this.cellSide);
                        button.addMouseListener(new HoverButtonMouseListener(this.currentGameFrame, row, col));
                        this.add(button);
                    }
                }
                catch (InvalidCoordinateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateHoverButtons() throws InvalidCoordinateException {
        this.removeAll();
        for (int row = 0; row < board.getDIMENSION(); row++) {
            for (int col = 0; col < board.getDIMENSION(); col++) {
                BoardCoordinate bc = new BoardCoordinate(row,col);
                if (board.getPieceByCoordinate(bc) == Pieces.NONE) {
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
