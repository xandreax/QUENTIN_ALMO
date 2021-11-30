package gui.components.welcome_page;

import gui.components.GameFrame;
import gui.components.welcome_page.DialogForPlayers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class DiscardButtonActionListener implements ActionListener {
    //FIELDS
    protected Set<DialogForPlayers> dialogsToBeDisposed;
    protected GameFrame currentGameFrame;

    //CONSTRUCTORS
    public DiscardButtonActionListener(Set<DialogForPlayers> dialogsToBeDisposed, GameFrame currentGameFrame) {
        this.dialogsToBeDisposed = dialogsToBeDisposed;
        this.currentGameFrame = currentGameFrame;
    }

    //METHODS
    @Override
    public void actionPerformed(ActionEvent e) {
        for (DialogForPlayers d: this.dialogsToBeDisposed) {
            d.dispose();
        }
        this.currentGameFrame.setEnabled(true);
    }
}