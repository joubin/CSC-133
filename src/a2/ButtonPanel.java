package a2;

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
        this.setPreferredSize(new Dimension(200, 700));
        this.setLayout(new GridLayout(11, 1));
        this.setBorder(new TitledBorder("Commands"));
        addButtons();
    }

    public void addButtons() {
       /*
       Setup the quit button.
        */
        CommandHelp help = CommandHelp.getInstance();
        CommandAbout about = CommandAbout.getInstance();
        CommandQuit quit = CommandQuit.getInstance();

        JButton helpButton = new JButton(help);
        this.add(helpButton);
        JButton aboutButton = new JButton(about);
        this.add(aboutButton);
        JButton quitButton = new JButton(quit);
        this.add(quitButton);


    }
}
