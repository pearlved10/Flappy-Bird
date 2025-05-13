import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * Represents the main menu panel of the Flappy Bird game.
 * This panel displays the game title and a start button. The background and layout
 * are thoughtfully designed to resemble the game’s initial screen.
 * When the start button is clicked, the game screen is displayed.
 */
public class MainMenuPanel extends JPanel {

    private final JButton startButton;
    private final JFrame frame;
    
    /*
     * Constructor to set up the main menu screen.
     * Initializes the background, title image, and start button.
     * The start button is linked to an action that transitions to the game screen.
     * 
     * The background of the menu consists of a sky, clouds, and grass, 
     * with a central title and start button.
     */
    public MainMenuPanel(JFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(new Color(0x59CCC9));
        this.setLayout(null);

        // Set up the title image and position it in the center of the panel
        JLabel titleLabel = new JLabel(new ImageIcon(getClass().getResource("title.jpg")));
        titleLabel.setBounds(107, 50, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);
        this.add(titleLabel);

        // Set up the start button with an image and position it near the bottom
        startButton = new JButton();
        startButton.setIcon(new ImageIcon(getClass().getResource("startImage.png")));
        startButton.setBounds(157, 300, 156, 60);
        this.add(startButton);

        // Add an ActionListener to the start button that triggers the transition to the game screen
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("click.wav");
                frame.getContentPane().removeAll(); // Remove MainMenuPanel
                GamePanel gamePanel = new GamePanel(frame); // Create GamePanel
                frame.getContentPane().add(gamePanel); // Add GamePanel
                frame.revalidate();
                frame.repaint();
                frame.setLocationRelativeTo(null); // Recenter the frame
            }
        });
    }

    /**
     * This method handles loading and playing a sound clip,
     * (Plays a sound effect when an action occurs of clicking the start button).
     * 
     *  The sound file is loaded, and if it's available, it is played immediately.
     */
    private void playSound(String soundFile) {
        try {
             // Attempt to load and play the sound file using Java's sound API
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/" + soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream); // Open the audio clip
            clip.start(); // Start playing the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();  // Print any errors that occur while loading or playing the sound
        }
    }

    /**
     * Custom paint method to render the background and other visual elements of the main menu.
     * This method handles drawing the sky, clouds, ground, and grass, creating a aesthetic background 
     * for the player while they are on the main menu screen.
     * 
     * The background elements are drawn in layers to simulate a 2D landscape with rolling clouds
     * and layered hills.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the grass hills using arcs 
        g2d.setColor(new Color(0x77F846)); //Back hill
        g2d.fillArc(-50, 180, 300, 565, 0, 180);
        g2d.fillArc(120, 230, 300, 525, 0, 180);
        g2d.fillArc(300, 150, 300, 650, 0, 180);
        g2d.fillArc(-210, 160, 300, 565, 0, 180);

        g2d.fillOval(188, 252, 20, 30);
        g2d.fillOval(398, 250, 20, 30);
        g2d.fillOval(322, 250, 20, 30);
        g2d.fillOval(332, 248, 20, 30);

        g2d.setColor(new Color(0x46EA09)); //Middle hill
        g2d.fillArc(-20, 250, 190, 350, 0, 200);
        g2d.fillArc(300, 270, 220, 350, 0, 200);

        g2d.setColor(new Color(0x3FD108)); //Front hill
        g2d.fillArc(-70, 310, 250, 500, 0, 180);
        g2d.fillArc(120, 270, 270, 500, 0, 180);
        g2d.fillArc(300, 320, 265, 400, 0, 180);

        g2d.setColor(new Color(0xE6C28B)); //Dirt-like ground 
        g2d.fillRect(0, 420, 500, 100);
        g2d.setColor(new Color(0xB98E5A));
        g2d.fillRect(0, 440, 500, 60);

        g2d.setColor(new Color(0x896B12)); //Lines to resemble bricks on ground 
        for (int x = 5; x <= 480; x += 25) {
            g2d.fillRect(x, 440, 5, 60);
        }

        // Draw the clouds using ovals and round rectangles 
        g2d.setColor(new Color(0XF9F5EA));
        g2d.fillOval(22, 16, 65, 45);
        g2d.fillOval(10, 33, 50, 37);
        g2d.fillOval(50, 33, 50, 37);
        g2d.fillRoundRect(12, 53, 85, 20, 40, 40); //cloud 1

        g2d.setColor(new Color(0xDADAD9));
        g2d.fillArc(20, 61, 75, 12, 200, 180);

        g2d.setColor(new Color(0xF9F5EA));
        g2d.fillOval(430, 95, 40, 30);
        g2d.fillOval(420, 107, 25, 22);
        g2d.fillOval(458, 107, 25, 22);
        g2d.fillRoundRect(410, 117, 90, 25, 40, 40); //cloud 2

        g2d.setColor(new Color(0xDADAD9));
        g2d.fillArc(430, 134, 55, 10, 200, 190);
    }
}
