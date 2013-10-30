package a2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * User: joubin
 */
public class ScoreView extends JPanel implements IObserver{
    private JLabel gameTime = new JLabel("");
    private JLabel score = new JLabel("");
    private JLabel lives = new JLabel("");
    private JLabel soundLable = new JLabel("");

    public ScoreView() {

        this.setPreferredSize(new Dimension(1024,60)); // 200 ratio


        this.setBorder(new TitledBorder("Game Info"));
        this.add(new JLabel("Time: "));
        this.add(gameTime);
        this.add( Box.createHorizontalStrut(30) );

        this.add(new JLabel("Score: "));
        this.add(score);
        this.add( Box.createHorizontalStrut(30) );

        this.add(new JLabel("Lives: "));
        this.add(lives);
        this.add( Box.createHorizontalStrut(30) );

        this.add(new JLabel("Sound: "));
        this.add(soundLable);


    }

    @Override
    public void update(IObservable o, Object arg) {
        GameWorldProxy gwp = (GameWorldProxy)o;
        Integer myTime = gwp.getClock();
        Integer myScore = gwp.getScore();
        Integer myLives = gwp.getPlayerHealth();
        Boolean sound = gwp.getSound();
        this.gameTime.setText(""+myTime);
        this.score.setText(myScore.toString());
        this.lives.setText(myLives.toString());
        if(sound == true){
            soundLable.setText("ON");
        }else{
            soundLable.setText("OFF");
        }



    }
}
