package gui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GenericButton extends JButton {
    //FIELDS
    protected Color BACKGROUND_COLOR;
    protected Color FONT_COLOR;
    protected Color BORDER_COLOR;
    protected Color BACKGROUND_HOVER_COLOR;
    protected Color BORDER_HOVER_COLOR;
    protected Color FONT_HOVER_COLOR;
    protected int THICKNESS;
    protected boolean ROUNDNESS;

    //CONSTRUCTORS
    public GenericButton(String text, GameFrame gf) {
        super();
        this.THICKNESS = 2;
        this.ROUNDNESS = true;
        this.BACKGROUND_COLOR = new Color(132, 0, 0);
        this.FONT_COLOR = Color.WHITE;
        this.BORDER_COLOR = Color.WHITE;
        this.BACKGROUND_HOVER_COLOR = Color.WHITE;
        this.FONT_HOVER_COLOR = Color.BLACK;
        this.BORDER_HOVER_COLOR = Color.BLACK;

        this.setText(text);
        this.setEnabled(true);
        this.setBackground(BACKGROUND_COLOR);
        this.setFont(gf.getApplicationFont());
        this.setForeground(FONT_COLOR);
        this.setFocusPainted(false);
        this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, THICKNESS, ROUNDNESS));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                GenericButton.this.setBackground(BACKGROUND_HOVER_COLOR);
                GenericButton.this.setForeground(FONT_HOVER_COLOR);
                GenericButton.this.setBorder(BorderFactory.createLineBorder(BORDER_HOVER_COLOR, THICKNESS, ROUNDNESS));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                GenericButton.this.setBackground(BACKGROUND_COLOR);
                GenericButton.this.setForeground(Color.WHITE);
                GenericButton.this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, THICKNESS, ROUNDNESS));
            }
        });
    }

    //METHODS
}
