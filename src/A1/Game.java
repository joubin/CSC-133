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
    private Tank myTank = new Tank();


    public Game() {
        gw = new GameWorld();

        play();
    }

    private void play() {
        // Code here to operate on the game world
    }

    private getCommand(){
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        switch (temp){
            case "r":
                myTank.setDirection(5);
                break;
            case "l":
                myTank.setDirection(-5);
                break;
            case "i":
                myTank.modifySpeed(true);
                break;
            case "k":
                myTank.modifySpeed(false);

        }
    }
}
