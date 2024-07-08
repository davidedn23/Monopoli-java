package it.unimol.monopoli.gui.RentPaymentMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;

import javax.swing.*;
import java.awt.*;

public class RentPaymentMenuDialog extends JDialog {
    private RentPaymentMenu rentPaymentMenu;
    private GestorePlayer players;
    private int turnCounter;
    private GestoreProprieta contratti;
    private JTextArea textArea1;
    private JDialog gameTurnDialog;

    public RentPaymentMenuDialog(String title, GestorePlayer players, int turnCounter, GestoreProprieta contratti, JTextArea textArea1, JDialog jDialog, Cronometro cronometro) {
        this.players=players;
        this.turnCounter=turnCounter;
        this.contratti=contratti;
        this.textArea1=textArea1;
        this.gameTurnDialog=jDialog;

        this.setSize(400, 400);
        this.setTitle(title);
        this.setMinimumSize(new Dimension(399, 399));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.rentPaymentMenu = new RentPaymentMenu(this,this.players,this.turnCounter,this.contratti,this.textArea1,this.gameTurnDialog,cronometro);
        this.add(rentPaymentMenu.getPanel());
    }
}
