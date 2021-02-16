package objects;

import bird.Constants;
import bird.Handler;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import framework.Animation;
import java.awt.Graphics;
import java.util.ArrayList;


public class Bird extends GameObject{

    
    private boolean alive=true;

    public boolean isAlive() {
        return alive;
    }
    
    
    
    public float ka;
    
    public float kb;
    
    public float kc;
    
    public float threshold;
    
    public int score;
    
    
    
    Texture tex = Constants.getTex();
    private Animation birdAnimation =null;
    
    public Bird(ObjectId id, float x, float y, float witdh, float height) {
        super(id, x, y, witdh, height);
        
        this.ay=(float) +0.5;
        
        this.maxvy=20;
        
        
        this.birdAnimation= new Animation(7, tex.bird);
        
        if(Constants.learn()){
            
            ka=Constants.rand();
            kb=Constants.rand();
            kc=Constants.rand();
            threshold=Constants.rand()*Constants.interval();
            
        }
        
        
        
    }
    

    
    public void render(Graphics g) {
       
        
        double angle=0;
        
        if(vy<0){
            
            angle=50*vy/maxvy;
            
        }
        else if(vy>0){
            
            angle=50*vy/maxvy;
            
        }
        
        birdAnimation.drawAnimation(g, (int)x, (int)y,(int)witdh,(int)height,angle);
        
       
        
    }
    
    
    private boolean first=true;
   
    
    public void tick() {
        
        if(beforetick&&Constants.learn())return;
        
        beforetick=true;
        
        
        
        if(Constants.learn()&&calculator())jump();
        
        
        s();
        collapse(Handler.getListblock());
           
        if(this.y>=Constants.getMidH())alive=false;
        
        if(this.y+height<-40)this.y=-30;
        
        
        if(alive==false)Handler.getHandler().remove(this);
        
        first=false;
        
        birdAnimation.runAnimation();
        
    }

 
    
    private boolean calculator(){
        
        float a = Handler.getCurrentBlock().getX()+Handler.getCurrentBlock().getWitdh()-this.x;
        
        float b = this.y-Handler.getCurrentBlock().getTemp().getY()-Handler.getCurrentBlock().getTemp().getHeight();
        
        float c = this.y-this.height-Handler.getCurrentBlock().getY();
        
        
        float s = (ka*a)+(kb*b)+(kc*c);
        
        
        return s>=threshold;
    }
    
    
    public void jump(){
        
        vy=-10;
        
    }
    
    
    
    private void collapse(ArrayList<GameObject> List){
        
        
        for (int i = 0; i < List.size(); i++) {
            
            if(List.get(i).getId()==this.getId())continue;
            
            
            if(List.get(i).getBounds().intersects(this.getBounds())){
                alive=false;
            }
            
            
            
        }
        
        
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    
    

    
    
}
