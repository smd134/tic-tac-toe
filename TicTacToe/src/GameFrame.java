/** The class for the GameFrame, which initializes
 *  a window (JFrame).
 * @file GameFrame.java
 * @author Trevor Farthing (tfarthing)
 * @date March 19, 2015
 */
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GameFrame extends JFrame {
	/** The number of rows/columns on the board */
	public static final int BOARD_SIZE = 3;
	/** The symbols for the human and computer */
	public static final char HUMAN = 'X';
	public static final char COMPUTER = 'O';
	/** The height and width of the window */
	public static final int WINDOW_HEIGHT = 700;
	public static final int WINDOW_WIDTH = 700;
	
	/** The array of "box" buttons on the game Window */
	private static JButton[][] buttons;
	/** The URL for the music to be played in the background */
	private static URL music;
	private JPanel contentPane;
	private static GameFrame window;
	private static boolean quit = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		window = new GameFrame();
		window.setVisible(true);
		window.setResizable(false);
		window.playMusic(music);
		GameController g = GameController.getInstance();
		while(!quit) { //Condition needs to be updated
			System.out.println("loop check");
			if(!g.isUserTurn() && !g.hasWon(HUMAN)) {
				window.setButton();
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public GameFrame() {
		initialize();
	}
	
	/** Resets the GameFrame */
	public static void reset() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				buttons[i][j].setEnabled(true);
				buttons[i][j].setIcon(null);
			}
		}
	}
	
	/** Performs the computer's move to store it in
	 * memory and then simulates pressing the button.
	 */
	public void setButton() {
		//Performs the move
		GameController.getInstance().computerMove();
		int x = GameController.getInstance().getBoard().getLastX();
		int y = GameController.getInstance().getBoard().getLastY();
		//Simulates the computer clicking a button
		if(buttons[x][y].isEnabled())
			buttons[x][y].doClick();
	}
	
	/** Plays the music in the background during the game.
	 * @param url  The URL of the music to be played in the background
	 */
	public void playMusic(URL url) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();				
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Constructs the frame by adding all of its features */
	public void initialize() {
		//Creates the contentPane
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		getContentPane().add(contentPane);
		
		//Creates the window (frame)
		setTitle("Trevor's Tic Tac Toe - Version 3.0");
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							
		//Creates the top panel
		JPanel top_panel = new JPanel();
		top_panel.setBackground(new Color(255, 255, 102));
		contentPane.add(top_panel, BorderLayout.NORTH);
							
	    //Adds the label for the title of the game
		JLabel lblTicTacToe = new JLabel("TIC TAC TOE N' ROLL");
		lblTicTacToe.setForeground(Color.RED);
		lblTicTacToe.setFont(new Font("Chalkduster", Font.BOLD, 20));
		top_panel.add(lblTicTacToe);
		
		//Adds the restart button
		JButton btnRestartGame = new JButton("Restart Game");
		top_panel.add(btnRestartGame);
		RestartListener r = new RestartListener();
		btnRestartGame.addActionListener(r);
		
		ButtonGroup difficulties = new ButtonGroup();
		
		//Creates the random AI difficulty button
		JRadioButton rdbtnRandom = new JRadioButton("Random");
		top_panel.add(rdbtnRandom);
		difficulties.add(rdbtnRandom);
		difficulties.setSelected(rdbtnRandom.getModel(), true);
		rdbtnRandom.setActionCommand("Random");
		DifficultyListener d1 = new DifficultyListener();
		rdbtnRandom.addActionListener(d1);		
		
		//Creates the Easy AI difficulty button
		JRadioButton rdbtnEasyAi = new JRadioButton("Easy");
		top_panel.add(rdbtnEasyAi);
		difficulties.add(rdbtnEasyAi);
		rdbtnEasyAi.setActionCommand("Easy");
		DifficultyListener d2 = new DifficultyListener();
		rdbtnEasyAi.addActionListener(d2);	
		
		//Creates the Advanced AI difficulty button
		JRadioButton rdbtnAdv = new JRadioButton("Advanced");
		top_panel.add(rdbtnAdv);
		difficulties.add(rdbtnAdv);
		rdbtnAdv.setActionCommand("Advanced");
		DifficultyListener d3 = new DifficultyListener();
		rdbtnAdv.addActionListener(d3);	
				
		//Creates the bottom panel
		JPanel bottom_panel = new JPanel();
		contentPane.add(bottom_panel, BorderLayout.SOUTH);
				
		//Label with instructions on how to play
		JLabel lblClickBoxesTo = new JLabel("Click boxes to play!");
		bottom_panel.add(lblClickBoxesTo);
				
		//Creates the middle panel for the grid of buttons
		JPanel middle = new JPanel();
		middle.setForeground(new Color(0, 0, 0));
		middle.setBackground(new Color(255, 204, 0));
		middle.setBorder(new LineBorder(new Color(255, 0, 0), 5));
		contentPane.add(middle, BorderLayout.CENTER);
		middle.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 0, 0));
		
		//Sets up the array of buttons
		buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
				
		//Adds and sets up the amount of buttons needed for the game
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				JButton cell = new JButton();
				middle.add(cell);
				buttons[i][j] = cell;
				cell.setToolTipText("Click me!");
				BoxListener b = new BoxListener();
				cell.addActionListener(b);
				cell.setActionCommand(i + " " + j);
				cell.setBorder(new LineBorder(new Color(0)));
			}			
		}	
		//Assigns the URL to the music file
		music = this.getClass().getClassLoader().getResource("RockandRoll.wav");
	}
}
