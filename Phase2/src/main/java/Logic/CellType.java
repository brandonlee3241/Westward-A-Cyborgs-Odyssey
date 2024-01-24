package Logic;
/**
 * This enum represents the types of cells in the game.
 */
public enum CellType {
    /**
     * Represents a wall cell. Characters cannot move into wall cells.
     */
    WALL,

    /**
     * Represents an empty cell. Characters can move into empty cells.
     */
    EMPTY,

    /**
     * Represents the exit cell. The game ends when the Cyborg moves into the exit
     * cell.
     */
    EXIT,

    /**
     * Represents the entrance cell. The Cyborg starts the game in the entrance
     * cell.
     */
    ENTRANCE;
}