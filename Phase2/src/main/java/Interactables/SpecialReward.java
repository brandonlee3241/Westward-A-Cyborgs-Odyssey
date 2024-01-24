package Interactables;

import Characters.Position;

/**
 * SpecialReward is an abstract class that represents a special reward in the game.
 * It extends the Reward class and can be used to create different types of special rewards.
 */
public abstract class SpecialReward extends Reward {

    /**
     * Constructor for the SpecialReward class.
     * @param position The position of the special reward.
     * @param value The value of the special reward.
     */
    public SpecialReward(Position position, int value) {
        super(position, value); // Call to superclass constructor
    }

}