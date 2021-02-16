package bird;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;






public class GameLoop extends Canvas implements Runnable{
    
    
    
    
    private void init(){
        
        int inNumberofBirds = Constants.getNumberOfBirds();
        if(Constants.learn())
            for (int i = 0; i < inNumberofBirds; i++) {
                Handler.addBird();
            }
        else{
            Constants.setNumberOfBirds(1);
            Handler.addBird();
        }
        
        
        Handler.addBlock();
        
    }
    
    
    @Override
    public void run() {
        
        init();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
            while(run){

                long now = System.nanoTime();



                delta += (now - lastTime) / (ns/Constants.getGamespeed());
                lastTime = now;

                while(delta >= 1){
                    tick();
                    updates++;
                    delta--;

                }

                render();
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames + " TICKS: " + updates);
                    frames = 0;
                    updates = 0;
                }
                
                
            }
        
    }
    
    
    
    private Thread thread = new Thread(this);
    
    private boolean run=false;
    
    public void start(){
        
        if(run)return;
        
        run=true;
        thread.start();
        
        
    }
    
    public void stop(){
        
        if(!run)return;
        
        run=false;
        
    }
    
    
    
    private void tick(){
        
        
        
        Handler.birdsControl();
        Handler.blockControl();
        
        if(Handler.isReset()){
            reset();
        }
        
        
    }
    
    private void render(){
         BufferStrategy bs = this.getBufferStrategy();
         
        if(bs==null){
        
            this.createBufferStrategy(3);
            return;

        }
        
        Graphics g = bs.getDrawGraphics();
        
        Graphics2D g2d=(Graphics2D)g;
        
        g.setColor(Color.white);
        g.fillRect(0,0, getWidth(), getHeight());
        
        renderObjects(g);
        
   
        
        g.dispose();
        bs.show();
        
        
        
    }
    
    
    private void renderObjects(Graphics g){

        
        g.drawImage(Constants.getTex().background, 0, 0,this.getWidth(),this.getHeight(), null);
        
        for (int i = 0; i < Handler.getHandler().size(); i++) {
            
            
            Handler.getHandler().get(i).render(g);
            
        }
        
        g.setColor(Color.BLACK);
        
        String s = "SCORE=";
        
        s+=String.valueOf(Constants.getScore());
        
        s+=" GEN=";
        
        s+=String.valueOf(Constants.getGen());
        
        s+=" SPEED=";
        
        s+=String.valueOf(Constants.getGamespeed());
        
        String s2="";
        s2+=" BIRDS=";
        
        s2+=String.valueOf(Constants.getNumberOfBirds());
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.2F);
        g.setFont(newFont);
        g.drawString(s, 5, 30);
        g.drawString(s2, 5, 60);
        
        g.drawImage(Constants.getTex().ground, 0, this.getHeight()-29,this.getWidth(),29, null);
    }
    
    
    public void reset(){
        
        Handler.reset();
        Constants.increaseGen();
        
    }
    
    
    
    
    
    
    
}
