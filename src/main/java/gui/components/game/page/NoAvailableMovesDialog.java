package gui.components.game.page;

import entities.Player;
import gui.components.GameFrame;
import gui.components.GenericButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoAvailableMovesDialog extends JDialog {
    //FIELDS
    protected Player player;
    protected JTextArea textArea;
    protected GenericButton buttonOk;
    protected Color BACKGROUND_COLOR = new Color(157, 157, 157);

    //CONSTRUCTORS
    public NoAvailableMovesDialog(GameFrame gf, Player player) {
        this.player = player;
        this.setTitle("No available moves");
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(gf.getDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        this.textArea = new JTextArea(player.getUsername() + " has no available moves.");
        this.textArea.setFont(gf.getApplicationFont(false));
        this.textArea.setForeground(Color.RED);

        this.buttonOk = new GenericButton("Ok", gf);
        this.buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoAvailableMovesDialog.this.dispose();
            }
        });

        GridBagConstraints gbcTextArea = new GridBagConstraints();
        gbcTextArea.gridx = 0;
        gbcTextArea.gridy = 0;
        gbcTextArea.gridheight = 1;
        gbcTextArea.gridwidth = 1;
        gbcTextArea.weighty = 0.5;
        gbcTextArea.weightx = 0;
        gbcTextArea.anchor = GridBagConstraints.PAGE_START;
        gbcTextArea.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.textArea, gbcTextArea);

        GridBagConstraints gbcButtonOk = new GridBagConstraints();
        gbcButtonOk.gridx = 0;
        gbcButtonOk.gridy = 1;
        gbcButtonOk.gridheight = 1;
        gbcButtonOk.gridwidth = 1;
        gbcButtonOk.weighty = 0.5;
        gbcButtonOk.weightx = 0;
        gbcButtonOk.anchor = GridBagConstraints.PAGE_END;
        gbcButtonOk.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.buttonOk, gbcButtonOk);
    }

    //METHODS
}
