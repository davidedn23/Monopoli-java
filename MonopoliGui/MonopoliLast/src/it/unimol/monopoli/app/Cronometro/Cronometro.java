package it.unimol.monopoli.app.Cronometro;

import it.unimol.monopoli.gui.GameTurnMenu.GameTurn;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Classe addetta al timer dei turni, che automaticamente passa al giocatore successivo dopo 180 secondi
 *
 * @author Davide Di Niro
 * @version 1.0
 */
public class Cronometro extends Thread {
    private GameTurn gameTurn;
    private Boolean pause=false;
    JFormattedTextField timerTextField;
    JDialog jDialog ;
    int i=180;

    /**
     * Crea un nuovo timer capace di interagire con timerTextField e con i metodi del GameTurn corrente
     * @param timerTextField JFormattedTextField che mostra il tempo
     * @param gameTurn istanza di GameTurn da gestire nel thread
     * @param jDialog istanza di GameTurnDialog senza la quale il thread si arresta
     */
    public Cronometro(JFormattedTextField timerTextField,GameTurn gameTurn,JDialog jDialog) {
        this.timerTextField = timerTextField;
        this.gameTurn=gameTurn;
        this.jDialog=jDialog;
    }

    /**
     * Aspetta per 1000 millisecondi e cambia il testo in timerTextField per 180 volte per giocatore, poi termina
     * il turno tramite il metodo handleTerminaButtonClick di gameTurn
     */
    public void run() {
        while(i>=0)
            try {
                exitOnClose();
                this.timerTextField.setText(i+" s");
                sleep(1000);
                if(pause.equals(false))
                i-=1;
                if(i==0) {
                    this.gameTurn.handleTerminaButtonClick();
                }
            }catch(InterruptedException ignore){}

    }

    /**
     * Resetta il countdown modificandone l'intero che decrementa
     */
    public void resetTimer(){
            this.i=180;
            this.timerTextField.setText(i + " s");
    }

    /**
     * Flag utilizzato per verificare se il timer deve scorrere o meno
     * @param pause Boolean da modificare in caso di pausa
     * (false: no\true si)
     */
    public void setPause(Boolean pause){
        this.pause=pause;
    }

    /**
     * Metodo che interrompe l'esecuzione se viene chiusa la finestra tramite il metodo system.exit(0)
     */
    private void exitOnClose() {
        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
