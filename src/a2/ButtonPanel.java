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
    private GameWorld gameWorld = null;

    public ButtonPanel(GameWorld gwp) {
        this.gameWorld = gwp;
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
        CommandGetHitByMissile getHitByMissile = CommandGetHitByMissile.getInstance();
        CommandMissileHitMissile missileHitMissile = CommandMissileHitMissile.getInstance();
        CommandChangeStrategy changeStrategy = CommandChangeStrategy.getInstance();
        CommandPause pauseCommand = CommandPause.getInstance();
        CommandReverse commandReverse = CommandReverse.getInstance();
        changeStrategy.setTarget(gameWorld);
        missileHitMissile.setTarget(gameWorld);
        getHitByMissile.setTarget(gameWorld);
        tick.setTarget(gameWorld);
        commandReverse.target(gameWorld);
        pauseCommand.target(gameWorld);
        JButton missileHitMissileButton = new JButton(missileHitMissile);
        JButton tickButton = new JButton(tick);
        JButton getHit = new JButton(getHitByMissile);
        JButton helpButton = new JButton(CommandHelp.getInstance());
        JButton quitButton = new JButton(CommandQuit.getInstance());
        JButton changeStrategyButton = new JButton(changeStrategy);
        JButton pauseButton = new JButton(pauseCommand);
        JButton commandReverseButton = new JButton(commandReverse);

        this.add(pauseButton);
        this.add(commandReverseButton);
        this.add(changeStrategyButton);
        this.add(missileHitMissileButton);
        this.add(getHit);
        this.add(tickButton);
        this.add(helpButton);
        this.add(quitButton);
        if (gameWorld.getTimerStat()){
            pauseButton.setText("Pause");
        }else{
            pauseButton.setText("Play");
        }


    }
}
