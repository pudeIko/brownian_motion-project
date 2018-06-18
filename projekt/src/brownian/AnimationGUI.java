package brownian;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * GUI class
 * 
 * @author WP
 *
 */

public class AnimationGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;
	private	AnimationPanel animationPanel;
	private BottomPanel bottomPanel;
	private RepaintTheBoard repaintTheBoard;
	private Charts chartsPanel;
	public static JFrame frame;
	
	//Constructor to create the GUI components
	public AnimationGUI() {
		super("Brownian Motion Animation");
		
		// Create the frame
		frame = new JFrame();
		
		// Define the size of the frame
		frame.setSize(1000, 800);
		
		// Define position based on a component
		frame.setLocationRelativeTo(null);
		
		// Define how the frame exits (Click the Close Button)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// BorderLayout allows to place components against any of the four borders of the container
	    frame.setLayout(new BorderLayout());

	    // Initialize Menu class object and set it as JMenuBar for our frame
		menu = new Menu();
		frame.setJMenuBar(menu);
		
		// Allocate and add the game panel      
		animationPanel = new AnimationPanel(this.getWidth(), this.getHeight()-100);
		animationPanel.reset();

		// Make the drawing area take up the rest of the frame
		frame.add(animationPanel, BorderLayout.CENTER);
		
	    // Initialize repaintTheBoard class object and start the animation thread
		repaintTheBoard = new RepaintTheBoard(animationPanel);
		repaintTheBoard.gameStart();
		
		// Initialize BottomPanel class object place it at the bottom of the frame
		bottomPanel = new BottomPanel(repaintTheBoard, animationPanel);		
		frame.add(bottomPanel, BorderLayout.SOUTH);
	    
		// Creating another frame to hold all the charts
		chartsPanel = new Charts(repaintTheBoard.getDisplacementSeriesX(), repaintTheBoard.getDisplacementSeriesY(), 
				repaintTheBoard.getVelocitySeriesX(), repaintTheBoard.getVelocitySeriesY(), repaintTheBoard.getKineticEnergy());

		// Define the title for the frame
		frame.setTitle("Brownian Motion");
		
		// Show the frame
		frame.setVisible(true);
	}
}
