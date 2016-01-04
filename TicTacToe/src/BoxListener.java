/** The class for the BoxListener, which performs
 * actions when a button is clicked in the GameFrame.
 * @file BoxListener.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class BoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		GameController g = GameController.getInstance();
		Board board = g.getBoard();
		JButton b = (JButton) e.getSource();

			if(b != null && b.isEnabled()) {
				b.setEnabled(false);
			
				//Splits the action command into two coordinate parts
				String[] coords = b.getActionCommand().split(" ");
				//The x and y values to make the human move
				int x = Integer.parseInt(coords[0]);
				int y = Integer.parseInt(coords[1]);
			
				if(g.isUserTurn()) {	
					//Sets the X's icon
					URL url = getClass().getResource("Guitars.png");
					ImageIcon n = new ImageIcon(url); 
					b.setDisabledIcon(n);
					b.setIcon(n);
					//Stores the turn in memory
					g.humanMove(x, y);
					System.out.println("You click " + e.getActionCommand());
					System.out.println(g.toString());
					g.setUserTurn(false);
				} else {
					//Sets the O's icon
					URL url = getClass().getResource("Drum.jpg");
					ImageIcon n = new ImageIcon(url);
					b.setDisabledIcon(n);
					b.setIcon(n);
					//Stores the turn in memory
					System.out.println("Computer moves to " + board.getLastX() + " " + board.getLastY());
					System.out.println(g.toString());
					g.setUserTurn(true);
				}
				//Checks for winner
				if(g.getWinner() == GameFrame.HUMAN) {
					JOptionPane.showMessageDialog(b.getParent(), "Congratulations! You won!");
				} else if(g.getWinner() == GameFrame.COMPUTER) {
					JOptionPane.showMessageDialog(b.getParent(), "The computer won. Better luck next time!");
				} else if(g.isDraw()) {
					JOptionPane.showMessageDialog(b.getParent(), "The game is a draw!" );
				}
			}
		}
	}

