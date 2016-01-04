/** The class for the Difficulty Listener, which
 * sets the AI Strategy level based on which RadioButton 
 * is clicked.
 * @file DifficultyListener.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public class DifficultyListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		GameController g = GameController.getInstance();
		JRadioButton b = (JRadioButton) e.getSource();
		Player p = g.getComputerPlayer();
		System.out.println("hi");
		if(p instanceof ComputerPlayer) {
			ComputerPlayer c = (ComputerPlayer) p;
			//Checks which difficulty level and responds accordingly
			if(b.getActionCommand().equalsIgnoreCase("Random"))
				c.setAI(1);
			else if(b.getActionCommand().equalsIgnoreCase("Easy")) 
				c.setAI(2);
			else if(b.getActionCommand().equalsIgnoreCase("Advanced"))
				c.setAI(3);
		}
	}
}
