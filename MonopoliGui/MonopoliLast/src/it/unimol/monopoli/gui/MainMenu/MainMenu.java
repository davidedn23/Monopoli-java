package it.unimol.monopoli.gui.MainMenu;

import it.unimol.monopoli.gui.LoadGameMenu.LoadGameMenuDialog;
import it.unimol.monopoli.gui.NewGameMenu.NewGameMenuDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class MainMenu extends JPanel{

    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton exitButton;
    private JPanel panel;
    private JDialog jDialog;

    public MainMenu(JDialog jDialog){
        this.jDialog= jDialog;
        newGameButton.addActionListener(actionListener->{
            deleteFile();
            closeDialogWindow();
            new NewGameMenuDialog();
        });
        loadGameButton.addActionListener(actionListener-> {
            closeDialogWindow();
            new LoadGameMenuDialog();
        });
        exitButton.addActionListener(actionListener-> closeDialogWindow());
    }

    public void closeDialogWindow(){
        jDialog.dispose();
    }

    public Component getPanel(){
        return this.panel;
    }
    public void deleteFile(){
        try {
            Files.deleteIfExists(Paths.get(("C:\\Users\\public\\PlayersList.bin")));
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

