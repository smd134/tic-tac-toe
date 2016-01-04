/** The class for the Player Factory, which creates
 * a certain type of player using the produce function.
 * @file PlayerFactory.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class PlayerFactory {
	
	public PlayerFactory() {}
	
	/** Creates a certain type of player based
	 *  on the input string.
	 * @param playerType  The type of player to be created
	 * @return The new Player
	 */
	public Player produce(String playerType) {
		if(playerType == null)
			return null;
		else if(playerType.equalsIgnoreCase("Human")) 
			return new HumanPlayer();
		else if(playerType.equalsIgnoreCase("Computer"))
			return new ComputerPlayer();
		return null;
	}
}
