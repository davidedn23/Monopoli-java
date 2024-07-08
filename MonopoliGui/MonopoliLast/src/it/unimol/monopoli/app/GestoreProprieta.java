package it.unimol.monopoli.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Classe che si occupa della gestione delle proprietà presenti
 *
 * @author Davide Di Niro
 * @version 1.0
 */

public class GestoreProprieta implements Serializable {
    private Map<String, Proprieta> contratti;
    ArrayList <String> nomiProprieta ;

    /**
     * Crea le proprietà da gestire,una Mappa in cui inserirle e un ArrayList di nomi di tutte le proprietà
     */
    public GestoreProprieta(){
        nomiProprieta = new ArrayList<>();
        contratti = new HashMap<>();

        Proprieta vicoloCorto = new Proprieta("Vicolo corto",60,2);
        Proprieta vicoloStretto = new Proprieta("Vicolo stretto",60,4);
        Proprieta bastioniGranSasso = new Proprieta("Bastioni Gran Sasso",100,6);
        Proprieta vialeMonterosa = new Proprieta("Viale Monterosa",100,6);
        Proprieta vialeVesuvio = new Proprieta("Viale Vesuvio",120,8);
        Proprieta viaAccademia = new Proprieta("Via Accademia",140,10);
        Proprieta corsoAteneo = new Proprieta("Corso Ateneo",140,10);
        Proprieta piazzaUniversita = new Proprieta("Piazza Università", 160,12);
        Proprieta viaVerdi = new Proprieta("Via Verdi",180,14);
        Proprieta corsoRaffaello = new Proprieta("Corso Raffaello",180,14);
        Proprieta piazzaDante = new Proprieta("Piazza Dante",200,16);
        Proprieta viaMarcoPolo = new Proprieta("Via Marco Polo",220,18);
        Proprieta corsoMagellano = new Proprieta("Corso Magellano",220,18);
        Proprieta largoColombo = new Proprieta("Largo Colombo",240,20);
        Proprieta vialeCostantino = new Proprieta("Viale Costantino",260,22);
        Proprieta vialeTraiano =new Proprieta("Viale Traiano",260,22);
        Proprieta piazzaGiulioCesare = new Proprieta("Piazza Giulio Cesare",280,24);
        Proprieta viaRoma = new Proprieta("Via Roma",300,26);
        Proprieta corsoImpero = new Proprieta("Corso Impero",300,26);
        Proprieta largoAugusto = new Proprieta("Largo Augusto",320,28);
        Proprieta vialeDeiGiardini = new Proprieta("Viale dei Giardini",350,35);
        Proprieta parcoDellaVittoria = new Proprieta("Parco della Vittoria",400,400);
        Proprieta stazioneSud = new Proprieta("Stazione Sud",200,25);
        Proprieta stazioneNord = new Proprieta("Stazione Nord",200,25);
        Proprieta stazioneEst = new Proprieta("Stazione Est",200,25);
        Proprieta stazioneOvest = new Proprieta("Stazione Ovest",200,25);


        contratti.put("Vicolo Corto",vicoloCorto);
        contratti.put("Vicolo Stretto",vicoloStretto);
        contratti.put("Bastioni Gran Sasso",bastioniGranSasso);
        contratti.put("Viale Monterosa",vialeMonterosa);
        contratti.put("Viale Vesuvio",vialeVesuvio);
        contratti.put("Via Accademia",viaAccademia);
        contratti.put("Corso Ateneo",corsoAteneo);
        contratti.put("Piazza Università",piazzaUniversita);
        contratti.put("Via Verdi",viaVerdi);
        contratti.put("Corso Raffaello",corsoRaffaello);
        contratti.put("Piazza Dante",piazzaDante);
        contratti.put("Via Marco Polo",viaMarcoPolo);
        contratti.put("Corso Magellano",corsoMagellano);
        contratti.put("Largo Colombo",largoColombo);
        contratti.put("Viale Costantino",vialeCostantino);
        contratti.put("Viale Traiano",vialeTraiano);
        contratti.put("Piazza Giulio Cesare",piazzaGiulioCesare);
        contratti.put("Via Roma",viaRoma);
        contratti.put("Corso Impero",corsoImpero);
        contratti.put("Largo Augusto",largoAugusto);
        contratti.put("Viale dei Giardini",vialeDeiGiardini);
        contratti.put("Parco della Vittoria",parcoDellaVittoria);
        contratti.put("Stazione Sud",stazioneSud);
        contratti.put("Stazione Nord",stazioneNord);
        contratti.put("Stazione Est",stazioneEst);
        contratti.put("Stazione Ovest",stazioneOvest);

        nomiProprieta.add("Vicolo Corto");
        nomiProprieta.add("Vicolo Stretto");
        nomiProprieta.add("Bastioni Gran Sasso");
        nomiProprieta.add("Viale Monterosa");
        nomiProprieta.add("Viale Vesuvio");
        nomiProprieta.add("Via Accademia");
        nomiProprieta.add("Corso Ateneo");
        nomiProprieta.add("Piazza Università");
        nomiProprieta.add("Via Verdi");
        nomiProprieta.add("Corso Raffaello");
        nomiProprieta.add("Piazza Dante");
        nomiProprieta.add("Via Marco Polo");
        nomiProprieta.add("Corso Magellano");
        nomiProprieta.add("Largo Colombo");
        nomiProprieta.add("Viale Costantino");
        nomiProprieta.add("Viale Traiano");
        nomiProprieta.add("Piazza Giulio Cesare");
        nomiProprieta.add("Via Roma");
        nomiProprieta.add("Corso Impero");
        nomiProprieta.add("Largo Augusto");
        nomiProprieta.add("Viale dei Giardini");
        nomiProprieta.add("Parco della Vittoria");
        nomiProprieta.add("Stazione Sud");
        nomiProprieta.add("Stazione Nord");
        nomiProprieta.add("Stazione Est");
        nomiProprieta.add("Stazione Ovest");
    }

    /**
     * Dato un nome, restituisce la proprietà con quel nome
     *
     * @param nome String del nome
     * @return Proprieta p associata alla stringa nome
     */
    public Proprieta getProprieta(String nome){
        return contratti.get(nome);
    }
    /*public Proprieta getProprieta(int posizione){
        return contratti.get(posizione);
    }*/

    /**
     * Restituisce un nome  caso tra quelli presenti nella lista dei nomi delle proprieta e
     * poi lo rimuove da quest'ultima
     *
     * @return String nome in posizione randomName
     */
    public String getProprietaRandomKey(){
        Random random = new Random();
        String randomName=nomiProprieta.get(random.nextInt(nomiProprieta.size()));
        nomiProprieta.remove(randomName);
        return randomName;
    }

    /**
     * Confronta i nomi associati a tutte le proprietà della mappa e quando
     * trova quello uguale , rimuove la proprietà corrispondente
     *
     * @param nome String da confrontare
     */
    public void removeProprieta(String nome){
        contratti.remove(nome);
        for (String s:nomiProprieta) {
            if(s.equals(nome))
                nomiProprieta.remove(s);
        }
    }

    /**
     * Restituisce true se esiste una proprietà associata alla stringa nome, altrimenti false
     *
     * @param nome String da cercare
     * @return true se esiste, false se non esiste
     */
    public boolean isThereProprieta(String nome){
        return contratti.containsKey(nome);
    }

    /**
     * Restituisce la lista dei nomi delle proprietà sotto forma di ArrayList
     *
     * @return ArrayList di nomi
     */
    public ArrayList getNomiProprieta(){
        return this.nomiProprieta;
    }

    /**
     * Data una proprietà la aggiunge alla mappa e ne aggiunge il suo nome alla lista dei nomi
     * @param p Proprieta da aggiungere
     */
    public void addProprieta(Proprieta p){
        contratti.put(p.getProprietaNome(),p);
        nomiProprieta.add(p.getProprietaNome());
    }
    /*
    public String nomiProprietaString(){
        String s="";
        for (String string:nomiProprieta) {
            s+=string+"\n";
        }
        return s;
    }*/
}
