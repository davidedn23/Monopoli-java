package it.unimol.monopoli.app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che permette di registrare il giocatore
 * (il nome,la pedina,il denaro,le proprietà e la sua posizione in tabella)
 *
 * @author Davide Di Niro
 * @version 1.0
 */

public class Player implements Serializable {

    private String nome;
    private String pedina;
    private int soldi;
    private boolean prigioniero;
    private ArrayList<Proprieta> possedimenti;
    private int posizione;

    /**
     * Crea un nuovo giocatore con le seguenti caratteristiche
     * @param nome String nome di Player
     * @param pedina String nome della pedina di Player
     */
    public Player(String nome,String pedina){
        this.pedina= pedina;
        this.nome=nome;
        this.possedimenti = new ArrayList<>();
        this.posizione = 0;
    }
    //nome
    public String getPlayerNome(){
        return this.nome;
    }

    /**
     * Aggiunge denaro al giocatore
     * @param somma int da accreditare a soldi di Player
     */
    public void addPlayerSoldi(int somma){
        this.soldi+=somma;
    }

    /**
     * Sottrae denaro al giocatore
     * @param somma int da addebitare a soldi di Player
     */
    public void removePlayerSoldi(int somma){
        this.soldi-=somma;
    }

    /**
     * Ritorna la liquidità del giocatore
     * @return int soldi di Player
     */
    public int getPlayerSoldi(){
        return this.soldi;
    }

    /**
     * Aggiunge una proprietà alla lista delle proprietà del giocatore
     * @param p proprietà da aggiungere
     */
    public void addPlayerPossedimenti(Proprieta p){
        this.possedimenti.add(p);
    }
    /*public void removePlayerPossedimenti(Player player,Proprieta p){
        this.possedimenti.remove(p);
    }*/

    /**
     * Restituisce la lista delle proprietà del giocatore
     * @return ArrayList di proprietà di Player
     */
    public ArrayList<Proprieta> getPlayerPossedimenti(){
        return this.possedimenti;
    }

    /**
     * Restituisce la lista delle proprietà del giocatore sotto forma di stringa
     * @return String di proprietà di Player
     */
    public String getPlayerPossedimentiString(){
        String poss = "";
        for (Proprieta proprieta: possedimenti) {
            poss+= proprieta.toString()+"\n      ";
        }
        return poss;
    }

    /**
     * Restituisce tutti i parametri del giocatore sotto forma di stringa
     * @return String di Player
     */
    public String toString(){
        return  "Nome: "
                +this.nome
                +"\nSoldi:"
                +this.soldi
                +"€"
                +"\nPedina:"
                +this.pedina
                +"\n"
                + "Proprietà:\n      "
                +this.getPlayerPossedimentiString();
    }

    /**
     * Restituisce la posizione del giocatore sotto forma di intero e se supera il VIA
     * nelle proprietà gli accredita 500
     * @return int posizione di Player
     */
    public int getPlayerPosizione(){
        if (posizione>28){
            posizione-=29;
            this.addPlayerSoldi(500);
        }
        return posizione;
    }

    /**
     * Incrementa la posizione del giocatore di un valore i
     * @param i int da incrementare
     */
    public void playerGoForward(int i){
        posizione+=i;
    }

    /**
     * Restituisce il nome della pedina del giocatore sotto forma di stringa
     * @return String pedina di Player
     */
    public String getPlayerPedina() {
        return this.pedina;
    }

    /**
     * Assegna al parametro posizione del giocatore un valore n
     * @param n int da assegnare a posizione di Player
     */
    public void setPlayerPosizione(int n) {
        this.posizione=n;
    }
}
