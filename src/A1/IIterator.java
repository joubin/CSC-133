package A1;

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

}
