package gui.components;

import entities.Board;
import entities.Move;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    //FIELDS
    protected static Color BACKGROUND_COLOR = new Color(0, 156, 10);
    protected static Color BOARD_COLOR = new Color(0, 125, 8);
    //protected static Color TRANSPARENT = new Color(0f,0f,0f,0f );
    protected int shorterDimension;
    protected Board board;

    //CONSTRUCTORS
    public GameFrame(Board board) {
        this.board = board;
        //JFrame setup
        this.setTitle("Quentin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1280, 720));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setResizable(true);
        ImageIcon logo = new ImageIcon("src/main/java/gui/logo.png");
        this.setIconImage(logo.getImage());
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.shorterDimension = getShorterDimensionScaled(this.getWidth(), this.getHeight());
        this.setLayout(new GridBagLayout());
        //JLayeredPane setup: container for board panel. The "background" are the trapezoids
        BoardLayeredPane boardLayeredPanel = new BoardLayeredPane();
        boardLayeredPanel.setLayout(new GridBagLayout());
        boardLayeredPanel.setPreferredSize(new Dimension(this.shorterDimension, this.shorterDimension));
        boardLayeredPanel.setOpaque(true);
        boardLayeredPanel.setBackground(Color.WHITE);
        this.getContentPane().add(boardLayeredPanel);
        boardLayeredPanel.setLayout(new GridBagLayout());
        //JPanel: Board Panel container
        JPanel boardPanelContainer = new JPanel();
        int len = 5*this.shorterDimension/6;
        Dimension boardPanelContainerDimension = new Dimension(len, len);
        boardPanelContainer.setPreferredSize(boardPanelContainerDimension);
        boardPanelContainer.setLayout(new GridBagLayout());
        boardPanelContainer.setBackground(BOARD_COLOR);
        boardLayeredPanel.add(boardPanelContainer);
        //JPanel: Board Panel
        len = 5*len/6;
        InnerBoardPanel boardPanel = new InnerBoardPanel(this.board, len);
        Dimension realDimension = new Dimension(boardPanel.getDimensionWithPadding(), boardPanel.getDimensionWithPadding());
        boardPanel.setOpaque(true);
        boardPanel.setBackground(BOARD_COLOR);
        //boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        boardPanel.setPreferredSize(realDimension);
        //boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        boardPanelContainer.add(boardPanel, 0);
    }

    //METHODS

    //AUXILIARY METHODS
    private static int getShorterDimensionScaled(int width, int height) {
        return 7*Math.min(width, height)/8;
    }
}
