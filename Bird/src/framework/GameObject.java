package framework;

import java.awt.Graphics;
import java.awt.Rectangle;



public abstract class GameObject {
    
    
    public ObjectId id;
    
    protected float x;
    protected float y;
    
    protected float vx;
    protected float vy;
    
    protected float ax;
    protected float ay;
    
    protected float witdh;
    protected float height;
    
    
    protected float maxvx=Float.MAX_VALUE;
    protected float maxvy=Float.MAX_VALUE;
    
    public boolean beforetick=false;

    public GameObject(ObjectId id, float x, float y,float witdh, float height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.witdh = witdh;
        this.height = height;
    }
    
    
    
    
    
    public abstract void render(Graphics g);
    
    
    
    public abstract void tick();
    
    protected void s(){
        
        this.vy+=ay;
        this.vx+=ax;
        

        
        if(vy>maxvy){
            vy=maxvy;
        }
        if(vy<-maxvy){
            vy=-maxvy;
        }
        
        if(vx>maxvx){
            vx=maxvx;
        }
        if(vx<-maxvx){
            vx=-maxvx;
        }
        
        
        this.y+=vy;
        this.x+=vx;
        
        
        
    }
    
    
    public Rectangle getBounds() {
        
      return new Rectangle((int)x,(int)y, (int) witdh, (int) height);
      

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public float getAx() {
        return ax;
    }

    public float getAy() {
        return ay;
    }

    public float getHeight() {
        return height;
    }

    public float getWitdh() {
        return witdh;
    }

    public ObjectId getId() {
        return id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public void setAy(float ay) {
        this.ay = ay;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWitdh(float witdh) {
        this.witdh = witdh;
    }
    
    
    
    
    
}
