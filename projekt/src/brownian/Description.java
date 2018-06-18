package brownian;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class displays general information about the experiment. 
 * 
 * @author WP
 *
 */

public class Description extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;
	private JPanel textPanel, imagePanel;
	private JTextArea textArea1;
	private JScrollPane scroll;
	private JLabel label;
	public static JFrame frame;
	static File infoFile = new File("info2.txt");

	public Description() {
		
		// Create the frame
		frame = new JFrame();
		
		// Define the size of the frame
		frame.setSize(1000, 800);
		
		// Define position based on a component
		frame.setLocationRelativeTo(null);
		
		// BorderLayout allows to place components against any of the four borders of the container
	    frame.setLayout(new BorderLayout());

	    // Initialize Menu class object and set it as JMenuBar for our frame
		menu = new Menu();
		frame.setJMenuBar(menu);
		
		// Allocate panel1 which will hold text description
		textPanel = new JPanel();		
		textPanel.setMinimumSize(new Dimension(600,600));

		// Initialize an object of JTextArea class
		textArea1 = new JTextArea();
		
		// Set the line-wrapping policy of the text area.
	    textArea1.setLineWrap(true);
	    textArea1.setWrapStyleWord(true);
	    
	    // Make textArea1 unEditable
	    textArea1.setEditable(false);
	    
	    // Read text from file and put it into textArea1
	    try {
			FileInputStream isr = new FileInputStream(infoFile);
			String description = getFileContent(isr);
			textArea1.setText(description);			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    // Creates a grid layout with the specified number of rows and columns. 
	    textPanel.setLayout(new GridLayout(1,1));
	    textPanel.add(textArea1);
	    
	    // Provides a scrollable view
	    scroll = new JScrollPane(textArea1);
	    scroll.setBorder(null);
	    textPanel.add(scroll);
	    
	    // Add panel1 to the frame
	    frame.add(textPanel,BorderLayout.CENTER);

	    // Initialize a second panel for an image
	    imagePanel = new JPanel();
	   
	    // Read image from the file and set it as JLabel
	    ImageIcon brown = new ImageIcon("./img/brown.jpg");
		label = new JLabel();
		label.setIcon(brown);
		label.setIconTextGap(15);
	    imagePanel.add(label);
	    
	    // Add panel2 to the frame
	    frame.add(imagePanel,BorderLayout.WEST);
	    
		// Define the title for the frame
		frame.setTitle("Brownian Motion");
		
		// Show the frame
		frame.setVisible(true);

	}
	
	// Method used to read file
	public static String getFileContent(FileInputStream fis) throws IOException {
		try( BufferedReader br = new BufferedReader( new InputStreamReader(fis))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while(( line = br.readLine()) != null ) {
				sb.append( line );
				sb.append( '\n' );
			}
			return sb.toString();
		}
	}
	

}
