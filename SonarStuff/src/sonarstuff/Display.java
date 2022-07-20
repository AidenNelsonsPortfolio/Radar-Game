
package sonarstuff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The main class of the entire game, that controls all functionality and movement of the board. 
 * Made by extending the JPanel class.
 */
public class Display extends JPanel{
    
    private Random rand = new Random();
    private SweeperArm sweep;
    private int level = 1, rectX, rectY, hearts = 3, missleSize, levelIncreaser, levelTimes;
    public boolean spawnEnemies = true;
    private ArrayList<Missle> missles;
    private ArrayList<Enemy> badGuys;
    private static ArrayList<Boolean> levels;
    private Base base;
    private long rotation = 1;
    private Area oldSweep, rect;
    private Color backgroundColor = Color.GREEN.darker();
    private Font best;
    private ImageIcon heart, background;
    /**
     * This class has only a default constructor, and initializes all values. 
     * It paints the components and objects from the other classes on the gameboard when they are called
     * as well as keep track of lives, intersections, damage, and object counts.
     */
    public Display(){
        super();
        setBackground(backgroundColor);
        sweep = new SweeperArm();
        badGuys = new ArrayList<>();
        missles = new ArrayList<>();
        levels = new ArrayList<>();
        for(int i = 0; i < 100; i++)
            levels.add(false);
        base = new Base(50,50);
        rect = new Area(new Rectangle(GUI.getWidth(1)/2-(base.width/2+2), GUI.getHeight(1)/12, base.width-2, GUI.getHeight(1)/2-(GUI.getHeight(1)/12)));
        rectX = -100;
        rectY = -100;
        best = new Font("Times New Roman", 35, 35);
        heart = new ImageIcon("heart.png");
        background = new ImageIcon("sonar.png");
        levelIncreaser = 0;
        levelTimes = 0;
        
    }
 /**
 * This overriden method makes it so that, every time the board is re-painted, 
 * all relevant objects, movements, translations, rotations, intersections, and generations 
 * are also checked and documented, as well as being recorded and stored, and being
 * printed to the screen for the user to see.
 */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(5,5,200,45);
        g2.fillRect(725,5,175,45);
        g2.setColor(Color.WHITE);
        g2.setFont(best);
        g2.drawString("Score: "+ GUI.getGameScore(),30,35);

        g2.drawString("Level: " + (GUI.getLevel()+levelIncreaser), 750,35);
        for(int i = 0; i < hearts; i ++)
            g2.drawImage(heart.getImage(), 50*i, 100, 50, 50, this);
        g2.drawImage(background.getImage(), 100, -40, 800, 750, this);
        
        if(GUI.getGameScore() >= 100 && !GUI.getInfinite()){
            GUI.stopEnemyTimer();
            GUI.stopMainTimer();
            GUI.window.dispose();
            System.out.println("You Beat Level # " + GUI.getLevel()+ "!");
            String[] choices = {"Sure!", "Nope."};
            try{
            try{String input = (String) JOptionPane.showInputDialog(null, "You Beat Level # " + GUI.getLevel()+ "Would you like to try again?",
                    "Choices...", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]); 
            if(input.equals("Sure!")){
                GUI.setGameScore(0);
                new GUI();
            }
            else
                System.exit(0);
            }catch(NumberFormatException e){
                System.exit(0);
            }}
            catch(NullPointerException e){
                       System.exit(0);
            }

        }
        
        
        if(GUI.enemySpawningTime && badGuys.size() < GUI.getLevel() + 5 + levelIncreaser){
            
            badGuys.add(new Enemy(false, rand.nextInt(39)));
            GUI.setEnemySpawn(false);
            levelTimes += 1;
            
            if(levelTimes % 20 ==0 && GUI.getInfinite()){
                levelIncreaser++;
                badGuys.add(new Enemy(false, rand.nextInt(39)));
                badGuys.get(badGuys.size()-1).setSpecial(true);
            }
            else if(levelTimes % 20 ==0){
                badGuys.add(new Enemy(false, rand.nextInt(39)));
                badGuys.get(badGuys.size()-1).setSpecial(true);
                levelTimes = 1;
            }
           
        }
        
        for(int i = 0; i<badGuys.size(); i ++){
            
            if(GUI.movingTime){
                
                (badGuys.get(i)).translate((badGuys.get(i)).getDX(),(badGuys.get(i)).getDY());   
                
            }
            if(badGuys.get(i).isIntersected()){
                if(badGuys.get(i).isSpecial()){
                    g2.setColor(Color.RED.darker());
                    g2.draw(badGuys.get(i));
                    g2.fill(badGuys.get(i));
                }
                else{
                g2.setColor(Color.GREEN.darker());
                g2.draw(badGuys.get(i));
                g2.fill(badGuys.get(i));
                }
            }
            
            if(!badGuys.get(i).intersects(-200,-100,1500,1000))
                badGuys.remove(i);
        }
        GUI.movingTime = false;
        
        if(GUI.mouseMoved){
            g2.setColor(Color.GREEN);
            rectX = GUI.mouseX-7;
            rectY = GUI.mouseY-10;
            g2.drawRect(rectX-3, rectY-45, 6, 20);
            g2.fillRect(rectX-3, rectY-45, 6, 20);
            g2.drawRect(rectX-3, rectY-15, 6, 20);
            g2.fillRect(rectX-3, rectY-15, 6, 20);
            g2.drawRect(rectX-25, rectY-23, 20, 6);
            g2.fillRect(rectX-25, rectY-23, 20, 6);
            g2.drawRect(rectX+5, rectY-23, 20, 6);
            g2.fillRect(rectX+5, rectY-23, 20, 6);
            GUI.mouseMoved = false;
        }
        else{
            g2.setColor(Color.GREEN);            
            g2.drawRect(rectX-3, rectY-35, 6, 10);
            g2.fillRect(rectX-3, rectY-35, 6, 10);
            g2.drawRect(rectX-3, rectY-15, 6, 10);
            g2.fillRect(rectX-3, rectY-15, 6, 10);
            g2.drawRect(rectX-15, rectY-23, 10, 6);
            g2.fillRect(rectX-15, rectY-23, 10, 6);
            g2.drawRect(rectX+5, rectY-23, 10, 6);
            g2.fillRect(rectX+5, rectY-23, 10, 6);
        }
        if(missles.size()<badGuys.size()&&(GUI.mouseClicked||GUI.spaceBar)){
            missles.add(new Missle(GUI.mouseX, GUI.mouseY));
            GUI.mouseClicked = false;
            GUI.spaceBar = false;
        }
        
        for(int i = 0; i < missles.size(); i ++){
            g2.setColor(Color.BLUE);   
            missles.get(i).translate((missles.get(i).getDX())/20, (missles.get(i).getDY()/20));  
            g2.draw(missles.get(i));
            g2.fill(missles.get(i));
            if(!missles.get(i).intersects(0,0, GUI.getWidth(1), GUI.getHeight(1))){
                    missles.remove(i);
            }
            else{
            for(int j = 0; j < badGuys.size(); j++){
                try{
                if(badGuys.get(j)!= null && missles.get(i)!= null && badGuys.get(j).intersects(missles.get(i).getBounds2D())&& badGuys.get(j).isIntersected()){
                    badGuys.get(j).decLives();
                    missles.remove(i);
                }}catch(IndexOutOfBoundsException e){
                }
                }
            }
        }
        g2.setColor(Color.BLACK);
        g2.draw(base);
        g2.fill(base);
        g2.setColor(Color.BLUE);
        
        AffineTransform af = new AffineTransform();
        af.rotate(Math.toRadians(rotation), GUI.getWidth(1)/2, GUI.getHeight(1)/2);

        
        oldSweep = rect.createTransformedArea(af);
        
        g2.rotate(Math.toRadians(rotation), GUI.getWidth(1)/2, GUI.getHeight(1)/2-15);
        Color green = new Color(50,255,50,75);
        g2.setColor(green);
        g2.draw(sweep);
        g2.fill(sweep);
        
        g2.rotate(-Math.toRadians(2*rotation),GUI.getWidth(1)/2, GUI.getHeight(1)/2-15);
        
        for(int i = 0; i < badGuys.size(); i ++){
            
            if(badGuys.get(i).intersects(oldSweep.getBounds2D())){
                badGuys.get(i).setIntersected(true);
            }
            
            if(badGuys.get(i).intersects(base.getBounds2D())){
                if(badGuys.get(i).isSpecial()){
                    hearts -= 2;
                    badGuys.remove(i);
                }
                else{
                badGuys.remove(i);
                hearts --;
                }
                /*
                if(badGuys.get(i).isSpecial())
                    hearts = 0;
                else
                    hearts--;
                */
            }
            
            if(badGuys.get(i).getLives()<1){
                if(badGuys.get(i).isSpecial()&& hearts > 0){
                    hearts = 3;
                    badGuys.remove(i);
                    GUI.setGameScore(10);
                }
                else{
                    badGuys.remove(i);
                    GUI.setGameScore(1);
                }
            }
        }
        if(hearts < 1){
                   GUI.stopEnemyTimer();
                   GUI.stopMainTimer();
                   GUI.window.dispose();
                   String[] choices = {"Sure!", "Nope."};
                   String input;
                   try{try{
                       if(GUI.getInfinite()){
                        System.out.println("Game over, you got to level " + (GUI.getLevel() + 5 + levelIncreaser) + "!");
                        input = (String) JOptionPane.showInputDialog(null, "Game Over, you got to level " +(GUI.getLevel() + 5 + levelIncreaser) + " with " + GUI.getGameScore() + " points!" + " Play again?",
                                "Choices...", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]); 
                       }
                       else{
                        System.out.println("Game over, all your hearts were lost!");
                        input = (String) JOptionPane.showInputDialog(null, "Game Over, All of Your Hearts Were Lost!"+ " You were so close, you got " + 
                                GUI.getGameScore() + " out of 100 points needed to beat the level! " + " Play again?",
                    "Choices...", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]); 
                    }
                   if(input.equals("Sure!")){
                       GUI.setGameScore(0);
                       new GUI();
                   }
                   else
                       System.exit(0);
                   }catch(NumberFormatException e){
                      System.exit(0);
                   }
                   }catch(NullPointerException e){
                       System.exit(0);
                   }
        }
        g2.setColor(Color.BLACK);
        rotation ++;
        
        
        
    }

}
