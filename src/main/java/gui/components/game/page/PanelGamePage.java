package gui.components.game.page;

import entities.Board;
import gui.components.GameFrame;
import javax.swing.*;
import java.awt.*;

public class PanelGamePage extends JPanel {
    //FIELDS
    private Board board;
    private final GameFrame currentGameFrame;
    private final HeaderGamePage header;
    private final BoardPanel boardPanel;

    //CONSTRUCTORS
    public PanelGamePage(GameFrame gameFrame, Board board) {
        super();
        this.currentGameFrame = gameFrame;
        this.board = board;
        this.setBackground(GameFrame.BOARD_COLOR);
        this.setLayout(new GridBagLayout());

        this.header = new HeaderGamePage(this.getCurrentGameFrame(), currentGameFrame.getGame().getPlayer1(), currentGameFrame.getGame().getPlayer2());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbcHeader.weightx = 1;
        gbcHeader.weighty = 0.2;
        gbcHeader.anchor = GridBagConstraints.PAGE_START;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.insets = new Insets(0, 0, 20, 0);
        add(this.header, gbcHeader);

        boardPanel = new BoardPanel(this.getCurrentGameFrame(), board);
        GridBagConstraints gbcBoardPanel = new GridBagConstraints();
        gbcBoardPanel.gridx = 0;
        gbcBoardPanel.gridy = 1;
        gbcBoardPanel.weightx = 1;
        gbcBoardPanel.weighty = 0.8;
        gbcBoardPanel.anchor = GridBagConstraints.PAGE_END;
        this.add(this.boardPanel, gbcBoardPanel);
    }

    //METHODS
    public GameFrame getCurrentGameFrame() {
        return currentGameFrame;
    }

    public HeaderGamePage getHeader() {
        return header;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}
