package ui.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertDialog extends JDialog {

    //CONSTRUCTORS
    public AlertDialog(String text, GameFrame gf) {
        this.setTitle("Error");
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(gf.getAlertDialogDimension());
        this.setSize(gf.getDialogDimension());
        this.setUndecorated(false);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        Color BACKGROUND_COLOR = new Color(114, 0, 0);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        //FIELDS
        JLabel labelArea = new JLabel(text);
        labelArea.setFont(gf.getApplicationFont(false));
        labelArea.setForeground(Color.RED);

        GenericButton buttonOk = new GenericButton("Ok", gf);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlertDialog.this.dispose();
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
        this.getContentPane().add(labelArea, gbcTextArea);

        GridBagConstraints gbcButtonOk = new GridBagConstraints();
        gbcButtonOk.gridx = 0;
        gbcButtonOk.gridy = 1;
        gbcButtonOk.gridheight = 1;
        gbcButtonOk.gridwidth = 1;
        gbcButtonOk.weighty = 0.5;
        gbcButtonOk.weightx = 0;
        gbcButtonOk.anchor = GridBagConstraints.PAGE_END;
        gbcButtonOk.fill = GridBagConstraints.NONE;
        this.getContentPane().add(buttonOk, gbcButtonOk);
    }

    //METHODS
}
