package it.unimol.monopoli.gui.TaxPaymentMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;

import javax.swing.*;
import java.awt.*;

public class TaxPaymentMenuDialog extends JDialog {
    private TaxPaymentMenu taxPaymentMenu;
    private GestorePlayer players;
    private int turnCounter;
    private JTextArea jTextArea1;

    private JDialog gameTurnDialog;
    private GestoreProprieta contratti;

    public TaxPaymentMenuDialog(String title, GestorePlayer players, int turnCounter, GestoreProprieta contratti, JTextArea textArea1, JDialog jDialog, Cronometro cronometro) {
        this.players=players;
        this.turnCounter=turnCounter;
        this.contratti=contratti;
        this.jTextArea1=textArea1;
        this.gameTurnDialog =jDialog;

        this.setSize(400, 400);
        this.setTitle(title);
        this.setMinimumSize(new Dimension(399, 399));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.taxPaymentMenu = new TaxPaymentMenu(this,this.players,this.turnCounter,this.contratti,this.jTextArea1,this.gameTurnDialog,cronometro);
        this.add(taxPaymentMenu.getPanel());
    }
}

