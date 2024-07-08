package it.unimol.monopoli.app.CustomExceptions;

/**
 * Exception con messaggio personalizzato in caso di nome vuoto
 * @author Davide Di Niro
 * @version 1.0
 */
public class NullNameException extends Exception {
    public NullNameException(){
        super("Il nome non può essere vuoto.");
    }
}
