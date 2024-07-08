package it.unimol.monopoli.gui.TaxPaymentMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;

import javax.swing.*;
import java.awt.*;

public class TaxPaymentMenu extends JPanel{
    private GestorePlayer players;
    private GestoreProprieta contratti;
    private int turnCounter;
    private JFormattedTextField importoTextField;
    private JPanel panel;
    private JButton pagaButton;
    private JDialog jDialog;
    private JTextArea jTextArea1;
    private JDialog gameTurnDialog;
    private Cronometro cronometro;



    public TaxPaymentMenu(JDialog jDialog, GestorePlayer players, int turnCounter, GestoreProprieta contratti, JTextArea jTextArea1, JDialog gameTurnDialog, Cronometro cronometro){
        this.jDialog=jDialog;
        this.players=players;
        this.turnCounter = turnCounter;
        this.contratti=contratti;
        this.jTextArea1=jTextArea1;
        this.gameTurnDialog=gameTurnDialog;
        this.cronometro=cronometro;

        this.pagaButton.addActionListener(
                actionListener-> handlePagaButtonClick());
    }


    public void handlePagaButtonClick(){
        try {
            this.players.getCurrentPlayer(this.turnCounter).removePlayerSoldi(Integer.parseInt(importoTextField.getText()));
            this.jTextArea1.setText(this.players.getCurrentPlayer(this.turnCounter).toString());
            closeDialogWindow();
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
