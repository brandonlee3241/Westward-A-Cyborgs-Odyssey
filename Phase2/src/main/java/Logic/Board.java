package Logic;
import Characters.*;
import Interactables.*;

import java.util.List;

/**
 * Board is a class that represents the game board.
 * It manages the cells, score display, and bonus score display.
 */
public class Board {
    private Cell[][] cells;
    private String scoreDisplay;
    private String bonusScoreDisplay;
    private Cell EntranceCell;
    private Cell ExitCell;

    /**
     * Default constructor for the Board class.
     * Initializes the board with a default size.
     */
    public Board() {
        cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // make the borders of the board walls
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    cells[i][j] = new Cell(i, j, CellType.WALL);
                } else {
                    cells[i][j] = new Cell(i, j, CellType.EMPTY);
                }
            }
        }
    }

    /**
     * Constructs a new Board instance based on a given matrix.
     * The matrix represents the layout of the board, where:
     * 0's represent walls,
     * 1's represent open tiles,
     * 2's represent the entrance, and
     * 3's represent the exit.
     * The board is transposed during initialization, effectively rotating it 90
     * degrees.
     *
     * @param matrix The 2D integer array representing the layout of the board.
     */
    public Board(int[][] matrix) {
        cells = new Cell[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                switch (matrix[i][j]) {
                    case 0:
                        cells[j][i] = new Cell(j, i, CellType.WALL);
                        break;
                    case 1:
                        cells[j][i] = new Cell(j, i, CellType.EMPTY);
                        break;
                    case 2:
                        cells[j][i] = new Cell(j, i, CellType.ENTRANCE);
                        EntranceCell = cells[j][i];
                        break;
                    case 3:
                        cells[j][i] = new Cell(j, i, CellType.EXIT);
                        ExitCell = cells[j][i];
                        break;
                }
            }
        }
    }

    /**
     * Constructor for the Board class.
     * Initializes a 2D array of cells with the given width and height.
     * All cells are initially set to EMPTY, except for the border cells which are
     * set to WALL.
     * Also sets the entrance and exit cells based on the provided positions.
     * 
     * @param width            The width of the board.
     * @param height           The height of the board.
     * @param entrancePosition The position of the entrance cell.
     * @param exitPosition     The position of the exit cell.
     */
    public Board(int width, int height, Position entrancePosition, Position exitPosition) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    cells[i][j] = new Cell(i, j, CellType.WALL);
                } else {
                    cells[i][j] = new Cell(i, j, CellType.EMPTY);
                }
            }
        }
        EntranceCell = cells[entrancePosition.getX()][entrancePosition.getY()];
        ExitCell = cells[exitPosition.getX()][exitPosition.getY()];
        EntranceCell.setType(CellType.ENTRANCE);
        ExitCell.setType(CellType.EXIT);
    }

    /**
     * Loads the board.
     */
    public void loadBoard() {
        // Logic to load the board
    }

    /**
     * Returns the cell at the given coordinates.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The cell at the given coordinates.
     */
    // public Cell getCell(int x, int y) {
    // // Return the cell at the given coordinates
    // return cells[x][y];
    // }
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < cells.length && y >= 0 && y < cells[x].length) {
            return cells[x][y];
        }
        return null;
    }

    /**
     * Returns the entrance cell of the board.
     * 
     * @return The entrance cell of the board.
     */
    public Cell getEntranceCell() {
        return EntranceCell;
    }

    /**
     * Returns the exit cell of the board.
     * 
     * @return The exit cell of the board.
     */
    public Cell getExitCell() {
        return ExitCell;
    }

    /**
     * Returns the width of the board.
     * 
     * @return The width of the board.
     */
    public int getWidth() {
        return cells.length;
    }

    /**
     * Returns the height of the board.
     * 
     * @return The height of the board.
     */
    public int getHeight() {
        return cells[0].length;
    }

    /**
     * Updates the locations of all game entities on the board.
     * This method first clears all cell occupants, then sets the occupants of each
     * cell based on the positions of the monsters, cyborg, rewards, and
     * punishments.
     * 
     * @param monsters    The list of monsters in the game.
     * @param cyborg      The cyborg in the game.
     * @param rewards     The list of rewards in the game.
     * @param punishments The list of punishments in the game.
     */
    public void updateLocations(List<Monster> monsters, Cyborg cyborg, List<Reward> rewards, List<Punishment> punishments) {
        clearCellOccupants();
        updateMonsterLocations(monsters);
        updateRewardLocations(rewards);
        updatePunishmentLocations(punishments);
    }
    
    /**
     * Clears the occupants from all cells on the board.
     * This method sets the occupant of each cell to null.
     */
    private void clearCellOccupants() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setOccupant(null);
            }
        }
    }
    
    /**
     * Updates the cell locations for each monster in the game.
     * This method sets the cell occupant to the corresponding monster based on its position.
     *
     * @param monsters The list of monsters to update in the game board.
     */
    private void updateMonsterLocations(List<Monster> monsters) {
        for (Monster monster : monsters) {
            Position position = monster.getPosition();
            Cell cell = getCell(position.getX(), position.getY());
            if (cell != null) {
                cell.setOccupant(monster);
            }
        }
    }
    
    /**
     * Updates the cell locations for each reward in the game.
     * This method sets the reward of the cell based on the position of each reward.
     *
     * @param rewards The list of rewards to update in the game board.
     */
    private void updateRewardLocations(List<Reward> rewards) {
        for (Reward reward : rewards) {
            Position position = reward.getPosition();
            Cell cell = getCell(position.getX(), position.getY());
            if (cell != null) {
                cell.setReward(reward);
            }
        }
    }
    
    /**
     * Updates the cell locations for each punishment in the game.
     * This method sets the cell occupant to the corresponding punishment based on its position.
     *
     * @param punishments The list of punishments to update in the game board.
     */
    private void updatePunishmentLocations(List<Punishment> punishments) {
        for (Punishment punishment : punishments) {
            Position position = punishment.getPosition();
            Cell cell = getCell(position.getX(), position.getY());
            if (cell != null) {
                cell.setOccupant(punishment);
            }
        }
    }
    

    /**
     * Updates the score display.
     * 
     * @param score The score to display.
     */
    public void updateScoreDisplay(int score) {
        this.scoreDisplay = "Score: " + score;
        displayScore();
    }

    /**
     * Updates the bonus score display.
     * 
     * @param bonusScore The bonus score to display.
     */
    public void updateBonusScoreDisplay(int bonusScore) {
        this.bonusScoreDisplay = "Bonus: " + bonusScore;
        displayBonusScore();
    }

    /**
     * Displays the score.
     */
    private void displayScore() {
        // Logic to display the score on the board or UI
        System.out.println(this.scoreDisplay); // Just an example. Replace with actual display logic.
    }

    /**
     * Displays the bonus score.
     */
    private void displayBonusScore() {
        // Logic to display the bonus score on the board or UI
        System.out.println(this.bonusScoreDisplay); // Just an example. Replace with actual display logic.
    }

}