
package sonarstuff;

import java.awt.Rectangle;

/**
 * This class extends Rectangle, and forms a square shape in the center of the game panel, representing a base/house.
 */
public class Base extends Rectangle {
    /**
     * @param width is used to set the width of the base.
     * @param height is used to set the height of the base.
    */
    public Base(int width, int height){
    
       this.x = GUI.getWidth(1)/2-(int)(width*0.5);
       this.y = GUI.getHeight(1)/2-(int)(width*0.75);
       this.height = height;
       this.width = width;
       
       
    }
    
    
}
