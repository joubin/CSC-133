package a2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Iterator;

/**
 * User: joubin
 */
public class MapView extends JPanel implements IObserver {
    private GameWorldProxy gwp;
    private GameWorld gameWorldInstance;

    public MapView(GameWorld gameWorldInstance) {
        this.gameWorldInstance = gameWorldInstance;
        this.setBorder(new TitledBorder("Map"));

    }

    @Override
    public void update(IObservable o, Object arg) {
        gwp = (GameWorldProxy)o;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        GameObjectCollection localGameObjectCollection;
        localGameObjectCollection = gameWorldInstance.getGameWorldObjects();
        Iterator iterator = localGameObjectCollection.iterator();
        while (iterator.hasNext()){
            GameObject tmp = (GameObject) iterator.next();
            tmp.draw(g);
        }

    }
}
