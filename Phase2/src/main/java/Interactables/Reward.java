package Interactables;
import Characters.Position;

/**
 * Reward is an abstract class that represents a reward in the game.
 * It extends the Interactables class and holds a value that indicates the reward's worth.
 */
public abstract class Reward extends Interactables {

    /**
     * The value of the reward, indicating its worth.
     */
    protected int value;
    

    /**
     * Constructor for the Reward class.
     * @param position The position of the reward.
     * @param value The value of the reward.
     */
    public Reward(Position position, int value) {
        super(position);  // Explicitly calling the constructor of Interactables
        this.value = value;
    }

    /**
     * Returns the value of the reward.
     * @return The value of the reward.
     */
    public int getValue() {
        return value;
    }

 
}