package brownian;

/**
 * Main Program for running the simulation. 
 * 
 * @author AD WP
 *
 */

public class Main {
	
	public Main () {
		new IntroMenu();
	}
	
	public static void main(String[] args) {
		// Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        		Main main = new Main();
	        }
	    });
	}
}
