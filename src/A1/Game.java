package A1;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:33 PM
 */
public class Game {

    private GameWorld gw; // An instance of the game world
    private Scanner in = new Scanner(System.in); // Scanner used to get input from the user


    public Game() {
        /*
        This constructor creates an instance of the game world and initializes it with all of the needed
        parameters.
         */
        gw = new GameWorld();
        int forgiveness = 0; // To allow the user the wrong input before the game is initialized.
        do try {
            System.out.println("Please enter the number of enemy tanks:");
            int numberOfTanks = Integer.parseInt(in.nextLine()); // get the number of tanks from the user
            System.out.println("Please enter the number of Rocks:");
            int numberOfRocks = Integer.parseInt(in.nextLine()); // get the number of rocks from the user
            System.out.println("Please enter the number of Tress:");
            int numberOfTrees = Integer.parseInt(in.nextLine()); // get the number of trees from the user
            gw.initialize(numberOfTanks, numberOfRocks, numberOfTrees); // init the gameworld
            break; // if this part of the code is reached, that means the game has been init properly.
            // Otherwise, loop the try catch until forgiveness is reached 2
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Wrong key input");
            forgiveness += 1;
            if (forgiveness >= 2) System.exit(1);
        } while (forgiveness < 2);
        play();
    }

    private void play() {
        /*
        Play implements the interaction between the gameworld and game to the user.
        Asks for a series of commands and checks with "get command" to see if such moves are allowed.
         */
        System.out.println("Ready for commands:");
        while (true) {
            System.out.print("Command: ");
            String command = in.next();
            char c = command.charAt(0);
            getCommand(c);
        }
    }

    private void getCommand(char c) {
        /*

         */
        switch (c) {
            case 'r':
                gw.changePlayerTankDirection(5); // Ask the game world to change player game direction
                break;
            case 'l':
                gw.changePlayerTankDirection(-5); // Ask the game world to change player game direction
                break;
            case 'i':
                gw.modifyPlayerTankSpeed(1); // Ask the game world increase player speed
                break;
            case 'k':
                gw.modifyPlayerTankSpeed(-1); // Ask the game world decrease player speed
                break;
            case 'f':
                gw.firePlayerTankMissile(); // Ask the game world to fire a missile from the players tank
                break;
            case 'e':
                gw.fireEnemyTankMissile(); // Ask the game world to fire a missile from a random enemy tank
                break;
            case '1':
                gw.getHitWithMissle(); // Ask the game world to simulate tank getting hit with a missile
                break;
            case '2':
                gw.missileCollisions(); // Ask the game world to simulate a collision between two missiles
                break;
            case '3':
                gw.blockMovableObject(); // Ask the game world to simulate an object getting blocked by a lanscape object
                break;
            case 't':
                gw.tickClock();  // Ask the game world to tick the clock of the game
                break;
            case 'd':
                gw.displayCurrentGameState();  // Ask the game world to display game stats regarding the player
                break;
            case 'm':
                gw.drawMap(); // Ask the game world to draw a map of the world. (Right now in txt)
                break;
            case '?':
                gw.printHelpMessage(); // Print a help message outlining how to use the commands on the console
                break;
            case 'q':
                gw.quit(); // Ask the gameworld to quit the game
                break;
            default:
                System.out.println("You have picked an option that does not exist"); // In case an option that has not been coded is used

        }
    }
}
