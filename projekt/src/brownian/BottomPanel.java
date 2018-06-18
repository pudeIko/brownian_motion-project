package brownian;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class for all the Swing elements used to manipulate the simulation
 * 
 * @author WP & AD
 *
 */

public class BottomPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton smallRadiusButton, smallMassButton, smallColorButton, smallRandomColorButton;
	private JButton bigRadiusButton, bigMassButton, bigColorButton;
	private JButton bgColorButton, resetButton, launcherButton;
	private JTextField launcherField;
	private static int launchedParticles;
	private JCheckBox pauseCheckBox, aperiodicCheckBox;
	private JSlider speedSlider;
	private JPanel smallParticlesPAanel, bigParticlesPanel, animationSettingsPanel;
	private JLabel smallParticlesNumber;
	private AnimationPanel animationPanel;
	private RepaintTheBoard repaintTheBoard;
	boolean launchBoolean = true;
	
	//Constructor to initialize UI components of the controls
	public BottomPanel(RepaintTheBoard repaintTheBoard, AnimationPanel animationPanel) {
		this.repaintTheBoard = repaintTheBoard;
		this.animationPanel = animationPanel;
		
		// Create a border for settings menu at the bottom of the frame
		Border border = BorderFactory.createTitledBorder("SIMULATION SETTINGS");
		this.setBorder(border);
			
		// Set GridLayout
		this.setLayout(new GridLayout(3, 1));

		// Create instance of a listeners for JComponents
		buttonListener bListener = new buttonListener();
		checkBoxListener cListener = new checkBoxListener();
		sliderListener sListener = new sliderListener();

		// Panel to hold all the settings for the small particle
		smallParticlesPAanel = new JPanel();
		Border Border1 = BorderFactory.createTitledBorder("Small particles properties");
		smallParticlesPAanel.setBorder(Border1);
		
		// Button used to change the radius of a particle
		smallRadiusButton = new JButton("Set Radius");
		smallRadiusButton.setToolTipText("Change radius of small particles");
		smallRadiusButton.addActionListener(bListener);
		smallParticlesPAanel.add(smallRadiusButton);
				
		// Button used to change the mass of a particle
		smallMassButton = new JButton("Set Mass");
		smallMassButton.setToolTipText("Change mass of small particles");
		smallMassButton.addActionListener(bListener);
		smallParticlesPAanel.add(smallMassButton);
				
		// Button used to change the color of a particle
		smallColorButton = new JButton("Set Color");
		smallColorButton.setToolTipText("Change color of small particles");
		smallColorButton.addActionListener(bListener);
		smallParticlesPAanel.add(smallColorButton);
			
		// Button used to change the color of a particle to random
		smallRandomColorButton = new JButton("Random Color");
		smallRandomColorButton.setToolTipText("Give all small particles random color");
		smallRandomColorButton.addActionListener(bListener);
		smallParticlesPAanel.add(smallRandomColorButton);
				
		// Slider for adjusting the speed of all the balls by a factor
		int minFactor = 5;    // percent
		int maxFactor = 200;  // percent
		speedSlider = new JSlider(JSlider.HORIZONTAL, minFactor, maxFactor, 100);
		speedSlider.setToolTipText("Change speed of small particles in range from 5% to 200% of current speed");
		speedSlider.addChangeListener(sListener);
		smallParticlesPAanel.add(new JLabel("Speed"));
		smallParticlesPAanel.add(speedSlider);
		
		// Add panel1 to the bottomPanel
		this.add(smallParticlesPAanel);

		// Panel to hold all the settings for the big particle
		bigParticlesPanel = new JPanel();
		Border Border2 = BorderFactory.createTitledBorder("Big particle properties");
		bigParticlesPanel.setBorder(Border2);
				
		// Button used to change the radius of a particle
		bigRadiusButton = new JButton("Set Radius");
		bigRadiusButton.setToolTipText("Change radius of big particle");
		bigRadiusButton.addActionListener(bListener);
		bigParticlesPanel.add(bigRadiusButton);
				
		// Button used to change the mass of a particle
		bigMassButton = new JButton("Set Mass");
		bigMassButton.setToolTipText("Change mass of big particle");
		bigMassButton.addActionListener(bListener);
		bigParticlesPanel.add(bigMassButton);
				
		// Button used to change the color of a particle
		bigColorButton = new JButton("Set Color");
		bigColorButton.setToolTipText("Change color of big particle");
		bigColorButton.addActionListener(bListener);
		bigParticlesPanel.add(bigColorButton);

		// Add panel2 to the bottomPanel
		this.add(bigParticlesPanel);

		// Panel holding all the general options
		animationSettingsPanel = new JPanel();
		Border Border3 = BorderFactory.createTitledBorder("General options");
		animationSettingsPanel.setBorder(Border3);
				
		// Checkbox to toggle pause/resume movement
		animationSettingsPanel.add(new JLabel("Pause"));
		pauseCheckBox = new JCheckBox();
		pauseCheckBox.setToolTipText("Pause animation");
		pauseCheckBox.addItemListener(cListener);
		animationSettingsPanel.add(pauseCheckBox);
		
		// Checkbox to enable aperiodic boundary conditions
		animationSettingsPanel.add(new JLabel("Aperiodic"));
		aperiodicCheckBox = new JCheckBox();
		aperiodicCheckBox.setToolTipText("Change borders mode to aperiodic");
		aperiodicCheckBox.addItemListener(cListener);
		animationSettingsPanel.add(aperiodicCheckBox);
				
		// Button for launching the remaining balls
		launcherField = new JTextField("Enter number");
		launcherField.setToolTipText("Set number of particles to launch");
		launcherField.setPreferredSize(new Dimension(100, 20));
		launcherButton = new JButton("Launch!");
		launcherButton.setToolTipText("Launch new particles.");
		launcherButton.addActionListener(bListener);
		animationSettingsPanel.add(launcherField);
		animationSettingsPanel.add(launcherButton);
		
		// Button used to change the background color
		bgColorButton = new JButton("BG COLOR");
		bgColorButton.setToolTipText("Change background color");
		bgColorButton.addActionListener(bListener);
		animationSettingsPanel.add(bgColorButton);
						
		// Button used to go back to the default settings
		resetButton = new JButton("RESET");
		resetButton.setToolTipText("Reset all changes");
		resetButton.addActionListener(bListener);
		animationSettingsPanel.add(resetButton);
		
		// Label displaying current number of particles
		smallParticlesNumber = new JLabel("No. of particles: " + animationPanel.getCurrentNumParticles());
		smallParticlesNumber.setToolTipText("Current number of particles");
		animationSettingsPanel.add(smallParticlesNumber);
		
		// Add panel3 to the bottomPanel
		this.add(animationSettingsPanel);
	}
	
	// Implement the listeners	
	
	// Listener for checkBox
	private class checkBoxListener implements ItemListener{
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(pauseCheckBox.isSelected()) {
					repaintTheBoard.setPaused(true); 
					transferFocusUpCycle();  
				}
				else if (!pauseCheckBox.isSelected()) {
		           repaintTheBoard.setPaused(false);
		           transferFocusUpCycle();  
		       }
				if(aperiodicCheckBox.isSelected()) {
					repaintTheBoard.setPeriodicity(false);
				}
				else if (!aperiodicCheckBox.isSelected()) {
					repaintTheBoard.setPeriodicity(true);
			    }
		    }
		}
	
	// Listener for Slider
	private class sliderListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				final float[] ballSavedSpeedXs = new float[animationPanel.getMax()];
				final float[] ballSavedSpeedYs = new float[animationPanel.getMax()];
				for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
					ballSavedSpeedXs[i] = animationPanel.getElement(i).vx;
					ballSavedSpeedYs[i] = animationPanel.getElement(i).vy;
				}
				//int minFactor = 5;    // percent
				//int maxFactor = 200;  // percent
				
				int percentage = (int)source.getValue();
				for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
					animationPanel.getElement(i).vx = ballSavedSpeedXs[i] * percentage / 100.0f;
					animationPanel.getElement(i).vy = ballSavedSpeedYs[i] * percentage / 100.0f;
				}
			}
			transferFocusUpCycle();  // To handle key events
		}
	 }
		
	// Listener for Buttons
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Change radius of the small particles
			if(e.getSource() == smallRadiusButton) {
				int oldR = AnimationPanel.radius;
				int newR = oldR;
				try {
					newR = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.setSmallRadius(newR);
					for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
						animationPanel.getElement(i).radius = newR;
					}
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}

			// Change mass of the small particles
			else if(e.getSource() == smallMassButton) {
				int oldM = AnimationPanel.mass;
				int newM = oldM;
				try {
					newM = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new mass", "Edit mass", JOptionPane.QUESTION_MESSAGE, null, null, oldM).toString());
					if(oldM == newM) { return; }
					for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
						animationPanel.getElement(i).mass = newM;
					}
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			// Change color of the small particles
			else if(e.getSource() == smallColorButton) {
				Color color1 = JColorChooser.showDialog(null, "Change the color of small particles", Color.WHITE);
				for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
					animationPanel.getElement(i).color = color1;
				}
				animationPanel.setSmallColor(color1);
			}
			
			// Change color of the small particles to random
			else if(e.getSource() == smallRandomColorButton) {
				for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
					animationPanel.getElement(i).color = Color.getHSBColor((float)(2*3.1416*animationPanel.getElement(i).vy/animationPanel.getElement(i).vx), 1f, 
							(float)(animationPanel.getElement(i).y/animationPanel.getElement(i).x));
				}
			}			
			
			// Change radius of the big particle
			else if(e.getSource() == bigRadiusButton) {
				int oldR = AnimationPanel.Radius;
				int newR = oldR;
				try {
					newR = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.getElement(0).radius = newR;
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			// Change mass of the big particle
			else if(e.getSource() == bigMassButton) {
				int oldM = AnimationPanel.Mass;
				int newM = oldM;
				try {
					newM = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new mass", "Edit mass", JOptionPane.QUESTION_MESSAGE, null, null, oldM).toString());
					if(oldM == newM) { return; }
					animationPanel.getElement(0).mass = newM;

				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			// Change color of the big particle
			else if(e.getSource() == bigColorButton) {
				Color color2 = JColorChooser.showDialog(null, "Change the color of big particle", Color.WHITE);
				animationPanel.getElement(0).color = color2;
			}
			
			// Change color of the background
			else if(e.getSource() == bgColorButton) {
				Color color3 = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
				animationPanel.getBoard().setColor(color3);
				animationPanel.repaint();
			}
			
			// Call the method reset from AnimationPanel whenever reset Button is pressed
			else if(e.getSource() == resetButton) {
				if(!repaintTheBoard.getPaused()) {
					repaintTheBoard.setPaused(true);
					try {
						Thread.sleep(10);
						animationPanel.reset();
						repaintTheBoard.setPaused(false);
						smallParticlesNumber.setText("No. of particles " + animationPanel.getCurrentNumParticles());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else {
					animationPanel.reset();
					repaintTheBoard.setPaused(false);
					pauseCheckBox.setSelected(false);
					smallParticlesNumber.setText("No. of particles " + animationPanel.getCurrentNumParticles());
				}
			
			}
			
			// Launcher button listener used to launch new particles
			else if(e.getSource() == launcherButton) {
				
				// Set the number of launched particles
				try{
					launchedParticles = Integer.parseInt(launcherField.getText());
				}
				// Make sure input was a number
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); 
				}
					
				// Make a new thread to control the release of the particles
					new Thread(() ->{
						int counter = 0;
						while (launchBoolean) {
							Toolkit.getDefaultToolkit().sync();
							try {
								Thread.sleep(250);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (animationPanel.getCurrentNumParticles() < animationPanel.getMax()) {
								animationPanel.addNew();
								animationPanel.setCurrentNumParticles(animationPanel.getCurrentNumParticles() + 1);
								
								// Check if we haven't reached the maximum number of allowed particles
								if (animationPanel.getCurrentNumParticles() == animationPanel.getMax()) {
									// Disable the button, as there is no more particles
									launcherButton.setEnabled(false);
					              }
					            }
								transferFocusUpCycle();  // To handle key events
								smallParticlesNumber.setText("No. of particles " + animationPanel.getCurrentNumParticles());
								
								// Determine when to stop
								counter++;
								if (counter >= launchedParticles) {
									launchBoolean = false;
								}
						}
						launchBoolean = true;
					}).start();
				}
			}
	}
}
