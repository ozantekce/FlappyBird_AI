
import bird.Constants;
import bird.GameLoop;
import bird.KeyInput;
import java.awt.Dimension;
import javax.swing.JFrame;




public class Main extends GameLoop{
    
    public static void main(String[] args) {
       
        Main m = new Main();
        
        m.start();
        
    }

    
    
    public Main() {
        
        
        this.setPreferredSize(new Dimension(Constants.getMidW(),Constants.getMidH()));
        this.setMaximumSize(new Dimension(Constants.getMidW(),Constants.getMidH()));
        this.setMinimumSize(new Dimension(Constants.getMidW(),Constants.getMidH()));
        
        JFrame frame = new JFrame("Flappy Duck");
         frame.add(this);
        //frame.setSize(350, 350);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        KeyInput kl = new KeyInput();
        
        this.addKeyListener(kl);
        
        frame.add(this);
        
        
    }
    
    
    
}

