package it.unimol.monopoli.gui.LoadGameMenu;

import javax.swing.*;
import java.awt.*;

public class LoadGameMenuDialog extends JDialog {
    LoadGameMenu loadGameMenu;

    public LoadGameMenuDialog() {
        this.setSize(300, 600);
        this.setTitle("Carica partita salvata");
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.loadGameMenu = new LoadGameMenu(this);
        this.add(loadGameMenu.getPanel());
    }

}
