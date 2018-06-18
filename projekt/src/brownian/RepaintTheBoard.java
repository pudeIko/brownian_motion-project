package brownian;

import org.jfree.data.xy.XYSeries;

/**
 * Run method used to create an animation
 * 
 * @author AD
 *
 */

public class RepaintTheBoard {
	private static final int UPDATE_RATE = 30;    // Frames per second (fps)
	private static final float EPSILON_TIME = 1e-2f;  // Threshold for zero time
	private XYSeries displacementSeriesX = new XYSeries("DisplacementX");
	private XYSeries displacementSeriesY = new XYSeries("DisplacementY");
	private XYSeries velocitySeriesX = new XYSeries("VelocityX");
	private XYSeries velocitySeriesY = new XYSeries("VelocityY");
	private XYSeries kineticEnergySeries = new XYSeries("KineticEnergy");
	public double timeUnit = 0;


	AnimationPanel animationPanel;
	private boolean paused = false;
	private boolean periodicity = true;

	public RepaintTheBoard(AnimationPanel animationPanel){
			this.animationPanel = animationPanel;
	}
	 
	// A method which makes the particles bouncing
	   public void gameStart() {
	      // Run the game logic in its own thread.
	      Thread gameThread = new Thread() {
	         public void run() {
	            while (true) {
	               long beginTimeMillis, timeTakenMillis, timeLeftMillis;
	               beginTimeMillis = System.currentTimeMillis();
	               
	               if (!paused) {
	                  // Execute one game step
	                  gameUpdate();
	                  // Refresh the display
	                  animationPanel.repaint();
	               }
	               
	               // Provide the necessary delay to meet the target rate
	               // Method currentTimeMillis() returns the current time in milliseconds.
	               timeTakenMillis = System.currentTimeMillis() - beginTimeMillis;
	               timeLeftMillis = 1000L / UPDATE_RATE - timeTakenMillis;
	               if (timeLeftMillis < 5) timeLeftMillis = 5; // Set a minimum
	               
	               // Delay and give other thread a chance
	               try {
	                  Thread.sleep(timeLeftMillis);
	               } catch (InterruptedException ex) {}
	            }
	         }
	      };
	      gameThread.start();  // Invoke GaemThread.run()
	   
	   }
	   
	   // Update the game objects, with proper collision detection and response.
	   public void gameUpdate() {
	      float timeLeft = 1.0f;  // One time-step to begin with
	      
	      // Repeat until the one time-step is up 
	      do {
	         // Find the earliest collision up to timeLeft among all objects
	         float tMin = timeLeft;
	         
	         // Check collision between two balls
	         for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
	            for (int j = 0; j < animationPanel.getCurrentNumParticles(); j++) {
	               if (i < j) {
	                  animationPanel.getElement(i).intersect( animationPanel.getElement(j), tMin);
	                  if ( animationPanel.getElement(i).earliestCollisionResponse.t < tMin) {
	                     tMin =  animationPanel.getElement(i).earliestCollisionResponse.t;
	                  }
	               }
	            }
	         }

	        // Check collision between the balls and the board
	        for(Particle p: animationPanel.particles){
				p.wallTest(animationPanel, periodicity);
			}
	   
	         // Update all the balls up to the detected earliest collision time tMin,
	         // or timeLeft if there is no collision.
	         for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
	        	 animationPanel.getElement(i).update(tMin);
	        	 //update series for charts every 3600th iteration
	        	 if(timeUnit%3600==0) {
	        		 displacementSeriesX.add(timeUnit/36000, animationPanel.getElement(0).getX());
	        		 displacementSeriesY.add(timeUnit/36000, animationPanel.getElement(0).getY());
	        		 velocitySeriesX.add(timeUnit/36000, animationPanel.getElement(0).getVx());
	        		 velocitySeriesY.add(timeUnit/36000, animationPanel.getElement(0).getVy());
	        		 kineticEnergySeries.add(timeUnit/36000, animationPanel.getElement(0).getKineticEnergy());
	        	 }
	        	 timeUnit++;
	        	 
	         }
	         timeLeft -= tMin;                // Subtract the time consumed and repeat
	      } while (timeLeft > EPSILON_TIME);  // Ignore remaining time less than threshold
	   }

	   // Setters
	   void setPaused(boolean paused){
		   this.paused = paused;
	   }
	   
	   void setPeriodicity(boolean periodicity){
		   this.periodicity = periodicity;
	   }
	
	   // Getters
	   boolean getPaused() {
		   return paused;
	   }
	   
	   boolean getPeriodicity() {
		   return periodicity;
	   }
	   
	   XYSeries getDisplacementSeriesX() {
		   return displacementSeriesX;
	   }
	   
	   XYSeries getDisplacementSeriesY() {
		   return displacementSeriesY;
	   }
	   
	   XYSeries getVelocitySeriesX() {
		   return velocitySeriesX;
	   }
	   
	   XYSeries getVelocitySeriesY() {
		   return velocitySeriesY;
	   }
	   
	   XYSeries getKineticEnergy() {
		   return kineticEnergySeries;
	   }
}
