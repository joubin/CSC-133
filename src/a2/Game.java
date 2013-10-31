package a2;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:33 PM
 */
public class Game extends JFrame{

    private GameWorld gw; // An instance of the game world
    private Scanner in = new Scanner(System.in); // Scanner used to get input from the user
    // Make a gameworld proxy
    private GameWorldProxy gwp;
    private ButtonPanel bp;
    private ScoreView sv;
    private MapView mv;


    public Game() {
        /*
        This constructor creates an instance of the game world and initializes it with all of the needed
        parameters.
         */
        gw = new GameWorld();
        gwp = new GameWorldProxy(gw);
        bp = new ButtonPanel(gwp);
        sv = new ScoreView();
        mv = new MapView();

        gw.addObserver(sv);
        gw.addObserver(mv);
        int forgiveness = 0; // To allow the user the wrong input before the game is initialized.
        do try {          //TODO
            // Change this to a GUI object that appears and asks for input
//            System.out.println("Please enter the number of enemy tanks:");
//            int numberOfTanks = Integer.parseInt(in.nextLine()); // get the number of tanks from the user
//            System.out.println("Please enter the number of Rocks:");
//            int numberOfRocks = Integer.parseInt(in.nextLine()); // get the number of rocks from the user
//            System.out.println("Please enter the number of Tress:");
//            int numberOfTrees = Integer.parseInt(in.nextLine()); // get the number of trees from the user
//            gw.initialize(numberOfTanks, numberOfRocks, numberOfTrees); // init the gameworld
            gw.initialize(10,0,0);
            break; // if this part of the code is reached, that means the game has been init properly.
            // Otherwise, loop the try catch until forgiveness is reached 2
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Wrong key input");
            forgiveness += 1;
            if (forgiveness >= 2) System.exit(1);
        } while (forgiveness < 2);

        JPanel mapPanel = mv;
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap myMap = mapPanel.getInputMap(mapName);
//        InputMap myMap2 = new InputMap();
        ActionMap myAction = mapPanel.getActionMap();
//        int myMap = JComponent.WHEN_IN_FOCUSED_WINDOW;

        CommandLeft turnLeft = CommandLeft.getInstance();
        CommandRight turnRight = CommandRight.getInstance();
        CommandIncreaseSpeed increaseSpeed = CommandIncreaseSpeed.getInstance();
        CommandDecreaseSpeed decreaseSpeed = CommandDecreaseSpeed.getInstance();
        CommandFireMissile fireMissile = CommandFireMissile.getInstance();
        fireMissile.target(gwp);
        turnLeft.target(gwp);
        turnRight.target(gwp);

        KeyStroke leftArrow = KeyStroke.getKeyStroke("LEFT");
        KeyStroke rightArrow = KeyStroke.getKeyStroke("RIGHT");
        KeyStroke upArrow = KeyStroke.getKeyStroke("UP");
        KeyStroke downArrow = KeyStroke.getKeyStroke("DOWN");
        KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
        myMap.put(leftArrow, "left");
        myAction.put("left", turnLeft);
        myMap.put(rightArrow, "right");
        myAction.put("right", turnRight);
        myMap.put(upArrow, "up");
        myAction.put("up", increaseSpeed);
        myMap.put(downArrow, "down");
        myAction.put("down", decreaseSpeed);
        myMap.put(spaceKey, "space");
        myAction.put("space", fireMissile);

        this.makeGUI();
        this.requestFocus();



        play();
    }

    private void makeGUI(){

        //Get commands
        CommandAbout about = CommandAbout.getInstance();
        CommandHelp help = CommandHelp.getInstance();
        CommandIncreaseSpeed increaseSpeed = CommandIncreaseSpeed.getInstance();
        CommandLeft left = CommandLeft.getInstance();
        CommandQuit quit = CommandQuit.getInstance();
        CommandRight right = CommandRight.getInstance();
        CommandSound sound = CommandSound.getInstance();
        CommandTick tick = CommandTick.getInstance();


        sound.target(gwp);


        this.setLayout(new BorderLayout());
        this.setSize(1024, 1024);
        this.setLocation(1, 1);
        this.add(sv, BorderLayout.NORTH);
        this.add(bp, BorderLayout.WEST);
        this.add(mv, BorderLayout.CENTER);
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem myNew = new JMenuItem("New");
        JMenuItem  mySave = new JMenuItem("Save");
        JMenuItem myUndo = new JMenuItem("Undo");
        JMenuItem myAbout = new JMenuItem("About");
        JMenuItem myQuit = new JMenuItem("Quit");
        JCheckBoxMenuItem soundMenu = new JCheckBoxMenuItem("Sound",false);
        soundMenu.setAction(sound);
        file.add(myNew);
        file.add(mySave);
        file.add(myUndo);
        file.add(soundMenu);

//        file.add(myAbout);
        file.add(new JMenuItem(about));
        file.add(new CommandQuit());

        menuBar.add(file);

        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

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
                gw.getHitWithMissile(); // Ask the game world to simulate tank getting hit with a missile
                break;
            case '2':
                gw.missileCollisions(); // Ask the game world to simulate a collision between two missiles
                break;
            case '3':
                gw.blockMovableObject(); // Ask the game world to simulate an object getting blocked by a lanscape object
                break;
            case 't':
                gw.tick();  // Ask the game world to tick the clock of the game
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
