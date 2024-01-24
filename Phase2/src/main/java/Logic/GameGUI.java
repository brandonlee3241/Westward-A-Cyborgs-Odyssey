package Logic;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import Characters.*;
import Interactables.*;

/**
 * GameGUI is the graphical user interface for the game. It extends JFrame and implements KeyListener to provide
 * interactive visual representation of the game state. The class is responsible for rendering the game's grid, 
 * characters, and interactable items. It also handles keyboard input for player interactions.
 *
 * This GUI visually represents the game's board, displaying elements like rewards and the player's character.
 * It interacts closely with the {@link Game} and {@link Board} classes to reflect the current state of the game.
 */
public class GameGUI extends JFrame implements KeyListener {
    /**
     * A two-dimensional array of JPanel objects representing each cell on the game board.
     */
    private JPanel[][] grid;
    /**
     * The main game logic handler, responsible for managing the game's state.
     */
    private Game game;
    /**
     * The game board containing the layout and cells for the game.
     */
    private Board board;
    /**
     * Icon representing a regular reward in the game.
     */
    private ImageIcon regularReward;
    /**
     * Icon representing a bonus reward in the game.
     */
    private ImageIcon bonusReward;

    /**
     * Constructs a new GameGUI object.
     *
     * @param game the game object this GUI is associated with
     * @param rows the number of rows in the game grid
     * @param cols the number of columns in the game grid
     */
    public GameGUI(Game game, int rows, int cols) {
        this.game = game;
        this.board = game.getBoard();
        regularReward = new ImageIcon(getClass().getResource("/object_art/blueheart.png"));
        bonusReward = new ImageIcon(getClass().getResource("/object_art/manacrystal_full.png"));

        setTitle("Westward: Cyborg's Odyssey");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));
        grid = new JPanel[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JPanel panel = new JPanel();
                // panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid[i][j] = panel;
                boardPanel.add(grid[i][j]);
            }
        }

        add(boardPanel);
       

        // Make sure the GameGUI can get the focus for key events
        setFocusable(true);
        requestFocusInWindow();
         addKeyListener(this);
    }

    /**
     * Updates the graphical representation of the game grid.
     * calls the update methods for the cells, cyborg, monsters, rewards, and punishments.
     */
    public void updateGrid() {
        updateCells();
        updateCyborg();
        updateMonsters();
        updateRewards();
        updatePunishments();
    
        // Revalidate and repaint the grid to ensure the changes are displayed
        revalidate();
        repaint();
    }
    

    /**
     * Updates the graphical representation of the cells in the game grid.
     * Iterates over each cell in the grid and updates its background color based on its type.
     */
    public void updateCells() {
        // Iterate over each cell in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                JPanel cell = grid[i][j];
                // Remove all components from the cell
                cell.removeAll();
    
                // Set the background color of the cell based on its type
                if (board.getCell(j, i).getType() == CellType.WALL)
                    cell.setBackground(Color.BLACK);
                else if (board.getCell(j, i).getType() == CellType.ENTRANCE) {
                    cell.setBackground(Color.BLUE);
                } else if (board.getCell(j, i).getType() == CellType.EXIT) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.GREEN);
                }
            }
        }
    }
    
    /**
     * Updates the graphical representation of the cyborg in the game grid.
     * Adds the cyborg to its cell.
     */
    public void updateCyborg() {
        // Add the Cyborg to its cell
        Position cyborgPosition = game.getCyborg().getPosition();
        grid[cyborgPosition.getY()][cyborgPosition.getX()].add(new JLabel(
                new ImageIcon(getClass().getResource("/Characters/cyborg_art/cyborg_down_1.png"))));
    }
    
    /**
     * Updates the graphical representation of the monsters in the game grid.
     * Adds each monster to its cell.
     */
    public void updateMonsters() {
        // Add each Monster to its cell
        for (Monster monster : game.getMonsters()) {
            Position monsterPosition = monster.getPosition();
            JPanel cell = grid[monsterPosition.getY()][monsterPosition.getX()];
            cell.add(new JLabel(monster.getSprite()));
        }
    }
    
    /**
     * Updates the graphical representation of the rewards in the game grid.
     * Adds each reward to its cell.
     */
    public void updateRewards() {
        // Add each Reward to its cell
        for (Reward reward : game.getRewards()) {
            if (reward instanceof RegularReward) {
                Position rewardPosition = reward.getPosition();
                grid[rewardPosition.getY()][rewardPosition.getX()].add(new JLabel(regularReward));
            } else if (reward instanceof BonusReward) {
                Position rewardPosition = reward.getPosition();
                grid[rewardPosition.getY()][rewardPosition.getX()].add(new JLabel(bonusReward));
            }
        }
    }
    
    /**
     * Updates the graphical representation of the punishments in the game grid.
     * Adds each punishment to its cell.
     */
    public void updatePunishments() {
        // Add each Punishment to its cell
        for (Punishment punishment : game.getPunishments()) {
            Position punishmentPosition = punishment.getPosition();
            grid[punishmentPosition.getY()][punishmentPosition.getX()].add(new JLabel(punishment.getSprite()));
        }
    }

    /**
     * This method is not used, but is required by the KeyListener interface.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key press events.
     * This method is called when a key is pressed, and it passes the event to the
     * game's keyPress method.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        game.keyPress(e);
    }

    /**
     * This method is not used, but is required by the KeyListener interface.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}