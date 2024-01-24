package Logic;
import Characters.Punishment;
import Characters.Position;

/**
 * Trap is a type of Punishment that can be activated for a Cyborg.
 * When activated, it decreases the Cyborg's score.
 */
public class Trap extends Punishment {
    
    /**
     * Constructor for the Trap class.
     *
     * @param position The position of the Trap.
     * @param imagePath The path to the image representing the Trap.
     */
    public Trap(Position position, String imagePath) {
        super(position,imagePath);  // Call to superclass constructor
    }
}