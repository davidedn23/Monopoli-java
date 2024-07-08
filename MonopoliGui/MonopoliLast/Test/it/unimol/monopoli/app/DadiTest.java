package it.unimol.monopoli.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DadiTest {

    @Test
    void lanciaDadi() {
        Dadi dado= new Dadi();
        int lancio=dado.lanciaDadi();
        if(lancio<=0)
            fail("lancio negativo o nullo");
    }
}