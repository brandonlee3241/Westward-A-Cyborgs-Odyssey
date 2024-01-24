package Testing;

import Logic.*;


import java.io.FileNotFoundException;


import org.junit.Test;

/**
 * Test Running Game
 * Purposely Run Into Monster
 */
public class RunningFailTest  {
	
	@Test
	public void RunningTest() throws FileNotFoundException {
        String filename =  "/brandonMapMatrix.txt";
        Game game = new Game(filename);
        game.startGame();
        game.endGame();
    }
}
