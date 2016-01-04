/** The class for the RestartListener, which performs
	 * actions in response to the Restart button being clicked.
	 * @file RestartListener.java
	 * @author Trevor Farthing (tfarthing)
	 * @date March 19, 2015
	 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Resets the GameController and the GameFrame
		GameController.getInstance().reset();
		GameFrame.reset();
	}
}
