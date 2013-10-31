package a2;

import java.util.ArrayList;
import java.util.Iterator;

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
