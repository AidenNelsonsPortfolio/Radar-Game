
package sonarstuff;

import java.awt.Polygon;
import java.util.Random;

/**
 * This class extends the Polygon class, and makes a new triangular shape. 
 * It is generated with a random type, which sets what part of the Display screen it will spawn and move in from.
 */
public class Enemy extends Polygon{
    private boolean spawn, intersected, visible;
    private int speed;
    public int startXDistance, lives;
    public int startYDistance, x, y;
    private Random rand;
    public double angle;
    private int colorGradient;
    private boolean special = false;
    /**
     * @param spawned A boolean value that states whether or not the enemy has been spawned in
     * @param type This sets the value of where the enemy comes onto the Display screen from.
     */
    public Enemy(boolean spawned, int type){
        spawn = spawned;
        visible = false;
        lives = 1;
        npoints = 3;
        switch(type){
            case 0: this.x = 0; this.y = -50; break;
            case 1: this.x = -100; this.y = -50; break;
            case 2: this.x = -100; this.y = 50; break;
            case 3:this.x = 100; this.y = -50; break;
            case 4:this.x = 200; this.y = -50; break;
            case 5:this.x = 300; this.y = -50; break;
            case 6:this.x = 400; this.y = -50; break;
            case 7:this.x = 500; this.y = -50; break;
            case 8:this.x = 600; this.y = -50; break;
            case 9:this.x = 700; this.y = -50; break;
            case 10:this.x = 800; this.y = -50; break;
            case 11:this.x = 900; this.y = -50; break;
            case 12:this.x = 1000; this.y = -50; break; 
            case 13:this.x = 1100; this.y = -50; break;
            case 14:this.x = 100; this.y = 750; break;
            case 15:this.x = 200; this.y = 750; break;
            case 16:this.x = 300; this.y = 750; break;
            case 17:this.x = 400; this.y = 750; break;
            case 18:this.x = 500; this.y = 750; break;
            case 19:this.x = 600; this.y = 750; break;
            case 20:this.x = 700; this.y = 750; break;
            case 21:this.x = 800; this.y = 750; break;
            case 22:this.x = 900; this.y = 750; break;
            case 23:this.x = 1000; this.y = 750; break; 
            case 24:this.x = 1100; this.y = 750; break;
            case 25:this.x = 0; this.y = 750; break;
            case 26: this.x = -100; this.y = 150; break;
            case 27: this.x = -100; this.y = 250; break;
            case 28: this.x = -100; this.y = 350; break;
            case 29: this.x = -100; this.y = 450; break;
            case 30: this.x = -100; this.y = 550; break;
            case 31: this.x = -100; this.y = 650; break;
            case 32: this.x = -100; this.y = 750; break;
            case 33: this.x = 1100; this.y = 50; break;
            case 34: this.x = 1100; this.y = 150; break;
            case 35: this.x = 1100; this.y = 250; break;
            case 36: this.x = 1100; this.y = 350; break;
            case 37: this.x = 1100; this.y = 450; break;
            case 38: this.x = 1100; this.y = 550; break;
            case 39: this.x = 1100; this.y = 650; break;
            case 40: this.x = 1100; this.y = 750; break;
            
                    
        }
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        
        xpoints[0] = x;
        ypoints[0] = y;
        xpoints[1] = x+30;
        ypoints[1] = y-30;
        xpoints[2] = x-30;
        ypoints[2] = y-30;
        startXDistance = x-((GUI.getWidth(1))/2);
        startYDistance = y-((GUI.getHeight(1))/2);
        
        if(startYDistance>0&&startXDistance>0)
           angle = -Math.atan((double)startYDistance/startXDistance);
        else if(startYDistance > 0 && startXDistance< 0)
           angle = (Math.atan((double)startYDistance/startXDistance))- Math.PI/2;
        else if(startYDistance < 0 && startXDistance< 0)
           angle = (Math.atan((double)startYDistance/startXDistance)+Math.PI/2);
        else
           angle = (-Math.atan((double)startYDistance/startXDistance));
        
                
    }
    /**
 * @return A boolean value dictating whether the enemy has been spawned in yet or not.
 */
    public boolean getSpawned(){
        return spawn;
    }
    /**
 * @return An integer value stating how far the object will be translated in the x direction by the 
 * Display class on the next movement.
 */
    public int getDX(){
        return -this.startXDistance/100;
    }
    /**
 * @return An integer value stating how far the object will be translated in the y direction by
 * the Display class on the next movement. 
 */
    public int getDY(){
        return -this.startYDistance/100;
    }
   
    /**
 * @return A boolean value that tells whether or not an enemy is intersected with something else or not (excluding other enemies).
 */
    public boolean isIntersected(){
        return intersected;
    }
    /**
 * @return A boolean value that tells whether or not an enemy is special (having more hearts, different color, etc. in Display).
 */
    public boolean isSpecial(){
        return special;
    }
    /**
 * @param b Sets whether or not this enemy will be special, and the updates its heart count based upon the level of the game. 
 */
    public void setSpecial(boolean b){
        special = b;
        lives = (GUI.getLevel()>=20)? 20:GUI.getLevel();
    }
    /**
 * This class decrements the number of lives an enemy has by one. 
 */
    public void decLives(){
        lives --;
    }
    /**
 * @return An integer value for the number of lives an enemy has.
 */
    public int getLives(){
        return lives;
    }
    /**
 * @param b Take a boolean value that updates whether an enemy is intersected or not. 
 */
    public void setIntersected(boolean b){
        this.intersected = b;
    }
    
}
