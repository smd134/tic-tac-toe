/** The class for the Computer Player, which controls
 * the level of AI strategy and the computer's moves.
 * @file ComputerPlayer.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
public class ComputerPlayer extends Player {
	private int aiLevel;
	
	public ComputerPlayer() {
		aiLevel = 1;
	}
	
	public ComputerPlayer(int aiLevel) {
		if(aiLevel >= 1 && aiLevel <= 3)
			this.aiLevel = aiLevel;	
		else
			aiLevel = 1;
	}
	
	public int getAI() {
		return aiLevel;
	}
	
	/**
	 * Sets the level of AI Strategy
	 * @param aiLevel A number between 1-3 to set the level
	 */
	public void setAI(int aiLevel) {
		if(aiLevel >= 1 && aiLevel <= 3)
			this.aiLevel = aiLevel;
	}

	@Override
	public void move() {
		if(aiLevel == 1) {
			RandomStrategy r = new RandomStrategy();
			r.move();
		} else if(aiLevel == 2) {
			EasyAIStrategy e = new EasyAIStrategy();
			e.move();
		} else if(aiLevel == 3) {
			AdvAIStrategy a = new AdvAIStrategy();
			a.move();
		}
	}
}
