package brownian;

/**
 * GUI class
 * 
 * @author WP
 *
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IntroMenu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	AnimationGUI animationGui;
	private Description description;
	private Charts charts;
	private JButton animButton, infoButton, exitButton;
	public static JFrame frame;
    
	//Constructor to create the GUI components
	public IntroMenu () {
		
		// Create the frame
		frame = new JFrame();
				
		// Define the size of the frame
		frame.setSize(400, 400);
				
		// Define position based on a component
		frame.setLocationRelativeTo(null);
				
		// Define how the frame exits (Click the Close Button)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize an Image object as what was returned by requestImage() method
		final Image myImage = requestImage("./img/physics.jpg");

		// Allocate  a new panel and make fill it with myImage
		JPanel panel1 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(myImage, 0, 0, null);
	            }
		};
		
		// Make sure that panel1 fills the whole frame
		panel1.setPreferredSize(new Dimension(400, 400));
		
		// Create panel to hold menu buttons
		JPanel panel2 = new JPanel();
		
		// BoxLayout allows components to be placed on top of each other
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		
		// Create start button and add it to the panel
		animButton = new JButton("Start");
		panel2.add(animButton);
		
		// Add a listener to the button
		animButton.addActionListener(this);
		
	    // Create info button and add it to the panel
		infoButton = new JButton("Info");	         
	 	panel2.add(infoButton);
	 		
	 	// Add a listener to the button
	 	infoButton.addActionListener(this);
	    
		// Create exit button and add it to; the panel
	 	exitButton = new JButton("Exit");	         
		panel2.add(exitButton);
		
		// Add a listener to the button
		exitButton.addActionListener(this);
	    
	    // Add panel holding buttons to the panel with background image
	    panel1.add(panel2);
	    
	    // Set panel1 as ContentPane of the frame
		frame.setContentPane(panel1);
	    
	    // Define the title for the frame
		frame.setTitle("Brownian Motion");
		
		// Show the frame
		frame.setVisible(true);
	}
	
	// Listeners
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
			
		// Open animation panel
		if (ob == animButton) {
			animationGui = new AnimationGUI();
			frame.setVisible(false);
		}
		
		// Open description window
		if (ob == infoButton) {
			description = new Description();
		}
				
		// Exit application
		if (ob == exitButton) {
			System.exit(0);
		}
		
	}
	
	//Method used to read an image from a file and return an image object
	 public Image requestImage(String text) {
		Image image = null;
		 
		try {
			image = ImageIO.read(new File(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return image;
	 }
	
}
