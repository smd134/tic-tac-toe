/** The class for the Board object, which uses a
 * multi-dimensional array to store values.
 * @file Board.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class Board {
	/** The array that contains the characters for the board*/
	private char[][] cells;
	/** The size of the board (both length and width) */
	private int board_size;
	/** The most recent x and y values that were filled by a move*/
	private int last_x;
	private int last_y;
	
	/** The constructor for the Board class.
	 * @param board_size
	 */
	public Board(int board_size) {
		this.board_size = board_size;
		cells = new char[board_size][board_size];
	}
	
	public int getBoardSize() {
		return board_size;
	}
	
	public int getLastX() {
		return last_x;
	}
	
	public int getLastY() {
		return last_y;
	}
	
	public void setLastX(int last_x) {
		this.last_x = last_x;
	}
	
	public void setLastY(int last_y) {
		this.last_y = last_y;
	}
	
	/** Checks if the given index is within the board.
	 * @param index  The index to check on the board 
	 * @return Whether the index is valid or not
	 */
	public boolean isValidIndex(int index) {
		return index >= 0 && index < board_size;
	}
	
	/** Checks if the cell is currently filled or not
	 * @param x  The x value of the cell to check on the board
	 * @param y  The y value of the cell to check on the board
	 * @return   Whether the cell is filled or not
	 */
	public boolean isFilledCell(int x, int y) {
		return cells[x][y] != 0;
	}
	
	public char getCellAtPosition(int x, int y) {
		//Might need a check here
		return cells[x][y];
	}
	
	public void setCellAtPosition(int x, int y, char move) {
			cells[x][y] = move;
			last_x = x;
			last_y = y;
	}
	
	/** Checks if the board is currently full
	 * @return Whether the board is full or not
	 */
	public boolean isFull() {
		for(int x = 0; x < board_size; x++) {
			for(int y = 0; y < board_size; y++) {
				if(cells[x][y] == 0)
					return false;
			}
		}
		return true;
	}
	
	/** Creates a String representation of the board
	 * @return s  The board in String form
	 */
	public String toString() {
		String s = "";
		//Loops through the array
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				if(isFilledCell(i, j))
					s += " " + cells[i][j] + " ";
				else
					s += "   ";
				if(j != board_size - 1)
					s += "|";
			}
			if(i != board_size -1)
				s += "\n-----------\n";
		}
		return s;
	}
}