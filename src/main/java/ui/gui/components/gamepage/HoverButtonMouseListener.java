package ui.gui.components.gamepage;

import entities.BoardCoordinate;
import exceptions.InvalidCoordinateException;
import ui.gui.components.GameFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverButtonMouseListener implements MouseListener {
    //FIELDS
    protected GameFrame currentGameFrame;
    protected int row;
    protected int col;

    //CONSTRUCTORS
    public HoverButtonMouseListener(GameFrame gameFrame, int row, int col) {
        this.currentGameFrame = gameFrame;
        this.row = row;
        this.col = col;
    }

    //METHODS
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            this.currentGameFrame.handleMove(new BoardCoordinate(this.row, this.col));
        } catch (InvalidCoordinateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
