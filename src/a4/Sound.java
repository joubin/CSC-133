package a4;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * User: joubin
 */
public class Sound {
    private AudioClip sound;
    private GameWorldProxy gwp;

    public Sound(String name, GameWorldProxy gwp) throws MalformedURLException {
        String filename = "." + File.separator + "Sounds" + File.separator + name;
        sound = Applet.newAudioClip(new File(filename).toURI().toURL());
    }

    public void play() {
        sound.play();
    }

    public void stop() {
        sound.stop();
    }

    public void loop() {
        sound.loop();
    }
}
