package Logic;
import java.io.FileNotFoundException;

/**
 * Main class that contains the entry point for the application.
 * It initializes the game, starts it, and ends it after 10 seconds.
 */
public class Main {

    /**
     * Default constructor.
     */
    public Main() {
    }

    /**
     * Initializes the game environment. 
     * This method should be called before the game starts.
     *
     * @throws FileNotFoundException if the required game files are not found.
     */
    public static void start() throws FileNotFoundException {
        
    }

    /**
     * The main method which is the entry point for the application.
     * 
     * @param args Command line arguments passed to the application.
     * @throws FileNotFoundException if the game initialization fails due to missing files.
     */
    public static void main(String[] args) throws FileNotFoundException  {
        //frame_start();
        Game game = new Game("/brandonMapMatrix.txt"); // Initialize the game
        game.startGame(); // Start the game
        game.endGame();
    }
}
