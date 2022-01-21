package ui.gui.components.gamepage;

import entities.Player;
import ui.gui.components.GameFrame;
import ui.gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;

public class NoAvailableMovesDialog extends JDialog {
    //FIELDS
    protected Player player;
    protected JLabel labelArea;
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

        this.labelArea = new JLabel(player.getUsername() + " has no available moves.");
        this.labelArea.setFont(gf.getApplicationFont(false));
        this.labelArea.setForeground(Color.RED);

        this.buttonOk = new GenericButton("Ok", gf);
        this.buttonOk.addActionListener(e -> NoAvailableMovesDialog.this.dispose());

        GridBagConstraints gbcLabelArea = new GridBagConstraints();
        gbcLabelArea.gridx = 0;
        gbcLabelArea.gridy = 0;
        gbcLabelArea.gridheight = 1;
        gbcLabelArea.gridwidth = 1;
        gbcLabelArea.weighty = 0.5;
        gbcLabelArea.weightx = 0;
        gbcLabelArea.anchor = GridBagConstraints.PAGE_START;
        gbcLabelArea.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.labelArea, gbcLabelArea);

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
