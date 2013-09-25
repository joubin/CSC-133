package A1;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private GameWorld gw;


    public Game() {
        gw = new GameWorld();

        play();
    }

    private void play() {
        // Code here to operate on the game world
        System.out.println("Please enter the number of enemy tanks:");
        Scanner in = new Scanner(System.in);
        int numberOfTanks = Integer.parseInt(in.nextLine());
        System.out.println("Please enter the number of Rocks:");
        int numberOfRocks = Integer.parseInt(in.nextLine());
        System.out.println("Please enter the number of Tress:");
        int numberOfTrees = Integer.parseInt(in.nextLine());

        gw.initialize(numberOfTanks, numberOfRocks, numberOfTrees);
        System.out.println("Ready for commands:");
        while (true) {
            System.out.print("Command: ");
            String command = in.next();
            char c = command.charAt(0);
            getCommand(c);
        }
    }

    private void getCommand(char c){
        switch (c){
            case 'r':
                gw.changePlayerTankDirection(5);
                break;
            case 'l':
                gw.changePlayerTankDirection(-5);
                break;
            case 'i':
                gw.modifyPlayerTankSpeed(1);
                break;
            case 'k':
                gw.modifyPlayerTankSpeed(-1);
                break;
            case 'f':
                gw.firePlayerTankMissile();
                break;
            case 'e':
                gw.fireEnemyTankMissile();
                break;
            case '1':
                gw.getHitWithMissle();
                break;
            case '2':
                gw.missileCollisions();
                break;
            case '3':
                gw.blockMovableObject();
                break;
            case 't':
                gw.tickClock();
                break;
            case 'd':
                gw.displayCurrentGameState();
                break;
            case 'm':
                gw.drawMap();
                break;
            case '?':
                gw.printHelpMessage();
                break;
            case 'q':
                gw.quit();
                break;
            default:
                System.out.println("You have picked an option that does not exist");

        }
    }
}
