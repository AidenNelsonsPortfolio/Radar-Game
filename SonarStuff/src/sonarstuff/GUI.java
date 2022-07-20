
package sonarstuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI{
    
    public static JFrame window;
    private JLabel startText, levelText;
    public static Display mainPanel;
    private JPanel buttonPanel, a, b, c, d, e, big;
    private static JSlider levelSlider;
    private ImageIcon background;
    private Color back;
    private static Timer mainTimer, enemyTimer;
    private static JButton resultsButton, infiniteButton, levelButton;
    private final static int SCREEN_WIDTH = 1000;
    private final static int SCREEN_HEIGHT = 700;
    private static int gameScore = 0, value = 1;
    public static int mouseX, mouseY, level, numTimes, timesThru;
    public static boolean mouseMoved = false, mouseClicked = false, spaceBar = false, enemySpawningTime = true, movingTime = true, timeToGo = true, infiniteLevel = false;
    
    /**
    * This class forms a new GUI object, which is the basis for the game. It gives the user the ability to select between several gamemodes,
    * and then, once one is selected, makes a new Display object that begins the game in earnest. 
    */
    public GUI(){
        level = 20;
        
        back = new Color(0,0,0,100);
        
        infiniteButton = new JButton("Infinite Enemies Mode");
        
        levelButton = new JButton("Level Mode \n (select level below)");
       
        levelButton.setFont(new Font("Times New Roman", 40, 40));
        
        infiniteButton.setFont(new Font("Times New Roman", 40, 40));
        
        levelButton.addActionListener(new LevelListener());
        
        infiniteButton.addActionListener(new InfiniteListener());
        
        background = new ImageIcon("back.jpg");
        
        startText = new JLabel("Welcome to Sonar Game!");
        
        startText.setForeground(Color.WHITE.darker());
        
        startText.setFont(new Font("Times New Roman", 80, 80));
       
        buttonPanel = new JPanel(new GridLayout(3,1)){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(background.getImage(),0,0,null);
            }
        };
        
        buttonPanel.setBackground(back);
        
        a = new JPanel();
        b = new JPanel();
        c = new JPanel();
        e = new JPanel();
        
        a.add(startText);

        
        b.add(infiniteButton);
        
        
        e.setBackground(back);

        
        e.add(levelButton);
        
        
        b.setBackground(back);
        
        
        a.setBackground(back);
        
        c.setLayout(new GridLayout(1,2));
        
        JButton credits = new JButton("Credits");
        JButton controls = new JButton("Controls + Instructions");
        credits.addActionListener(new CreditsListener());
        controls.addActionListener(new ControlsListener());
        c.add(controls);
        c.add(credits);
        
        buttonPanel.add(a);
        buttonPanel.add(b);
        buttonPanel.add(e);        
        window = new JFrame("Radar Game");
        
        
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        window.setLocationRelativeTo(null);
        
        
        window.setResizable(false);
        
        window.add(c, BorderLayout.PAGE_END);
        
        window.addMouseMotionListener(new CrosshairListener());
        
        //Creating the mainPanel
        
        window.add(buttonPanel, BorderLayout.CENTER);
        
        window.setVisible(true);
        
    }
    
    /**
 * @return An integer value for the width of the screen.
 * @param a A necessary parameter that does nothing, so that the method can be overloaded, 
 * but still be called from non-static references.
 */
    public static int getWidth(int a){
        return SCREEN_WIDTH;
    }
    /**
 * @return A boolean value for if the selected gamemode is infinite.
 */
    public static boolean getInfinite(){
        return infiniteLevel;
    }
    /**
 * @return An integer value for what level the game is.
 */
    public static int getLevel(){
        return level;
    }
    /**
 * @return A value for the height of the GUI.
 * @param a Once again, this is a necessary parameter to overload the getHeight 
 * method, so that it can be called from non-static references.
 */
    public static int getHeight(int a){
        return SCREEN_HEIGHT;
    }
    /**
 * @return An integer value for the current gamescore the player has gotten.
 */
    public static int getGameScore(){
        return gameScore;
    }
    /**
 * @param a This is the score that the gameScore value will be updated to,
 * if less than or equal to 0, or added to, if greater than 0.
 */
     public static void setGameScore(int a){
        if(a>0)
            gameScore += a;
        else
            gameScore = a;
    }
    /**
 * This method stops the enemy timer
 */
    public static void stopEnemyTimer(){
        enemyTimer.stop();
    }
     /**
 * This stops the main game timer
 */
    public static void stopMainTimer(){
        mainTimer.stop();
    }
    /**
 * @param b this sets whether or not enemies are allowed to spawn now.
 */
    public static void setEnemySpawn(boolean b){
        enemySpawningTime = b;
    }
/**
 * This class listens to the credits button, and will display text about the game 
 * when pressed, via a JOptionPane.
 */
    private class CreditsListener implements ActionListener{
        

        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            JOptionPane.showMessageDialog(null,"Made by: Aiden Nelson\n\nDate of Completion: 5/10/2022\n\nPurpose of Program: "
                    + "A radar based game in which the goal is \n to eliminate as many enemies as possible before they reach your base."
                    + "\n\nCompleted Second Semester of Java at SWIC.");
        }
    
    }
    /**
 * This class listens to the controls button, and will display text about the game 
 * when pressed, via a JOptionPane.
 */
    private class ControlsListener implements ActionListener{
        

        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            JOptionPane.showMessageDialog(null,"Controls: \nRight Mouse Button/Space Bar: Fire Missile\n\n"
                    + "Try to take out 100 enemies to beat a level, or try our infinite play mode! \n\n"
                    + "Each enemy takes one heart off of your health, if you get to 0, the game is over!\n\n"
                    + "Red enemies are harder to take out, but they clear the screen if you can succeed!\n\n"
                    + "They also give one extra life (to a maximum of 3 total) if you destroy them.\n\n"
                    + "However, they will instantly kill you if they hit your base."
                    + "\n\nAnd that is all. Really. Try it out!");
        }
    
    }
    /**
 * This class listens to the Infinite button on the GUI object,via the ActionListener interface, and, when pressed,
 * makes a new Display object, and, therefore, a new game.
 */
    private class InfiniteListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            window.dispose();
            mainPanel = new Display();   
            window = new JFrame("Radar Game");
            window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.add(mainPanel); 
            window.addMouseMotionListener(new CrosshairListener());
        
            window.addMouseListener(new ClickListener());
        
            window.addKeyListener(new SpaceListener());
        
            mainTimer = new Timer(10, new MovementListener());
        
            enemyTimer = new Timer(200-level, new EnemyListener());
        
        
            window.setVisible(true);
            
            mainTimer.start();
            enemyTimer.start();
            mouseX = SCREEN_WIDTH/2;
            mouseY = SCREEN_HEIGHT/2;
            mouseMoved = false;
            
            infiniteLevel = true;
        }
        
    }
    /**
 * This class listens to the level button via the ActionListener interface, and will
 * generate a new Display instance with a certain level difficulty when pressed (after
 * the user selects their level via a JOptionPane readout).
 */
    private class LevelListener implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae)throws NumberFormatException{
            try{
            level = Integer.parseInt(JOptionPane.showInputDialog(null,"What level would you like to play? (out of 100)"));
            if(level > 100 || level < 1)
                throw new NumberFormatException();
            mainPanel = new Display();   
            window.dispose();
            window = new JFrame("Radar Game");
            window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.add(mainPanel); 
            window.addMouseMotionListener(new CrosshairListener());
        
            window.addMouseListener(new ClickListener());
        
            window.addKeyListener(new SpaceListener());
        
            mainTimer = new Timer(10, new MovementListener());
        
            enemyTimer = new Timer(200-level, new EnemyListener());
            
            
            
            window.setVisible(true);
            
            mainTimer.start();
            enemyTimer.start();
            mouseX = SCREEN_WIDTH/2;
            mouseY = SCREEN_HEIGHT/2;
            mouseMoved = false;
            infiniteLevel = false;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Input must be a number between 1 and 100, try again.");
            }
        }
    }
    /**
 * This class uses an ActionListener interface to control how many times enemy objects
 * are supposed to move, as well as how many times to repaint the main panel of the game. 
 */
    private class MovementListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            mainPanel.repaint();      
            numTimes ++;
            timesThru ++;
            if(numTimes %10 ==0){
            movingTime = true;
            numTimes = 0;
            }
            if(timesThru %12 == 0){
                timeToGo = true;
                timesThru = 0;
            }
            
        }
    }
    /**
 * This class sets, once activated via a timer, when it is time to spawn new enemies in.
 */
    private class EnemyListener implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            enemySpawningTime = true;
            
        }
    } 
/**
 * This class listens to mouse input via a MouseMotionListener interface, and will aid in making a
 * crosshair of sorts around the mouse whenever it is moved across the screen while the Display panel is active. 
 */
    private class CrosshairListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent me) {
        
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            mouseMoved = true;
            mouseX = me.getX();
            mouseY = me.getY();
        }
        

}
    /**
 * This class also listens to the mouse via a MouseListener interface, and tells the Display class when to make a 
 * new missile spawn in, and what coordinates to have it lock onto. 
 */
    private class ClickListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
            if(mouseX > 460 && mouseX < 530 && mouseY < 410 && mouseY > 340){}
                    
            else
                mouseClicked = true;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        @Override
        public void mouseExited(MouseEvent me) {
            
        }
    
    }
    /**
 * This class acts very similarly to the mouse click listener above, but uses a KeyListener interface. 
 * When you press the space bar, it also tells the Display panel to generate a new missile at your mouse's x and y coords. 
 */
    private class SpaceListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                spaceBar = true;
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            /*
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                spaceBar = true; 
*/
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                spaceBar = true;        
        }
    
    }
    
}
