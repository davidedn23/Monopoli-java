package it.unimol.monopoli.app;

import it.unimol.monopoli.app.CustomExceptions.NullNameException;
import it.unimol.monopoli.app.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void getPlayerNome() {
        try{
            Player player=new Player("","cane");
            assertEquals("",player.getPlayerNome(),"Nome vuoto inserito con successo");
            throw new NullNameException();
        }catch(NullNameException e) {
            System.out.println(e.getMessage());
        }
    }
}