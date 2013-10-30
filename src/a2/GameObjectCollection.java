package a2;

import java.util.ArrayList;
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

    public IIterator iterator() {
        GameObjectIterator gameItr = new GameObjectIterator(myObjects);
        return gameItr;

    }

    public void add(GameObject o) {
        myObjects.add(o);
    }

    private class GameObjectIterator implements IIterator {

        private int current, next;
        private ArrayList<GameObject> localGameObjects;

        public GameObjectIterator(ArrayList<GameObject> go) {
            localGameObjects = go;
        }

        @Override
        public GameObject randomItem() {
            Random r = new Random();
            return localGameObjects.get(localGameObjects.indexOf(r.nextInt(localGameObjects.size() - 1)));
        }

        @Override
        public boolean hasNext() {
            return current < localGameObjects.size();
        }

        @Override
        public GameObject next() {
            GameObject currentObj = localGameObjects.get(current);
            current += 1;
            next += 1;
            return currentObj;

        }

        @Override
        public void remove(GameObject o) {
            localGameObjects.remove(localGameObjects.indexOf(o));
        }

        @Override
        public int size() {
            return localGameObjects.size();
        }


        // Adding some comment so that I can push
        // adding more
    }
}
