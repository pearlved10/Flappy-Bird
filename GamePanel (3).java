import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {

    private Bird bird;  // The bird object
    private final List<Pipe> pipes;  // List of pipes in the game
    private boolean gameOver;  // Flag to indicate whether the game is over
    private final Timer timer;  // Timer to update game at fixed intervals
    private int score;  // Player's score
    private final JLabel scoreLabel;  // Label to display the score
    private JProgressBar birdPositionBar;  // Bar showing the bird's current position
    private final JFrame frame;  // The main window holding the game

    /*
     * Initializes the game panel, setting up the bird, pipes, and UI components.
     * Sets up the timer to update the game and handle user input.
     */
    public GamePanel(JFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(new Color(0x59CCC9));
        this.setLayout(null);

        bird = new Bird(100, 250); // Create a new bird at the initial position
        pipes = new ArrayList<>(); // Initialize the list of pipes
        pipes.add(new Pipe(500)); //// Add the first pipe to the list

        this.addKeyListener(this);
        this.setFocusable(true);

        gameOver = false;
        score = 0;

        // Initialize and configure the score label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(new Color (0,100,0));
        scoreLabel.setBounds(5, 4, 150, 30);
        this.add(scoreLabel);

        // Initialize and configure the bird position progress bar
        birdPositionBar = new JProgressBar(JProgressBar.VERTICAL, 0, 470 - 30); // range to fit screen
        birdPositionBar.setBounds(5, 30, 20, 420);
        birdPositionBar.setForeground(new Color(100,100,100)); 
        birdPositionBar.setBackground(new Color(246, 255, 101));
        this.add(birdPositionBar);

        // Set up the game update timer
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    bird.update(); // Update the bird's position
                    birdPositionBar.setValue(470 - 30 - bird.getY());  // Update the bird position bar
                    movePipes();
                    checkCollisions();
                    repaint();
                }
            }
        });
        timer.start();
    }

    /*
     * Checks for collisions between the bird and the pipes or the ground.
     * If a collision occurs, the game ends.
     */
    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (bird.getBounds().intersects(pipe.getTopBounds()) || bird.getBounds().intersects(pipe.getBottomBounds())) {
                gameOver();
                return;
            }
        }
        // Check if the bird hits the ground
        if (bird.getY() >= 470) {
            gameOver();
            return;
        }
    
        // Check if the bird passed a pipe and increment the score
        boolean scored = false;
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            if (bird.getBounds().x > pipe.getTopBounds().x + pipe.getTopBounds().width && !pipe.offScreen() && !pipe.getTopBounds().contains(bird.getBounds().x+1,bird.getBounds().y) && !pipe.getBottomBounds().contains(bird.getBounds().x+1,bird.getBounds().y) && !scored){
                score += 10;
                scoreLabel.setText("Score: " + score); // Update the score label
                scored = true;
            }
        }
    }

    /*
     * Moves the pipes to the left and handles pipe recycling when they go off-screen.
     */
    private void movePipes() {
        // Move each pipe to the left
        for (int i = 0; i < pipes.size(); i++) {
            pipes.get(i).move();
        }
        // Remove pipes that have moved off-screen
        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).offScreen()) {
                pipes.remove(i);
                i--;
            }
        }
        // Add a new pipe if the last pipe has gone off-screen
        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).offScreen()) {
            pipes.add(new Pipe(500));
        }
    }

    /*
     * Handles the game over logic by stopping the game timer
     * and displaying the game over screen.
     */
    private void gameOver() {
        gameOver = true;
        timer.stop();
        GameOverScreen.show(this, score, frame);
    }

    /*
     * Draws the game elements (bird, pipes, background) to the screen.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        bird.draw(g2d);

        for (Pipe pipe : pipes) {
            pipe.draw(g2d);
        }
    }

    /*
     * Draws the background elements, such as the sky, clouds, and ground.
     */
    private void drawBackground(Graphics2D g2d) {
        // Draw the hills, sky, ground and other elements
        g2d.setColor(new Color(0x77F846));
        g2d.fillArc(-70, 310, 250, 500, 0, 180);
        g2d.fillArc(120, 270, 270, 500, 0, 180);
        g2d.fillArc(300, 320, 265, 400, 0, 180);

        g2d.setColor(new Color(0xE6C28B));
        g2d.fillRect(0, 420, 500, 100);
        g2d.setColor(new Color(0xB98E5A));
        g2d.fillRect(0, 440, 500, 60);

        g2d.setColor(new Color(0x896B12));
        for (int x = 5; x <= 480; x += 25) {
            g2d.fillRect(x, 440, 5, 60);
        }

        g2d.setColor(new Color(0XF9F5EA));
        g2d.fillOval(22, 16, 65, 45);
        g2d.fillOval(10, 33, 50, 37);
        g2d.fillOval(50, 33, 50, 37);
        g2d.fillRoundRect(12, 53, 85, 20, 40, 40);

        g2d.setColor(new Color(0xDADAD9));
        g2d.fillArc(20, 61, 75, 12, 200, 180);

        g2d.setColor(new Color(0xF9F5EA));
        g2d.fillOval(430, 95, 40, 30);
        g2d.fillOval(420, 107, 25, 22);
        g2d.fillOval(458, 107, 25, 22);
        g2d.fillRoundRect(407, 117, 90, 25, 40, 40);

        g2d.setColor(new Color(0xDADAD9));
        g2d.fillArc(430, 134, 55, 10, 200, 190);

        g2d.setColor(new Color(0xF9F5EA));
        g2d.fillOval(200, 190, 40, 30);
        g2d.fillOval(190, 202, 25, 22);
        g2d.fillOval(228, 202, 25, 22);
        g2d.fillRoundRect(180, 212, 90, 25, 40, 40);

        g2d.setColor(new Color(0xDADAD9));
        g2d.fillArc(190, 227, 75, 12, 200, 180);
    }

    /*
     * Responds to key presses. Specifically, handles the space bar for bird flapping.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.flap(); // Make the bird flap when the space bar is pressed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {} // Not used, but must be implemented

    @Override
    public void keyReleased(KeyEvent e) {} // Not used, but must be implemented

    /*
     * Requests focus for the game panel so it can receive key events.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}