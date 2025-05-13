import javax.swing.*;
/*
 * Main class that initializes and launches the Flappy Bird game.
 * This class sets up the JFrame, adds the main menu panel, and displays the game window.
 */

public class Project2Runner {

/*
* Name: <Pearl Ved>
* Student ID: <501312930>
*
******** Project Description ********
*
* This program is a simple version of the Flappy Bird game created using Java Swing. In the game, you control a bird that must fly between pipes without hitting them. 
* The bird starts at rest, but when you press the space bar, it flaps upwards. If you don’t press the space bar again, gravity pulls the bird down. The goal is to avoid 
* crashing into the pipes or the ground and to keep the bird flying as long as possible. The game shows your score, which increases each time the bird successfully passes through 
* a pipe. If you crash, the game ends, and you can restart it to try again.
*
* On the left-hand side of the game screen, there is a progress bar that shows the bird's position in the game. This progress bar helps you track how far the bird has flown, making it 
* easier to position the bird to navigate through the pipes. It gives you a better sense of control over the bird's movement, helping you time your flaps and avoid obstacles. If the bird 
* crashes into a pipe or the ground, the game ends, and a game-over screen appears with a restart button. You can click the restart button to begin a new game with a score of 0.
*
*
******** Swing Requirement ********
*
* My program satisfies the requirement of including a JFrame and at least three unique Swing components. The JFrame is used as the main window for the game and 
* is defined in the Project2Runner.java file, starting at line 85. To meet the requirement of three unique Swing components, the program uses a JLabel, JButton, 
* and JProgressBar. The JLabel is used to display the score in the GamePanel.java file, starting at line 39. Two JButton components are utilized in the game: 
* one as the start button in the MainMenuPanel.java file, starting at line 38, which transitions to the game screen, and another as the restart button in the GameOverScreen.java file, 
* starting at line 28, which allows the player to restart the game after a game over. The JProgressBar is used in the GamePanel.java file, starting at line 46, to visually track the 
* bird’s vertical position on the left side of the screen. These components play key roles in providing a functional and interactive user interface for the game
*
*
******** 2D Graphics Requirement ********
*
*
* The program satisfies the 2D graphics requirement by using JPanel as a canvas in both the MainMenuPanel and GamePanel classes. In the MainMenuPanel file (panel starting at line 13), 
* the paintComponent(Graphics g) method (starting at line 85) is overridden to draw a variety of 2D shapes including arcs, ovals, rectangles, and round rectangles, creating the visual 
* elements of the main menu. The background is drawn using different colours, and images are imported to enhance the appearance, such as the title image (title.jpg) and a start button 
* image (startImage.png). Similarly, in the GamePanel file (panel starting at line 7), the paintComponent(Graphics g) method (starting at line 132) is also overridden to draw the background, 
* the bird, and pipes using shapes like arcs and ovals. The game’s visual elements, such as the bird and pipes, are rendered with images (pipe.png, pipeflipped.png, bird.png) along with 
* graphical elements for the background. Both panels make use of JPanel to render meaningful 2D graphics with a combination of colours, shapes, and images, fulfilling the requirement to use 
* at least one JPanel for drawing something visually engaging.
*
*
******** Event Listener Requirement ********
*
*
* In my program, the ActionListener is attached to the startButton in the MainMenuPanel class. The listener is defined starting at line 44 in the MainMenuPanel.java file. When the startButton 
* is clicked, it triggers an ActionEvent, and the ActionListener removes the current panel from the JFrame and replaces it with the GamePanel, which begins the game. The update to the JFrame 
* is triggered by calling frame.revalidate() and frame.repaint() after the panel swap.The GamePanel class implements the KeyListener interface to listen for keyboard events. Specifically, 
* the keyPressed method (defined on line 195) listens for the spacebar key (KeyEvent.VK_SPACE). When the spacebar is pressed, it triggers the bird's flap() method, causing the bird to move 
* upwards. This user interaction updates the graphical display by modifying the bird's position on the screen. Additionally, the program uses a Timer (defined starting on line 53 of the GamePanel 
* file) with an ActionListener that periodically updates the game state, moves the progress bar, moves pipes, checks for collisions, and repaints the panel. Finally, the GameOverScreen has its 
* own ActionListener attached to the restartButton, defined within the show() method in the GameOverScreen class (line 35, GameOverScreen file). When the restartButton is clicked, it triggers the restartGame() method, 
* which restarts the game by replacing the current GamePanel with a new instance of itself. These events collectively contribute to the graphical updates in the game.
*
*/

/* Main Program Description:
 * This program is a functional implementation of the Flappy Bird game using Java Swing. The player controls a bird that must navigate through an endless series of pipes, pressing the space bar 
 * to make the bird flap and avoid colliding with the pipes or the ground. The game includes real-time rendering, where the bird’s position is dynamically updated based on gravity and user input, 
 * and the pipes continuously move toward the bird, requiring quick reactions. As the bird flies through the pipes, the score increases by 200, and the game tracks the bird’s progress via a visual 
 * progress bar.

 * The program uses various Swing components such as JButton for start/restart buttons, JProgressBar to track progress, and JLabel to display the score. KeyListener is used to handle user input, enabling 
 * the bird to flap when the space bar is pressed. The game also includes collision detection to determine when the bird hits a pipe or the ground, triggering a game-over screen. After the game 
 * is over, a JButton appears on the game-over screen, allowing the player to restart the game with a score of 0. The goal is to navigate through as many pipes as possible while avoiding collisions, 
 * and players can restart the game to try again after a game ends.
 * 
 */

        /*
         * The entry point for the application.
         * This method sets up the JFrame for the game and initializes the MainMenuPanel.
         * The MainMenuPanel is displayed when the application starts.
         * 
         * The method creates a window of size 500x500 pixels, with a title of "Flappy Bird",
         * and positions the window in the center of the screen.
        */
        public static void main(String[] args) {
                
                JFrame frame = new JFrame("Flappy Bird");
                MainMenuPanel mainMenuPanel = new MainMenuPanel(frame); // Create MainMenuPanel and add it to the frame
                frame.getContentPane().add(mainMenuPanel); // Add the main menu panel to the frame's content pane
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // Center the frame
                frame.setVisible(true);

        }

}