/** The class for the Advanced AI Strategy, which uses
 * a win/block strategy against the user.
 * @file AdvAIStrategy.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class AdvAIStrategy implements ComputerStrategy {
	
	public AdvAIStrategy() {}

	/** Makes possible moves to either win or block against
	 *  the user, and uses the Easy Strategy otherwise.
	 */
	public void move() {
		GameController g = GameController.getInstance();
		Board b = g.getBoard();
		//Gets possible moves to win or block
		int[] win = checkTwo(GameFrame.COMPUTER);
		int[] block = checkTwo(GameFrame.HUMAN);
		if(win != null && !b.isFilledCell(win[0], win[1])) {
			b.setCellAtPosition(win[0],win[1],GameFrame.COMPUTER);
		} else if(block != null && !b.isFilledCell(block[0],block[1])) {
			b.setCellAtPosition(block[0], block[1], GameFrame.COMPUTER);
		} else {
			//Resorts to the EasyAI in all other cases
			EasyAIStrategy e = new EasyAIStrategy();
			e.move();
		}
	}
	
	/**
	 * Checks whether there is two in a row for any player on the board.
	 * @param player  The char to be checked on the board
	 * @return  The array of the two integers that tell where the 
	 *          next move should be 
	 */
	public int[] checkTwo(char player) {
		GameController g = GameController.getInstance();
		Board board = g.getBoard();
		int size = board.getBoardSize();
		//Check columns
		for(int i = 0; i < size; i++) {
			if(board.getCellAtPosition(board.getLastX(), i) != player)
				break;
			if(i == size - 2)
				return new int[]{board.getLastX(), i + 1};
		}
		//Check rows
		for(int i = 0; i < size; i++) {
			if(board.getCellAtPosition(i, board.getLastY()) != player)
				break;
			if(i == size - 2)
				return new int[]{i + 1, board.getLastY()};
		}
		//Check first diagonal
		if(board.getLastX() ==  board.getLastY()) {
			for(int i = 0; i < size; i++) {
				if(board.getCellAtPosition(i, i) != player)
					break;
				if(i == size - 2)
					return new int[]{i + 1, i + 1};
			}
			
		}
		//Check second diagonal
		for(int i = 0; i < size; i++) {
			if(board.getCellAtPosition(i, (size - 1) - i) != player)
				break;
			if(i == size - 2)
				return new int[]{i + 1, (size - 2) - i};
		}
		//If none of these are true
		return null;
	}
}
