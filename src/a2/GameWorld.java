package a2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:32 PM
 */
public class GameWorld {
    /*
    The game world is responsible of encapsulating all game related objects
     */
    private GameObjectCollection go = new GameObjectCollection(); // Arraylist containg all game objects at play
    private int clock = 0; // initial clock of the game
    private Tank myTank; // Unique tank for the player
    private int score; // Unique score for the player


    public void initialize(int numTank, int numRock, int numTree) {
        /*
        this method behaves exactly like a constructor, however, I renamed it becase the professors sample code called
        created an instance of the gameworld without any parameters.
         */
        for (int i = 0; i < numRock; ++i) {
            /*
            Create the request number of rocks

            The getAllXY provides an X and Y location that has not been used yet.
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
            Tank temp = new Tank(getAllXY()[0], getAllXY()[1]);
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

            IIterator iterator = go.iterator();

            while (iterator.hasNext()){
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
    }

    public void modifyPlayerTankSpeed(int i) { // increase or decrease speed of the player tank
        myTank.modifySpeed(i);
    }

    public void firePlayerTankMissile() { // Fire player tank
        boolean ableToFire = myTank.fireMissile(); // First check to see if the tank is able to fire
        if (ableToFire) { // if so, create a new missile object and decrease the missile count of players tank
            Missile m = new Missile(myTank);
            myTank.modifyMissleCount(-1);
            go.add(m); // add the newly created missile to the collection of game objects.
        } else {
            System.out.println("This tank has no more ammo"); // Print error message.
        }
    }


    public GameObjectCollection returnAllTanksFromObject(GameObjectCollection obj) {
        /*
        Returns all tanks that are in obj.
        This is a helper method
         */
        GameObjectCollection tmp = new GameObjectCollection();
        IIterator iterator = obj.iterator();
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
        IIterator iterator = obj.iterator();

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
        IIterator iterator = obj.iterator();

        while (iterator.hasNext()) {
            GameObject tmpObject = (GameObject) iterator.next();
            if (tmpObject instanceof Tank || tmpObject instanceof Missile) {
                tmp.add(tmpObject);
            }
        }
        return tmp;

    }


    public void fireEnemyTankMissile() { // Fire enemy Tank
        GameObjectCollection tmp = returnAllTanksFromObject(go); // Get all tanks from the game world
        Random r = new Random(); // A random number generator
        Tank t = null;
        IIterator iterator = tmp.iterator();

        if (iterator.size() > 1) {
            do {
                t = (Tank) iterator.randomItem(); // Get a random tank from the collection
            } while (t == myTank); // as long as the tank is not the player tank
        } else {
            System.out.println("You are the only tank");
            return;
        }
        boolean ableToFire = t.fireMissile(); // check to see if the given tank can fire
//        System.out.println(t.getName()); //debug code
        if (ableToFire) { // if the tank is able to fire,
            Missile m = new Missile(t);// create a new missile
            t.modifyMissleCount(-1); // remove the missile from the tanks ammo count
            go.add(m); // add the newly created missile to the collection of game objects
        } else {
            System.out.println("This tank has no more ammo");   // Print error message
        }


    }

    public void getHitWithMissle() {
        // simulate that a random tank has been hit by a missile.
        GameObjectCollection tanksInGame = returnAllTanksFromObject(go); // get an array of all tanks in the game
        IIterator iterator = tanksInGame.iterator();
        Random r = new Random(); // Random number generator
        Tank randomTank = (Tank) iterator.randomItem(); // pick a random tank
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
    }

    public boolean removeMissileFromMap(int x) {
        /*
        This is a helper function to remove missles from the map
        */
        GameObjectCollection m = returnAllMissileFromObject(go);  // Get all missiles from the game
        IIterator itr = m.iterator();
        if (itr.size() >= x) {
            Random r = new Random();
            for (int i = 0; i < x; ++i) {
                itr.remove(itr.randomItem()); // remove the number of random missiles

            }
            return true; // return true if able to remove a missile from the game
        }
        return false; // false otherwise.
    }

    public void missileCollisions() { //
        // simulate two missiles coliding and remove 2
        boolean didRemoveMissile = removeMissileFromMap(2);
        if (!didRemoveMissile) {
            System.out.println("There arent two missiles for two to collide"); //print error message
        }
    }

    public void blockMovableObject() {
        /*
        Block a random movable object
         */
        GameObjectCollection tanks = returnAllTanksFromObject(go);
        IIterator itr = tanks.iterator();
        Tank tmp = (Tank) itr.randomItem();
        tmp.setSpeed(0);
        tmp.toggleBlocked(); // set its status to blocked
    }

    public void tickClock() {
        /*
        on a tick, update all objects.

        That means to call their update method.

        Each object has a unique update method to its behavior.
         */
        clock = clock + 1;
        GameObjectCollection tmp = returnAllMoveableItemsFromObject(go);
        IIterator itr = tmp.iterator();
        while (itr.hasNext()){
                MovableItem tmpGameObject = (MovableItem) itr.next();
                tmpGameObject.update();
                deathReaper(tmpGameObject);
        }
    }

    public void deathReaper(MovableItem m) {
        /*
        Remove an object that has health 0 or less (Should never happen)
         */
        IIterator itr = go.iterator();
        int tmpHealth = m.getHealth();
        if (tmpHealth < 1) {
            itr.remove(m);
        }

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
        IIterator iterator = go.iterator();
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
}
