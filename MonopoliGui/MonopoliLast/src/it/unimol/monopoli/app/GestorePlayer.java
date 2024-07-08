package it.unimol.monopoli.app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che gestisce l'elenco di giocatori con le relative caratteristiche
 *
 * @author Davide Di Niro
 * @version 1.0
 */
public class GestorePlayer implements Serializable {
    private ArrayList<Player> players;

    public GestorePlayer() {
        players = new ArrayList<>(1);
    }

    /**
     * Dati nome e pedina come parametri, se players non contiene già un giocatore con lo stesso
     * nome o pedina ritorna true e lo aggiunge alla lista, altrimenti ritorna false e non lo aggiunge.
     *
     * @param nome String nome da aggiungere al nuovo giocatore
     * @param pedina String pedina da aggiungere al nuovo giocatore
     * @return true se lo aggiunge, false se non lo aggiunge
     */
    public boolean addNewPlayer(String nome,String pedina) {
        for (Player player : players) {
            if (player.getPlayerNome().equals(nome)||(player.getPlayerPedina().equals(pedina)))
                return false;
        }
            Player player = new Player(nome, pedina);
            players.add(player);
            return true;
        }

    /**
     * Restituisce la lista dei giocatori sotto forma di Arraylist
     * @return ArrayList di Player
     */
    public ArrayList<Player> getPlayerList(){
        return this.players;
    }

    /**
     * Aggiunge una determinata quantità di soldi al denaro di tutti i giocatori in base al loro numero:
     * 2:8750
     * 3:7500
     * 4:6250
     * 5:5000
     * 6:3750
     *
     * Se il numero non è compreso tra due e sei , stampa a video un messaggio di errore
     * (non dovrebbe mai esserci un caso di default , pertanto la stampa non dovrebbe mai essere eseguita)
     */
    public void playerSetInitialMoney(){
        switch (getNumPlayers()){
            case 2 :
                for (Player p: players) {
                    p.addPlayerSoldi(8750);
                }
                break;
            case 3 :
                for (Player p: players) {
                    p.addPlayerSoldi(7500);
                }
                break;
            case 4 :
                for (Player p: players) {
                    p.addPlayerSoldi(6250);
                }
            case 5 :
                for (Player p: players) {
                    p.addPlayerSoldi(5000);
                }
                break;
            case 6 :
                for (Player p: players) {
                    p.addPlayerSoldi(3750);
                }
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    /**
     * Dato un intero rappresentante la posizione all'interno della lista, elimina il giocatore dalla lista
     * @param posizione int del giocatore da eliminare
     */
    public void removePlayer(int posizione){
        players.remove(posizione);
    }

    /**
     * Dato un intero rappresentante la posizione all'interno della lista, restituisce il giocatore
     * @param id int del giocatore da restituire
     * @return Player p nella posizione id della lista
     */
    public Player getCurrentPlayer(int id){
        return players.get(id);
    }

    /**
     * Restituisce le dimensioni della lista di giocatori come intero
     * @return int di players.size()
     */
    public int getNumPlayers(){
        return players.size();
    }

    /**
     * Restituisce tutti i giocatori della lista come stringa
     * @return String di ogni giocatore
     */
    public String toString(){
        String s ="";
        for (Player p:players) {
            s+=p.toString()+"\n";
        }
        return s;
    }
}




