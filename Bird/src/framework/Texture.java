package framework;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class Texture {
    
    SpriteSheer bs,bbs,tbs,bg,g;
    
    
    private BufferedImage bird_sheet=null;
    public BufferedImage botblock_sheet=null;
    public BufferedImage topblock_sheet=null;
    public BufferedImage background=null;
    public BufferedImage ground=null;
    public BufferedImage[] bird = new BufferedImage[7];

    
    
    public Texture(){
        
        try {
            
        File file= new File("Bird.png");
        File file2= new File("botblock.png");
        File file3= new File("topblock.png");
        File file4= new File("background.png");
        File file5= new File("ground.png");
        
           bird_sheet=ImageIO.read(file);
           botblock_sheet=ImageIO.read(file2);
           topblock_sheet=ImageIO.read(file3);
           background=ImageIO.read(file4);
           ground=ImageIO.read(file5);
           
        } catch (Exception e) {
            System.out.println("a");
        }
        
        
        
    
        bs=new SpriteSheer(bird_sheet);
        bbs=new SpriteSheer(botblock_sheet);
        tbs=new SpriteSheer(topblock_sheet);
        bg=new SpriteSheer(background);
        g=new SpriteSheer(ground);
        
        getTexture();
        
    }
    
    
    private void getTexture(){
    
        
        for (int i = 0; i < bird.length; i++) {
            bird[i] = bs.grabImage(i+1, 1, 58, 42);
            
        }
        
        
    }
    
    
    
    
    
}