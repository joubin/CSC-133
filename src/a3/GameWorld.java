package a3;

import javax.swing.*;
import java.net.MalformedURLException;
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
    private StrategyFireConservative everyOtherTick = null;
    private StrategyFireFrivolously everyTick = null;
    private IStrategy currStrat = null;
    private Timer timer;
    private int millSecTime;
    private int gameClock;
    private Sound fireMissile;
    private Sound hitRock;
    private Sound missileExplode;
    private Sound tankMoving;
    private Sound theme;
    private GameWorldProxy myProxy;
    private Sound allSound[];
    private boolean timerOn = true;

    public void initialize(int numTank, int numRock, int numTree) throws MalformedURLException {
        /*
        this method behaves exactly like a constructor, however, I renamed it becase the professors sample code called
        created an instance of the gameworld without any parameters.
         */
        myProxy = new GameWorldProxy(this);
        fireMissile = new Sound("fireMissile.wav", myProxy);
        hitRock = new Sound("hitRock.wav", myProxy);
        missileExplode = new Sound("missileExplode.wav", myProxy);
        tankMoving = new Sound("tankMoving.wav", myProxy);
        theme = new Sound("theme.wav", myProxy);
        allSound = new Sound[]{fireMissile, hitRock, missileExplode, tankMoving, theme};
        everyTick = new StrategyFireFrivolously(this);
        everyOtherTick = new StrategyFireConservative(this);
        currStrat = everyTick;
        System.out.println("Rock " + numRock + " Tank " + numTank + " tree " + numTree);
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
        theme.loop();

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
            float[] xy = {randGen.nextInt(700), randGen.nextInt(700)}; // pick and x and y between 0 - 1024
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
        if (sound && i > 0 && myTank.getSpeed() > 0) tankMoving.loop();
        if (myTank.getSpeed() < 1) tankMoving.stop();

        notifyObservers();
    }


    public void firePlayerTankMissile() { // Fire player tank
        boolean ableToFire = myTank.fireMissile(); // First check to see if the tank is able to fire
        if (ableToFire) { // if so, create a new missile object and decrease the missile count of players tank
            Missile m = new Missile(myTank);
            myTank.modifyMissleCount(-1);
            go.add(m); // add the newly created missile to the collection of game objects.
            if (sound) fireMissile.play();
        } else {
            System.out.println("This tank has no more ammo"); // Print error message.
        }
        notifyObservers();
    }


    @Override
    public void fireEnemyTankMissile() { // Fire enemy Tank
        Tank randomTank;
        do {
            randomTank = (Tank) go.returnRandomTank();
        } while (randomTank == myTank);

        boolean ableToFire = randomTank.fireMissile(); // check to see if the given tank can fire
//        System.out.println(t.getName()); //debug code
        if (ableToFire) { // if the tank is able to fire,
            Missile m = new Missile(randomTank);// create a new missile
            randomTank.modifyMissleCount(-1); // remove the missile from the tanks ammo count
            go.add(m); // add the newly created missile to the collection of game objects
        } else {
            System.out.println("This tank has no more ammo");   // Print error message
        }

        notifyObservers();
    }


    /**
     * Random Tank gets hit by missile
     */
    public void getHitWithMissile() {

        Tank randomTank;
        do {
            randomTank = (Tank) go.returnRandomTank();
        } while (randomTank == myTank);

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
        int passes = 0;
        ArrayList<GameObject> tmpMissiles = new ArrayList<GameObject>();
        while (passes < x) {
            passes++;
            tmpMissiles.add((GameObject) go.returnRandomMissile());
        }
        if (tmpMissiles.size() != x || tmpMissiles.contains(null)) return false;
        for (GameObject m : tmpMissiles) {
            if (!(m instanceof Missile)) {
                return false;
            }
        }

        for (GameObject m : tmpMissiles) {
            m.setShoulddie();
        }
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

        Tank t = (Tank) go.returnRandomTank();
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

//        millSecTime += millSecondElapsed;
//        int currentMillTime = millSecTime;
//
//        if(millSecTime >= 1000){
//            updateClock();
//            millSecTime = 0;
//        }
        notifyObservers();
        Iterator itr = go.iterator();
        while (itr.hasNext()) {
            Iterator itr2 = go.iterator();
            GameObject tmp = (GameObject) itr.next();
            while (itr2.hasNext()) {
                GameObject tmp2 = (GameObject) itr2.next();
//                System.out.println("Checking for collisions");

                if (tmp.collidesWith(tmp2) && tmp != tmp2) {
                    //System.out.println("Found collision");

                    tmp.handleCollision(tmp2);
                    if (sound) {
                        if ((tmp instanceof Missile || tmp2 instanceof Missile) && sound)
                            missileExplode.play(); // Collison of a missile and something happened
                        if (tmp instanceof Tank && (tmp2 instanceof LandscapeItem || tmp2 instanceof Tank))
                            hitRock.play(); // hit something
                        if (tmp instanceof Missile && tmp2 instanceof LandscapeItem && sound) hitRock.play();
                        if (tmp instanceof LandscapeItem && tmp2 instanceof Missile && sound) hitRock.play();
                        // if missile is mine and tank is not mine
                        if (tmp instanceof Missile && tmp2 instanceof Tank) {
                            Missile tmpMissile = (Missile) tmp;
                            Tank tmpTank = (Tank) tmp2;
                            if (tmpMissile.getMissileOwner() == myTank && tmpTank != myTank) {
                                addScore();
                                fireMissile.stop();
                                if (sound) {
                                    hitRock.play();
                                    hitRock.play();
                                    hitRock.play();
                                }


                            }
                        }
                    }
                }
            }
        }


        itr = go.iterator();
        while (itr.hasNext()) {
            GameObject dumbTmpGameObject = (GameObject) itr.next();
            MovableItem tmpGameObject = null;
            if (dumbTmpGameObject instanceof MovableItem) {
                tmpGameObject = (MovableItem) dumbTmpGameObject;
                tmpGameObject.update(clock);
                deathReaper(tmpGameObject);
            }
        }
        notifyObservers();
    }

    private void updateClock() {
        gameClock++;
    }


    public void deathReaper(MovableItem m) {
        /*
        Remove an object that has health 0 or less (Should never happen)
         */
        Iterator itr = go.iterator();
        while (itr.hasNext()) {
            if (m == itr.next()) {
                boolean shouldDie = m.getShoudDie();
                if (shouldDie) {
                    itr.remove();
                }
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
        if (!sound)
            for (Sound s : allSound) {
                s.stop();
            }
        else
            theme.loop();
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
        //drawMap();
    }

    @Override
    public void toggleStrategy() {

        Iterator iterator = go.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = (GameObject) iterator.next();
            if (gameObject instanceof Tank) {
                if (currStrat == everyOtherTick)
                    ((Tank) gameObject).setCurStrategy(everyTick);
                if (currStrat == everyTick)
                    ((Tank) gameObject).setCurStrategy(everyOtherTick);

            }
        }

    }

    public GameObjectCollection getGameWorldObjects() {
        return go;
    }

    public void setTimer(Timer t) {
        timer = t;
    }

    public void reverseAll() {
        Iterator itr = go.iterator();
        while (itr.hasNext()) {
            GameObject tmp = (GameObject) itr.next();
            if (tmp instanceof MovableItem && tmp instanceof ISelectable && ((ISelectable) tmp).isSelected()) {
                ((Vehicle) tmp).steer(180);
            }
        }
    }

    public void toggleTimer() {
        if (timerOn) {
            timer.stop();
            setSound(false);
        }
        if (!timerOn) {
            timer.start();
            setSound(true);
        }
        timerOn = !timerOn;

    }

    public boolean getTimerStat() {
        return timerOn;
    }



}





