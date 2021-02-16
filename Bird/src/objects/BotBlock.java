package objects;

import bird.Constants;
import bird.Handler;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java.awt.Color;
import java.awt.Graphics;


public class BotBlock extends GameObject{
    
    private float firstx;
    
    private TopBlock temp;
    
    
    private Texture tex = Constants.getTex();
    
    public BotBlock(ObjectId id, float x, float y, float witdh, float height) {
        super(id, x, y, witdh, height);
        
        this.vx=(float) -4;
        
        this.y = (float)(Math.random()*(Constants.getMidH()-300))+180;
        temp = new TopBlock(ObjectId.TopBlock, this.x, -80, this.witdh, this.y-80);
        Handler.getHandler().add(temp);
        Handler.getListblock().add(temp);
        firstx = this.x;
    }

    
    
    public float getDis(){
        
        return this.firstx-x;
    }
    


    
    public void render(Graphics g) {
        
        
        g.drawImage(tex.botblock_sheet,(int)x, (int)y, (int)witdh, (int)height,null);
        
        
    }

    

   
    public void tick() {
       
        if(beforetick)return;
        
        beforetick=true;
        
        s();
        
        temp.setX(x);
        
        if(getDis()>Constants.getMidW()+150){
            Handler.getListblock().remove(this);
            Handler.getListblock().remove(temp);
            Handler.getHandler().remove(this);
            Handler.getHandler().remove(temp);
            
        }
        
        
    }

    public TopBlock getTemp() {
        return temp;
    }
    
    
    
    
    
    
}
