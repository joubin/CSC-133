package a2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/10/13
 * Time: 9:54 AM
 */
public class GameObjectCollection implements ICollection {


    private ArrayList<GameObject> myObjects;

    public GameObjectCollection() {
        myObjects = new ArrayList<GameObject>();
    }

    public Iterator iterator() {
        GameObjectIterator gameItr = new GameObjectIterator(myObjects);
        return gameItr;

    }

    public void add(GameObject o) {
        myObjects.add(o);
    }

    public int getSize() {
        return myObjects.size();
    }

    private ArrayList<Tank> returnAllTanks() {
        /*
        Returns all tanks that are in obj.
        This is a helper method
         */
        ArrayList<Tank> tmp = new ArrayList<Tank>();
        for (GameObject o : myObjects) {
            if (o instanceof Tank) tmp.add((Tank) o);
        }

        return tmp;
    }

    private ArrayList<Missile> returnAllMissile() {

        ArrayList<Missile> tmp = new ArrayList<Missile>();

        for (GameObject o : myObjects) {
            if (o instanceof Missile) tmp.add((Missile) o);
        }

        return tmp;

    }

    private ArrayList<MovableItem> returnAllMovable() {
        ArrayList<MovableItem> tmp = new ArrayList<MovableItem>();
        for (GameObject o : myObjects) {
            if (o instanceof MovableItem) tmp.add((MovableItem) o);
        }
        return tmp;
    }


    public GameObject returnRandomTank() {
        Random r = new Random();
        ArrayList<Tank> tmpAllTanks = returnAllTanks();

        int random = r.nextInt(tmpAllTanks.size());
        return tmpAllTanks.get(random);
    }

    public GameObject returnRandomMissile() {
        Random r = new Random();
        int random = r.nextInt(myObjects.size());
        return myObjects.get(random);
    }

    public GameObject returnRandomMovable() {
        Random r = new Random();
        ArrayList<MovableItem> tmpMovable = returnAllMovable();
        int random = r.nextInt(tmpMovable.size());
        return tmpMovable.get(random);

    }

    public void remove(GameObject o) {
        myObjects.remove(myObjects.indexOf(o));
    }

    private class GameObjectIterator implements Iterator<GameObject> {

        private int current = 0, next = 0;

        public GameObjectIterator(ArrayList<GameObject> go) {
            myObjects = go;
        }


        @Override
        public boolean hasNext() {
            return next < myObjects.size();
        }

        @Override
        public GameObject next() {
            current = next;
            GameObject currentObj = myObjects.get(current);
            next += 1;
            return currentObj;

        }


        @Override
        public void remove() {
            myObjects.remove(current);
        }


        // Adding some comment so that I can push
        // adding more
    }
}
