package it.unimol.monopoli.gui.NewGameMenu;

import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;
import it.unimol.monopoli.app.Player;
import it.unimol.monopoli.app.Proprieta;
import it.unimol.monopoli.gui.GameTurnMenu.GameTurnDialog;
import it.unimol.monopoli.gui.NewPlayerMenu.NewPlayerMenuDialog;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class NewGameMenu {
    private JDialog jDialog;
    private JPanel panel;
    private JLabel playerLabel;
    private JButton addPlayerButton;
    private JButton startMatchButton;
    private JTextArea playerTextArea;

    private GestorePlayer players;
    private GestoreProprieta contratti = new GestoreProprieta();

    public NewGameMenu(JDialog jDialog) {
        players=load();
        this.jDialog= jDialog;
        this.playerTextArea.setText(players.toString());

        addPlayerButton.addActionListener(actionListener->{
            new NewPlayerMenuDialog(players);
            closeDialogWindow();
        });
        if(players.getNumPlayers()<=1)
            startMatchButton.setEnabled(false);
        else startMatchButton.setEnabled(true);

        startMatchButton.addActionListener(actionListener-> {
            deleteFile();
            deleteDeedsFile();
            players.playerSetInitialMoney();
            addRandomPropertiesToPlayer(players.getPlayerList());
            closeDialogWindow();
            new GameTurnDialog(players,contratti,0);
        });
    }

    public void closeDialogWindow(){
        jDialog.dispose();
    }

    public Component getPanel(){
        return this.panel;
    }
    private GestorePlayer load(){
        try (
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\public\\PlayersList.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            Object o = objectInputStream.readObject();
            objectInputStream.close();
            return (GestorePlayer) o;
        } catch (FileNotFoundException e) {
            return new GestorePlayer();
        } catch (ClassNotFoundException ignore) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    public void addRandomPropertiesToPlayer(List<Player> pls) {
        switch (pls.size()) {

            case 2:

                for (Player p : pls) {
                    for (int i = 1; i < 7; i++) {
                        String key = contratti.getProprietaRandomKey();
                        Proprieta proprieta = contratti.getProprieta(key);
                        p.removePlayerSoldi(proprieta.getProprietaPrezzo());
                        p.addPlayerPossedimenti(proprieta);
                        contratti.removeProprieta(key);
                    }
                }
                break;
            case 3:

                for (Player p : pls) {
                    for (int i = 1; i < 6; i++) {
                        String key = contratti.getProprietaRandomKey();
                        Proprieta proprieta = contratti.getProprieta(key);
                        p.removePlayerSoldi(proprieta.getProprietaPrezzo());
                        p.addPlayerPossedimenti(proprieta);
                        contratti.removeProprieta(key);
                    }
                }
                break;
            case 4:

                for (Player p : pls) {
                    for (int i = 1; i < 5; i++) {
                        String key = contratti.getProprietaRandomKey();
                        Proprieta proprieta = contratti.getProprieta(key);
                        p.removePlayerSoldi(proprieta.getProprietaPrezzo());
                        p.addPlayerPossedimenti(proprieta);
                        contratti.removeProprieta(key);
                    }
                }
                break;
            case 5:

                for (Player p : pls) {
                    for (int i = 1; i < 4; i++) {
                        String key = contratti.getProprietaRandomKey();
                        Proprieta proprieta = contratti.getProprieta(key);
                        p.removePlayerSoldi(proprieta.getProprietaPrezzo());
                        p.addPlayerPossedimenti(proprieta);
                        contratti.removeProprieta(key);
                    }
                }
                break;
            case 6:

                for (Player p : pls) {
                    for (int i = 1; i < 3; i++) {
                        String key = contratti.getProprietaRandomKey();
                        Proprieta proprieta = contratti.getProprieta(key);
                        p.removePlayerSoldi(proprieta.getProprietaPrezzo());
                        p.addPlayerPossedimenti(proprieta);
                        contratti.removeProprieta(key);
                    }
                }
                break;

        }
    }
    public void deleteDeedsFile(){
        try {
            Files.deleteIfExists(
                    Paths.get("C:\\Users\\public\\DeedsList.bin"));
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
    public void deleteFile(){
        try {
            Files.deleteIfExists(
                    Paths.get("C:\\Users\\public\\PlayersList.bin"));
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

}

