package ui.gui.components.welcome.page;

import logic.UIGame;
import ui.gui.components.GameFrame;
import ui.gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;

public class PanelWelcomePage extends JPanel {
    //FIELDS
    protected GameFrame currentGameFrame;
    protected JLabel topLabel;
    protected JPanel bottomPanel;
    protected GenericButton startGameButton;

    //CONSTRUCTORS
    public PanelWelcomePage(GameFrame gameFrame, UIGame uiGame) {
        super();
        this.currentGameFrame= gameFrame;
        this.setPreferredSize(new Dimension(this.currentGameFrame.getSide(), this.currentGameFrame.getSide()));
        this.setSize(new Dimension(this.currentGameFrame.getSide(), this.currentGameFrame.getSide()));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        this.setBackground(GameFrame.BOARD_COLOR);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JLabel top = new JLabel();
        ImageIcon logo = new ImageIcon("src/main/java/ui/gui/logo_big.png");

        top.setIcon(logo);

        top.setText("Quentin");
        top.setFont(new Font("Purisa", Font.BOLD, 34));
        top.setForeground(Color.WHITE);
        top.setOpaque(false);
        top.setHorizontalAlignment(SwingConstants.CENTER);
        top.setVerticalAlignment(SwingConstants.CENTER);
        this.topLabel = top;
        GridBagConstraints gbcTop = new GridBagConstraints();
        gbcTop.gridx = 0;
        gbcTop.gridy = 0;
        gbcTop.gridwidth = 1;
        gbcTop.gridheight = 1;
        gbcTop.weightx = 1;
        gbcTop.weighty = 0.5f;
        gbcTop.anchor = GridBagConstraints.CENTER;
        gbcTop.fill = GridBagConstraints.BOTH;
        this.add(top, gbcTop);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        this.bottomPanel = bottom;
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 1;
        gbcBottom.gridwidth = 1;
        gbcBottom.gridheight = 1;
        gbcBottom.weightx = 1;
        gbcBottom.weighty = 0.5f;
        gbcBottom.anchor = GridBagConstraints.CENTER;
        gbcBottom.fill = GridBagConstraints.BOTH;
        GenericButton buttonStart = new GenericButton("Start game", this.currentGameFrame);
        buttonStart.setPreferredSize(new Dimension(this.getWidth()/2, 50));
        buttonStart.addActionListener(e -> uiGame.startGame());
        this.startGameButton = buttonStart;
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.gridwidth = 1;
        gbcButton.gridheight = 1;
        gbcButton.weightx = 0;
        gbcButton.weighty = 0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        gbcButton.fill = GridBagConstraints.NONE;
        bottom.add(buttonStart, gbcButton);
        this.add(bottom, gbcBottom);
    }

    //METHODS
}
