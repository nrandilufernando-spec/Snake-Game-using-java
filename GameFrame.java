import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GameFrame extends JFrame {
    
    private Clip backgroundMusic;
    static final int DELAY = 7000; 
    Timer timer;  
    
    
    
    
    public GameFrame() throws IOException {
        
        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        playBackgroundMusic();
        /* timer = new Timer(DELAY, (ActionListener) this); // Initialize timer (75ms delay)
        timer.start(); 
        try {
            Thread.sleep(2000); // Delay next line by 2000ms (2 seconds)
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        stopMusic();*/ 
        
        

        
    }
    private void playBackgroundMusic() {
    try {
        for(int i=1; i<3; i++){

            if(i==1){

                
                // Load audio file (place your music.wav in src folder)
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    new File("C:\\Users\\Randil fernando\\Desktop\\Java\\music\\medieval-fanfare-6826.wav"));
                // Get sound clip
                backgroundMusic  = AudioSystem.getClip();
                backgroundMusic.open(audioIn);
                
                // Loop continuously
                //backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundMusic.start();

               
            }
            else{
                timer = new Timer(DELAY, (ActionListener) this); // Initialize timer (75ms delay)
        timer.start(); 
        try {
            Thread.sleep(7000); // Delay next line by 2000ms (2 seconds)
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

                backgroundMusic.stop();
                backgroundMusic.close();

            }


        }
      

        
        
        
        
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        System.err.println("Error playing music: " + e.getMessage());
    }
}
public void stopMusic(){
    
}


    public static void main(String[] args) throws IOException {
        new GameFrame();

    }
}
