package Characters;

/**
 * Position is a class that represents a position in a 2D space.
 * It holds x and y coordinates and has methods to get and set these coordinates, and to compare positions.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor for the Position class.
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the position.
     * @return The x-coordinate of the position.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the position.
     * @param x The new x-coordinate of the position.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the position.
     * @return The y-coordinate of the position.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the position.
     * @param y The new y-coordinate of the position.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the position.
     * @return A string representation of the position.
     */
    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * Compares this position to another object for equality.
     * @param obj The object to compare this position to.
     * @return True if the given object represents a Position equivalent to this position, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Position position = (Position) obj;

        if (x != position.x) return false;
        return y == position.y;
    }

    /**
     * Returns a hash code value for the position.
     * @return A hash code value for the position.
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}