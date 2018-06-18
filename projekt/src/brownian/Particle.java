package brownian;
import java.awt.*;
import java.util.Formatter;
import collisionphysics.*;

/**
 * New data type, used
 * 
 * @author AD
 *
 */

public class Particle {
	
	// Particle's center coordinates x and y and other parameters
	public float x, y;
	public float vx, vy; 
	public float radius;
	public float mass;
	public Color color;  
   
   //Constructor to initialize a particle object
   // For user friendliness, user specifies velocity in speed and moveAngle in usual Cartesian coordinates
   public Particle(float x, float y, float radius, float speed, float angleInDegree, float mass, Color color) {
      this.x = x;
      this.y = y;
      // Convert (speed, angle) to (x, y), with y-axis inverted
      this.vx = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
      this.vy = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
      this.radius = radius;
      this.mass = mass;
      this.color = color;
   }
   
   
   // Bounce of vertical wall by reflecting x-velocity
   private void bounceOffVerticalWall(float distance) {
       vx = -vx;
       x = distance;
   }

   // Bounce of horizontal wall by reflecting y-velocity
   private void bounceOffHorizontalWall(float distance) {
       vy = -vy;
       y = distance;
   }
	
   // Reverse the direction of the particle whenever it reaches one of the four walls
   // In case of aperiodic boundary conditions make the particle appear on the opposite side of the board
   public void wallTest(AnimationPanel panel, boolean periodicity) {
	   if(periodicity == true) {
			if (x < radius)bounceOffVerticalWall(radius);
		    if (x > panel.getWidth()-radius) bounceOffVerticalWall(panel.getWidth()-radius);
		   	if (y < radius)bounceOffHorizontalWall(radius);
		    if (y > panel.getHeight()-radius) bounceOffHorizontalWall(panel.getHeight()-radius);
	   }
	   else if(periodicity == false){
		   if (x < - radius) setX(panel.getWidth());
		   if (x > panel.getWidth()+radius) setX(0);
		   if (y < - radius)setY(panel.getHeight());
		   if (y > panel.getHeight()+radius) setY(0);
	   }
   }
   
   // Working copy for computing response in intersect(Particle, timeLimit), to avoid repeatedly allocating objects.
   private CollisionResponse thisResponse = new CollisionResponse(); 
   private CollisionResponse anotherResponse = new CollisionResponse(); 
   
   // Maintain the response of the earliest collision detected by this ball instance. 
   // Only the first collision matters!
   CollisionResponse earliestCollisionResponse = new CollisionResponse();
   
   //Check if the particle collides with the given another particle in the interval  (0, timeLimit].
   public void intersect(Particle another, float timeLimit) {
      // Call movingPointIntersectsMovingPoint() with timeLimit.
      // Use thisResponse and anotherResponse, as the working copies, to store the
      // responses of this ball and another ball, respectively.
      // Check if this collision is the earliest collision, and update the particle's
      // earliestCollisionResponse accordingly.
      CollisionPhysics.pointIntersectsMovingPoint(
            this.x, this.y, this.vx, this.vy, this.radius, this.mass,
            another.x, another.y, another.vx, another.vy, another.radius, another.mass,
            timeLimit, thisResponse, anotherResponse);
      
      if (anotherResponse.t < another.earliestCollisionResponse.t) {
            another.earliestCollisionResponse.copy(anotherResponse);
      }
      if (thisResponse.t < this.earliestCollisionResponse.t) {
            this.earliestCollisionResponse.copy(thisResponse);
      }
   }

   // Update the states of the particle for the given time.
   // If this Particle's earliestCollisionResponse.time equals to time, this
   // Particle is the one that collided; otherwise, there is a collision elsewhere.
   public void update(float time) {
      // Check if this particle is responsible for the first collision?
      if (earliestCollisionResponse.t <= time) {
         // This particle collided, get the new position and speed
         this.x = earliestCollisionResponse.getNewX(this.x, this.vx);
         this.y = earliestCollisionResponse.getNewY(this.y, this.vy);
         this.vx = earliestCollisionResponse.newSpeedX;
         this.vy = earliestCollisionResponse.newSpeedY;
      } else {
         // This particle does not involve in a collision. Move straight.
         this.x += this.vx * time;         
         this.y += this.vy * time;         
      }
      // Clear for the next collision detection
      earliestCollisionResponse.reset();
   }
   
   // Draw itself using the given graphics context.
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius),
            (int)(2 * radius));
   }
   


   // Display info
   public String toString() {
      sb.delete(0, sb.length());
      formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%3.0f,%3.0f) " +
            "\u0398=%4.0f KE=%3.0f", 
            x, y, radius, vx, vy, getMoveAngle(),
            getKineticEnergy());  // \u0398 is theta
      return sb.toString();
   }
   private StringBuilder sb = new StringBuilder();
   private Formatter formatter = new Formatter(sb);
  
   // Setters
   public void setX (float x) {
	   this.x = x;
   }
   
   public void setY (float y) {
	   this.y = y;
   }
   
   // Getters
   public float getX() {
	   return x;
   }
   
   public float getY() {
	   return y;
   }
   
   public float getVx() {
	   return vx;
   }
   
   public float getVy() {
	   return vy;
   }
   
   // Return the kinetic energy (0.5mv^2) */
   public float getKineticEnergy() {
      return 0.5f * mass * (vx * vx + vy * vy);
   }
   
   // Return the direction of movement in degrees (counter-clockwise).
   public float getMoveAngle() {
      return (float)Math.toDegrees(Math.atan2(-vy, vx));
   }
}
