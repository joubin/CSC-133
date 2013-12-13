package a4;

/**
 * User: joubin
 * Date: 9/19/13
 * Time: 11:38 PM
 */
public abstract class LandscapeItem extends GameObject {
    /*
    collection of landscape items.
     */

    public LandscapeItem(){
        super();
    }
    @Override
    public void setShoulddie() {

    }

    @Override
    public boolean getShoudDie() {
        return false;        // landscape items should never be removed or die as they dont have health
    }

    //Items that do not move
}
