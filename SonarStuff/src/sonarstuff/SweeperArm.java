
package sonarstuff;

import java.awt.Polygon;

/**
 * This class extends the Polygon class and forms a rectangular arm ending in an arrow. 
 */
public class SweeperArm extends Polygon{
    /**
     * This class makes a new default sweeper arm out of 7 default points, 
     * originating/being centered at the center of the Display class's screen.
    */
    public SweeperArm(){
        npoints = 7;
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        
        
        xpoints[0] = GUI.getWidth(1)/2;
        ypoints[0] = GUI.getHeight(1)/2-15;
        xpoints[1] = GUI.getWidth(1)/2+10;
        ypoints[1] = GUI.getHeight(1)/7;
        xpoints[2] = GUI.getWidth(1)/2+50;
        ypoints[2] = GUI.getHeight(1)/7+10;
        xpoints[3] = GUI.getWidth(1)/2;
        ypoints[3] = 0;
        xpoints[4] = GUI.getWidth(1)/2-50;
        ypoints[4] = GUI.getHeight(1)/7+10;
        xpoints[5] = GUI.getWidth(1)/2-10;
        ypoints[5] = GUI.getHeight(1)/7;
        xpoints[6] = GUI.getWidth(1)/2;
        ypoints[6] = GUI.getHeight(1)/2-15;
        
    }
    
    
}
