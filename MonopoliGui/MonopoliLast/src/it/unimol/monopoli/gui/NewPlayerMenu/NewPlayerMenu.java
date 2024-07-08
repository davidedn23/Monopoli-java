package it.unimol.monopoli.gui.NewPlayerMenu;

import it.unimol.monopoli.app.CustomExceptions.MaxPlayerAddedException;
import it.unimol.monopoli.app.CustomExceptions.NullNameException;
import it.unimol.monopoli.app.CustomExceptions.PlayerAlreadyExistsException;
import it.unimol.monopoli.app.GestorePlayer;
import it.unimol.monopoli.gui.NewGameMenu.NewGameMenuDialog;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class NewPlayerMenu extends Component {
    private JLabel nameLabel;
    private JFormattedTextField formattedTextField1;
    private JComboBox comboBox1;
    private JLabel pedinaLabel;
    private JPanel panel;
    private JButton addPlayerButton;
    private JDialog jDialog;
    private GestorePlayer players;

    public NewPlayerMenu(JDialog jdialog, GestorePlayer players) {
        this.jDialog=jdialog;
        this.players=players;
        comboBox1.addItem("cane");
        comboBox1.addItem("auto");
        comboBox1.addItem("barca");
        comboBox1.addItem("scarpa");
        comboBox1.addItem("fiasco");
        comboBox1.addItem("gatto");
        comboBox1.addItem("moto");
        comboBox1.addItem("aereo");

        addPlayerButton.addActionListener(
                actionListener-> {
                    if(players.getNumPlayers()<6){
                        try {
                            if(formattedTextField1.getText().equals(""))
                                throw new NullNameException();
                            if (!players.addNewPlayer(formattedTextField1.getText(), (String) comboBox1.getSelectedItem()))
                                throw new PlayerAlreadyExistsException();
                        }catch(PlayerAlreadyExistsException e){
                            JOptionPane.showMessageDialog(jDialog,e.getMessage());
                        }catch (NullNameException e){
                            JOptionPane.showMessageDialog(jDialog,e.getMessage());
                        }
                        fileSave();
                        closeDialogWindow();
                        new NewGameMenuDialog();
                    }
                    else {
                        try{
                            throw new MaxPlayerAddedException();
                        }
                        catch(MaxPlayerAddedException e){
                            JOptionPane.showMessageDialog(jDialog, e.getMessage());
                            closeDialogWindow();
                            new NewGameMenuDialog();
                        }
                    }
                });

    }

    public void closeDialogWindow(){
        jDialog.dispose();
    }

    public Component getPanel(){
        return this.panel;
    }
    private void fileSave(){
        try (
                FileOutputStream f = new FileOutputStream("C:\\Users\\public\\PlayersList.bin");
                ObjectOutputStream o = new ObjectOutputStream(f);
        ){
            o.writeObject(players);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

}

