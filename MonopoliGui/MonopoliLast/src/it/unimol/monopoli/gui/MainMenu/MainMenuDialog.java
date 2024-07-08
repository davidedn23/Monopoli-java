package it.unimol.monopoli.gui.MainMenu;

import javax.swing.*;
import java.awt.*;

public class MainMenuDialog extends JDialog {
    MainMenu menu;

    public MainMenuDialog(){
        this.setSize(401, 401);
        this.setTitle("Menu");
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.menu = new MainMenu(this);
        this.add(this.menu.getPanel());
    }
}
