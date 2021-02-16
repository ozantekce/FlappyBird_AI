package objects;

import bird.Constants;
import bird.Main;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java.awt.Color;
import java.awt.Graphics;


public class TopBlock extends GameObject{

    private Texture tex = Constants.getTex();
    
    public TopBlock(ObjectId id, float x, float y, float witdh, float height) {
        super(id, x, y, witdh, height);
        
        
        
        
        
    }

    
    


    
    public void render(Graphics g) {
        
        //g.setColor(Color.BLUE);
        //g.fillRect((int)x, (int)y, (int)witdh, (int)height);
        
       g.drawImage(tex.topblock_sheet,(int)x, (int) (y+height-600),null);
        
        
    }

    
    
    public void tick() {
       
        
        
        
    }
    
    
    
    
}
