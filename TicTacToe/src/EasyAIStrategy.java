/** The class for the Easy AI Strategy, which
 * takes a pre-defined set of moves on the board.
 * @file EasyAIStrategy.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class EasyAIStrategy implements ComputerStrategy {
	//The array of moves in order that the computer will take
	int[][] preferredMoves;	
	
	public EasyAIStrategy() {
		preferredMoves = new int[][]{{1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
									{0, 1}, {1, 0}, {1, 2}, {2, 1}};
	}
	
	@Override
	public void move() {
		GameController g = GameController.getInstance();
		Board b = g.getBoard();	
		int move = 0;
		int x = preferredMoves[0][0];
		int y = preferredMoves[0][1];
		//Re-prompt if spot is already taken
		while(b.isFilledCell(x, y)) {
			move++;
			x = preferredMoves[move][0];
			y = preferredMoves[move][1];
		}
		b.setCellAtPosition(x, y, GameFrame.COMPUTER);
		System.out.println("Computer moves EASY");
	}
}
