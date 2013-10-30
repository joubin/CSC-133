package a2;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * User: joubin
 */
public class MapView extends JPanel implements IObserver{
    public MapView() {
        this.setBorder(new TitledBorder("Map"));

    }

    @Override
    public void update(IObservable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
