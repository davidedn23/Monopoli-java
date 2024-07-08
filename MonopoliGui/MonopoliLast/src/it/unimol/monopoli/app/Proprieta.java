package it.unimol.monopoli.app;

import java.io.Serializable;

/**
 * Classe che permette di registrare una nuova proprietà
 * (il nome, il prezzo di affitto, il prezzo di acquisto)
 *
 * @author Davide Di Niro
 * @version 1.0
 */
public class Proprieta implements Serializable {
    private String nome;
    private int prezzo;
    private int affitto;

    /**
     * Dati un nome, il prezzo di acquisto e di affitto crea una nuova proprietà
     *
     * @param nome String nome della proprietà
     * @param prezzo int prezzo di acquisto
     * @param affitto int prezzo di affitto
     */
    public Proprieta(String nome,int prezzo, int affitto){
        this.nome=nome;
        this.prezzo=prezzo;
        this.affitto=affitto;
    }

    /**
     * Restituisce il nome della proprietà sotto forma di stringa
     *
     * @return String nome
     */
    public String getProprietaNome(){
        return this.nome;
    }
    /**
     * Restituisce il prezzo della proprietà sotto forma di intero
     *
     * @return int prezzo
     */
    public int getProprietaPrezzo(){
        return this.prezzo;
    }
    /**
     * Restituisce il prezzo di affitto della proprietà sotto forma di intero
     *
     * @return int prezzo di affitto
     */
    public int getProprietaAffitto(){
        return this.affitto;
    }
    /**
     * Restituisce la proprietà sotto forma di stringa
     *
     * @return String prezzo di affitto
     */
    public String toString(){
        return ""+this.nome+"  (Prezzo: "+this.prezzo+"€, Affitto: "+this.affitto+"€)";
    }
}
