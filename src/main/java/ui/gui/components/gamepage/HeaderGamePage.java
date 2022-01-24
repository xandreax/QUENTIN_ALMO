package ui.gui.components.gamepage;

import entities.Player;
import ui.gui.components.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HeaderGamePage extends JPanel {
    //FIELDS
    private final GameFrame currentGameFrame;
    private final Player player1;
    private final Player player2;
    private final JLabel player1Text;
    private final JLabel player2Text;
    private final JLabel turnText;

    //CONSTRUCTORS
    public HeaderGamePage(GameFrame gf, List<Player> players) {
        super();
        this.setLayout(new GridLayout());
        this.setBackground(Color.WHITE);

        this.currentGameFrame = gf;
        this.player1 = players.get(0);
        this.player2 = players.get(1);
        this.player1Text = new JLabel("[" + player1.getPieces().getName().toUpperCase() + "] " + player1.getUsername());
        this.turnText = new JLabel("..");
        this.player2Text = new JLabel("[" + player2.getPieces().getName().toUpperCase() + "] " + player2.getUsername());

        this.player1Text.setFont(gf.getApplicationFont(false));
        this.player1Text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.turnText.setFont(gf.getApplicationFont(false));
        this.turnText.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        this.player2Text.setFont(gf.getApplicationFont(false));
        this.player2Text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        this.add(this.player1Text);
        this.add(this.turnText);
        this.add(this.player2Text);
    }

    //METHODS
    public void highlightTurn(Player player) {
        if (this.player1.equals(player)) {
            this.player1Text.setFont(this.currentGameFrame.getApplicationFont(true));
            this.player1Text.setForeground(Color.RED);
            this.turnText.setText(this.player1.getUsername() + "'s turn");

            this.player2Text.setFont(this.currentGameFrame.getApplicationFont(false));
            this.player2Text.setForeground(Color.BLACK);
        } else {
            this.player2Text.setFont(this.currentGameFrame.getApplicationFont(true));
            this.player2Text.setForeground(Color.RED);
            this.turnText.setText(this.player2.getUsername() + "'s turn");

            this.player1Text.setFont(this.currentGameFrame.getApplicationFont(false));
            this.player1Text.setForeground(Color.BLACK);
        }
    }

    public void swapPieces() {
        this.player1Text.setText("[" + player1.getPieces().getName().toUpperCase() + "] " + player1.getUsername());
        this.player2Text.setText("[" + player2.getPieces().getName().toUpperCase() + "] " + player2.getUsername());
    }
}
