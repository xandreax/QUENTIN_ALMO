package ui.gui.components.gamepage;

import entities.Player;
import ui.gui.components.GameFrame;
import ui.gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;

public class VictoryDialog extends JDialog {
    private final GameFrame gameFrame;

    //CONSTRUCTORS
    public VictoryDialog(GameFrame gf, Player player) {
        this.gameFrame = gf;
        this.setTitle("VICTORY");
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(gf.getDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        Color BACKGROUND_COLOR = new Color(0, 212, 203);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        //FIELDS
        JLabel labelArea = new JLabel(player.getUsername() + " HAS WON!!!");
        labelArea.setFont(gf.getApplicationFont(false));
        labelArea.setForeground(Color.RED);
        labelArea.setBackground(Color.WHITE);

        GenericButton buttonOk = new GenericButton("Ok", gf);
        buttonOk.addActionListener(e -> {
            VictoryDialog.this.dispose();
            VictoryDialog.this.gameFrame.dispose();
        });

        GridBagConstraints gbcLabelArea = new GridBagConstraints();
        gbcLabelArea.gridx = 0;
        gbcLabelArea.gridy = 0;
        gbcLabelArea.gridheight = 1;
        gbcLabelArea.gridwidth = 1;
        gbcLabelArea.weighty = 0.5;
        gbcLabelArea.weightx = 0.5;
        gbcLabelArea.anchor = GridBagConstraints.CENTER;
        gbcLabelArea.fill = GridBagConstraints.NONE;
        this.getContentPane().add(labelArea, gbcLabelArea);

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
        this.getContentPane().add(buttonOk, gbcButtonOk);
    }

    //METHODS
}
