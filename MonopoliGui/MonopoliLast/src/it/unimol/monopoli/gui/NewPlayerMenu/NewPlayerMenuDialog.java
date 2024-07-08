package it.unimol.monopoli.gui.NewPlayerMenu;

import it.unimol.monopoli.app.GestorePlayer;

import javax.swing.*;
import java.awt.*;

public class NewPlayerMenuDialog extends JDialog {
    NewPlayerMenu newPlayerMenu;
    GestorePlayer players;

    public NewPlayerMenuDialog(GestorePlayer players) {
        this.players=players;
        this.setSize(300, 600);
        this.setTitle("Nuovo giocatore");
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.newPlayerMenu = new NewPlayerMenu(this,this.players);
        this.add(newPlayerMenu.getPanel());
    }

}
