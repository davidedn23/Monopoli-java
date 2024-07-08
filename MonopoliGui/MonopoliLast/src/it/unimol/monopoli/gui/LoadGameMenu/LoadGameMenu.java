package it.unimol.monopoli.gui.LoadGameMenu;

import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.app.GestoreProprieta;
import it.unimol.monopoli.gui.GameTurnMenu.GameTurnDialog;
import it.unimol.monopoli.gui.MainMenu.MainMenuDialog;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadGameMenu {
    private JTextArea playerTextArea;
    private JButton startMatchButton;
    private JLabel playerListLabel;
    private JPanel panel;
    private JButton indietroButton;
    private JDialog jDialog;
    private GestorePlayer players;
    private GestoreProprieta contratti;

    public LoadGameMenu(JDialog jDialog) {
        players=load();
        contratti=loadProprieta();

        this.playerTextArea.setText(players.toString());
        this.jDialog= jDialog;



        if(players.getNumPlayers()<=1)
            startMatchButton.setEnabled(false);
        else startMatchButton.setEnabled(true);

        startMatchButton.addActionListener(actionListener-> {
            closeDialogWindow();
            new GameTurnDialog(players, contratti,0);
        });
        indietroButton.addActionListener(actionListener->{
            closeDialogWindow();
            new MainMenuDialog();
        });
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
            this.playerTextArea.setText("\n\n\n\n\n\n\n\nNessuna partita in memoria");
            return new GestorePlayer();
        } catch (ClassNotFoundException ignore) {
            return null;
        } catch (IOException e) {
            return load();
        }
    }
    private GestoreProprieta loadProprieta(){
        try (
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\public\\DeedsList.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            Object o = objectInputStream.readObject();
            objectInputStream.close();
            return (GestoreProprieta) o;
        } catch (FileNotFoundException e) {
            return new GestoreProprieta();
        } catch (ClassNotFoundException ignore) {
            return null;
        } catch (IOException e) {
            return loadProprieta();
        }
    }
    public void closeDialogWindow(){
        jDialog.dispose();
    }

    public Component getPanel(){
        return this.panel;
    }
}
