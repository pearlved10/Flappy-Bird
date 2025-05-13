import java.awt.*;
import javax.swing.ImageIcon;

/*
 * Represents a pipe in the Flappy Bird game that the bird must navigate through.
 * Each pipe consists of two sections: a top pipe (flipped upside down) and a bottom pipe.
 * There is a gap between these two pipes, which the bird must pass through.
 */
public class Pipe {

    private int x; // The x-coordinate of the pipe
    private final int width = 60; // Width of the pipe (fixed value)
    private int height; // Height of the top pipe section
    private static final int GAP = 150; // Vertical gap between the top and bottom pipes
    private final Image pipeImage; // Image for the bottom section of the pipe
    private final Image flippedPipeImage; // Image for the top section of the pipe (flipped upside down)

    /*
     * Constructor to initialize a pipe at a specific x-coordinate.
     * The height of the top pipe is randomly chosen to create varied pipe heights
     * and gaps for each new pipe. The images for both the top and bottom pipes
     * are loaded during initialization.
     * 
     * The height is calculated randomly within screen limits to ensure that the gap
     * between the top and bottom pipes stays within playable space.
     */
    public Pipe(int x) {
        this.x = x;
         // Randomize the height of the top pipe to ensure the gap size is varied
        this.height = (int) (Math.random() * (500 - GAP));
        // Load the images for both the top and bottom pipes
        pipeImage = new ImageIcon(getClass().getResource("pipe.png")).getImage();
        flippedPipeImage = new ImageIcon(getClass().getResource("pipeflipped.png")).getImage();
    }

    /*
     * Moves the pipe to the left by a fixed distance to create the illusion that
     * the pipe is moving towards the bird.
     * 
     * This is called every frame to update the pipe's position, creating the
     * movement effect as part of the game’s mechanics.
     */
    public void move() {
        x -= 5;
    }

    /*
     * Resets the pipe’s position and height when the pipe has moved off-screen.
     * This method repositions the pipe to the far right side of the screen and 
     * randomizes the height again, ensuring that new pipes can be generated as 
     * part of the game's obstacle system.
     */
    public void reset(int newX) {
        this.x = newX;
        this.height = (int) (Math.random() * (500 - GAP));
    }

    /*
     * Draws both the top (flipped) and bottom sections of the pipe on the screen.
     * The top section is drawn first (flipped upside down), followed by the 
     * bottom section, creating the appearance of a complete obstacle for the player.
     * 
     * The dimensions and positioning of both sections are based on the pipe’s 
     * current x-coordinate and height, along with the defined constant for the gap.
     */
    public void draw(Graphics g) {
        g.drawImage(flippedPipeImage, x, 0, width, height, null);
        g.drawImage(pipeImage, x, height + GAP, width, 500 - height - GAP, null);
    }

    /*
     * Returns a rectangle representing the boundaries of the top pipe.
     * This is used for collision detection, allowing the game to check if the bird
     * has collided with the top pipe.
     */
    public Rectangle getTopBounds() {
        return new Rectangle(x, 0, width, height);
    }

    /*
     * Returns a rectangle representing the boundaries of the bottom pipe.
     * This is used for collision detection, allowing the game to check if the bird
     * has collided with the bottom pipe.
     */
    public Rectangle getBottomBounds() {
        return new Rectangle(x, height + GAP, width, 500 - height - GAP);
    }

    /*
     * Checks if the pipe has moved off the left side of the screen.
     * This is used to determine when the pipe should be reset and recycled for the next obstacle.
     */
    public boolean offScreen() {
        return x + width < 0;
    }
}