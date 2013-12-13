package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * User: joubin
 */
public class CommandAbout extends AbstractAction {
    private static CommandAbout theAboutCommand = null;

    /**
     * Constructor to pass the name of the command to the parent AbstractAction to be used when
     * creating menu items
     */
    public CommandAbout() {
        super("About");
    }


    public static synchronized CommandAbout getInstance() {
        if (theAboutCommand == null) {
            theAboutCommand = new CommandAbout();
        }
        return theAboutCommand;
    }


    public synchronized void actionPerformed(ActionEvent e) {
//        URL pathToImage = this.getClass().getResource( File.separator+"a4"+File.separator+"Resources "+File.separator+"tank.png");

        String pathToTankLogo = "." + File.separator + "Resources" + File.separator + "tank.png";
        final ImageIcon icon = new ImageIcon(pathToTankLogo);
        System.out.println("About From " + e.getActionCommand() + " " + e.getSource().getClass());
//        PauseCommand.getInstance().action();
        JOptionPane.showMessageDialog(null, " Tank \n Author: Joubin Jabbari \n CSC 133 @ CSU, Sacramento \n Version a.2", "About", JOptionPane.INFORMATION_MESSAGE, icon);

    }
}
