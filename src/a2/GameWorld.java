package a2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * User: joubin
 */
public class GameWorld implements IObservable, IGameWold {
    /*
    The game world is responsible of encapsulating all game related objects
     */
    private GameObjectCollection go = new GameObjectCollection(); // Arraylist containg all game objects at play.


    //For A2, we made our own collection. According to the assignment, we needed that only for this
    private int clock = 0; // initial clock of the game
    private Tank myTank; // Unique tank for the player
    private int score; // Unique score for the player
    private ArrayList<IObserver> observers = new ArrayList<IObserver>(); // Since GameWorld is observable, it has to register its observers
    private boolean sound = true;
    private StrategyEveryOtherTick everyOtherTick = null;
    private StrategyEveryTick everyTick = null;
    private IStrategy currStrat = null;



    public void initialize(int numTank, int numRock, int numTree) {
        /*
        this method behaves exactly like a constructor, however, I renamed it becase the professors sample code called
        created an instance of the gameworld without any parameters.
         */
        everyTick = new StrategyEveryTick(this);
        everyOtherTick = new StrategyEveryOtherTick(this);
        currStrat = everyTick;
        for (int i = 0; i < numRock; ++i) {
            /*
            Create the request number of rocks

            The tickAllXY provides an X and Y location that has not been used yet.
             */
            go.add(new Rock(getAllXY()[0], getAllXY()[1]));
        }

        for (int i = 0; i < numTree; ++i) {
            /*
            Create the correct number of trees.
            Also ensure that it is not placed on top of another object.
             */
            go.add(new Tree(getAllXY()[0], getAllXY()[1]));
        }

        for (int i = 0; i < numTank; ++i) {
            /*
            Create the correct number of tanks and add them to the game
             */
            Tank temp = new Tank(getAllXY()[0], getAllXY()[1], currStrat);
            go.add(temp);
        }
        /*
        Finally initialize and add myTank (players tank) to the collection of gameObjects go
         */
        myTank = new Tank(getAllXY()[0], getAllXY()[1], true);
        go.add(myTank);

    }

    public float[] getAllXY() {
        /*
        This code has not been tested for accuracy.

        However, since it was not a requirement by the assignment, Im leaving it in.
         */
        /*
        This method ensures that objects dont get placed on top of each other
         */
        Random randGen = new Random(); // Random number generator
        /*
        check all objects in my collection of gameobjects for the above x and y pair
         */


        while (true) { // loop will exit when something is returned.
            float[] xy = {randGen.nextInt(1024), randGen.nextInt(1024)}; // pick and x and y between 0 - 1024
            int checkedAll = 0;

            Iterator iterator = go.iterator();

            while (iterator.hasNext()) {
                GameObject a = (GameObject) iterator.next();
                if (a.getX() == xy[0] || a.getY() == xy[1]) {
                    xy[0] = randGen.nextFloat();
                    xy[1] = randGen.nextFloat();
                }
            }
            return xy;

        }
    }

    public void changePlayerTankDirection(int i) { // call the change direction method for the player tank.
        myTank.changeDirection(i);
        notifyObservers();
    }

    public void modifyPlayerTankSpeed(int i) { // increase or decrease speed of the player tank
        myTank.modifySpeed(i);
        notifyObservers();
    }

    public void firePlayerTankMissile() { // Fire player tank
        System.out.print("ASD");
        boolean ableToFire = myTank.fireMissile(); // First check to see if the tank is able to fire
        if (ableToFire) { // if so, create a new missile object and decrease the missile count of players tank
            Missile m = new Missile(myTank);
            myTank.modifyMissleCount(-1);
            go.add(m); // add the newly created missile to the collection of game objects.
        } else {
            System.out.println("This tank has no more ammo"); // Print error message.
        }
        notifyObservers();
    }


    public GameObjectCollection returnAllTanksFromObject(GameObjectCollection obj) {
        /*
        Returns all tanks that are in obj.
        This is a helper method
         */
        GameObjectCollection tmp = new GameObjectCollection();
        Iterator iterator = obj.iterator();
        while (iterator.hasNext()) {
            GameObject tmpTank = (GameObject) iterator.next();
            if (tmpTank instanceof Tank) {
                tmp.add(tmpTank);
            }
        }
        return tmp;
    }

    public GameObjectCollection returnAllMissileFromObject(GameObjectCollection obj) {
        /*
        Returns all missiles that are in obj.
        This is a helper method
         */
        GameObjectCollection tmp = new GameObjectCollection();
        Iterator iterator = obj.iterator();

        while (iterator.hasNext()) {
            GameObject tmpMissile = (GameObject) iterator.next();
            if (tmpMissile instanceof Missile) {
                tmp.add(tmpMissile);
            }
        }
        return tmp;
    }

    public GameObjectCollection returnAllMoveableItemsFromObject(GameObjectCollection obj) {
        /*
        Returns all Moveable items that are in obj.
        This is a helper method
         */
        GameObjectCollection tmp = new GameObjectCollection();
        Iterator iterator = obj.iterator();

        while (iterator.hasNext()) {
            GameObject tmpObject = (GameObject) iterator.next();
            if (tmpObject instanceof Tank || tmpObject instanceof Missile) {
                tmp.add(tmpObject);
            }
        }
        return tmp;

    }


    @Override
    public void fireEnemyTankMissile() { // Fire enemy Tank
        Random r = new Random(); // A random number generator
        GameObject t = null;
        Tank tankChosen = null;
        Iterator iterator = go.iterator();

            do {
                t = (GameObject) iterator.next(); // Get a random tank from the collection
            } while (iterator.hasNext() && (t == myTank || !(t instanceof Tank))); // as long as the tank is not the player tank

        if (t == null || t == myTank) {
            System.out.println("You are the only tank");
            return;
        }else {
            tankChosen = (Tank) t;
        }

        boolean ableToFire = tankChosen.fireMissile(); // check to see if the given tank can fire
//        System.out.println(t.getName()); //debug code
        if (ableToFire) { // if the tank is able to fire,
            Missile m = new Missile(tankChosen);// create a new missile
            tankChosen.modifyMissleCount(-1); // remove the missile from the tanks ammo count
            go.add(m); // add the newly created missile to the collection of game objects
        } else {
            System.out.println("This tank has no more ammo");   // Print error message
        }

        notifyObservers();
    }


    public void getHitWithMissile() {
        // simulate that a random tank has been hit by a missile.
        GameObjectCollection tanksInGame = returnAllTanksFromObject(go); // get an array of all tanks in the game
        Iterator iterator = tanksInGame.iterator();
        GameObject randomObject = null;
        Tank randomTank = null;
        while (iterator.hasNext()) {
            randomObject = (GameObject) iterator.next();
            if (randomObject instanceof Tank) {
                randomTank = (Tank) randomObject;// Pick a random Tank
            }
        }
//        ArrayList<Missile> missilesInGame = returnAllMissileFromObject(go);
//        Missile randomMissile = missilesInGame.get(r.nextInt(missilesInGame.size()));
        /*
        check to see if there are missiles in the game.

        If there are, remove the missile from the game and decrease the armor of a the random tank
         */
        if (!removeMissileFromMap(1)) {
            System.out.println("There were no missiles"); //print error message
        } else {
            randomTank.modifyArmorStrength(-1);
        }
        notifyObservers();
    }

    public boolean removeMissileFromMap(int x) {
        /*
        This is a helper function to remove missles from the map
        */
        Missile myMissile = null;
        GameObject myGameObject;
        int i = 0;
        int pass = 0;
        while (i < x && pass <= x) {
            System.out.print("Making a new itr\n\n");
            pass++;
            Iterator itr = go.iterator();
            while (itr.hasNext()) {
                myGameObject = (GameObject) itr.next();
                if (myGameObject instanceof Missile) {
                    itr.remove();
                    i++;
                    break;
                }
            }


        }
        if (i < x) return false;
        notifyObservers();
        return true;
    }


    public void missileCollisions() { //
        // simulate two missiles coliding and remove 2
        boolean didRemoveMissile = removeMissileFromMap(2);
        if (!didRemoveMissile) {
            System.out.println("There arent two missiles for two to collide"); //print error message
        }
        notifyObservers();
    }

    public void blockMovableObject() {
        /*
        Block a random movable object
         */
        GameObjectCollection tanks = returnAllTanksFromObject(go);
        Iterator itr = tanks.iterator();
        Tank t;

        t = (Tank) itr.next(); // Get a random tank from the collection

        t.setSpeed(0);
        t.toggleBlocked(); // set its status to blocked
        notifyObservers();
    }

    public void tick() {
        /*
        on a tick, update all objects.

        That means to call their update method.

        Each object has a unique update method to its behavior.
         */
        clock = clock + 1;

        Iterator itr = go.iterator();
        while (itr.hasNext()) {
            GameObject dumbTmpGameObject = (GameObject) itr.next();
            MovableItem tmpGameObject = null;
            if (dumbTmpGameObject instanceof MovableItem) {
                tmpGameObject = (MovableItem) dumbTmpGameObject;
                tmpGameObject.update();
                deathReaper(tmpGameObject);
            }
        }
        notifyObservers();
    }


    public void deathReaper(MovableItem m) {
        /*
        Remove an object that has health 0 or less (Should never happen)
         */
        Iterator itr = go.iterator();
        while (itr.hasNext())
        if (m == itr.next()){
            int tmpHealth = m.getHealth();
            if (tmpHealth < 1) {
                itr.remove();
            }
        }
        notifyObservers();

    }

    public void displayCurrentGameState() {
        /*
        display game states related to the player
         */
        int health = myTank.getHealth();
        System.out.print("Clock:" + clock +
                "\nScore:" + score +
                "\nHealth:" + health + "/10\n");

    }

    public void drawMap() {
        /*
        draw a map, provide information regarding the object on the map using their unique toStrings
         */
        Iterator iterator = go.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());

        }
    }

    public void printHelpMessage() {
        /*
        print a help menu.
         */
        System.out.println("Help\n" +
                "r: Turn right 5 degrees clockwise\n" +
                "l: Turn left 5 degrees counter-clockwise\n" +
                "i: Increase speed of your tank\n" +
                "k: Decrease speed of your tank\n" +
                "f: Fire missile from your tank\n" +
                "e: Fire missile from enemy tank\n" +
                "1: Indicate that a random tank has been hit by a missile\n" +
                "2: indicates that a collision occurred between two missiles\n" +
                "3: Random tank has colided with a land object and is now blocked\n" +
                "t: Tick game clock\n" +
                "d: Display current game states and values\n" +
                "m: Display map for the current game\n" +
                "q: Quit game\n" +
                "?: Print this menu\n");
    }

    public void quit() {
        /*
        gracefully quit the game
         */
        System.exit(0);
    }

    public int getClock() {
        return clock;
    }

    public int getPlayerHealth() {
        return myTank.getHealth();
    }

    public int getScore() {
        return this.score;
    }

    public void addScore() {
        this.score++;
        notifyObservers();
    }

    public boolean getSound() {
        return this.sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
        notifyObservers();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        GameWorldProxy tmpProxy = new GameWorldProxy(this);
        Object randomObject = new Object();
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(tmpProxy, randomObject);
        }
        drawMap();
    }

    @Override
    public void toggleStrategy(){

        Iterator iterator = go.iterator();
        while (iterator.hasNext()){
            GameObject gameObject = (GameObject) iterator.next();
            if (gameObject instanceof Tank){
                if (currStrat == everyOtherTick)
                    ((Tank) gameObject).setCurStrategy(everyTick);
                if (currStrat == everyTick)
                    ((Tank) gameObject).setCurStrategy(everyOtherTick);

            }
        }

    }

}


