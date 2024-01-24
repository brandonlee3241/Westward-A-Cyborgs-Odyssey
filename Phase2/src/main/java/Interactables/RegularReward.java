package Interactables;

import Characters.Position;

/**
 * RegularReward is a class that represents a regular reward in the game.
 * It extends the Reward class and has methods to create the reward and grant it to a Cyborg.
 */
public class RegularReward extends Reward {
    
    /**
     * Constructor for the RegularReward class.
     * @param position The position of the regular reward.
     */
    public RegularReward(Position position) {
        super(position, 1); // Call to superclass constructor
    }
}