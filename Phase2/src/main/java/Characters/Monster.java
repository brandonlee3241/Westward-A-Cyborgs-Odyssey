package Characters;
import javax.swing.ImageIcon;
import java.net.URL;

/**
 * Monster is a class that represents a monster character in the game.
 * It extends the Character class and has methods to move the monster towards
 * the Cyborg or move randomly.
 */
public class Monster extends GameCharacter {

    private ImageIcon monsterSprite;

    /**
     * Constructor for the Monster class.
     *
     * @param initialPosition The initial position of the monster.
     * @param Sprite URL of the sprite image for the monster.
     */
    public Monster(Position initialPosition, URL Sprite) {
        super(initialPosition); // Calling the super constructor with the initial position
        monsterSprite = new ImageIcon(Sprite);
    }

    /**
     * Returns the sprite image of the monster.
     * 
     * @return The sprite image of the monster.
     */
    @Override
    public ImageIcon getSprite() {
        return monsterSprite;
    }
}