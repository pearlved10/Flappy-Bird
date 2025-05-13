import java.awt.*;
import javax.swing.ImageIcon;

public class Bird {

    private final int x;  // The x-coordinate of the bird
    private int y;  // The y-coordinate of the bird
    private int velocity;  // The bird's current velocity (how fast it's moving)
    private static final int GRAVITY = 1;  // Gravity force that pulls the bird down
    private static final int FLAP_STRENGTH = 9;  // The strength of the bird's flap
    private boolean gameStarted;  // Whether the game has started or not
    private final Image birdImage;  // Image of the bird

    /*
     * Initializes the bird at a specific position on the screen.
     * The bird's image is loaded, and its initial state is set.
     */
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocity = 0;
        this.gameStarted = false;

        ImageIcon birdIcon = new ImageIcon("bird.png");
        birdImage = birdIcon.getImage();
    }

    /*
     * Updates the bird's position based on its current velocity and gravity.
     * This method moves the bird downward due to gravity and stops the bird at the top and bottom limits.
     */
    public void update() {
        if (gameStarted) {
            velocity += GRAVITY; // Increase velocity due to gravity
            y += velocity; // Update bird's position based on velocity
        }
        // Prevent the bird from moving out of the top
        if (y < 0) {
            y = 0;
            velocity = 0;
        }

    }

    /*
     * Draws the bird on the screen at its current position.
     * The bird is drawn using the stored image.
     */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(birdImage, x, y, 30, 30, null);
    }

    /*
     * Makes the bird flap by resetting its velocity to move upward.
     * If the game hasn't started yet, it will be marked as started.
     */
    public void flap() {
        if (!gameStarted) {
            gameStarted = true;
        }
        velocity = -FLAP_STRENGTH;
    }   

    /*
     * Gets the y-coordinate of the bird.
     */
    public int getY() {
        return y;
    }

    /*
     * Gets the bounding box of the bird, which is used for collision detection.
     */
    public Rectangle getBounds(){
        return new Rectangle(x,y,30,30);
    }
}