package a3;

/**
 * User: joubin
 */
public interface ICollider {
    public boolean collidesWith(ICollider otherObject);

    public void handleCollision(ICollider otherObject);

    public int getSize();

    public void setSize();
}
