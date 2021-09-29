package gui.components;

import javax.swing.*;
import java.awt.*;

public class BoardLayeredPane extends JLayeredPane {
    //FIELDS

    //CONSTRUCTORS

    //METHODS
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2D = (Graphics2D) g;
        g.setColor(Color.BLACK);
        //north
        g.fillPolygon(new int[]{0, this.getWidth()/2, this.getWidth()},
                new int[]{0, this.getHeight()/2, 0},
                3);
        //south
        g.fillPolygon(new int[]{0, this.getWidth()/2, this.getWidth()},
                new int[]{this.getHeight(), this.getHeight()/2, this.getHeight()},
                3);
    }
}
