package Interactables;

import Characters.Position;

/**
 * BonusReward is a class that represents a bonus reward in the game.
 * It extends the Reward class and has methods to create the bonus reward and grant it to a Cyborg.
 */
public class BonusReward extends Reward {
    long startTime;
    /**
     * Constructor for the BonusReward class.
     * @param position The position of the bonus reward.
     */
    public BonusReward(Position position) {
        super(position, 5); // Call to superclass constructor
        startTime = System.currentTimeMillis();
    }

    /**
     * Returns the how long the bonus reward has been active for.
     * @return The duration of the bonus reward.
     */
    public long getDuration() {
        return System.currentTimeMillis() - startTime;
    }
}