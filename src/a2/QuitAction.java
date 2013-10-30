package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 8:29 PM
 */
public class QuitAction extends AbstractAction {
    private static QuitAction quitAction = null;

    public QuitAction() {
        super("Quit");
    }

    public synchronized static QuitAction getInstance() {
        if (quitAction == null) quitAction = new QuitAction();
        return quitAction;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Quitting: " + e.getSource());

        int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        return;
    }
}
