import greenfoot.*;

public class Treasure extends Actor {
    
    private GreenfootImage[] treasureImages; 
    private int currentImageIndex; 
    private Diver diver; 
    private WaterWorld1 world; 
    private boolean isBigTreasure; 
    
    public Treasure(Diver diver, WaterWorld1 world, boolean isBigTreasure) {
        this.diver = diver;
        this.world = world;
        this.isBigTreasure = isBigTreasure; 
        
        treasureImages = new GreenfootImage[2]; 
        treasureImages[0] = new GreenfootImage("treasure1.png");
        treasureImages[1] = new GreenfootImage("treasure2.png");
        currentImageIndex = 0; 
        
        int newWidth = 30; 
        int newHeight = 30; 
        for (int i = 0; i < treasureImages.length; i++) {
            treasureImages[i].scale(newWidth, newHeight);
        }
        
        if(isBigTreasure){
            setImage(treasureImages[1]);
        } else {
            setImage(treasureImages[0]);
        }
    }
    
    public void act() {
        checkCollision();
    }
    
    private void checkCollision() {
        if (isTouching(Diver.class)) {
            if (shouldChangeImage()) {
                currentImageIndex = 1; 
                setImage(treasureImages[currentImageIndex]);
            }
            getWorld().removeObject(this);
        }
    }

    private boolean shouldChangeImage() {
        return false; 
    }
    
    public boolean isBigTreasure() {
        return isBigTreasure;
    }
}
