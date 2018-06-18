package brownian;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * Panel for charts and data handling. 
 * 
 * @author AD
 *
 */

public class Charts extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Menu menu;
	private JPanel panel;

	public Charts(XYSeries displacementX, XYSeries displacementY, 
		XYSeries velocityX, XYSeries velocityY, XYSeries kineticEnergy){
			
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
			
		// A panel to hold all the charts
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
			
		//Creating the chart for X displacement
			
		XYSeriesCollection datasetDisplacementX = new XYSeriesCollection();
		datasetDisplacementX.addSeries(displacementX);
			 
		// Add the chart
		JFreeChart chartDisplacementX = ChartFactory.createXYLineChart(
			"DisplacementX", // Title
			"time [s]", // x-axis Label
			"displacemnt [um]", // y-axis Label
			datasetDisplacementX, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);

		ChartPanel displacementPanelX = new ChartPanel(chartDisplacementX);
		displacementPanelX.setPopupMenu(null);
		panel.add(displacementPanelX);
			
		//Creating the chart for Y displacement
			
		XYSeriesCollection datasetDisplacementY = new XYSeriesCollection();
		datasetDisplacementY.addSeries(displacementY);
			 
		// Add the chart
		JFreeChart chartDisplacementY = ChartFactory.createXYLineChart(
			"DisplacementY", // Title
			"time [s]", // x-axis Label
			"displacemnt [um]", // y-axis Label
			datasetDisplacementY, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);
			 
		ChartPanel displacementPanelY = new ChartPanel(chartDisplacementY);
		displacementPanelY.setPopupMenu(null);
		panel.add(displacementPanelY);
			 
		//Creating the chart for X velocity
				
		XYSeriesCollection datasetVelocityX = new XYSeriesCollection();
		datasetVelocityX.addSeries(velocityX);
				 
		// Add the chart
		JFreeChart chartVelocityX = ChartFactory.createXYLineChart(
			"VelocityX", // Title
			"time [s]", // x-axis Label
			"velocity [um/s]", // y-axis Label
			datasetVelocityX, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);

		ChartPanel velocityPanelX = new ChartPanel(chartVelocityX);
		velocityPanelX.setPopupMenu(null);
		panel.add(velocityPanelX);
				
		//Creating the chart for Y velocity
				
		XYSeriesCollection datasetVelocityY = new XYSeriesCollection();
		datasetVelocityY.addSeries(velocityY);
				 
		// Add the chart
		JFreeChart chartVelocityY = ChartFactory.createXYLineChart(
			"VelocityY", // Title
			"time [s]", // x-axis Label
			"velocity [um/s]", // y-axis Label
			datasetVelocityY, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);

		ChartPanel velocityPanelY = new ChartPanel(chartVelocityY);
		velocityPanelY.setPopupMenu(null);
		panel.add(velocityPanelY);
				
		//Creating the chart for Kinetic Energy
				
		XYSeriesCollection datasetKineticEnergy = new XYSeriesCollection();
		datasetKineticEnergy.addSeries(kineticEnergy);
				 
		// Add the chart
		JFreeChart chartKineticEnergy = ChartFactory.createXYLineChart(
			"Kinetic Energy", // Title
			"time [s]", // x-axis Label
			"energy [pJ]", // y-axis Label
			datasetKineticEnergy, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);

		ChartPanel kineticEnergyPanel = new ChartPanel(chartKineticEnergy);
		kineticEnergyPanel.setPopupMenu(null);
		panel.add(kineticEnergyPanel);
				
		// Adding the panel holding all the charts to the frame
		panel.validate();
		frame.add(panel);
			
		// Define the title for the frame
		frame.setTitle("Charts");
				
		// Show the frame
		frame.setVisible(true);
	}
		
}
	
	
