
package sonarstuff;

import java.awt.Polygon;

/**
 * This class extends the Polygon class and forms a triangular shape, with a set distance that it is to move from the origin out each timer cycle.
 */
public class Missle extends Polygon{
    public double angle = 0;
    private int deltaX, deltaY;
    /**
     * @param targetX is used to set x value that the missile is attempting to approach (ie: target).
     * @param targetY is used to set y value of enemy target.
    */
    public Missle(int targetX, int targetY){        
        npoints = 4;
        xpoints = new int[4];
        ypoints = new int[4]; 
        deltaX = targetX-GUI.getWidth(1)/2;
        deltaY = targetY-GUI.getHeight(1)/2;
        
        if(deltaY<0&&deltaX > 0)
            angle = -(Math.atan((double)deltaY/deltaX)+Math.PI/2)+(2*Math.PI);
        else if(deltaY > 0 && deltaX > 0)
            angle = -(Math.atan((double)deltaY/deltaX))- Math.PI/2+(2*Math.PI);
        else if(deltaY < 0 && deltaX< 0)
           angle = (-Math.atan((double)deltaY/deltaX)+Math.PI/2);
        else
           angle = (-Math.atan((double)deltaY/deltaX)+Math.PI/2);

        xpoints[0] = GUI.getWidth(1)/2;
        ypoints[0] = GUI.getHeight(1)/2-10;
        xpoints[1] = GUI.getWidth(1)/2-10;
        ypoints[1] = GUI.getHeight(1)/2+10;
        xpoints[2] = GUI.getWidth(1)/2+10;
        ypoints[2] = GUI.getHeight(1)/2+10;
        xpoints[3] = GUI.getWidth(1)/2;
        ypoints[3] = GUI.getHeight(1)/2-10;
    }
    /**
 * @return an integer value representing the distance x a missile is supposed to move.
 */
    public int getDX(){
        return this.deltaX;
    }
 /**
 * @return an integer value representing the distance y that a missile is supposed to move.
 */
    public int getDY(){
        return this.deltaY;
    }
}
