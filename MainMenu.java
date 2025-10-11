import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    private JButton startButton, exitButton;
    private boolean  backgroundClip=false;
    private Clip backgroundMusic;
    
    public MainMenu() {
        
        playBackgroundMusic();  // Add this line
        setVisible(true);       
        
        // Frame setup
        setTitle("Snake Game - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Larger size for better image display
        setLocationRelativeTo(null);
        setResizable(false);

        // Create panel with background image
        ImagePanel backgroundPanel = new ImagePanel("C:\\Users\\Randil fernando\\Desktop\\Java\\snake_background.jpg"); // Path to your image
        
        backgroundPanel.setLayout(new GridBagLayout()); // For centering components
        
        // Create a transparent panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make transparent
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(380, 50, 50, 550));

        // Start Button with styling
        startButton = new JButton("Start Game");
        styleButton(startButton);
        startButton.addActionListener(this);
        
        buttonPanel.add(startButton);

        // Exit Button with styling
        exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        // Add components to background panel
        backgroundPanel.add(buttonPanel);
        add(backgroundPanel);
        
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180)); // Steel blue color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setPreferredSize(new Dimension(200, 60));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            stopMusic();
            
            dispose();
            try {
                
                new GameFrame();

                
            } 
            catch (IOException ex) {
            }
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
    private void playBackgroundMusic() {
    try {
        // Load audio file (place your music.wav in src folder)
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(
            new File("C:\\Users\\Randil fernando\\Downloads\\loop-menu-preview-109594.wav"));
        // Get sound clip
        backgroundMusic  = AudioSystem.getClip();
        backgroundMusic.open(audioIn);
        
        // Loop continuously
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundMusic.start();
        
        
        
        
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        System.err.println("Error playing music: " + e.getMessage());
    }
}
public void stopMusic(){
    backgroundMusic.stop();
    backgroundMusic.close();
    
}

    public static void main(String[] args) {
        new MainMenu();
       
    }
    
}