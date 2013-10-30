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
    private GameWorldProxy gwp = null;
    public ButtonPanel(GameWorldProxy gwp) {
        this.gwp =  gwp;
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
        CommandTick tick = CommandTick.getInstance();
        tick.target(gwp);
        JButton tickButton = new JButton(tick);
        JButton helpButton = new JButton(CommandHelp.getInstance());
        JButton quitButton = new JButton(CommandQuit.getInstance());

        this.add(tickButton);
        this.add(helpButton);
        this.add(quitButton);



    }
}
