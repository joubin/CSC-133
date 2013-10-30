package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandTick extends AbstractAction {

    GameWorldProxy gwProxy;
    private static CommandTick _TickCommand = null;

    public CommandTick(){
        super("Tick");
    }

    /**
     * Ensure that it is the only instance of the command
     * @return CommandTick
     */
    public static synchronized CommandTick getInstance(){
        if (_TickCommand == null) _TickCommand = new CommandTick();
        return _TickCommand;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwProxy.tick();
    }
}
