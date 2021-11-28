package gui.components;

import entities.Board;
import entities.BoardCoordinate;
import entities.Coordinate2D;
import entities.Pieces;
import exceptions.InvalidCoordinateException;
import gui.GUICoordinate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardPanel extends JLayeredPane {
    //FIELDS
    protected Board board;
    protected GameFrame gameFrame;
    protected int squareUnit;
    protected int pieceUnit;
    protected int padding;
    protected int dimensionWithoutPadding;
    protected int dimensionWithPadding;
    protected int size;
    protected int boardSide;
    protected int boardPadding;
    protected GUICoordinate[][] guiCoordinates;
    protected Color BOARD_COLOR = new Color(0, 105, 9);

    //CONSTRUCTORS
    public BoardPanel(GameFrame gameFrame, Board board, int side) {
        super();
        this.setMinimumSize(new Dimension(side, side));
        this.setSize(new Dimension(side, side));
        this.setPreferredSize(new Dimension(side, side));
        this.setOpaque(true);
        this.setVisible(true);
        this.setLayout(null);
        this.board = board;

        this.gameFrame = gameFrame;
        this.size = side;
        this.boardSide = 7*side/8;
        this.boardPadding = (side - boardSide) / 2;

        this.squareUnit = this.boardSide / 14;      //int of division (ROUNDING). 14 not 12 to have some padding then...
        this.dimensionWithoutPadding = this.squareUnit*12;      //dimensionWithoutPadding <= boardSide
        this.padding = (side - this.dimensionWithoutPadding) / 2;
        this.dimensionWithPadding = this.dimensionWithoutPadding+2*this.padding;
        this.pieceUnit = (int) (this.squareUnit / 1.5);
        this.guiCoordinates = new GUICoordinate[13][13];

        // set guiCoordinates and creates hover buttons
        this.setAndCreateHoverButtons();
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
        g.setColor(this.BOARD_COLOR);
        g.fillRect(this.boardPadding, this.boardPadding, this.boardSide, this.boardSide);

        // draw board lines and set the guiCoordinates
        g.setColor(Color.BLACK);
        int paddingSum = this.padding + this.boardPadding;
        for (int i = 0; i < this.getBoard().getDIMENSION(); i++) {
            g.drawLine(this.padding+i*this.squareUnit, this.padding, this.padding+i*this.squareUnit, this.padding+this.dimensionWithoutPadding);
            g.drawLine(this.padding, this.padding+i*this.squareUnit, this.padding+this.dimensionWithoutPadding, this.padding+i*this.squareUnit);
        }

        // draw little rectangles
        drawLittleRectsForHelp(g, this.guiCoordinates);

        // draw pieces of the board
        for (int i = 0; i < this.getBoard().getDIMENSION(); i++) {
            for (int j = 0; j < this.getBoard().getDIMENSION(); j++) {
                drawPiece(g, this.guiCoordinates[i][j], this.board.getMatrix()[i][j], this.pieceUnit);
            }
        }
    }

    public void setAndCreateHoverButtons() {
        for (int i = 0; i < this.getBoard().getDIMENSION(); i++) {
            for (int j = 0; j < this.getBoard().getDIMENSION(); j++) {
                try {
                    guiCoordinates[i][j] = new GUICoordinate(this.padding+i * this.squareUnit, this.padding+j * this.squareUnit);
                }
                catch (InvalidCoordinateException e) {
                    e.printStackTrace();
                }
                if (this.getBoard().getMatrix()[i][j] == Pieces.NONE) {
                    HoverPieceButton button = new HoverPieceButton();
                    button.setBounds(this.getGuiCoordinates()[i][j].getX() - (this.squareUnit/2), this.getGuiCoordinates()[i][j].getY() - (this.squareUnit/2), this.squareUnit, this.squareUnit);
                    int finalJ = j;
                    int finalI = i;
                    button.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                System.out.println("Pressed "+finalI+"x"+finalJ);
                                BoardPanel.this.gameFrame.handleMove(new BoardCoordinate(finalI, finalJ));
                            } catch (InvalidCoordinateException ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) { }

                        @Override
                        public void mouseReleased(MouseEvent e) { }

                        @Override
                        public void mouseEntered(MouseEvent e) { }

                        @Override
                        public void mouseExited(MouseEvent e) { }
                    });
                    this.add(button);
                }
            }
        }
    }

    public void updateHoverButtons() {
        this.removeAll();
        for (int i = 0; i < this.getBoard().getDIMENSION(); i++) {
            for (int j = 0; j < this.getBoard().getDIMENSION(); j++) {
                if (this.getBoard().getMatrix()[i][j] == Pieces.NONE) {
                    HoverPieceButton button = new HoverPieceButton();
                    button.setBounds(this.getGuiCoordinates()[i][j].getX() - (this.squareUnit/2), this.getGuiCoordinates()[i][j].getY() - (this.squareUnit/2), this.squareUnit, this.squareUnit);
                    int finalJ = j;
                    int finalI = i;
                    button.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                System.out.println("Pressed "+finalI+"x"+finalJ);
                                BoardPanel.this.gameFrame.handleMove(new BoardCoordinate(finalI, finalJ));
                            } catch (InvalidCoordinateException ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) { }

                        @Override
                        public void mouseReleased(MouseEvent e) { }

                        @Override
                        public void mouseEntered(MouseEvent e) { }

                        @Override
                        public void mouseExited(MouseEvent e) { }
                    });
                    this.add(button);
                }
            }
        }
    }

    //AUXILIARY METHODS
    private static void drawLittleRectsForHelp(Graphics graphics, GUICoordinate[][] guiCoordinates) {
        int lenRects = 6;
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
