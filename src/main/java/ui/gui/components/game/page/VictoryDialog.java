package ui.gui.components.game.page;

import entities.Player;
import ui.gui.components.GameFrame;
import ui.gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictoryDialog extends JDialog {
    //FIELDS
    protected JTextArea textArea;
    protected GenericButton buttonOk;
    protected Color BACKGROUND_COLOR = new Color(0, 212, 203);
    protected Player player;
    protected GameFrame gameFrame;

    //CONSTRUCTORS
    public VictoryDialog(GameFrame gf, Player player) {
        this.player = player;
        this.gameFrame = gf;
        this.setTitle("VICTORY");
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(gf.getDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        this.textArea = new JTextArea(player.getUsername() + " HAS WON!!!");
        this.textArea.setFont(gf.getApplicationFont(false));
        this.textArea.setForeground(Color.RED);
        this.textArea.setBackground(Color.WHITE);

        this.buttonOk = new GenericButton("Ok", gf);
        this.buttonOk.addActionListener(e -> {
            VictoryDialog.this.dispose();
            VictoryDialog.this.gameFrame.dispose();
        });

        GridBagConstraints gbcTextArea = new GridBagConstraints();
        gbcTextArea.gridx = 0;
        gbcTextArea.gridy = 0;
        gbcTextArea.gridheight = 1;
        gbcTextArea.gridwidth = 1;
        gbcTextArea.weighty = 0.5;
        gbcTextArea.weightx = 0.5;
        gbcTextArea.anchor = GridBagConstraints.CENTER;
        gbcTextArea.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.textArea, gbcTextArea);

        GridBagConstraints gbcButtonOk = new GridBagConstraints();
        gbcButtonOk.gridx = 0;
        gbcButtonOk.gridy = 1;
        gbcButtonOk.gridheight = 1;
        gbcButtonOk.gridwidth = 1;
        gbcButtonOk.weighty = 0.5;
        gbcButtonOk.weightx = 0.5;
        gbcButtonOk.ipadx = 10;
        gbcButtonOk.ipady = 10;
        gbcButtonOk.anchor = GridBagConstraints.CENTER;
        gbcButtonOk.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.buttonOk, gbcButtonOk);
    }

    //METHODS
}
