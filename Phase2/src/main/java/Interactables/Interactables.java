package Interactables;

import Characters.Position;

/**
 * Interactables is an abstract class that represents an interactable object in the game.
 * It holds a position and has methods to get and set the position, and to create the item.
 */
public abstract class Interactables {
    private Position position;

    /**
     * Constructor for the Interactables class.
     * @param position The position of the interactable object.
     */
    public Interactables(Position position) {
        this.position = position;
    }

    /**
     * Returns the position of the interactable object.
     * @return The position of the interactable object.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of the interactable object.
     * @param position The new position of the interactable object.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

}