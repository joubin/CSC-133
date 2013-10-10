package A1;

import java.util.ArrayList;

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

    private class GameObjectIterator implements IIterator {

        private int current, next;
        private ArrayList<GameObject> localGameObjects;

        public GameObjectIterator(ArrayList<GameObject> go) {
            localGameObjects = go;

        }


        @Override
        public boolean hasNext() {
            return current < localGameObjects.size();
        }

        @Override
        public Object next() {
            current = next;
            next += 1;
            return localGameObjects.get(current);

        }

        @Override
        public void remove(Object o) {
            localGameObjects.remove(localGameObjects.indexOf(o));
        }

    }
}