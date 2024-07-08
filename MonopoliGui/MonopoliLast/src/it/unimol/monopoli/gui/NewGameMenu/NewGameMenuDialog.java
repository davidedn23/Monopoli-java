package it.unimol.monopoli.gui.NewGameMenu;

import javax.swing.*;
import java.awt.*;

public class NewGameMenuDialog extends JDialog {
    NewGameMenu newGameMenu;

    public NewGameMenuDialog() {
        this.setSize(300, 600);
        this.setTitle("Nuova Partita");
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.newGameMenu = new NewGameMenu(this);
        this.add(newGameMenu.getPanel());
    }



}
