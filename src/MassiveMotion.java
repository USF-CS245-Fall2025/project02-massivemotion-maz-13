import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * MassiveMotion simulation class that creates and animates celestial bodies.
 * Reads configuration from a properties file and maintains a list of celestial objects.
 */
public class MassiveMotion extends JPanel implements ActionListener {

    private Timer timer;
    private List<CelestialBody> celestialBodies;
    private Random random;
    
    // Configuration properties
    private int timerDelay;
    private int windowSizeX;
    private int windowSizeY;
    private int starPositionX;
    private int starPositionY;
    private int starSize;
    private int starVelocityX;
    private int starVelocityY;
    private double genX;
    private double genY;
    private int bodySize;
    private int bodyVelocity;

    /**
     * Constructs a new MassiveMotion simulation with the specified property file.
     * @param propFile the path to the property file containing simulation configuration
     */
    public MassiveMotion(String propFile) {
        this.random = new Random();
        loadProperties(propFile);
        initializeSimulation();
    }
    
    /**
     * Loads configuration properties from the specified file.
     * Uses default values if the file cannot be read.
     * @param propFile the path to the property file
     */
    private void loadProperties(String propFile) {
        Properties props = new Properties();
        
        // Set default values
        timerDelay = 75;
        windowSizeX = 1024;
        windowSizeY = 768;
        starPositionX = 512;
        starPositionY = 384;
        starSize = 30;
        starVelocityX = 0;
        starVelocityY = 0;
        genX = 0.06;
        genY = 0.06;
        bodySize = 10;
        bodyVelocity = 3;
        
        try (FileInputStream fis = new FileInputStream(propFile)) {
            props.load(fis);
            
            // Load properties with defaults
            timerDelay = Integer.parseInt(props.getProperty("timer_delay", "75"));
            windowSizeX = Integer.parseInt(props.getProperty("window_size_x", "1024"));
            windowSizeY = Integer.parseInt(props.getProperty("window_size_y", "768"));
            starPositionX = Integer.parseInt(props.getProperty("star_position_x", "512"));
            starPositionY = Integer.parseInt(props.getProperty("star_position_y", "384"));
            starSize = Integer.parseInt(props.getProperty("star_size", "30"));
            starVelocityX = Integer.parseInt(props.getProperty("star_velocity_x", "0"));
            starVelocityY = Integer.parseInt(props.getProperty("star_velocity_y", "0"));
            genX = Double.parseDouble(props.getProperty("gen_x", "0.06"));
            genY = Double.parseDouble(props.getProperty("gen_y", "0.06"));
            bodySize = Integer.parseInt(props.getProperty("body_size", "10"));
            bodyVelocity = Integer.parseInt(props.getProperty("body_velocity", "3"));
            
            // Create the appropriate list implementation
            String listType = props.getProperty("list", "arraylist");
            celestialBodies = ListFactory.createList(listType);
            
        } catch (IOException e) {
            System.err.println("Warning: Could not read property file, using default values");
            // Use default list type
            celestialBodies = ListFactory.createList("arraylist");
        }
    }
    
    /**
     * Initializes the simulation by creating the timer and adding the initial star.
     */
    private void initializeSimulation() {
        timer = new Timer(timerDelay, this);
        
        // Create the initial star
        CelestialBody star = new CelestialBody(
            starPositionX, starPositionY, 
            starVelocityX, starVelocityY, 
            starSize, true
        );
        celestialBodies.add(star);
    }

    /**
     * Paints all celestial bodies on the canvas.
     * @param g the Graphics object used for painting
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Paint each celestial body
        for (int i = 0; i < celestialBodies.size(); i++) {
            CelestialBody body = celestialBodies.get(i);
            g.setColor(body.getColor());
            int diameter = body.getSize() * 2;
            g.fillOval(body.getX() - body.getSize(), body.getY() - body.getSize(), diameter, diameter);
        }
        
        // Start the timer if it's not already running
        if (!timer.isRunning()) {
            timer.start();
        }
    }


    /**
     * Updates the simulation state on each timer tick.
     * Moves celestial bodies, generates new ones, and removes out-of-bounds bodies.
     * @param actionEvent the action event from the timer
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Update positions of all celestial bodies
        for (int i = 0; i < celestialBodies.size(); i++) {
            CelestialBody body = celestialBodies.get(i);
            body.updatePosition();
        }
        
        // Remove bodies that are out of bounds
        for (int i = celestialBodies.size() - 1; i >= 0; i--) {
            CelestialBody body = celestialBodies.get(i);
            if (body.isOutOfBounds(windowSizeX, windowSizeY)) {
                celestialBodies.remove(i);
            }
        }
        
        // Generate new celestial bodies based on probability
        generateNewCelestialBodies();
        
        // Repaint the canvas
        repaint();
    }
    
    /**
     * Generates new celestial bodies based on the generation probabilities.
     * Bodies are generated at the edges of the canvas with random velocities.
     */
    private void generateNewCelestialBodies() {
        // Generate body from top or bottom edge
        if (random.nextDouble() < genX) {
            int x = random.nextInt(windowSizeX);
            int y = random.nextBoolean() ? -bodySize : windowSizeY + bodySize;
            int velocityX = generateRandomVelocity();
            int velocityY = random.nextBoolean() ? Math.abs(generateRandomVelocity()) : -Math.abs(generateRandomVelocity());
            
            CelestialBody newBody = new CelestialBody(x, y, velocityX, velocityY, bodySize, false);
            celestialBodies.add(newBody);
        }
        
        // Generate body from left or right edge
        if (random.nextDouble() < genY) {
            int x = random.nextBoolean() ? -bodySize : windowSizeX + bodySize;
            int y = random.nextInt(windowSizeY);
            int velocityX = random.nextBoolean() ? Math.abs(generateRandomVelocity()) : -Math.abs(generateRandomVelocity());
            int velocityY = generateRandomVelocity();
            
            CelestialBody newBody = new CelestialBody(x, y, velocityX, velocityY, bodySize, false);
            celestialBodies.add(newBody);
        }
    }
    
    /**
     * Generates a random velocity within the specified range, excluding zero.
     * @return a random velocity between -bodyVelocity and +bodyVelocity, excluding 0
     */
    private int generateRandomVelocity() {
        int velocity;
        do {
            velocity = random.nextInt(2 * bodyVelocity + 1) - bodyVelocity;
        } while (velocity == 0);
        return velocity;
    }

    /**
     * Main method to run the Massive Motion simulation.
     * @param args command line arguments - first argument should be the property file path
     */
    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        
        if (args.length == 0) {
            System.err.println("Usage: java MassiveMotion <property_file>");
            System.exit(1);
        }
        
        MassiveMotion mm = new MassiveMotion(args[0]);

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowSizeX, mm.windowSizeY);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
