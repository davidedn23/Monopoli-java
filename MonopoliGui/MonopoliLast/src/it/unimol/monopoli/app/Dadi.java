package it.unimol.monopoli.app;

import java.util.Random;

/**
 * Classe che si occupa del lancio di due dadi (min:1 e max:6) e se il i valori dei due dadi corrispondono ripete il
 * lancio per un massimo di 2 lanci totali.
 *
 * @author Davide Di Niro
 * @version 1.0
 */
public class Dadi {

    int[]nums={1,2,3,4,5,6};

    /**
     * Calcola il/i lancio/i e restituisce il risultato
     * @return int somma
     */
    public int lanciaDadi(){
        int lancio1=0;
        int lancio2=0;
        int somma=0;
        int rep = 0;
        Random random= new Random();

        do{
            do{
                lancio1 = random.nextInt(6);
            }while(lancio1==0);
            do {
                lancio2 = random.nextInt(6);
            }while(lancio2==0);
            somma+=lancio1+lancio2;
            if(lancio1==lancio2)
                rep++;
        }while((lancio1==lancio2)&&(rep)<1);

        return somma;
    }
}
