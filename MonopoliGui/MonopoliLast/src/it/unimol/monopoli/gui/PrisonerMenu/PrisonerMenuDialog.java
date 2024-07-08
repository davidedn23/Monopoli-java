package it.unimol.monopoli.gui.PrisonerMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;
import it.unimol.monopoli.gui.GameTurnMenu.GameTurn;

import javax.swing.*;
import java.awt.*;

public class PrisonerMenuDialog extends JDialog {
    private PrisonerMenu prisonerMenu;
    private GestorePlayer players;
    private int turnCounter;
    private GestoreProprieta contratti;
    private JDialog gameTurnDialog;

    public PrisonerMenuDialog(GestorePlayer players, GestoreProprieta contratti, int turnCounter, JDialog jDialog, Cronometro cronometro, GameTurn gameTurn) {
        this.players=players;
        this.turnCounter=turnCounter;
        this.contratti=contratti;
        this.gameTurnDialog=jDialog;

        this.setSize(400, 400);
        this.setTitle("Prigione");
        this.setMinimumSize(new Dimension(399, 399));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.prisonerMenu = new PrisonerMenu(this,this.players,this.turnCounter,this.contratti,this.gameTurnDialog,cronometro,gameTurn);
        this.add(prisonerMenu.getPanel());
    }
}
