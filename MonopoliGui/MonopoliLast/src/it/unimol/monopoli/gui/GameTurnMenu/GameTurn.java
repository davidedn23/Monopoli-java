package it.unimol.monopoli.gui.GameTurnMenu;

import it.unimol.monopoli.app.Cronometro.Cronometro;
import it.unimol.monopoli.app.Dadi;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;
import it.unimol.monopoli.app.Proprieta;
import it.unimol.monopoli.gui.PrisonerMenu.PrisonerMenuDialog;
import it.unimol.monopoli.gui.RentPaymentMenu.RentPaymentMenuDialog;
import it.unimol.monopoli.gui.TaxPaymentMenu.TaxPaymentMenuDialog;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ConcurrentModificationException;

public class GameTurn {
    private final JDialog jDialog;
    private int turnCounter;
    private GestoreProprieta contratti;
    private GestorePlayer players;
    private JPanel panel;
    private JButton terminaTurnoButton;
    private JButton vaiInPrigioneButton;
    private JButton pagaAffittoButton;
    private JButton compraProprietaButton;
    private JButton pagaTassaButton;
    private JTextArea textArea1;
    private JLabel currentPlayerLabel;
    private JButton stazioneSudButton;
    private JButton parcoDellaVittoriaButton;
    private JButton vialeDeiGiardiniButton;
    private JButton piazzaDanteButton;
    private JButton largoColomboButton;
    private JButton stazioneNordButton;
    private JButton stazioneEstButton;
    private JButton vicoloCortoButton;
    private JButton viaButton;
    private JButton viaVerdiButton;
    private JButton piazzaUniversitaButton;
    private JButton corsoAteneoButton;
    private JButton viaAccademiaButton;
    private JButton vialeVesuvioButton;
    private JButton vialeMonterosaButton;
    private JButton bastioniGranSassoButton;
    private JButton prigioneButton;
    private JButton vicoloStrettoButton;
    private JButton piazzaGiulioCesareButton;
    private JButton corsoRaffaelloButton;
    private JButton vialeCostantinoButton;
    private JButton stazioneOvestButton;
    private JButton corsoMagellanoButton;
    private JButton vaiInPrigioneButton1;
    private JButton largoAugustoButton;
    private JButton viaRomaButton;
    private JButton viaMarcoPoloButton;
    private JButton vialeTraianoButton;
    private JButton corsoImperoButton;
    private JButton lanciaIDadiButton;
    private JFormattedTextField timerTextField;
    private JLabel timerLabel;
    private Cronometro cronometro;

    public GameTurn(JDialog jDialog, GestorePlayer players, int turnCounter, GestoreProprieta contratti) {
        this.jDialog = jDialog;
        this.players = players;
        this.turnCounter = turnCounter;
        this.contratti = contratti;
        this.cronometro= new Cronometro(this.timerTextField,getGameTurn(),this.jDialog);
        this.cronometro.start();
        fileSave();
        fileSaveContratti();
        showCurrentPosition();
        textArea1.setText(this.players.getCurrentPlayer(this.turnCounter).toString());
        checkBankrupt();
        checkPrisoner();

        compraProprietaButton.addActionListener(actionListener -> handleCompraProprietaButton());

        lanciaIDadiButton.addActionListener(actionListener -> handleTiraDadiButtonClick());

        pagaTassaButton.addActionListener(actionListener -> handlePagaTassaButtonClick());

        terminaTurnoButton.addActionListener(actionListener -> handleTerminaButtonClick());

        pagaAffittoButton.addActionListener(actionListener -> handlePagaAffittoButtonClick());

        vaiInPrigioneButton.addActionListener(actionListener -> {
                    resetCurrentPosition();
                    this.players.getCurrentPlayer(this.turnCounter).setPlayerPosizione(7);
                    showCurrentPosition();
                    checkPrisoner();
                }
        );
    }

    private void checkPrisoner() {
        if(this.players.getCurrentPlayer(this.turnCounter).getPlayerPosizione()==7) {
            this.cronometro.setPause(true);
            this.jDialog.setVisible(false);
            new PrisonerMenuDialog(this.players,this.contratti,this.turnCounter,this.jDialog,this.cronometro,getGameTurn());
        }
    }
    private void checkBankrupt(){
        if(this.players.getCurrentPlayer(this.turnCounter).getPlayerSoldi()<=0) {
            JOptionPane.showMessageDialog(jDialog,this.players.getCurrentPlayer(this.turnCounter).getPlayerNome()+" è in bancarotta!");
            handleTerminaButtonClick();
        }
    }
    private void handlePagaAffittoButtonClick() {
        this.jDialog.setVisible(false);
        this.cronometro.setPause(true);
        new RentPaymentMenuDialog("Paga tassa",this.players,this.turnCounter,this.contratti,this.textArea1,this.jDialog,this.cronometro);
    }
    public void handleCompraProprietaButton(){
            String key= getCurrentPositionString();
            try {
                if (this.contratti.isThereProprieta(key)) {
                    Proprieta proprieta = this.contratti.getProprieta(key);
                    this.players.getCurrentPlayer(this.turnCounter).removePlayerSoldi(proprieta.getProprietaPrezzo());
                    this.players.getCurrentPlayer(this.turnCounter).addPlayerPossedimenti(proprieta);
                    textArea1.setText(this.players.getCurrentPlayer(this.turnCounter).toString());
                    JOptionPane.showMessageDialog(jDialog, "Proprietà acquistata");
                    this.contratti.removeProprieta(key);
                } else JOptionPane.showMessageDialog(jDialog, "Proprietà non disponibile");
            }catch (ConcurrentModificationException ignore){}

        }
    public void handlePagaTassaButtonClick(){
        this.jDialog.setVisible(false);
        this.cronometro.setPause(true);
        new TaxPaymentMenuDialog("Paga tassa",this.players,this.turnCounter,this.contratti,this.textArea1,this.jDialog,this.cronometro);
    }
    public void handleTerminaButtonClick(){

        if(this.players.getCurrentPlayer(this.turnCounter).getPlayerSoldi()<=0) {
            for (Proprieta p:this.players.getCurrentPlayer(this.turnCounter).getPlayerPossedimenti()) {
                contratti.addProprieta(p);
            }
            resetCurrentPosition();
            players.removePlayer(this.turnCounter);
            fileSave();
            fileSaveContratti();
        }
        if (players.getNumPlayers()<2){
            JOptionPane.showMessageDialog(jDialog,""+this.players.getCurrentPlayer(0).getPlayerNome()+" ha vinto la partita!!!");
            deleteFile();
            deleteDeedsFile();
            closeDialogWindow();
            this.cronometro.stop();
        }
        else {
            fileSave();
            fileSaveContratti();
            resetCurrentPosition();
            if(this.turnCounter<players.getNumPlayers()-1)
                this.turnCounter++;
            else this.turnCounter=0;
            showCurrentPosition();
            checkPrisoner();
            textArea1.setText(this.players.getCurrentPlayer(this.turnCounter).toString());
            lanciaIDadiButton.setEnabled(true);
            this.cronometro.resetTimer();
        }
    }
    public void handleTiraDadiButtonClick(){
        Dadi dadi =new Dadi();
        int lancio = dadi.lanciaDadi();
        resetCurrentPosition();
        this.players.getCurrentPlayer(this.turnCounter).playerGoForward(lancio);
        JOptionPane.showMessageDialog(jDialog,"Hai rollato "+lancio);
        showCurrentPosition();
        this.lanciaIDadiButton.setEnabled(false);
        checkPrisoner();
    }
    public String getCurrentPositionString(){
        String s ="";
        switch(this.players.getCurrentPlayer(this.turnCounter).getPlayerPosizione()) {
            case 0:
                s=this.viaButton.getText();
                break;
            case 1:
                s= this.vicoloCortoButton.getText();
                break;
            case 2:
                s= this.vicoloStrettoButton.getText();
                break;
            case 3:
                s= this.stazioneSudButton.getText();
                break;
            case 4:
                s= this.bastioniGranSassoButton.getText();
                break;
            case 5:
                s= this.vialeMonterosaButton.getText();
                break;
            case 6:
                s= this.vialeVesuvioButton.getText();
                break;
            case 7:
                s= this.prigioneButton.getText();
                break;
            case 8:
                s= this.viaAccademiaButton.getText();
                break;
            case 9:
                s= this.corsoAteneoButton.getText();
                break;
            case 10:
                s= this.stazioneEstButton.getText();
                break;
            case 11:
                s= this.piazzaUniversitaButton.getText();
                break;
            case 12:
                s= this.viaVerdiButton.getText();
                break;
            case 13:
                s= this.corsoRaffaelloButton.getText();
                break;
            case 14:
                s= this.piazzaDanteButton.getText();
                break;
            case 15:
                s= this.viaMarcoPoloButton.getText();
                break;
            case 16:
                s= this.corsoMagellanoButton.getText();
                break;
            case 17:
                s= this.largoColomboButton.getText();
                break;
            case 18:
                s= this.stazioneNordButton.getText();
                break;
            case 19:
                s= this.viaRomaButton.getText();
                break;
            case 20:
                s= this.vialeCostantinoButton.getText();
                break;
            case 21:
                s= this.vaiInPrigioneButton1.getText();
                this.players.getCurrentPlayer(this.turnCounter).setPlayerPosizione(7);
                break;
            case 22:
                s= this.piazzaGiulioCesareButton.getText();
                break;
            case 23:
                s= this.corsoImperoButton.getText();
                break;
            case 24:
                s= this.largoAugustoButton.getText();
                break;
            case 25:
                s= this.stazioneOvestButton.getText();
                break;
            case 26:
                s= this.vialeDeiGiardiniButton.getText();
                break;
            case 27:
                s= this.parcoDellaVittoriaButton.getText();
                break;
            case 28:
                s= this.vialeTraianoButton.getText();
                break;
        }
        return s;
    }
    public void showCurrentPosition(){
        switch(this.players.getCurrentPlayer(this.turnCounter).getPlayerPosizione()) {
            case 0:
                this.viaButton.setEnabled(false);
                break;
            case 1:
                this.vicoloCortoButton.setEnabled(false);
                break;
            case 2:
                this.vicoloStrettoButton.setEnabled(false);
                break;
            case 3:
                this.stazioneSudButton.setEnabled(false);
                break;
            case 4:
                this.bastioniGranSassoButton.setEnabled(false);
                break;
            case 5:
                this.vialeMonterosaButton.setEnabled(false);
                break;
            case 6:
                this.vialeVesuvioButton.setEnabled(false);
                break;
            case 7:
                this.prigioneButton.setEnabled(false);
                break;
            case 8:
                this.viaAccademiaButton.setEnabled(false);
                break;
            case 9:
                this.corsoAteneoButton.setEnabled(false);
                break;
            case 10:
                this.stazioneEstButton.setEnabled(false);
                break;
            case 11:
                this.piazzaUniversitaButton.setEnabled(false);
                break;
            case 12:
                this.viaVerdiButton.setEnabled(false);
                break;
            case 13:
                this.corsoRaffaelloButton.setEnabled(false);
                break;
            case 14:
                this.piazzaDanteButton.setEnabled(false);
                break;
            case 15:
                this.viaMarcoPoloButton.setEnabled(false);
                break;
            case 16:
                this.corsoMagellanoButton.setEnabled(false);
                break;
            case 17:
                this.largoColomboButton.setEnabled(false);
                break;
            case 18:
                this.stazioneNordButton.setEnabled(false);
                break;
            case 19:
                this.viaRomaButton.setEnabled(false);
                break;
            case 20:
                this.vialeCostantinoButton.setEnabled(false);
                break;
            case 21:
                this.vaiInPrigioneButton1.setEnabled(false);
                this.players.getCurrentPlayer(this.turnCounter).setPlayerPosizione(7);
                break;
            case 22:
                this.piazzaGiulioCesareButton.setEnabled(false);
                break;
            case 23:
                this.corsoImperoButton.setEnabled(false);
                break;
            case 24:
                this.largoAugustoButton.setEnabled(false);
                break;
            case 25:
                this.stazioneOvestButton.setEnabled(false);
                break;
            case 26:
                this.vialeDeiGiardiniButton.setEnabled(false);
                break;
            case 27:
                this.parcoDellaVittoriaButton.setEnabled(false);
                break;
            case 28:
                this.vialeTraianoButton.setEnabled(false);
                break;
        }
    }
    public void resetCurrentPosition(){
        switch(this.players.getCurrentPlayer(this.turnCounter).getPlayerPosizione()) {
            case 0:
                this.viaButton.setEnabled(true);
                break;
            case 1:
                this.vicoloCortoButton.setEnabled(true);
                break;
            case 2:
                this.vicoloStrettoButton.setEnabled(true);
                break;
            case 3:
                this.stazioneSudButton.setEnabled(true);
                break;
            case 4:
                this.bastioniGranSassoButton.setEnabled(true);
                break;
            case 5:
                this.vialeMonterosaButton.setEnabled(true);
                break;
            case 6:
                this.vialeVesuvioButton.setEnabled(true);
                break;
            case 7:
                this.prigioneButton.setEnabled(true);
                break;
            case 8:
                this.viaAccademiaButton.setEnabled(true);
                break;
            case 9:
                this.corsoAteneoButton.setEnabled(true);
                break;
            case 10:
                this.stazioneEstButton.setEnabled(true);
                break;
            case 11:
                this.piazzaUniversitaButton.setEnabled(true);
                break;
            case 12:
                this.viaVerdiButton.setEnabled(true);
                break;
            case 13:
                this.corsoRaffaelloButton.setEnabled(true);
                break;
            case 14:
                this.piazzaDanteButton.setEnabled(true);
                break;
            case 15:
                this.viaMarcoPoloButton.setEnabled(true);
                break;
            case 16:
                this.corsoMagellanoButton.setEnabled(true);
                break;
            case 17:
                this.largoColomboButton.setEnabled(true);
                break;
            case 18:
                this.stazioneNordButton.setEnabled(true);
                break;
            case 19:
                this.viaRomaButton.setEnabled(true);
                break;
            case 20:
                this.vialeCostantinoButton.setEnabled(true);
                break;
            case 21:
                this.vaiInPrigioneButton1.setEnabled(true);
                this.players.getCurrentPlayer(this.turnCounter).setPlayerPosizione(7);
                break;
            case 22:
                this.piazzaGiulioCesareButton.setEnabled(true);
                break;
            case 23:
                this.corsoImperoButton.setEnabled(true);
                break;
            case 24:
                this.largoAugustoButton.setEnabled(true);
                break;
            case 25:
                this.stazioneOvestButton.setEnabled(true);
                break;
            case 26:
                this.vialeDeiGiardiniButton.setEnabled(true);
                break;
            case 27:
                this.parcoDellaVittoriaButton.setEnabled(true);
                break;
            case 28:
                this.vialeTraianoButton.setEnabled(true);
                break;
        }
    }

    public Component getPanel() {
        return this.panel;
    }
    public void closeDialogWindow(){
        jDialog.dispose();
    }
    public void deleteFile(){
        try {
            Files.deleteIfExists(
                    Paths.get(("C:\\Users\\public\\PlayersList.bin")));
        }
        catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        }
        catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        }
        catch (IOException e) {
            System.out.println("Invalid permissions.");
        }
    }
    public void deleteDeedsFile(){
        try {
            Files.deleteIfExists(
                    Paths.get(("C:\\Users\\public\\DeedsList.bin")));
        }
        catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        }
        catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        }
        catch (IOException e) {
            System.out.println("Invalid permissions.");
        }
    }
    private void fileSave(){
        try (
                FileOutputStream f = new FileOutputStream("C:\\Users\\public\\PlayersList.bin");
                ObjectOutputStream o = new ObjectOutputStream(f);
        ){
            o.writeObject(this.players);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private void fileSaveContratti(){
        try (
                FileOutputStream f = new FileOutputStream("C:\\Users\\public\\DeedsList.bin");
                ObjectOutputStream o = new ObjectOutputStream(f);
        ){
            o.writeObject(this.contratti);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private GameTurn getGameTurn(){
        return this;
    }


}
