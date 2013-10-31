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

    public Iterator iterator() {
        GameObjectIterator gameItr = new GameObjectIterator(myObjects);
        return gameItr;

    }

    public void add(GameObject o) {
        myObjects.add(o);
    }

    private class GameObjectIterator implements Iterator {

        private int current, next;

        public GameObjectIterator(ArrayList<GameObject> go) {
            myObjects = go;
        }

        public GameObject get(int i){
            return myObjects.get(i);
        }

        @Override
        public int indexOf(Missile tmp) {
            return myObjects.indexOf(tmp);
        }

        @Override
        public GameObject randomItem() {
            Random r = new Random();
            int actualSize = myObjects.size();
            int randomItemWithinSize = r.nextInt(actualSize);
            return myObjects.get(randomItemWithinSize);
        }

        @Override
        public boolean hasNext() {
            return current < myObjects.size();
        }

        @Override
        public GameObject next() {
            GameObject currentObj = myObjects.get(current);
            current += 1;
            next += 1;
            return currentObj;

        }

        @Override
        public void remove(GameObject o) {
            System.out.print("asdl");
            myObjects.remove(o);
        }

        @Override
        public int size() {
            return myObjects.size();
        }


        // Adding some comment so that I can push
        // adding more
    }
}
