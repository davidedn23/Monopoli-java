package it.unimol.monopoli.gui.PrisonerMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;
import it.unimol.monopoli.gui.GameTurnMenu.GameTurn;

import javax.swing.*;
import java.awt.*;

public class PrisonerMenu extends JPanel {
    private JDialog jDialog;
    private GestorePlayer players;
    private int turnCounter;
    private GestoreProprieta contratti;
    private JPanel panel;
    private JButton esciGratisButton;
    private JButton esciPagandoButton;
    private JButton restaInPrigioneButton;
    private JLabel prisonerLabel;
    private Cronometro cronometro;
    private JDialog gameTurnDialog;
    private GameTurn gameTurn;

    public PrisonerMenu(JDialog jDialog, GestorePlayer players, int turnCounter, GestoreProprieta contratti, JDialog gameTurnDialog, Cronometro cronometro, GameTurn gameTurn) {
        this.players=players;
        this.turnCounter=turnCounter;
        this.contratti=contratti;
        this.jDialog=jDialog;
        this.gameTurnDialog=gameTurnDialog;
        this.cronometro=cronometro;
        this.gameTurn=gameTurn;

        this.prisonerLabel.setText(this.players.getCurrentPlayer(this.turnCounter).getPlayerNome());

        esciGratisButton.addActionListener(actionListener->{
            closeDialogWindow();
            this.gameTurnDialog.setVisible(true);
            this.cronometro.setPause(false);
        });
        esciPagandoButton.addActionListener(actionListener->{
            this.players.getCurrentPlayer(this.turnCounter).removePlayerSoldi(250);
            closeDialogWindow();
            this.gameTurnDialog.setVisible(true);
            this.cronometro.setPause(false);
        });
        restaInPrigioneButton.addActionListener(actionListener->{
            if(this.turnCounter<players.getNumPlayers()-1)
                this.turnCounter++;
            else this.turnCounter=0;
            closeDialogWindow();
            this.gameTurnDialog.setVisible(true);
            this.cronometro.setPause(false);
            this.gameTurn.handleTerminaButtonClick();
        });
    }
    public void closeDialogWindow(){
        jDialog.dispose();
    }

    public Component getPanel() {
        return this.panel;
    }
}

