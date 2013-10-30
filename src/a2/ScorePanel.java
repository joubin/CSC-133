package a2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * User: joubin
 * Date: 10/11/13
 * Time: 10:08 PM
 */
public class ScorePanel extends JPanel implements IObserver {

    private JLabel clock = new JLabel("");
    private JLabel lives = new JLabel("");
    private JLabel score = new JLabel("");

    public ScorePanel() {
        this.setPreferredSize(new Dimension(1300, 50));
        this.setLayout(new GridLayout(1, 10));
        this.setBorder(new TitledBorder("Score"));

        addButtons();
    }

    public void addButtons() {
        JLabel livesLabel = new JLabel("Lives");
        JLabel clockLabel = new JLabel("Clock");
        JLabel scoreLabel = new JLabel("Score");
        add(livesLabel);
        add(lives);
        add(clockLabel);
        add(clock);
        add(scoreLabel);
        add(score);
    }

    @Override
    public void update(IObservable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
