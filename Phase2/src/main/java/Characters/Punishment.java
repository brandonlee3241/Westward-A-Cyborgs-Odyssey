package Characters;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Punishment is a class that represents a punishment in the game.
 * It extends the GameCharacter class and has methods to get the sprite of the punishment.
 */
public class Punishment extends GameCharacter {
    private ImageIcon punishmentSprite;

    /**
     * Constructor for the Punishment class.
     * @param initialPosition The initial position of the punishment.
     */
    public Punishment(Position initialPosition) {
        super(initialPosition);
        URL imageUrl = getClass().getResource("/tile_art/trap.png");
        punishmentSprite = new ImageIcon(imageUrl);
    }

    /**
     * Constructor for the Punishment class.
     * @param initialPosition The initial position of the punishment.
     * @param imagePath The path to the image of the punishment.
     */
    public Punishment(Position initialPosition, String imagePath) {
        super(initialPosition);
        punishmentSprite = new ImageIcon(imagePath);
    }

    /**
     * Returns the sprite image of the punishment.
     * @return The sprite image of the punishment.
     */
    @Override
    public ImageIcon getSprite() {
        return punishmentSprite;
    }
}