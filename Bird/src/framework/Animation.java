package framework;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public class Animation {
    
    private int speed;
    private int frames;
    
    
    private int index=0;
    private int count=0;
    
    
    private BufferedImage[] images;
    private BufferedImage currentImg;
    
    
    private boolean animover=false;
    
     
    public Animation(int speed,BufferedImage...args){
        this.speed=speed;
        images=new BufferedImage[args.length];
        
        for (int i = 0; i < args.length; i++) {
            images[i]=args[i];
        }
        frames=args.length;
    
    
    }
    
    
    
    public void runAnimation(){
        index++;
        if(index>speed){
            index=0;
            nextFrame();
        }
    
    }
    
    
    
    private void nextFrame(){
        for (int i = 0; i < frames; i++) {
            if(count==i){
                currentImg=images[i];
            }
        }
        
            count++;
                        
            if(count==frames){
                
                animover=true;
                //System.out.println("efekt bitti");
            }
            
            if(count>frames){
                count=0;
            }

        
    }
    
    
    public void drawAnimation(Graphics g,int x,int y){
    
        g.drawImage(currentImg, x, y, null);
        
        
        
    }
    
    
    public void drawAnimation(Graphics g,int x,int y,int scaleX,int scaleY){
    
        g.drawImage(currentImg, x, y,scaleX,scaleY, null);
         
        
        
    }    
    
    public void drawAnimation(Graphics g,int x,int y,int scaleX,int scaleY,double angle){
        
        if(currentImg==null){
            drawAnimation(g, x, y);
            return;
        }
        
        BufferedImage temp = angle(currentImg, angle);
        
        g.drawImage(temp, x, y, null);
         
        
        
    }    
    

    public boolean isAnimover() {
        return animover;
    }

    public void setAnimover(boolean animover) {
        this.animover = animover;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
    private BufferedImage angle(BufferedImage image,double angle){
        
        
        final double rads = Math.toRadians(angle);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);


        return rotatedImage;
    }
    
    
    
    
}



