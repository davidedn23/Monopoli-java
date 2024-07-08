package it.unimol.monopoli.gui.RentPaymentMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.CustomExceptions.NullNameException;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;
import it.unimol.monopoli.app.Player;

import javax.swing.*;
import java.awt.*;

public class RentPaymentMenu extends JPanel{
    private JDialog jDialog;
    private JFormattedTextField importoTextField;
    private JPanel panel;
    private JButton pagaButton;
    private JComboBox playersComboBox;
    private GestorePlayer players;
    private int turnCounter;
    private JTextArea textArea1;
    private GestoreProprieta contratti;
    private JDialog gameTurnDialog;
    private Cronometro cronometro;
    public RentPaymentMenu(JDialog jDialog, GestorePlayer players, int turnCounter, GestoreProprieta contratti, JTextArea textArea1, JDialog gameTurnDialog, Cronometro cronometro) {
        this.players=players;
        this.turnCounter=turnCounter;
        this.contratti=contratti;
        this.jDialog=jDialog;
        this.textArea1=textArea1;
        this.gameTurnDialog=gameTurnDialog;
        this.cronometro=cronometro;
        for (Player p:players.getPlayerList()) {
            if(p.getPlayerNome().equals(this.players.getCurrentPlayer(this.turnCounter).getPlayerNome())==false)
            playersComboBox.addItem(p.getPlayerNome());
        }
        this.pagaButton.addActionListener(
                actionListener-> handlePagaButtonClick());
    }
    public void handlePagaButtonClick(){
        try {
            this.players.getCurrentPlayer(this.turnCounter).removePlayerSoldi(Integer.parseInt(importoTextField.getText()));
            for (Player p: this.players.getPlayerList()) {
                if(p.getPlayerNome().equals(playersComboBox.getSelectedItem()))
                    p.addPlayerSoldi(Integer.parseInt(importoTextField.getText()));
            }
            closeDialogWindow();
            this.textArea1.setText(this.players.getCurrentPlayer(this.turnCounter).toString());
            this.gameTurnDialog.setVisible(true);
            this.cronometro.setPause(false);

        }catch (NumberFormatException n){
            JOptionPane.showMessageDialog(jDialog,"Errore: inserire un numero.");
        }
    }
    public void closeDialogWindow(){
        jDialog.dispose();
    }

    public Component getPanel() {
        return this.panel;
    }
}
