package it.unimol.monopoli.app.CustomExceptions;
/**
 * Exception con messaggio personalizzato in caso di giocatore già registrato
 * @author Davide Di Niro
 * @version 1.0
 */
public class PlayerAlreadyExistsException extends Exception{
    public PlayerAlreadyExistsException(){
        super("Giocatore con questo nome o pedina già presente");
    }
}
