package a2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:33 PM
 */
public class Game extends JFrame {

    private GameWorld gw; // An instance of the game world
    private Scanner in = new Scanner(System.in); // Scanner used to get input from the user
    // Make a gameworld proxy
    private GameWorldProxy gwp;
    private ButtonPanel bp;
    private ScoreView sv;
    private MapView mv;
    private int numberOfTanks, numberOfTrees, numberOfRocks;


    private JFrame loadingMenu = new JFrame();

    public Game() {
        /*
        This constructor creates an instance of the game world and initializes it with all of the needed
        parameters.
         */
        JButton okButton = new JButton("    Ok    ");
        JButton quitButton = new JButton("  Quit  ");

        JLabel jLabelTanks = new JLabel("Enemy of Tanks  ");
        jLabelTanks.setHorizontalAlignment(SwingConstants.LEFT);
        final JTextField numTanksField = new JTextField();
        numTanksField.setColumns(10);

        JLabel jLabelTree = new JLabel("Number of Trees ");
        jLabelTree.setHorizontalAlignment(SwingConstants.LEFT);

        final JTextField numTreeField = new JTextField();
        numTreeField.setColumns(10);

        JLabel jLabelRock = new JLabel("Number of Rocks");
        jLabelRock.setHorizontalAlignment(SwingConstants.LEFT);
        final JTextField numOfRocksField = new JTextField();

        numOfRocksField.setColumns(10);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                numberOfTanks = Integer.parseInt(numTanksField.getText());
                numberOfTrees = Integer.parseInt(numTreeField.getText());
                numberOfRocks = Integer.parseInt(numOfRocksField.getText());
                gw.initialize(numberOfTanks, numberOfRocks, numberOfTrees); // init the gameworld
                loadingMenu.dispatchEvent(new WindowEvent(loadingMenu, WindowEvent.WINDOW_CLOSING));
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadingMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                loadingMenu.dispatchEvent(new WindowEvent(loadingMenu, WindowEvent.WINDOW_CLOSING));
            }
        });


        JPanel setup = new JPanel();


        loadingMenu.setLayout(new BorderLayout());
        loadingMenu.setSize(300, 150);
        loadingMenu.setLocationRelativeTo(null);

        setup.add(jLabelRock);
        setup.add(numOfRocksField);
        setup.add(jLabelTanks);
        setup.add(numTanksField);
        setup.add(jLabelTree);
        setup.add(numTreeField);

        setup.add(okButton);
        setup.add(quitButton);
        loadingMenu.add(setup, BorderLayout.CENTER);
//        loadingMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadingMenu.setResizable(false);

        gw = new GameWorld();
        gwp = new GameWorldProxy(gw);
        bp = new ButtonPanel(gwp);
        sv = new ScoreView();
        mv = new MapView();

        gw.addObserver(sv);
        gw.addObserver(mv);


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
        CommandFireEnemyMissile fireEnemyMissile = CommandFireEnemyMissile.getInstance();
        CommandGetHitByMissile getHitByMissile = CommandGetHitByMissile.getInstance();
        CommandChangeStrategy changeStrategy = CommandChangeStrategy.getInstance();

        changeStrategy.setTarget(gwp);


        getHitByMissile.setTarget(gwp);
        fireEnemyMissile.target(gwp);
        fireMissile.target(gwp);
        turnLeft.target(gwp);
        turnRight.target(gwp);
        decreaseSpeed.target(gwp);
        increaseSpeed.target(gwp);

        KeyStroke leftArrow = KeyStroke.getKeyStroke('l');
        KeyStroke rightArrow = KeyStroke.getKeyStroke('r');
        KeyStroke upArrow = KeyStroke.getKeyStroke('i');
        KeyStroke downArrow = KeyStroke.getKeyStroke('k');
        KeyStroke spaceKey = KeyStroke.getKeyStroke('f');
        KeyStroke eKey = KeyStroke.getKeyStroke('e');
        myMap.put(leftArrow, 'l');
        myAction.put('l', turnLeft);
        myMap.put(rightArrow, 'r');
        myAction.put('r', turnRight);
        myMap.put(upArrow, 'i');
        myAction.put('i', increaseSpeed);
        myMap.put(downArrow, 'k');
        myAction.put('k', decreaseSpeed);
        myMap.put(spaceKey, 'f');
        myAction.put('f', fireMissile);
        myMap.put(eKey, "e");
        myAction.put("e", changeStrategy);

        this.makeGUI();
        this.requestFocus();
        loadingMenu.requestFocus();


        play();
    }

    private void makeGUI() {

        //Get commands
        CommandAbout about = CommandAbout.getInstance();
        CommandHelp help = CommandHelp.getInstance();
        CommandIncreaseSpeed increaseSpeed = CommandIncreaseSpeed.getInstance();
        CommandLeft left = CommandLeft.getInstance();
        CommandQuit quit = CommandQuit.getInstance();
        CommandRight right = CommandRight.getInstance();
        CommandSound sound = CommandSound.getInstance();
        CommandTick tick = CommandTick.getInstance();
        CommandGetHitByMissile getHitByMissile = CommandGetHitByMissile.getInstance();
        CommandMissileHitMissile missileHitMissile = CommandMissileHitMissile.getInstance();
        CommandBlockTank blockTank = CommandBlockTank.getInstance();


        missileHitMissile.setTarget(gwp);
        getHitByMissile.setTarget(gwp);
        blockTank.setTarget(gwp);


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
        JMenu commands = new JMenu("Commands");
        JMenuItem myNew = new JMenuItem("New");
        JMenuItem mySave = new JMenuItem("Save");
        JMenuItem myUndo = new JMenuItem("Undo");
        JMenuItem myAbout = new JMenuItem("About");
        JMenuItem myQuit = new JMenuItem("Quit");
        JCheckBoxMenuItem soundMenu = new JCheckBoxMenuItem("Sound", false);
        soundMenu.setAction(sound);
        file.add(myNew);
        file.add(mySave);
        file.add(myUndo);
        file.add(soundMenu);
        commands.add(getHitByMissile);
        commands.add(missileHitMissile);
        commands.add(blockTank);

//        file.add(myAbout);
        file.add(new JMenuItem(about));
        file.add(new CommandQuit());

        menuBar.add(file);
        menuBar.add(commands);

        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        loadingMenu.setUndecorated(true);
        loadingMenu.setVisible(true);


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
