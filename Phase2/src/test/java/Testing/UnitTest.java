package Testing;

import Characters.*;
import Interactables.*;
import Logic.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

import org.junit.Test;

/**
 * UnitTest Class
 * Test single feature of the game
 */

public class UnitTest {
	
	/**
	 * Characters Test
	 * Test all elements in Characters Package
	 */
	
	/**
	 * Test Cyborg Object Initiation
	 */
	@Test
	public void CyborgInitiationTest() {
		Position initialPosition = new Position(14, 29);
		Cyborg cyborg = new Cyborg(initialPosition);
		assertEquals(initialPosition,cyborg.getPosition());
	}
	
	/**
	 * Test Cyborg Object Image
	 */
	@Test
	public void CyborgImageTest() {
		Position initialPosition = new Position(14, 29);
		Cyborg cyborg = new Cyborg(initialPosition);
		assertEquals(new ImageIcon(getClass().getResource("/Characters/cyborg_art/cyborg_down_1.png")).toString(),cyborg.getDown1Image().toString());
	}

	/**
	 * Test Cyborg Object Move
	 */
	@Test
	public void CyborgMoveTest() {
		Position initialPosition = new Position(14, 29);
		Cyborg cyborg = new Cyborg(initialPosition);
		cyborg.move(Direction.UP);
		assertEquals(new Position(14, 28),cyborg.getPosition());
		cyborg.move(Direction.DOWN);
		assertEquals(new Position(14, 29),cyborg.getPosition());
		cyborg.move(Direction.LEFT);
		assertEquals(new Position(13, 29),cyborg.getPosition());
		cyborg.move(Direction.RIGHT);
		assertEquals(new Position(14, 29),cyborg.getPosition());
	}

	/**
	 * Test Cyborg Object Collect Reward
	 */
	@Test
	public void CyborgCollectRewardTest() {
		Position initialPosition = new Position(14, 29);
		Cyborg cyborg = new Cyborg(initialPosition);
		BonusReward reward = new BonusReward(initialPosition);
		cyborg.collectReward(reward);
		assertEquals(5,cyborg.getScore());
	}

	/**
	 * Test Cyborg Object Hit Trap
	 */
	@Test
	public void CyborgHitTrapTest() {
		Position initialPosition = new Position(14, 29);
		Cyborg cyborg = new Cyborg(initialPosition);
		cyborg.hitTrap();
		assertEquals(-10,cyborg.getScore());
	}

	/**
	 * Test Cyborg Object Contact Monster
	 */
	@Test
	public void CyborgContactMonsterTest() {
		Position initialPosition = new Position(14, 29);
		Cyborg cyborg = new Cyborg(initialPosition);
		BonusReward reward = new BonusReward(initialPosition);
		cyborg.collectReward(reward);
		cyborg.contactWithMonster();
		assertEquals(0,cyborg.getScore());
	}

	/**
	 * Test BonusReward Object Duration Timing
	 */
	@Test
	public void BonusRewardTest() {
		Position initialPosition = new Position(14, 29);
		BonusReward reward = new BonusReward(initialPosition);
		assertNotEquals(System.currentTimeMillis(),reward.getDuration());
	}
	
	/**
	 * Test the Poistion Function
	 */
	@Test
	public void PositionTest() {
		Position initialPosition = new Position(14, 29);
		assertEquals(14, initialPosition.getX());
		assertEquals(29, initialPosition.getY());
		assertEquals("Position{x=14, y=29}", initialPosition.toString());
		assertEquals(31 * 14 + 29, initialPosition.hashCode());
	}
	
	/**
	 * Test Punishment
	 */
	@Test
	public void PunishmentTest() {
		Position initialPosition = new Position(14, 29);
		Punishment punishment = new Punishment(initialPosition);
		ImageIcon punishmentSprite = new ImageIcon(getClass().getResource("/tile_art/trap.png"));
		assertEquals(punishmentSprite.toString(), punishment.getSprite().toString());
	}

	@Test
	public void PunishmentTest2() {
		Position initialPosition = new Position(14, 29);
		Punishment punishment = new Punishment(initialPosition, "/tile_art/trap.png");
		ImageIcon punishmentSprite = new ImageIcon("/tile_art/trap.png");
		assertEquals(punishmentSprite.toString(), punishment.getSprite().toString());
	}

	/**
	 * Test Monster
	 */
	@Test
	public void MonsterTest() {
		Position initialPosition = new Position(14, 29);
		Monster monster = new Monster(initialPosition, getClass().getResource("/Characters/monster_art/bat_down_1.png"));
		ImageIcon monsterSprite = new ImageIcon(getClass().getResource("/Characters/monster_art/bat_down_1.png"));
		assertEquals(monsterSprite.toString(), monster.getSprite().toString());
	}
	

	/**
	 * Test Interactables
	 */

	/**
	* Test RegularReward
	*/
	@Test
	public void RegularRewardTest() {
		Position initialPosition = new Position(14, 29);
		RegularReward reward = new RegularReward(initialPosition);
		assertEquals(1, reward.getValue());
	}

	/**
	 * Test Logic
	 */

	/**
	 * Test Trap
	 */
	@Test
	public void TrapTest() {
		Position initialPosition = new Position(14, 29);
		Trap trap = new Trap(initialPosition, "/tile_art/trap.png");
		ImageIcon trapSprite = new ImageIcon("/tile_art/trap.png");
		assertEquals(trapSprite.toString(), trap.getSprite().toString());
	}
	
	/**
	 * Test Cell
	 */
	@Test
	public void CellTest() {
		Cell cell = new Cell(4, 5, CellType.WALL);
		assertEquals(CellType.WALL, cell.getType());
		cell.setType(CellType.EMPTY);
		assertEquals(CellType.EMPTY, cell.getType());
		assertEquals(4, cell.getX());
		assertEquals(5, cell.getY());
		cell.setX(5);
		cell.setY(6);
		assertEquals(5, cell.getX());
		assertEquals(6, cell.getY());
	}
	
	/**
	 * Test Board
	 */
	@Test
	public void BoardTest() {
		Board board_simple = new Board();
		assertEquals(10, board_simple.getWidth());
		assertEquals(10, board_simple.getHeight());
		Board board = new Board(new int[][] {{1, 2, 3}, {0, 0, 0}, {0, 0, 0}});
		assertEquals(3, board.getWidth());
		assertEquals(3, board.getHeight());
		assertEquals(CellType.WALL, board.getCell(0, 1).getType());
		assertEquals(CellType.EMPTY, board.getCell(0, 0).getType());
		assertEquals(CellType.ENTRANCE, board.getCell(1, 0).getType());
		assertEquals(CellType.EXIT, board.getCell(2, 0).getType());
		Board board2 = new Board(5, 5, new Position(0,0), new Position(4,4));
		assertEquals(5, board2.getWidth());
		assertEquals(5, board2.getHeight());
		assertEquals(CellType.ENTRANCE, board2.getCell(0, 0).getType());
		assertEquals(CellType.EXIT, board2.getCell(4, 4).getType());
		assertEquals(null, board2.getCell(6, 1));
		assertEquals(CellType.ENTRANCE, board2.getEntranceCell().getType());
		assertEquals(CellType.EXIT, board2.getExitCell().getType()); 
	}



	/**
	 * Test Game
	 */
	@Test
	public void GameTest() {
		Game game = new Game();
		assertEquals(30, game.getBoard().getWidth());
		assertEquals(30, game.getBoard().getHeight());
		assertEquals(CellType.ENTRANCE, game.getBoard().getCell(14, 29).getType());
		assertEquals(CellType.EXIT, game.getBoard().getCell(14, 0).getType());
	}

	/**
	 * Test Game from Matrix
	 */
	@Test
	public void GamereadMatrixTest() throws FileNotFoundException {
		String filename = "/brandonMapMatrix.txt";
		Game game = new Game(filename);
		assertEquals(30, game.getBoard().getWidth());
		assertEquals(30, game.getBoard().getHeight());
		assertEquals(CellType.ENTRANCE, game.getBoard().getCell(14, 29).getType());
		assertEquals(CellType.EXIT, game.getBoard().getCell(14, 0).getType());
	}

	/**
	 * Test GameTimer
	 */
	@Test
	public void GameTimerTest() {
		GameTimer timer = new GameTimer();
		assertEquals(0, timer.getElapsedSeconds());
		timer.startTimer();
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.stopTimer();
		assertEquals(1, timer.getElapsedSeconds());
	}
}

