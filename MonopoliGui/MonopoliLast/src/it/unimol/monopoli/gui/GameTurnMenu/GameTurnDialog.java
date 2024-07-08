package it.unimol.monopoli.gui.GameTurnMenu;

import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;

import javax.swing.*;
import java.awt.*;

public class GameTurnDialog extends JDialog {

    private GameTurn gameTurn;
    private GestorePlayer players;
    private GestoreProprieta contratti;
    private int turnCounter;

    public GameTurnDialog(GestorePlayer players, GestoreProprieta contratti,int turnCounter) {
        this.contratti=contratti;
        this.turnCounter=turnCounter;
        this.players=players;
        this.setSize(1600, 600);
        this.setTitle("MonopoliUnimol");
        this.setMinimumSize(new Dimension(1599, 600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.gameTurn = new GameTurn(this,this.players,this.turnCounter,this.contratti);
        this.add(gameTurn.getPanel());
    }


}