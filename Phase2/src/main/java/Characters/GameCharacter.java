package Characters;
import javax.swing.ImageIcon;

/**
 * GameCharacter is an abstract class that represents a character in the game.
 * It holds a position and has methods to get and set the position, to move the character,
 * and to get the sprite (image) of the character.
 */
public abstract class GameCharacter {

    private Position position;
    // Assuming the image is represented using the Image class. You may need to adjust based on your actual image handling.

    /**
     * Constructor for the GameCharacter class.
     * @param initialPosition The initial position of the character.
     */
    public GameCharacter(Position initialPosition) {
        this.position = initialPosition;
    }

    /**
     * Returns the position of the character.
     * @return The position of the character.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of the character.
     * @param position The new position of the character.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Moves the character in a specified direction.
     * @param direction The direction in which to move the character.
     */
    public void move(Direction direction) {
        // Movement logic based on the direction
        switch(direction) {
            case UP:
                position.setY(position.getY() - 1);
                break;
            case DOWN:
                position.setY(position.getY() + 1);
                break;
            case LEFT:
                position.setX(position.getX() - 1);
                break;
            case RIGHT:
                position.setX(position.getX() + 1);
                break;
            case NONE:
                // Do nothing, representing no movement or idle state.
                break;
        }
        System.out.println(  " Game Charcter is at " + position.getX() + ", " + position.getY());
    }

    /**
     * Abstract method to get the sprite (image) of the character.
     * This method should be implemented by all concrete subclasses.
     * 
     * @return The sprite (image) of the character.
     */
    public abstract ImageIcon getSprite();
}
