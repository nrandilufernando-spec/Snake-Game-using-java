import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  // For Swing components (JPanel, Timer)
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class GamePanel extends JPanel implements ActionListener{
  
    private Image snakeHeadImage;
    private BufferedImage bodyImage;
    private BufferedImage headImage;
    private BufferedImage backgroundImage ;
    private Clip backgroundMusic;
    private BufferedImage appleImage;
    private BufferedImage wallimage_0;
    private boolean imageLoaded=false;
    private boolean imageLoadedApple=false;
    private boolean imageLoadedbody=false;
    private boolean imageLoadedWall=false;
    private int wall_x;
    private int wall_Y;
    private int minGrid=50/UNIT_SIZE;
    private int maxGrid=550/UNIT_SIZE;
    static final int SCREEN_WIDTH = 600;  // Game window width
    static final int SCREEN_HEIGHT = 600;     // Game window height 
    static final int SCREEN_WIDTH_PLAYABLE = 575;  // Game window width
    static final int SCREEN_HEIGHT_PLAYABLE =575;     // Game window height 
    static final int SCREEN_HEIGHT_APPLE =200;     
    static final int SCREEN_WIDTH_APPLE = 200;  
    static final int UNIT_SIZE = 25;          // Size of each snake segment/apple
    static final int GAME_UNITS = (SCREEN_WIDTH_PLAYABLE * SCREEN_HEIGHT_PLAYABLE) / (UNIT_SIZE * UNIT_SIZE); // Max possible snake length
    static final int DELAY = 155; 
    final int z[][]=new int[GAME_UNITS][GAME_UNITS];           // Timer delay (controls game speed)
    final int x[] = new int[GAME_UNITS];  // Stores X-coordinates of snake segments
    final int y[] = new int[GAME_UNITS]; // Stores Y-coordinates of snake segments
    int bodyParts = 6;                    // Initial snake length
    int applesEaten;                      // Score counter
    int appleX, appleY;                   // Apple position
    char direction = 'R';                 // Starting direction (Right)
    boolean running = false;              // Game state (running/not running)
    Timer timer;
    Timer timer2;                          // Controls game loop
    Random random;                        // For random apple placement
    
    public GamePanel() throws IOException {

        for(int i=0;i<6;i++){
            x[i]=25;
            y[i]=25;

        }
        try {
            backgroundImage  = ImageIO.read(new File("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\playing area.jpg"));
            
        } 
        catch (IOException e) {
            System.err.println("Error loading background image");
            backgroundImage = null; // Fallback to solid color

        }
        
        
        
        random = new Random();                     // Initialize Random
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Set panel size
        this.setBackground(Color.black);           // Black background
        this.setFocusable(true);                   // Allow keyboard focus
        this.addKeyListener(new MyKeyAdapter());   // Add keyboard input listener
        startGame();
        ImagePanel snakeHeadImage = new ImagePanel("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\snake_green_head_32.png");   
        
        /*snakeHeadImage = new ImageIcon(getClass().getResource("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\snake_green_head_32.png")).getImage();
        if(snakeHeadImage==null){
            System.out.println("Error");
            } 
            else{
                System.out.println("Successfull");
                }*/
                try {
                    headImage = ImageIO.read(new File("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\snake_green_head_32.png"));
                    appleImage = ImageIO.read(new File("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\apple_alt_32.png"));
                    bodyImage = ImageIO.read(new File("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\snake_green_blob_32.png"));
                    wallimage_0 = ImageIO.read(new File("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\wall_block_32_0.png"));
                    imageLoaded = true;
                    imageLoadedApple=true;
                    imageLoadedbody=true;
                    imageLoadedWall=true;
                } catch (IOException e) {
                    System.err.println("Error loading head image: " + e.getMessage());
                    imageLoaded = false;
                    imageLoadedApple=false;
                    imageLoadedbody=false;
                    imageLoadedWall=false;
                }
            }
            public void startGame() {
                newApple();              // Place first apple
                running = true;          // Set game state to running
                timer = new Timer(DELAY, this); // Initialize timer (75ms delay)
                timer.start();           // Start the game loop
                ImagePanel backgroundPanel1 = new ImagePanel("C:\\Users\\Randil fernando\\Desktop\\Java\\src\\images\\playing area.jpg"); // Path to your image
                
                backgroundPanel1.setLayout(new GridBagLayout()); // For centering components
                
            }
            //drawing methods
            
            public void paintComponent(Graphics g){
                super.paintComponent(g);//call parent class method (clears screen)
                // Draw background (if loaded)
                if (backgroundImage != null) {
                    // Scale image to fit panel
                    g.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
                    }
                else {
                    // Fallback: Solid color background
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    }
                draw(g);// custom drawing logics
            


}

public void draw(Graphics g){
    if(running){// if game is running 
        if(imageLoaded){
            wall_x=13;
            wall_Y=13;

            for(int i=25; i<575; i++){
                Graphics2D W2d = (Graphics2D)g.create();
                W2d.translate(wall_x, wall_Y);
                W2d.drawImage(wallimage_0,-UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
                W2d.dispose();
                wall_Y +=25;
                
;                              
            }
            wall_x=13;
            wall_Y=13;

            for(int i=25; i<575; i++){
                Graphics2D W2d = (Graphics2D)g.create();
                W2d.translate(wall_x, wall_Y);
                W2d.drawImage(wallimage_0,-UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
                W2d.dispose();
                wall_x +=25;

            }
            wall_x=586;
            wall_Y=13;
            for(int i=25; i<575; i++){
                Graphics2D W2d = (Graphics2D)g.create();
                W2d.translate(wall_x, wall_Y);
                W2d.drawImage(wallimage_0,-UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
                W2d.dispose();
                wall_Y +=25;

            }
            wall_x=586;
            wall_Y=586;
            for(int i=25; i<575; i++){
                Graphics2D W2d = (Graphics2D)g.create();
                W2d.translate(wall_x, wall_Y);
                W2d.drawImage(wallimage_0,-UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
                W2d.dispose();
                wall_x -=25;

            }




        }
        if (imageLoaded) {//                       Apple
            Graphics2D A2d = (Graphics2D)g.create();
            A2d.translate(appleX, appleY);
            A2d.drawImage(appleImage, -UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
            A2d.dispose();
                          
            
        }
        else{
            g.setColor(Color.red);
            g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);

        }

        //Draw Snake 
        for(int i=0; i<bodyParts; i++){
            if(i==0){ //Snake HEAD(bright green)
                if (imageLoaded) {
                    Graphics2D g2d = (Graphics2D)g.create();
                    int centerX = x[i] + UNIT_SIZE/2;
                    int centerY = y[i] + UNIT_SIZE/2;
                    g2d.translate(centerX, centerY);
                          
        // Rotate based on direction
                   switch (direction) {
                      case 'U' -> g2d.rotate(Math.toRadians(-180));
                      case 'D' -> g2d.rotate(Math.toRadians(-360));
                      case 'L' -> g2d.rotate(Math.toRadians(90));
                      case 'R' -> g2d.rotate(Math.toRadians(-90));
            // 'R' needs no rotation
        }
        
                    g2d.drawImage(headImage, -UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
                    g2d.dispose();
                }

                else {
                    // Fallback to rectangle
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            else{//Snake body(darker green)
                if (imageLoaded) {
                    Graphics2D g3d = (Graphics2D)g.create();
                    int centerX = x[i] + UNIT_SIZE/2;
                    int centerY = y[i] + UNIT_SIZE/2;
                    g3d.translate(centerX, centerY);
                    g3d.drawImage(bodyImage, -UNIT_SIZE/2, -UNIT_SIZE/2, UNIT_SIZE, UNIT_SIZE, null);
                    g3d.dispose();
                    
                }
                else{
                    g.setColor(new Color(45, 180,0));
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);              

                }

            }
        }
        //Draw Score(White text)
        g.setColor(Color.black);
        g.setFont(new Font("Ink Free",Font.BOLD,20));
        FontMetrics metrics= getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten,(SCREEN_WIDTH- metrics.stringWidth("Score : "+applesEaten))/2, g.getFont().getSize());
        
    }
    else{// if game is over:
        gameOver(g); //Show "Game Over" screen
    }
    
}
public void newApple(){
    appleX = (random.nextInt(maxGrid - minGrid + 1) + minGrid) * UNIT_SIZE; //Random x(Multiple of 25)
    appleY = (random.nextInt(maxGrid - minGrid + 1) + minGrid) * UNIT_SIZE; //Random Y(Multiple of 25)
    
    System.out.println(appleX+" : appleX");
    System.out.println(appleY+" : appleY");
    
}
public void move(){
    //Shift all body parts (Expect head)
    for(int i=bodyParts; i>0; i--){
        x[i]=x[i-1];
        y[i]=y[i-1]; 
        
        System.out.println(x[i]);
    }
    //Move head based on direction
    switch(direction){
        case 'U' -> y[0] -= UNIT_SIZE;  // Up (decrease Y)
        case 'D' -> y[0] += UNIT_SIZE; // Down (increase Y)
        case 'L' -> x[0] -= UNIT_SIZE; // Left (decrease X)
        case 'R' -> x[0] += UNIT_SIZE; // Right (increase X)
    }
}
//Collisions 
public void checkApple(){
    if((x[0]==appleX)&&(y[0]==appleY)){//if snakes head hits apple
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new File("C:\\Users\\Randil fernando\\Desktop\\Java\\music\\game-bonus-2-294436.wav"));
                // Get sound clip
                backgroundMusic  = AudioSystem.getClip();
                backgroundMusic.open(audioIn);
                
                // Loop continuously
                backgroundMusic.start();
                
            } 
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.err.println("Error playing music: " + e.getMessage());
            }
            bodyParts++;
            applesEaten++;//increase score
            newApple(); //Place new apple
            
        }
        
    }
    public void checkCollisions(){
        //checks if head hits body
        for(int i=bodyParts; i>0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false; // Game over
            break;
        }
        
        
    }
    // Check if head hits wall
    if (x[0] < 1 || x[0] >= SCREEN_WIDTH_PLAYABLE || y[0] < 1 || y[0] >= SCREEN_HEIGHT_PLAYABLE) {
        running = false; // Game over
    }
    if (!running) {
        timer.stop(); // Stop game loop
    }
}
public void gameOver(Graphics g) {
    try {
        
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(
            new File("C:\\Users\\Randil fernando\\Desktop\\Java\\music\\winning-218995.wav"));
            // Get sound clip
            backgroundMusic  = AudioSystem.getClip();
            backgroundMusic.open(audioIn);
            
            // Loop continuously
            
            backgroundMusic.start();
            running=false;
            
        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
        
        
        
        
        // Draw final score
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        
        // Draw "Game Over" text (big red text)
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        
        new Timer(7000, e -> {
    // Code to run after 7 seconds
    aftergame();
    
    System.out.println("7 seconds passed!");
    ((Timer)e.getSource()).stop(); // Stop the timer
}).start();
        
        
        
    }
    public void aftergame(){
         // Get the parent JFrame and close it
         JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
         frame.dispose(); // Close the game window
         new MainMenu();

    }
    
    
    
    public void resetGame() {
        bodyParts = 6; // Reset snake length
        applesEaten = 0;
        direction = 'R';
        running = true;
        newApple();
        if (timer != null) timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) { // If game is active:
            move();          // Move snake
            checkApple();    // Check if apple was eaten
            
            checkCollisions(); // Check for collisions
            repaint(); // Redraw screen
        }
        else{
            new MainMenu();

        }
    }
    
public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (direction != 'R') direction = 'L';
            }
            case KeyEvent.VK_RIGHT -> {
                if (direction != 'L') direction = 'R';
                }
                case KeyEvent.VK_UP -> {
                    if (direction != 'D') direction = 'U';
                }
                case KeyEvent.VK_DOWN -> {
                    if (direction != 'U') direction = 'D';
                }
            }
        }
        
    }

}