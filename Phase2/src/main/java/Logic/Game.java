package Logic;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Characters.*;
import Interactables.*;
//import static UI.JFrame_Start.frame_start;

/**
 * Game is the main class that controls the game logic.
 * It manages the game board, characters, score, and game timer.
 */
public class Game { // <-- Implement the KeyListener interface
    private Board board;
    private Cyborg cyborg;
    private List<Monster> monsters;
    private List<Reward> rewards;
    private List<Punishment> punishments;
    private int score;
    private int bonusScore;
    private GameTimer timer;
    private boolean running;
    private GameGUI gui;
    private boolean bonusRewardSpawned;
    private BonusReward bonusReward;

    /**
     * Default constructor for the Game class.
     * Used for testing functionality.
     * Creates a game board, a Cyborg, a list of monsters, a list of rewards, a
     * list of punishments, and a game timer.
     */
    public Game() {
        // Initialize the game components

        // generate entrance and exit cells
        // at the moment, the entrance and exit cells are hard coded
        // to be at the bottom middle and top middle of the board respectively

        Position entrancePosition = new Position(14, 29);
        Position exitPosition = new Position(14, 0);
        this.bonusRewardSpawned = false;
        this.board = new Board(30, 30, entrancePosition, exitPosition); // Assuming Board has a default constructor
        this.cyborg = new Cyborg(entrancePosition);
        this.monsters = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.punishments = new ArrayList<>();
        this.bonusReward = null;

        // create a monster in every corner
        this.monsters.add(new Monster(new Position(1, 1),
                getClass().getResource("/Characters/monster_art/bat_down_1.png")));
        this.monsters.add(new Monster(new Position(1, 28),
                getClass().getResource("/Characters/monster_art/skeletonlord_down_1.png")));
        this.monsters.add(new Monster(new Position(28, 1),
                getClass().getResource("/Characters/monster_art/orc_down_1.png")));
        this.monsters.add(new Monster(new Position(28, 28),
                getClass().getResource("/Characters/monster_art/redslime_down_1.png")));

        // create some traps to test functionality
        // start by choosing the position
        Position trapPosition1 = new Position(5, 10);
        Position trapPosition2 = new Position(5, 11);

        // create the trap
        punishments.add(new Punishment(trapPosition1));

        punishments.add(new Punishment(trapPosition2));

        // create some regularRewards to test functionality
        // start by choosing the position
        Position regularRewardPosition1 = new Position(5, 5);
        Position regularRewardPosition2 = new Position(5, 6);
        Position regularRewardPosition3 = new Position(5, 7);
        Position regularRewardPosition4 = new Position(5, 8);
        // create the reward
        rewards.add(new RegularReward(regularRewardPosition1));
        rewards.add(new RegularReward(regularRewardPosition2));
        rewards.add(new RegularReward(regularRewardPosition3));
        rewards.add(new RegularReward(regularRewardPosition4));

        this.score = 0;
        this.bonusScore = 0;

        this.timer = new GameTimer(); // Pass the current Game instance to the GameTimer
        this.gui = new GameGUI(this, 30, 30); // assuming 30x30 grid
        this.gui.setVisible(true);
        spawnBonusReward();
       

    }

    /**
     * Constructs a new Game instance based on a matrix read from a file.
     * The matrix represents the game board, where 0's represent walls and 1's
     * represent open tiles.
     * The game also initializes an empty list of monsters, rewards, and
     * punishments, and sets the initial score to 0.
     * A new GameTimer and GameGUI are also created for this game.
     * A bonus reward is spawned and the game tick is started.
     *
     * @param filename The name of the file containing the matrix.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public Game(String filename) throws FileNotFoundException {
        int[][] matrix = readMatrixFromFile(filename);
        this.board = new Board(matrix);
        this.monsters = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.punishments = new ArrayList<>();

        placeCharacters(matrix);
        this.score = 0;
        this.bonusScore = 0;

        this.timer = new GameTimer();
        this.gui = new GameGUI(this, matrix.length, matrix[0].length);
        this.gui.setVisible(true);
        spawnBonusReward();
    

    }

    /**
     * Reads a matrix of integers from a file.
     * 
     * @param filename The name of the file.
     * @return The matrix.
     * @throws FileNotFoundException If the file cannot be found.
     */
    private int[][] readMatrixFromFile(String filename) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream(filename);
        Scanner scanner = new Scanner(is);
        try {
            List<int[]> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] numbers = scanner.nextLine().split("");
                int[] row = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    row[i] = Integer.parseInt(numbers[i]);
                }
                rows.add(row);
            }
            int[][] matrix = new int[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                matrix[i] = rows.get(i);
            }
            return matrix;
        } finally {
            scanner.close();
        }

    }

    /**
     * Places characters, rewards, and punishments on the game board.
     * The positions of these elements are hard-coded.
     * The cyborg is placed at the entrance.
     * Monsters are placed in each corner of the board.
     * Rewards are placed at four specific positions.
     * Punishments are placed at twelve specific positions.
     *
     * @param matrix The 2D integer array representing the game board.
     */
    private void placeCharacters(int[][] matrix) {
        // hard coded locations for the characters, base rewards, punishments, and
        // monsters
        // place the cyborg at the entrance
        Position cyborgPosition = new Position(14, 29);
        this.cyborg = new Cyborg(cyborgPosition);
        // place the monsters
        // create a monster in every corner
        this.monsters.add(new Monster(new Position(1, 1),
                getClass().getResource("/Characters/monster_art/bat_down_1.png")));
        this.monsters.add(new Monster(new Position(1, 28),
                getClass().getResource("/Characters/monster_art/skeletonlord_down_1.png")));
        this.monsters.add(new Monster(new Position(28, 1),
                getClass().getResource("/Characters/monster_art/orc_down_1.png")));
        this.monsters.add(new Monster(new Position(28, 28),
                getClass().getResource("/Characters/monster_art/redslime_down_1.png")));

        // place the rewards, one at 2,2, one at 3,25, one at 25,25, one at 25,5
        rewards.add(new RegularReward(new Position(2, 2)));
        rewards.add(new RegularReward(new Position(3, 25)));
        rewards.add(new RegularReward(new Position(25, 25)));
        rewards.add(new RegularReward(new Position(25, 5)));

        // place the punishments,
        punishments.add(new Punishment(new Position(10, 3)));
        punishments.add(new Punishment(new Position(22, 1)));
        punishments.add(new Punishment(new Position(13, 16)));
        punishments.add(new Punishment(new Position(5, 7)));
        punishments.add(new Punishment(new Position(28, 7)));
        punishments.add(new Punishment(new Position(21, 9)));
        punishments.add(new Punishment(new Position(2, 11)));
        punishments.add(new Punishment(new Position(10, 17)));
        punishments.add(new Punishment(new Position(18, 17)));
        punishments.add(new Punishment(new Position(28, 16)));
        punishments.add(new Punishment(new Position(26, 18)));
        punishments.add(new Punishment(new Position(7, 28)));
        punishments.add(new Punishment(new Position(21, 28)));
        punishments.add(new Punishment(new Position(13, 26)));

    }

    /**
     * Starts the game.
     * Starts the timer, places the Cyborg and Monsters on the board, and starts the
     * game loop.
     */

    public void startGame() {
        // Start the game, maybe start the timer, place Cyborg and Monsters on the board
        timer.startTimer();
        running = true;
        gui.setVisible(true);
        gameLoop();
        
    }

    /**
     * Ends the game.
     * Stops the timer and concludes the game.
     */
    public void endGame() {

        // Conclude the game
        timer.stopTimer();
        // Show final score, display victory/defeat message, etc.
        System.out.println("Final score: " + (score + bonusScore));

        running = false;
    }

    /**
     * Returns the game board.
     * 
     * @return The game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the list of rewards in the game.
     * 
     * @return The list of rewards in the game.
     */
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * Returns the list of punishments in the game.
     * 
     * @return The list of punishments in the game.
     */
    public List<Punishment> getPunishments() {
        return punishments;
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return the current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the current bonus score of the game.
     *
     * @return the current bonus score.
     */
    public int getBonusScore() {
        return bonusScore;
    }

    /**
     * Updates the score.
     * 
     * @param score The score to be added.
     */
    public void updateScore(int score) {
        this.score += score;
        board.updateScoreDisplay(this.score);
    }

    /**
     * Updates the bonus score.
     * 
     * @param score The score to be added.
     */
    public void updateBonusScore(int score) {
        this.bonusScore += score;
        board.updateBonusScoreDisplay(this.bonusScore);
    }

    /**
     * Moves a game character in a specified direction.
     * If the character is a monster and tries to move into a cell occupied by
     * another monster, or tries to move into the entrance/exit, the move is
     * reverted.
     * If the new position is a wall, the move is also reverted.
     * 
     * @param character The game character to move.
     * @param direction The direction to move the character in.
     */
    public void moveCharacter(GameCharacter character, Direction direction) {
        // Save the current position in case we need to revert the move
        Position oldPosition = new Position(character.getPosition().getX(), character.getPosition().getY());

        // Move the character
        character.move(direction);

        // Get the new position
        Position newPosition = character.getPosition();

        // Check if the new position is a wall
        if (board.getCell(newPosition.getX(), newPosition.getY()).getType() == CellType.WALL) {
            // If it is, revert the move
            character.setPosition(oldPosition);
        }

        // check if the character is a monster trying to move into a cell occupied by
        // another monster, or its trying to move into the entrance/exit
        else if ((character instanceof Monster
                && board.getCell(newPosition.getX(), newPosition.getY()).getOccupant() instanceof Monster) ||
                character instanceof Monster
                        && board.getCell(newPosition.getX(), newPosition.getY()).getType() == (CellType.ENTRANCE)
                ||
                character instanceof Monster
                        && board.getCell(newPosition.getX(), newPosition.getY()).getType() == (CellType.EXIT)) {
            // If so, revert the move
            character.setPosition(oldPosition);
        }

    }

    /**
     * Checks if two positions are within a specified distance of each other.
     * Helper function for moveMonsters
     * 
     * @param pos1     The first position.
     * @param pos2     The second position.
     * @param distance The distance to check.
     * @return True if the positions are within the distance, false otherwise.
     */
    private boolean isWithinDistance(Position pos1, Position pos2, int distance) {
        return Math.abs(pos1.getX() - pos2.getX()) <= distance && Math.abs(pos1.getY() - pos2.getY()) <= distance;
    }

    /**
     * Moves each monster in the game.
     * If a monster is within 10 units of the Cyborg, it moves towards the Cyborg.
     * Otherwise, it moves in a random direction.
     */
    public void moveMonsters() {
        Random random = new Random();
        Position cyborgPosition = cyborg.getPosition();
        for (Monster monster : monsters) {
            Position monsterPosition = monster.getPosition();

            if (isWithinDistance(monsterPosition, cyborgPosition, 10)) {
                moveTowardsCyborg(monster);
            } else {
                Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
                moveCharacter(monster, direction);
            }
        }
    }

    /**
     * Moves a monster towards the Cyborg.
     * The monster moves either horizontally or vertically towards the Cyborg,
     * chosen randomly.
     * If the monster is to the right of the Cyborg, it moves left. If it's to the
     * left, it moves right.
     * If the monster is below the Cyborg, it moves up. If it's above, it moves
     * down.
     * 
     * @param monster The monster to move.
     */
    public void moveTowardsCyborg(Monster monster) {
        Random random = new Random();
        boolean moveHorizontally = random.nextBoolean(); // Randomly choose to move horizontally or vertically

        if (moveHorizontally) {
            // Move horizontally towards the Cyborg
            if (monster.getPosition().getX() > cyborg.getPosition().getX()) {
                // If the monster is to the right of the Cyborg, move left
                moveCharacter(monster, Direction.LEFT);
            } else if (monster.getPosition().getX() < cyborg.getPosition().getX()) {
                // If the monster is to the left of the Cyborg, move right
                moveCharacter(monster, Direction.RIGHT);
            }
        } else {
            // Move vertically towards the Cyborg
            if (monster.getPosition().getY() > cyborg.getPosition().getY()) {
                // If the monster is below the Cyborg, move up
                moveCharacter(monster, Direction.UP);
            } else if (monster.getPosition().getY() < cyborg.getPosition().getY()) {
                // If the monster is above the Cyborg, move down
                moveCharacter(monster, Direction.DOWN);
            }
        }
    }

    /**
     * Handles key press events.
     * Moves the Cyborg based on the key pressed.
     * 
     * @param keyEvent The key event.
     */
    public void keyPress(KeyEvent keyEvent) {
        System.out.println("Cyborg is at " + cyborg.getPosition().getX() + ", " + cyborg.getPosition().getY());

        // Get the potential new position based on the key pressed
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                // check if we're on the boundary
                if (cyborg.getPosition().getY() == 0) {
                    break;
                } else {
                    moveCharacter(cyborg, Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (cyborg.getPosition().getY() == board.getHeight() - 1) {
                    break;
                } else {
                    moveCharacter(cyborg, Direction.DOWN);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (cyborg.getPosition().getX() == 0) {
                    break;
                } else {
                    moveCharacter(cyborg, Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (cyborg.getPosition().getX() == board.getWidth() - 1) {
                    break;
                } else {
                    moveCharacter(cyborg, Direction.RIGHT);
                }
                break;
            default:
                break;
        }


    }

    /**
     * Grants a reward to the Cyborg.
     * If the reward is a RegularReward, the Cyborg's score is increased.
     * If the reward is a BonusReward, the bonus score is updated and the reward is
     * removed from the reward list.
     * If the reward is an InvincibilityState, the Cyborg gains invincibility.
     * If the reward is a ProtectiveCover, the Cyborg gains a protective cover.
     * 
     * @param reward The reward to grant to the Cyborg.
     */
    public void grantRewardToCyborg(Reward reward) {
        // case to handle the different types of rewards

        // if the reward is a regular reward
        if (reward instanceof RegularReward) {
            // call the appropriate method to grant the reward to the cyborg
            System.out.println("Reward collected");
            increaseScore();
        } else if (reward instanceof BonusReward) {
            // remove the reward from the reward list
            rewards.remove(bonusReward);
            updateBonusScore(5);
            bonusRewardSpawned = false;
            System.out.println("Bonus reward collected");
        }

    }

    /**
     * Returns the Cyborg in the game.
     * 
     * @return The Cyborg in the game.
     */
    public Cyborg getCyborg() {
        return cyborg;
    }

    /**
     * Returns the list of monsters in the game.
     * 
     * @return The list of monsters in the game.
     */
    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Increases the score of the game by 1.
     */
    public void increaseScore() {
        score++;
    }

    /**
     * Decreases the score of the game by a specified amount.
     * 
     * @param n The amount to decrease the score by.
     */
    public void decreaseScore(int n) {
        score -= n;
    }

    /**
     * Increases the bonus score of the game by 1.
     */
    public void increaseBonusScore() {
        bonusScore++;
    }

    /**
     * Checks the total score of the game.
     * If the total score (score + bonus score) is less than 0, the game ends and a
     * message is printed to the console.
     */
    public void checkTotalScore() {
        if (score + bonusScore < 0) {
            System.out.println("You lose!");
            endGame();
        }
    }

    /**
     * Spawns a bonus reward in the game.
     * The bonus reward is placed in a random cell that is not a wall, entrance,
     * exit, or occupied by another interactable.
     */
    public void spawnBonusReward() {
        // chooses a random cell that is not a wall or an entrance or exit
        // and is also not occupied by another interactable
        Random random = new Random();
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());
        Cell cell = board.getCell(x, y);

        while (cell.getType() == CellType.WALL || cell.getType() == CellType.ENTRANCE
                || cell.getType() == CellType.EXIT || cell.getOccupant() != null) {
            x = random.nextInt(board.getWidth());
            y = random.nextInt(board.getHeight());
            cell = board.getCell(x, y);
        }

        bonusReward = new BonusReward(new Position(x, y));
        // create a bonus reward at the chosen cell
        rewards.add(bonusReward);
        bonusRewardSpawned = true;
    }

    /**
     * Checks the status of the bonus reward in the game.
     * If a bonus reward has not been spawned, there is a low chance to spawn one.
     * If a bonus reward has been spawned and its duration is over 10000, it is
     * removed from the game and a message is printed to the console.
     */
    public void checkBonus() {
        if (bonusRewardSpawned == false) {
            // have a low chance to spawn a bonus reward
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 2) {
                spawnBonusReward();
            }
        } else if (bonusRewardSpawned == true) {
            if (bonusReward.getDuration() > 10000) {
                rewards.remove(bonusReward);
                bonusRewardSpawned = false;
                System.out.println("Bonus reward expired");
            }
        }
    }

    /**
     * Checks for interactions between characters in the game.
     * If a Cyborg and a Monster are in the same cell, the game ends.
     * If a Cyborg and a Punishment are in the same cell, the Cyborg's score is
     * decreased and the Punishment is removed.
     * If a Cyborg and a Reward are in the same cell, the Reward is granted to the
     * Cyborg and removed from the game.
     * If the Cyborg is in the exit cell and the score is 4, the game ends and the
     * player wins.
     */
    public void checkInteractions() {
        // Logic to check for interactions between characters
        // For example, if a Cyborg and a Monster are in the same cell,
        // the Cyborg's score should be decreased.

        // first get cyborgs position
        Position cyborgPosition = cyborg.getPosition();
        // check if the cell at cyborgs position has an occupant
        Cell cell = board.getCell(cyborgPosition.getX(), cyborgPosition.getY());

        if (cell != null) {
            // get the occupant
            GameCharacter occupant = cell.getOccupant();
            Reward reward = cell.getReward();
            // check if the occupant is a monster
            if (occupant instanceof Monster) {
                // if it is, decrease the cyborgs score
                // debug message to see if score is being decreased
                System.out.println("You lose!");
                endGame();
                return;
            }
            // check if occupant is a punishment
            else if (occupant instanceof Punishment) {
                // if it is, decrease the cyborgs score
                // debug message to see if score is being decreased

                decreaseScore(5);
                punishments.remove(occupant);
            }

            else if (reward instanceof Reward) {
                // if it is, call the appropriate method to grant the reward to the cyborg
                // which is handled in the reward class
                grantRewardToCyborg(reward);
                cell.setReward(null);
                rewards.remove(reward);
                return;
            }

            else if (cell.getType() == CellType.EXIT && score == 4) {
                System.out.println("You win!");
                endGame();
            }

        }
    }

    /**
     * The game loop.
     * Runs the game tick at a fixed rate.
     */
    public void gameLoop() {
        final int FPS = 10; // Example FPS, adjust as needed
        final long TIME_PER_UPDATE = 1000 / FPS;
        int counter = 0;

        while (running) {
            long start = System.currentTimeMillis();

            board.updateLocations(monsters, cyborg, rewards, punishments);
            checkInteractions();
            gui.updateGrid();
            checkBonus();
            checkTotalScore();

            // Call moveMonsters once every second
            if (counter == FPS/2) {
                moveMonsters();
                counter = 0;
            } else {
                counter++;
            }

            long elapsed = System.currentTimeMillis() - start;
            long waitTime = TIME_PER_UPDATE - elapsed;
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        gui.dispose();
        gui = null;
    }

    /**
     * Checks if the game is currently running.
     *
     * @return {@code true} if the game is running, {@code false} otherwise.
     */
    public boolean isGameRunning() {
        return this.running;
    }

}