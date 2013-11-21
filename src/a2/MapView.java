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
//    private GameWorld gwp;

    public MapView(GameWorldProxy gameWorldInstance) {
        this.gwp = gameWorldInstance;
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
        localGameObjectCollection = gwp.getGameWorldObjects();
        Iterator iterator = localGameObjectCollection.iterator();
        while (iterator.hasNext()){
            GameObject tmp = (GameObject) iterator.next();
            tmp.draw(g);
        }

    }
}
