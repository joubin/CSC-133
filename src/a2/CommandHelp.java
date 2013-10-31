package a2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * User: joubin
 */
public class CommandHelp extends AbstractAction {

    GameWorld gwProxy;
    private static CommandHelp theCommandHelp = null;

    /**
     * Pass the string help to AbstractAction as its title
     */
    public CommandHelp(){
        super("Help");
    }



    public static synchronized CommandHelp getInstance(){
        if(theCommandHelp == null){
            theCommandHelp = new CommandHelp();
        }
        return theCommandHelp;
    }


    public synchronized void actionPerformed(ActionEvent e){

        // TODO pause the game
        URL pathToImage = this.getClass().getResource("/a2/Resources/tank.png");

        final ImageIcon icon = new ImageIcon(pathToImage);

        JOptionPane.showMessageDialog(null, "r: Turn right 5 degrees clockwise\n" +
                "l: Turn left 5 degrees counter-clockwise\n" +
                "i: Increase speed of your tank\n" +
                "k: Decrease speed of your tank\n" +
                "f: Fire missile from your tank\n" +
                "e: Fire missile from enemy tank\n" +
                "1: Indicate that a random tank has been hit by a missile\n" +
                "2: indicates that a collision occurred between two missiles\n" +
                "3: Random tank has colided with a land object and is now blocked\n" +
                "t: Tick game clock\n" +
                "d: Display current game states and values\n" +
                "m: Display map for the current game\n" +
                "q: Quit game\n" +
                "?: Print this menu ", "Help", JOptionPane.INFORMATION_MESSAGE, icon);

        System.out.println("Help From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}