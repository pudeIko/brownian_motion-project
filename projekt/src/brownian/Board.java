package brownian;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A rectangular container board with borders.  
 * 
 * @author AD
 *
 */

public class Board {
	
	// Board's bounds
	int minX, maxX, minY, maxY; 
	
	// Board's filled color (background)
	private Color colorFilled;  
	
	// Board's border color
	private Color colorBorder;   
	   
	   // Constructor
	   public Board(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
	      minX = x;
	      minY = y;
	      maxX = x + width - 1;
	      maxY = y + height - 1;
	      this.colorFilled = colorFilled;
	      this.colorBorder = colorBorder;
	   }
	   
	   // Method used for setting or resetting the borders
	   public void set(int x, int y, int width, int height) {
	      minX = x;
	      minY = y;
	      maxX = x + width - 1;
	      maxY = y + height - 1;
	   }

	   // This methods draws the board according to passed parameteres
	   public void draw(Graphics g) {
	      g.setColor(colorFilled);
	      g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
	      g.setColor(colorBorder);
	      g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
	   }
	   
	   // Setters
	   void setColor(Color color) {
		   this.colorFilled = color;
	   }
}

