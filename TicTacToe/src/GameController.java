/** The class for the GameController, which contains
 * the board and makes moves as well as checking for win/draw.
 * @file GameController.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class GameController {
	/** The one instance of the class, which makes it a Singleton */
	private static GameController instance = null;
	/** The board that the GameController will control*/
	private Board board = new Board(GameFrame.BOARD_SIZE);
	/** The boolean which controls whose turn it is */
	private boolean is_user_turn = true;
	
	private Player computer = new PlayerFactory().produce("Computer");
	private Player human = new PlayerFactory().produce("Human");
	
	/** The constructor for the GameController, which is declared private
	 *  to prevent other classes from creating another instance of GameController.
	 */
	private GameController() {}
	
	/** Gets the single instance of the GameController class.  Lazy instantiation
	 *  is used, creating the instance only when needed.
	 *  @return instance  The one instance of the GameController object 
	 */
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	/** Resets the GameController to its original state */
	public void reset() {
		is_user_turn = true;
		board = new Board(GameFrame.BOARD_SIZE);
	}
	
	public boolean isUserTurn() {
		return is_user_turn;
	}
	
	public void setUserTurn(boolean b) {
		is_user_turn = b;
	}
	
	public Board getBoard() {
		Board b = board;
		return b;
	}
	
	public Player getComputerPlayer() {
		Player p = computer;
		return p;
	}
	
	/** Picks a random spot on the board and then makes the 
	 *  computer move if possible
	 */
	public void computerMove() {
		computer.move();
	}
	
	/** Checks if the human can move to a certain spot and makes the move
	 *  if possible
	 * @param x  The row index for the human's move
	 * @param y  The column index for the human's move
	 * @return   Whether it is possible to move to the spot
	 */
	public void humanMove(int x, int y) {
		//Checks for a valid move
		if(board.isValidIndex(x) && board.isValidIndex(y) && !board.isFilledCell(x,y)) {
			if(human instanceof HumanPlayer) {
				HumanPlayer h = (HumanPlayer) human;
				h.setNextX(x);
				h.setNextY(y);
				h.move();
			}
		}
	}
	
	public String toString() {
		return board.toString();
	}
	
	/** Checks if there is currently a winner on the board
	 * @param player  The character to test winning combinations on
	 * @return        Whether the player/computer has won or not
	 */
	public boolean hasWon(char player) {
		int size = board.getBoardSize();
		//Check columns
		for(int i = 0; i < size; i++) {
			if(board.getCellAtPosition(board.getLastX(), i) != player)
				break;
			if(i == size - 1)
				return true;
		}
		//Check rows
		for(int i = 0; i < size; i++) {
			if(board.getCellAtPosition(i, board.getLastY()) != player)
				break;
			if(i == size -1)
				return true;
		}
		//Check first diagonal
		if(board.getLastX() ==  board.getLastY()) {
			for(int i = 0; i < size; i++) {
				if(board.getCellAtPosition(i, i) != player)
					break;
				if(i == size - 1)
					return true;
			}
			
		}
		//Check second diagonal
		for(int i = 0; i < size; i++) {
			if(board.getCellAtPosition(i, (size - 1) - i) != player)
				break;
			if(i == size - 1)
				return true;
		}
		//If none of these are true, there must be no winner
		return false;
	}
	
	/** Checks whether the player and computer have drawn the game
	 * (Neither side has won)
	 * @return  Whether there is a draw or not
	 */
	public boolean isDraw() {
		return board.isFull() && !hasWon(GameFrame.HUMAN) && !hasWon(GameFrame.COMPUTER);
	}
	
	/** Gets the winner of the game (either the human's symbol or the computer's)
	 * @return  The symbol (character) of whoever won the game
	 */
	public char getWinner() {
		if(hasWon(GameFrame.HUMAN))
			return GameFrame.HUMAN;
		else if(hasWon(GameFrame.COMPUTER))
			return GameFrame.COMPUTER;
		else
			return 0;
	}
}
