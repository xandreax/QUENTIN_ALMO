package ui.gui.components.game.page;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverPieceButton extends JButton {
    //FIELDS
    protected Border hoverBorder;
    protected int THICKNESS;
    protected boolean ROUNDNESS;

    //CONSTRUCTORS
    public HoverPieceButton() {
        super();
        this.THICKNESS = 2;
        this.ROUNDNESS = true;
        this.hoverBorder = BorderFactory.createLineBorder(Color.WHITE, THICKNESS, ROUNDNESS);

        this.setEnabled(true);
        this.setOpaque(false); // Must add
        this.setContentAreaFilled(false);
        this.setBorderPainted(false); // I'd like to enable it.
        this.setBorder(this.hoverBorder);
        this.setVisible(true);

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
                HoverPieceButton.this.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                HoverPieceButton.this.setBorderPainted(false);
            }
        });
    }

    //METHODS
}
