package bird;
import framework.GameObject;
import framework.ObjectId;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{

    
    
    
    public void keyPressed(KeyEvent e){
        
    
        int key =e.getKeyCode();
        
        
        if(!Constants.learn()){
            for (int i = 0; i < Handler.getHandler().size(); i++) {  
                GameObject tempObject1 = Handler.getHandler().get(i);
                if(tempObject1.getId()==ObjectId.Bird){
                    objects.Bird  temp = (objects.Bird) tempObject1;

                if(key==KeyEvent.VK_SPACE) {

                   temp.jump();

                }
                break;
                }
            }
        }
        
        
            if(key==KeyEvent.VK_D) {
                
               Constants.increaseGamespeed();
                
            }
        
            if(key==KeyEvent.VK_A) {
                
               Constants.decreaseGamespeed();
                
            }
            
    
    }
    

    
    
    
    
}    
    
    
    
    

    