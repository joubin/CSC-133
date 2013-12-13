package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * User: joubin
 */
public class CommandHelp extends AbstractAction {

    private static CommandHelp theCommandHelp = null;
    private static Game game;
    private static GameWorld gameWorld;

    /**
     * Pass the string help to AbstractAction as its title
     */
    public CommandHelp() {
        super("Help");
    }

    public void target(GameWorld gw, Game g) {
        this.gameWorld = gw;
        this.game = g;
    }

    public static synchronized CommandHelp getInstance(Game gw) {
        game = gw;
        if (theCommandHelp == null) {
            theCommandHelp = new CommandHelp();
        }
        return theCommandHelp;
    }


    public synchronized void actionPerformed(ActionEvent e) {
        gameWorld.toggleTimer();
        game.togglePauseButton();
        if (gameWorld.getTimerStat())
            game.changeTextOnPauseButton("Pause");
        else game.changeTextOnPauseButton("Play");

        // TODO pause the game
        URL pathToImage = this.getClass().getResource("/a4/Resources/tank.png");

        final ImageIcon icon = new ImageIcon(pathToImage);

        JOptionPane.showMessageDialog(null, "Right Arrow: Turn right 5 degrees clockwise\n" +
                "Left Arrow: Turn left 5 degrees counter-clockwise\n" +
                "Up Arrow: Increase speed of your tank\n" +
                "Down Arrow: Decrease speed of your tank\n" +
                "Space: Fire missile from your tank\n", "Help", JOptionPane.INFORMATION_MESSAGE, icon);

        System.out.println("Help From " + e.getActionCommand() + " " + e.getSource().getClass());
        game.requestMapViewFocus();

    }
}