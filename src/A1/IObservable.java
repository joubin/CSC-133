package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 9:20 PM
 */
public interface IObservable {

    public void addObserver(IObserver obs);

    public void notifyObservers();

}
