/** The class for the Random Strategy, in which the
 * computer takes random spots on the board.
 * @file RandomStrategy.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
import java.util.Random;

public class RandomStrategy implements ComputerStrategy {
	Random rand;

	public RandomStrategy() {
		rand = new Random();
	}
	
	@Override
	public void move() {
		GameController g = GameController.getInstance();
		Board b = g.getBoard();
		//Gets random values for computer move
		int comp_x = rand.nextInt(b.getBoardSize());
		int comp_y = rand.nextInt(b.getBoardSize());
		//Re-prompt if spot is already taken
		while(b.isFilledCell(comp_x, comp_y)) {
			comp_x = rand.nextInt(b.getBoardSize());
			comp_y = rand.nextInt(b.getBoardSize());
		}
			b.setCellAtPosition(comp_x, comp_y, GameFrame.COMPUTER);
	}
}
