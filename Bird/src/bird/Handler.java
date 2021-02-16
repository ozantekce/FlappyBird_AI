package bird;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java.util.ArrayList;
import objects.Bird;
import objects.BotBlock;




public class Handler {
    
    
    private static ArrayList<GameObject> handler = new ArrayList<GameObject>();
    
    private static ArrayList<GameObject> listblock = new ArrayList<GameObject>();
    
    private static ArrayList<Bird> birds = new ArrayList<>();

    public static ArrayList<Bird> getBirds() {
        return birds;
    }

    public static ArrayList<GameObject> getListblock() {
        return listblock;
    }

    public static ArrayList<GameObject> getHandler() {
        return handler;
    }
    
    
    
    
    
    private static BotBlock tempblock;
    
    private static BotBlock currentBlock;

    public static BotBlock getCurrentBlock() {
        return currentBlock;
    }
    
    
    
    
    public static void addBird(){
        
        if(Constants.learn()){
            
            Bird tempbird  = new Bird(ObjectId.Bird, (Constants.getMidW()/2)-40,
                    (Constants.getMidH()/2)+10, Constants.birdWidth(), Constants.birdHeight());
            
            if(Constants.getCapacityOfBirds()>Constants.getNumberOfBirds()){
                
                birds.add(tempbird);
                handler.add(tempbird);
                Constants.setNumberOfBirds(Constants.getNumberOfBirds()+1);
                
            }
            
            
        }
        else{
            
            Bird tempbird  = new Bird(ObjectId.Bird, (Constants.getMidW()/2)-40,
                    (Constants.getMidH()/2)+10, Constants.birdWidth(), Constants.birdHeight());
            
            if(birds.size()>0)   
                birds.set(0,tempbird);
            else{
                birds.add(tempbird);
            }
            handler.add(tempbird);
            
            
        }
        
    }
    
    public static void addBird(Bird bird){
        
        if(Constants.learn()){
            
            
            if(Constants.getCapacityOfBirds()>Constants.getNumberOfBirds()){
                
                birds.add(bird);
                handler.add(bird);
                Constants.setNumberOfBirds(Constants.getNumberOfBirds()+1);
            }
            
            
        }
        else{
            
            
            
            
        }
        
    }
    
    
    
    public static void addBlock(){
        
        
        tempblock=new BotBlock(ObjectId.BotBlock, Constants.getMidW()+50, 0,
                Constants.blockWidth(), Constants.getMidH());
        
        
        listblock.add(tempblock);
        handler.add(tempblock);
        
        Handler.tempblock=tempblock;
        
        if(currentBlock==null)
            currentBlock = tempblock;
        
    }
    

    
    public static void reset(){
        
        reset=false;
        
        listblock = new ArrayList<GameObject>();
        handler = new ArrayList<GameObject>();
        
        currentBlock = null;
        tempblock = null;
        
        addBlock();
        
        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).setAlive(true);
            handler.add(birds.get(i));
            
        }
        Constants.setNumberOfBirds(birds.size());
    
    }
    
    
    public static void blockControl(){
        
        if(tempblock.getDis()>Constants.getMidW()-100){
            addBlock();
        }
        
        
        
        if(currentBlock.getX()+currentBlock.getWitdh()<(Constants.getMidW()/2)-40){
            Constants.increaseScore();
            currentBlock=tempblock;
        }
        
    }
    
    
    public static void birdsControl(){
        
        if(true){
            
            int birds=0;


            int a = handler.size();

            for (int i = 0; i < a; i++) {

                if(a!=handler.size()){
                    birds=0;
                    a = handler.size();
                    i=0;
                }

                if(handler.get(i).getId()==ObjectId.Bird){
                    birds++;
                    Bird tp =(Bird)handler.get(i);
                    tp.score=Constants.getScore();
                }
                handler.get(i).tick();

            }



            for (int i = 0; i < handler.size(); i++) {

                handler.get(i).beforetick=false;

            }

            if(birds==0){
                if(Constants.learn())
                    nextTurn();
                else{
                    addBird();
                    reset = true;
                }
            }
        
            
        }
        
        
    }
    
    
    
    private static int mutation=10;

    
    private static boolean reset=false;

    public static boolean isReset() {
        return reset;
    }
    
    
    
    private static void nextTurn(){
        
        
        sort();
        
        if(birds.size()==1){
            
            mut(birds.get(0));
            Bird temp =nwBird(birds.get(0), birds.get(0));
            addBird(temp);
            reset=true;
            return;
        }
        
        for (int i = 0; i < birds.size(); i++) {
            
            if(birds.get(i).score>0){
                
                int a = (int)((double)birds.get(i).score/birds.get(0).score*3);
                for (int j = 0; j < a; j++) {
                    
                    int r = (int) (Math.random()*birds.size());
                    while (r==i) {                
                        r = (int) (Math.random()*birds.size());
                    }
            
                    addBird(nwBird(birds.get(i), birds.get(r)));
                }
                
                if(a==0){
                    int r = (int) (Math.random()*birds.size());
                    while (r==i) {                
                        r = (int) (Math.random()*birds.size());
                    }
            
                    match(birds.get(i), birds.get(r)); 
                }
                
            }
            else{
                int r = (int) (Math.random()*birds.size());
                while (r==i) {                
                    r = (int) (Math.random()*birds.size());
                }
            
                match(birds.get(i), birds.get(r)); 
            }
            
            int r2 = (int) (Math.random()*101);
            
            if(r2<mutation){
                
                mut(birds.get(i));
                
            }
            
            birds.get(i).setX((Constants.getMidW()/2)-40);
            birds.get(i).setY((Constants.getMidH()/2)+10);
            
            
        }
        
        reset=true;
        
    }
    
    
    private static void mut(Bird b1){
        
        int r = (int) (Math.random()*4);
        
        
        if(r==0){
            b1.threshold=Constants.rand()*100;
        }
        else if(r==1){
            b1.ka=Constants.rand();
        }
        else if(r==2){
            b1.kb=Constants.rand();
        }
        else if(r==3){
            b1.kc=Constants.rand();
        }
        
    }
    
    
    
    private static void match(Bird b1,Bird b2){
        
        int r = (int) (Math.random()*4);
        
        int r2 = (int) (Math.random()*4);
        
        while (r<r2) {            
            r2 = (int) (Math.random()*4);
        }
        
        float holder=0;
        
        for (int i = r2; i < r; i++) {
            if(i==0){
                holder=b1.threshold;
                b1.threshold=b2.threshold;
                b2.threshold=holder;
            }
            else if(i==1){
                holder=b1.ka;
                b1.ka=b2.ka;
                b2.ka=holder;
            }
            else if(i==2){
                holder=b1.kb;
                b1.kb=b2.kb;
                b2.kb=holder;
            }
            else if(i==3){
                holder=b1.kc;
                b1.kc=b2.kc;
                b2.kc=holder;
            }
        }

        
        
    }
    
    
    
    private static Bird nwBird(Bird b1,Bird b2){
        
        Bird tempBird = new Bird(ObjectId.Bird, (Constants.getMidW()/2)-40, (Constants.getMidH()/2)+10,
                Constants.birdWidth(), Constants.birdHeight());
        
        tempBird.threshold=b1.threshold;
        tempBird.ka=b1.ka;
        tempBird.kb=b1.kb;
        tempBird.kc=b1.kc;
        
        int r = (int) (Math.random()*4);
        
        if(r==0){
            tempBird.threshold=b2.threshold;
        }
        else if(r==1){
            tempBird.ka=b2.ka;
        }
        else if(r==2){
            tempBird.kb=b2.kb;
        }
        else if(r==3){
            tempBird.kc=b2.kc;
        }
        
        
        return tempBird;
    }
    
    
    
    
    private static void sort(){

    Bird temp;
    
    
    for (int i = 0; i < birds.size(); i++) {
          
        for (int j = 0; j < birds.size()-1; j++) {
              
            if(birds.get(j).score<birds.get(j+1).score){
                  
                temp = birds.get(j+1);
                birds.set(j+1, birds.get(j));
                birds.set(j, temp);
                  
            }
              
              
        }
          
          
    }

    
 }
    
    
    
    
    
}
