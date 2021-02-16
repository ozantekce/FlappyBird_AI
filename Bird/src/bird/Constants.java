package bird;

import framework.Texture;




public class Constants {
    
    private static boolean learn=true;
    
    private static final int midW=482;
    private static final int midH=589;
    
    
    private static final float birdw=58;
    private static final float birdh=42;
    private static final float blockw=90;
    
    
    private static int numberOfBirds=10;
    private static final int capacityOfBirds=200;
    
    
    private static final double baseGamespeed=0.5;
    private static final double capacityofGamespeed=512;
    
    private static double gamespeed=baseGamespeed;
    private static Texture tex= new Texture();
    
    

    public static float birdHeight() {
        return birdh;
    }
    
    public static float birdWidth() {
        return birdw;
    }

    public static float blockWidth() {
        return blockw;
    }

    public static boolean learn() {
        return learn;
    }

    public static int getMidW() {
        return midW;
    }

    public static int getMidH() {
        return midH;
    }
    
    
    
    private static int interval=100;

    public static int interval() {
        return interval;
    }
    
    
    
    public static int getNumberOfBirds() {
        
        return numberOfBirds;
    }

    public static void setNumberOfBirds(int numberOfBirds) {
        Constants.numberOfBirds = numberOfBirds;
    }
    
    

    public static int getCapacityOfBirds() {
        return capacityOfBirds;
    }

    public static Texture getTex() {
        return tex;
    }

    public static double getGamespeed() {
        return gamespeed;
    }
    
    
    public static void increaseGamespeed(){
        
        if(gamespeed<capacityofGamespeed){
            gamespeed*=2;
        }
        
    }
    
    public static void decreaseGamespeed(){
        
        if(gamespeed>baseGamespeed){
            gamespeed/=2;
        }
        
    }
    
    
    private static int score;
    private static int gen;

    public static int getScore() {
        return score;
    }

    public static int getGen() {
        return gen;
    }
    
    public static void increaseScore(){
        
        score++;
        
    }
    
    public static void increaseGen(){
        score=0;
        gen++;
        
    }
    
    
    public static float rand(){
        
        float r = (float) (Math.random()*Constants.interval());
        
        int p = (int) (Math.random()*2);
        /*
        if(p==0){
            r*=-1;
        }
*/
        
        return r;
    }
 
    
    
}
