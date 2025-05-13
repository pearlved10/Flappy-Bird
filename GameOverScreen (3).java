import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverScreen {

    /*
     * Displays the game over screen with the player's final score and a restart button once game ends.
     */
    public static void show(GamePanel gamePanel, int score, JFrame frame) { // Receive JFrame in show()
        // Create a new JPanel that will serve as the "Game Over" screen
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setBackground(new Color(255, 200, 200)); 
        gameOverPanel.setLayout(null);

        int frameWidth = frame.getContentPane().getWidth();
        int frameHeight = frame.getContentPane().getHeight();

        // Create and set up a "Game Over!" label at the center of the screen
        JLabel gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 40));
        Dimension labelSize = gameOverLabel.getPreferredSize();
        int labelX = (frameWidth - labelSize.width) / 2;
        int labelY = (frameHeight / 2) - 100;
        gameOverLabel.setBounds(labelX, labelY, labelSize.width, labelSize.height);

        // Create a restart button for the user to start a new game
        JButton restartButton = new JButton("Restart?");
        restartButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        restartButton.setBounds(frameWidth / 2 - 100, frameHeight - 170, 200, 70);
        restartButton.setBackground(new Color(255, 100, 100)); // reddish background
        restartButton.setForeground(Color.WHITE); // white text for readability

        // Add an ActionListener to the restart button so that when clicked, it restarts the game
        restartButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame(gamePanel, frame); // Call the restartGame method when the button is pressed
            }
        });

        // Add the "Game Over!" label and the restart button to the gameOverPanel
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(restartButton);

        // Remove the existing game content and replace it with the "Game Over" screen
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gameOverPanel);
        frame.revalidate();
        frame.repaint();
    }

    // Method to restart the game and initialize a new game panel
    private static void restartGame(GamePanel gamePanel, JFrame frame) { // Receive JFrame in restartGame()
        // Remove all existing components from the frame
        frame.getContentPane().removeAll();

        // Create a new instance of GamePanel and add it to the frame to start a fresh game
        GamePanel newGamePanel = new GamePanel(frame);
        frame.getContentPane().add(newGamePanel);
        
        frame.revalidate();
        frame.repaint();
        frame.setLocationRelativeTo(null); // Recenter the frame on the screen
    }
}