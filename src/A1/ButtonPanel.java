package A1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 8:20 PM
 */
public class ButtonPanel extends JPanel {

    public ButtonPanel() {
        this.setPreferredSize(new Dimension(300, 700));
        this.setLayout(new GridLayout(11, 1));
        this.setBorder(new TitledBorder("Commands"));
        addButtons();
    }

    public void addButtons() {
       /*
       Setup the quit button.
        */
        QuitAction quit = QuitAction.getInstance();
        JButton quitButton = new JButton(quit);
        this.add(quitButton);

    }
}
