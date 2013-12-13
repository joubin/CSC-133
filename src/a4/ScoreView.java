package a4;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * User: joubin
 */
public class ScoreView extends JPanel implements IObserver {
    private JLabel gameTime = new JLabel("0");
    private JLabel score = new JLabel("0");
    private JLabel lives = new JLabel("10");
    private JLabel missile = new JLabel("100");
    private JLabel grenade = new JLabel("50");
    private JLabel plasma = new JLabel("10");
    private JLabel soundLable = new JLabel("ON");

    public ScoreView() {

        this.setPreferredSize(new Dimension(1024, 60)); // 200 ratio


        this.setBorder(new TitledBorder("Game Info"));
        this.add(new JLabel("Time: "));
        this.add(gameTime);
        this.add(Box.createHorizontalStrut(30));

        this.add(new JLabel("Score: "));
        this.add(score);
        this.add(Box.createHorizontalStrut(30));

        this.add(new JLabel("Lives: "));
        this.add(lives);
        this.add(Box.createHorizontalStrut(30));

        this.add(new JLabel("Missle Count: "));
        this.add(missile);
        this.add(Box.createHorizontalStrut(30));

        this.add(new JLabel("Grenade Count: "));
        this.add(grenade);
        this.add(Box.createHorizontalStrut(30));

        this.add(new JLabel("Plasma Energy: "));
        this.add(plasma);
        this.add(Box.createHorizontalStrut(30));

        this.add(new JLabel("Sound: "));
        this.add(soundLable);


    }

    @Override
    public void update(IObservable o, Object arg) {
        GameWorldProxy gwp = (GameWorldProxy) o;
        Integer myTime = gwp.getClock() / 50;
        Integer myScore = gwp.getScore();
        Integer myLives = gwp.getPlayerHealth();
        Boolean sound = gwp.getSound();
        int[] amo = gwp.getAmoCount();
        int missileC = amo[0];
        int plasmaE =  amo[1];
        int grenadeC =  amo[2];
//        Integer missileCount
        this.gameTime.setText("" + myTime);
        this.score.setText(myScore.toString());
        this.lives.setText(myLives.toString());
        this.grenade.setText(((Integer)grenadeC).toString());
        this.plasma.setText(((Integer)plasmaE).toString());
        this.missile.setText(((Integer) missileC).toString());
        if (sound == true) {
            soundLable.setText("ON");
        } else {
            soundLable.setText("OFF");
        }


    }
}
