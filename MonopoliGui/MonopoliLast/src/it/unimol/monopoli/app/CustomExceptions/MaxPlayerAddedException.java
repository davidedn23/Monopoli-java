package it.unimol.monopoli.app.CustomExceptions;

/**
 * Exception con messaggio personalizzato in caso di troppi giocatori
 * @author Davide Di Niro
 * @version 1.0
 */
public class MaxPlayerAddedException extends Exception{
    public MaxPlayerAddedException() {
        super("Numero massimo di giocatori raggiunto: 6 giocatori.");
    }
}
