package gui.components.game_page;

import entities.Player;
import gui.components.GameFrame;
import gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieRuleDialog extends JDialog {
    //FIELDS
    protected JTextArea textArea;
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
        this.setMinimumSize(gf.getAlertDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        this.textArea = new JTextArea(player.getUsername() + " wanna use Pie Rule?");
        this.textArea.setFont(gf.getApplicationFont());
        this.textArea.setForeground(Color.BLACK);

        this.buttonYes = new GenericButton("Yes", gf);
        this.buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PieRuleDialog.this.gameFrame.doPieRule();
                PieRuleDialog.this.dispose();
            }
        });

        this.buttonNo = new GenericButton("No", gf);
        this.buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PieRuleDialog.this.dispose();
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

        GridBagConstraints gbcButtonYes = new GridBagConstraints();
        gbcButtonYes.gridx = 0;
        gbcButtonYes.gridy = 1;
        gbcButtonYes.gridheight = 1;
        gbcButtonYes.gridwidth = 1;
        gbcButtonYes.weighty = 0.33;
        gbcButtonYes.weightx = 0;
        gbcButtonYes.anchor = GridBagConstraints.PAGE_END;
        gbcButtonYes.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.buttonYes, gbcButtonYes);

        GridBagConstraints gbcButtonNo = new GridBagConstraints();
        gbcButtonNo.gridx = 1;
        gbcButtonNo.gridy = 1;
        gbcButtonNo.gridheight = 1;
        gbcButtonNo.gridwidth = 1;
        gbcButtonNo.weighty = 0.66;
        gbcButtonNo.weightx = 0;
        gbcButtonNo.anchor = GridBagConstraints.PAGE_END;
        gbcButtonNo.fill = GridBagConstraints.NONE;
        this.getContentPane().add(this.buttonNo, gbcButtonNo);
    }

    //METHODS
}
