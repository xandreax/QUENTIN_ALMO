package ui.gui.components.game.page;

import entities.Player;
import ui.gui.components.GameFrame;
import ui.gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;

public class PieRuleDialog extends JDialog {
    //FIELDS
    protected JLabel labelArea;
    protected GenericButton buttonYes;
    protected GenericButton buttonNo;
    protected GameFrame gameFrame;
    protected Player player;
    public static Color BACKGROUND_COLOR = new Color(157, 157, 157);

    //CONSTRUCTORS
    public PieRuleDialog(GameFrame gf, Player player) {
        this.gameFrame = gf;
        this.player = player;
        this.setTitle("Pie Rule");
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(gf.getDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        this.labelArea = new JLabel(player.getUsername() + " wanna use Pie Rule?");
        this.labelArea.setFont(gf.getApplicationFont(false));
        this.labelArea.setForeground(Color.BLACK);

        this.buttonYes = new GenericButton("Yes", gf);

        this.buttonNo = new GenericButton("No", gf);

        GridBagConstraints gbcLabelArea = new GridBagConstraints();
        gbcLabelArea.gridx = 0;
        gbcLabelArea.gridy = 0;
        gbcLabelArea.gridheight = 1;
        gbcLabelArea.gridwidth = 2;
        gbcLabelArea.weighty = 0.25;
        gbcLabelArea.weightx = 0.25;
        gbcLabelArea.anchor = GridBagConstraints.CENTER;
        gbcLabelArea.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.labelArea, gbcLabelArea);

        GridBagConstraints gbcButtonYes = new GridBagConstraints();
        gbcButtonYes.gridx = 0;
        gbcButtonYes.gridy = 1;
        gbcButtonYes.gridheight = 1;
        gbcButtonYes.gridwidth = 1;
        gbcButtonYes.weighty = 0.33;
        gbcButtonYes.weightx = 0.33;
        gbcButtonYes.anchor = GridBagConstraints.CENTER;
        gbcButtonYes.ipadx = 10;
        gbcButtonYes.ipady = 10;
        gbcButtonYes.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.buttonYes, gbcButtonYes);

        GridBagConstraints gbcButtonNo = new GridBagConstraints();
        gbcButtonNo.gridx = 1;
        gbcButtonNo.gridy = 1;
        gbcButtonNo.gridheight = 1;
        gbcButtonNo.gridwidth = 1;
        gbcButtonNo.weighty = 0.33;
        gbcButtonNo.weightx = 0.33;
        gbcButtonNo.ipadx = 10;
        gbcButtonNo.ipady = 10;
        gbcButtonNo.anchor = GridBagConstraints.CENTER;
        gbcButtonNo.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.buttonNo, gbcButtonNo);
    }

    //METHODS

    public GenericButton getButtonYes() {
        return buttonYes;
    }

    public GenericButton getButtonNo() {
        return buttonNo;
    }
}
