package Logic;
import Characters.GameCharacter;
import Interactables.*;

/**
 * Cell is a class that represents a cell in the game board.
 * It holds information about its coordinates, type, and the character occupying it.
 */
public class Cell {
    private int x;
    private int y;
    private CellType type;
    private GameCharacter occupant;  // Can hold either a Cyborg or a Monster, both subclasses of Character
    private Reward reward;  // Can hold different types of rewards, subclasses of Reward

    /**
     * Default constructor for the Cell class.
     * Initializes the cell with default values.
     */
    public Cell() {
        // Initialization logic here...
    }
    
    /**
     * Constructor for the Cell class.
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @param type The type of the cell.
     */
    public Cell(int x, int y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.occupant = null;  // Initially, there's no occupant
        this.reward = null;  // Initially, there's no reward
    }

    /**
     * Returns the type of the cell.
     * @return The type of the cell.
     */
    public CellType getType() {
        return type;
    }


    /**
     * Sets the type of the cell.
     * @param type The type to set.
     */
    public void setType(CellType type) {
        this.type = type;
    }

    /**
     * Returns the x-coordinate of the cell.
     * @return The x-coordinate of the cell.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the cell.
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the cell.
     * @return The y-coordinate of the cell.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the cell.
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the character occupying the cell.
     * @return The character occupying the cell.
     */
    public GameCharacter getOccupant() {
        return occupant;
    }

    /**
     * Returns the reward in the cell.
     * @return The reward in the cell.
     */
    public Reward getReward() {
        return reward;
    }

    /**
     * Sets the character to occupy the cell.
     * @param occupant The character to set.
     */
    public void setOccupant(GameCharacter occupant) {
        this.occupant = occupant;
    }

    /**
     * Sets the reward in the cell.
     * @param reward The reward to set.
     */
    public void setReward(Reward reward) {
        this.reward = reward;
    }
}