package a2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 8:29 PM
 */
public class CommandQuit extends AbstractAction {
    private static CommandQuit quitAction = null;

    public CommandQuit() {
        super("Quit");
    }

    public synchronized static CommandQuit getInstance() {
        if (quitAction == null) quitAction = new CommandQuit();
        return quitAction;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Quitting: " + e.getSource());
        URL pathToImage = this.getClass().getResource("/a2/Resources/tank.png");
        final ImageIcon icon = new ImageIcon(pathToImage);

        int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        return;
    }
}
