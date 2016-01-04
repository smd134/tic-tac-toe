/** The class for the Human Player, which contains
 * the next move values (that it is about to take)
 * and actually moves for the user.
 * @file HumanPlayer.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class HumanPlayer extends Player {
	//The next x spot for the move about to be made
	private int next_x;
	//The next y spot for the move about to be made
	private int next_y;
	
	public HumanPlayer() {}
	
	public void move() {
		GameController g = GameController.getInstance();
		Board b = g.getBoard();
		b.setCellAtPosition(next_x, next_y, GameFrame.HUMAN);
	}
	
	public void setNextX(int x) {
		next_x = x;
	}
	
	public void setNextY(int y) {
		next_y = y;
	}
}
