package Characters;
import javax.swing.ImageIcon;
import Interactables.Reward;

/**
 * Cyborg is a class that represents a cyborg character in the game.
 * It extends the Character class and has methods to move the cyborg, collect
 * rewards, hit traps, contact with monsters, and get the score.
 */
public class Cyborg extends GameCharacter {
    private int score;
    private boolean hasProtectiveCover;
    private boolean hasInvincibility;

    // Image icons for the cyborg character
    private ImageIcon cyborgDown1;

    /**
     * Constructor for the Cyborg class.
     */
    public Cyborg() {
        this(new Position(0, 0)); // Default position at (0, 0)
    }

    /**
     * Constructor for the Cyborg class.
     * 
     * @param initialPosition The initial position of the cyborg.
     */
    public Cyborg(Position initialPosition) {
        super(initialPosition);
        this.score = 0;
        this.hasProtectiveCover = false;
        this.hasInvincibility = false;

        loadImages(); // Load the character images upon instantiation
    }

    private void loadImages() {
        // The path is relative to the Cyborg class's compiled .class file
        String basePath = "/Characters/cyborg_art/";

        cyborgDown1 = new ImageIcon(getClass().getResource(basePath + "cyborg_down_1.png"));

        // Verification
        if (cyborgDown1 == null || cyborgDown1.getImage() == null) {
            System.out.println("Failed to load cyborgDown1 image.");
        }
    }

    /**
     * Returns the first image of the Cyborg moving down.
     * 
     * @return The first image of the Cyborg moving down.
     */
    public ImageIcon getDown1Image() {
        return cyborgDown1;
    }

        /**
     * Returns the sprite (image) of the Cyborg.
     * 
     * @return The sprite of the Cyborg.
     */
    @Override
    public ImageIcon getSprite() {
        return cyborgDown1;
    }

    /**
     * Moves the cyborg in a specified direction.
     * 
     * @param direction The direction in which to move the cyborg.
     */
    @Override
    public void move(Direction direction) {
        super.move(direction); // Call the parent's move method
        // Additional logic, if any, to move the Cyborg, keeping in mind the walls,
        // barriers, and other obstacles
    }

    /**
     * Collects a reward and increases the cyborg's score.
     * 
     * @param reward The reward to be collected.
     */
    public void collectReward(Reward reward) {
        this.score += reward.getValue(); // Assuming the Reward class has a getValue() method
        // Remove references to ProtectiveCover and InvincibilityState
    }

    /**
     * Hits a trap and decreases the cyborg's score.
     */
    public void hitTrap() {
        this.score -= 10; // Decreasing score by a fixed value, adjust as necessary
    }

    /**
     * Contacts with a monster and handles the interaction based on the cyborg's
     * state. If the cyborg does not have protective cover or invincibility,
     * the score is reset to 0. If the cyborg has invincibility, it will
     * destroy the monster and lose invincibility. If the cyborg has a
     * protective cover, it will protect the cyborg and the cover will be used up.
     */
    public void contactWithMonster() {
        if (!this.hasProtectiveCover && !this.hasInvincibility) {
            this.score = 0;
        } else if (this.hasInvincibility) {
            // Logic to destroy the monster when Cyborg is invincible
            this.hasInvincibility = false; // Resetting the invincibility after use
        } else if (this.hasProtectiveCover) {
            // Logic to protect the Cyborg when it has a protective cover
            this.hasProtectiveCover = false; // Resetting the protective cover after use
        }
    }

    /**
     * Returns the score of the cyborg.
     * 
     * @return The score of the cyborg.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns whether the cyborg has a protective cover.
     * 
     * @return True if the cyborg has a protective cover, false otherwise.
     */
    public boolean hasProtectiveCover() {
        return hasProtectiveCover;
    }

    /**
     * Returns whether the cyborg is invincible.
     * 
     * @return True if the cyborg is invincible, false otherwise.
     */
    public boolean isInvincible() {
        return hasInvincibility;
    }

    /**
     * Decreases the score of the cyborg by 1.
     */
    public void decreaseScore() {
        this.score -= 1;
    }

    /**
     * Increases the score of the cyborg by 1.
     */
    public void increaseScore() {
        this.score += 1;
    }
}