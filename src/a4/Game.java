package a4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:33 PM
 */
public class Game extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener {

    private GameWorld gw; // An instance of the game world
    private Scanner in = new Scanner(System.in); // Scanner used to get input from the user
    // Make a gameworld proxy
    private GameWorldProxy gwp;
    private ButtonPanel bp;
    private ScoreView sv;
    private MapView mv;
    private int numberOfTanks, numberOfTrees, numberOfRocks;
    private Timer timer;
    private Point2D pp = new Point2D.Float();
    private Point mousePrevPoint = new Point(0,0);


    private JFrame loadingMenu = new JFrame();

    public Game() {
        setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // This line is from stack overflow from google around

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
                try {
                    gw.initialize(numberOfTanks, numberOfRocks, numberOfTrees); // init the gameworld
                } catch (MalformedURLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                CommandTick tick = CommandTick.getInstance();
                tick.setTarget(gw);
                timer = new Timer(30, tick);
                timer.start();
                gw.setTimer(timer);
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
        bp = new ButtonPanel(gw, this);
        sv = new ScoreView();
        mv = new MapView(gwp);
        mv.addMouseListener(this);
        mv.addMouseMotionListener(this);
        mv.addMouseWheelListener(this);

        gw.addObserver(sv);
        gw.addObserver(mv);


//        JPanel mapPanel = mv;
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap myMap = mv.getInputMap(mapName);
//        InputMap myMap2 = new InputMap();
        ActionMap myAction = mv.getActionMap();
//        int myMap = JComponent.WHEN_IN_FOCUSED_WINDOW;

        CommandLeft turnLeft = CommandLeft.getInstance();
        CommandRight turnRight = CommandRight.getInstance();
        CommandIncreaseSpeed increaseSpeed = CommandIncreaseSpeed.getInstance();
        CommandDecreaseSpeed decreaseSpeed = CommandDecreaseSpeed.getInstance();
        CommandFireMissile fireMissile = CommandFireMissile.getInstance();
        CommandFirePlasma firePlasma = CommandFirePlasma.getInstance();
        CommandFireGrenade fireGrenade = CommandFireGrenade.getInstance();
        CommandFireEnemyMissile fireEnemyMissile = CommandFireEnemyMissile.getInstance();
        CommandGetHitByMissile getHitByMissile = CommandGetHitByMissile.getInstance();
        CommandChangeStrategy changeStrategy = CommandChangeStrategy.getInstance();


        changeStrategy.setTarget(gw);
        getHitByMissile.setTarget(gw);
        fireEnemyMissile.target(gw);
        fireMissile.target(gw);
        fireGrenade.target(gw);
        firePlasma.target(gw);
        turnLeft.target(gw);
        turnRight.target(gw);
        decreaseSpeed.target(gw);
        increaseSpeed.target(gw);

        KeyStroke leftArrow = KeyStroke.getKeyStroke("LEFT");
        KeyStroke rightArrow = KeyStroke.getKeyStroke("RIGHT");
        KeyStroke upArrow = KeyStroke.getKeyStroke("UP");
        KeyStroke downArrow = KeyStroke.getKeyStroke("DOWN");
        KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
        KeyStroke eKey = KeyStroke.getKeyStroke('e');
        KeyStroke gKey = KeyStroke.getKeyStroke('g');
        KeyStroke pKey = KeyStroke.getKeyStroke('p');
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
        myMap.put(gKey, "g");
        myAction.put("g", fireGrenade);
        myMap.put(pKey, "p");
        myAction.put("p", firePlasma);
        this.makeGUI();
        this.requestFocus();
        loadingMenu.requestFocus();


//        play();
    }

    private void makeGUI() {

        //Get commands
        CommandAbout about = CommandAbout.getInstance();
        CommandSound sound = CommandSound.getInstance();
        CommandGetHitByMissile getHitByMissile = CommandGetHitByMissile.getInstance();
        CommandMissileHitMissile missileHitMissile = CommandMissileHitMissile.getInstance();
        CommandBlockTank blockTank = CommandBlockTank.getInstance();


        missileHitMissile.setTarget(gw);
        getHitByMissile.setTarget(gw);
        blockTank.setTarget(gw);


        sound.target(gw);


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
        JCheckBoxMenuItem soundMenu = new JCheckBoxMenuItem("Sound", true);
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

    public void togglePauseButton() {
        bp.togglePauseButton();
        mv.requestFocus();
    }

    public void changeTextOnPauseButton(String s) {
        bp.changeTextOnPausePlayButton(s);
        mv.requestFocus();
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


    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (!gw.getTimerStat()) {
            pp = e.getPoint();
            mv.select((Point) pp, e.isControlDown());

        }
    }
    private Point pressedHere;
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        pressedHere = e.getPoint();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void requestMapViewFocus() {
        mv.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            int x = (int) mousePrevPoint.getX();
            int y = (int) mousePrevPoint.getY();
            int newx = e.getX();
            int newy = e.getY();
            if (newx > x){
                mv.pan('r');
            }
            if (newx < x){
                mv.pan('l');
            }
            if (newy > y){
                mv.pan('u');
            }
            if (newy <y){
                mv.pan('d');
            }

            mousePrevPoint = e.getPoint();


    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation()>0){
            mv.zoomOut();
        }else{
            mv.zoomIn();
        }
    }
}
