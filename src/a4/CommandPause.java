package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandPause extends AbstractAction {
    private static CommandPause commandPause = null;
    private GameWorld gameWorld;
    private Game game;


    public CommandPause() {
        super("Pause");
    }

    public synchronized static CommandPause getInstance() {
        if (commandPause == null) commandPause = new CommandPause();
        return commandPause;
    }

    public void target(GameWorld gw, Game g) {
        this.gameWorld = gw;
        this.game = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.toggleTimer();
        game.togglePauseButton();
        if (gameWorld.getTimerStat())
            game.changeTextOnPauseButton("Pause");
        else game.changeTextOnPauseButton("Play");


        System.out.println("CommandReverse " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
