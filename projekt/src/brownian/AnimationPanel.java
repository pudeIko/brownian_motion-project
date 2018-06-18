package brownian;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * The control logic and main display panel for simulation. 
 * 
 * @author AD
 *
 */

public class AnimationPanel extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Particle Parameters
	private static final int MAX = 500; // Max number allowed 
	private int currentNumParticles = 100; // Number currently active
	Color bgColor = Color.white;
	Color bigColor = Color.red;
	Color smallColor = Color.green;
	static int Radius = 50, radius = 10;
	static int Mass = 1000, mass = 10;
	double min = 0.5, max = 2;
	Random r = new Random();
	
	//Graphics setting
	Graphics2D graphicSettings;
	
	// The container rectangular box
	private Board board; 	
	private int canvasWidth;
	private int canvasHeight;

	//A list that holds every particle we create
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	
    //Constructor to create the GUI components and initialize the game objects.
    //Set the drawing canvas to fill the screen (given its width and height).
	public AnimationPanel(int width, int height) {
		canvasWidth = width;
		canvasHeight = height; 
      
		//Create the first 100 particles
		populate();
		
	    
	    
	    // Initialize the Board to fill the screen
	    board = new Board(0, 0, canvasWidth, canvasHeight, bgColor, Color.BLACK);
	    
	    // Handling window resize. Adjust container box to fill the screen.
	    this.addComponentListener(new ComponentAdapter() {
	    	// Called back for first display and subsequent window resize.
	    	@Override
	    	public void componentResized(ComponentEvent e) {
	    		Component c = (Component)e.getSource();
	    		Dimension dim = c.getSize();
	    		canvasWidth = dim.width;
	    		canvasHeight = dim.height;
	    		// Need to resize all components that is sensitive to the screen size.
	    		board.set(0, 0, canvasWidth, canvasHeight);
         }
      });
   }
		 
	public void paintComponent(Graphics g) {
		// Allows me to make many settings changes in regards to graphics
		graphicSettings = (Graphics2D)g; 
  			
  		// Draw a black background that is as big as the game board
  		graphicSettings.setColor(bgColor);
  		graphicSettings.fillRect(0, 0, getWidth(), getHeight());
  			
  		// makes rendering more beautiful
  		graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
  			
  		graphicSettings.setColor(bigColor);
  		
  		// Draw the balls and box
  		board.draw(g);
  		
  		for (Particle p : particles) {
			p.draw(g);
		}
	
  		// Display balls' information
  		g.setColor(Color.BLACK);
  		g.setFont(new Font("Courier New", Font.PLAIN, 12));
  		g.drawString(particles.get(0).toString(), 20, 30);
  }

   void populate() {
	   
	   // Create one big particle an add it to the list particles
	   Particle big = new Particle(400, 200, Radius, 0, 0, Mass, bigColor);
	   particles.add(big);
	   
	   // Create n number of smaller particles and add them to the list particles
	   for(int i = 0; i < currentNumParticles-1; i++){
			
			// Set default position at random
			int x=(new Random()).nextInt(500)+50;
			int y=(new Random()).nextInt(300)+40;
			
			// Set random velocity and angle
			double v = 10*(new Random()).nextGaussian();
			int teta = (new Random()).nextInt(120);
			
			Particle p = new Particle(x, y, radius, (float)v, teta, mass, smallColor);
			particles.add(p);
					
		}		
   }
   
   void addNew() {
	   particles.add(new Particle(20, canvasHeight - 20, radius, 5, 45, mass, smallColor));
   }
	
	// To be invoked when default settings are needed
	public void reset() {
		particles.clear();
		getBoard().setColor(Color.WHITE);
		bigColor = Color.RED;
		smallColor = Color.GREEN;
		Radius = 50;
		radius = 10;
		setCurrentNumParticles(100);
		populate();
	}
	

	// All the getters
   int getCanvasHeight() {
	   return canvasHeight;
   }
   
   int getCurrentNumParticles() {
	   return currentNumParticles;
   }
   
   public Particle getElement(int i) {
	    return particles.get(i);
	}
   
   public int getMax() {
	   return MAX;
   }
  
   public Board getBoard() {
	  return board;
   }
   
	// All the setters
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	public void setBigColor(Color ballColor) {
		this.bigColor = ballColor;
	}
	
	public void setSmallColor(Color ballColor) {
		this.smallColor = ballColor;
	}
	
	public void setBigRadius(int r) {
		AnimationPanel.Radius = r;
	}
	
	public void setSmallRadius(int r) {
		AnimationPanel.radius = r;
	}
	
	void setCurrentNumParticles(int a) {
		currentNumParticles = a;
	}
}