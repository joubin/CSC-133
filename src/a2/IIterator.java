package a2;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/10/13
 * Time: 9:51 AM
 */
public interface IIterator {
    public boolean hasNext();

    public Object next();

    public void remove(GameObject o);

    public int size();

    public GameObject randomItem();
    
    public GameObject get(int a);

    public int indexOf(Missile tmp);
}
