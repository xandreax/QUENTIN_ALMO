package ui.gui.components.welcomepage;

import entities.Pieces;
import ui.gui.components.GameFrame;
import ui.gui.components.GenericButton;

import javax.swing.*;
import java.awt.*;

public class DialogForPlayers extends JDialog {
    //FIELDS
    private final GenericButton buttonOk;
    private final JTextField textField;
    private final GenericButton buttonDiscard;

    //CONSTRUCTORS
    public DialogForPlayers(Pieces pieces, GameFrame gf) {
        super();
        this.setTitle("Add player ("+pieces.getName()+")");
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(gf.getDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("src/main/java/gui/logo.png");
        this.setIconImage(logo.getImage());
        this.setLayout(new GridBagLayout());

        this.textField = new JTextField();
        this.textField.setFont(gf.getApplicationFont(false));
        GridBagConstraints gbcTextField = new GridBagConstraints();
        gbcTextField.gridx = 0;
        gbcTextField.gridy = 0;
        gbcTextField.gridwidth = 2;
        gbcTextField.gridheight = 1;
        gbcTextField.weightx = 0;
        gbcTextField.weighty = 0.7;
        gbcTextField.anchor = GridBagConstraints.CENTER;
        gbcTextField.fill = GridBagConstraints.HORIZONTAL;
        gbcTextField.insets = new Insets(0, 15, 0, 15);
        this.getContentPane().add(this.textField, gbcTextField);

        this.buttonOk = new GenericButton("Ok", gf);
        GridBagConstraints gbcButtonOk = new GridBagConstraints();
        gbcButtonOk.gridx = 1;
        gbcButtonOk.gridy = 1;
        gbcButtonOk.gridwidth = 1;
        gbcButtonOk.gridheight = 1;
        gbcButtonOk.weightx = 0.5;
        gbcButtonOk.weighty = 0.3;
        gbcButtonOk.anchor = GridBagConstraints.CENTER;
        gbcButtonOk.fill = GridBagConstraints.NONE;
        gbcButtonOk.ipadx = this.getWidth()/10;
        gbcButtonOk.ipady = this.getHeight()/10;
        gbcButtonOk.insets = new Insets(0, 15, 0, 15);
        this.getContentPane().add(this.buttonOk, gbcButtonOk);

        this.buttonDiscard = new GenericButton("Discard", gf);
        GridBagConstraints gbcButtonDiscard = new GridBagConstraints();
        gbcButtonDiscard.gridx = 0;
        gbcButtonDiscard.gridy = 1;
        gbcButtonDiscard.gridwidth = 1;
        gbcButtonDiscard.gridheight = 1;
        gbcButtonDiscard.weightx = 0.5;
        gbcButtonDiscard.weighty = 0.3;
        gbcButtonDiscard.anchor = GridBagConstraints.CENTER;
        gbcButtonDiscard.fill = GridBagConstraints.NONE;
        gbcButtonDiscard.ipadx = this.getWidth()/10;
        gbcButtonDiscard.ipady = this.getHeight()/10;
        gbcButtonDiscard.insets = new Insets(0, 15, 0, 15);
        this.getContentPane().add(this.buttonDiscard, gbcButtonDiscard);
    }

    //METHODS
    public JTextField getTextField() {
        return textField;
    }

    public GenericButton getButtonOk() {
        return buttonOk;
    }

    public GenericButton getButtonDiscard() {
        return buttonDiscard;
    }
}
