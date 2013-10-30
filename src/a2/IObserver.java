package a2;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 9:20 PM
 */
public interface IObserver {
    public void update(IObservable o, Object arg);
}
