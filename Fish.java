import greenfoot.*;

public class Fish extends SmoothMover {
    
    private GreenfootImage[][] fishImages;
    private int speed = 1; 
    private int animationDelay = 10;
    private int delayCount = 0; 
    private int currentAnimationIndex = 0;
    private int currentImageIndex = 0; 
    private int initialDirection; 

    public Fish() {
        fishImages = new GreenfootImage[3][]; 
        fishImages[0] = new GreenfootImage[2]; 
        fishImages[0][0] = new GreenfootImage("fish1.1.png"); 
        fishImages[0][1] = new GreenfootImage("fish1.2.png"); 
        
        fishImages[1] = new GreenfootImage[2]; 
        fishImages[1][0] = new GreenfootImage("fish2.1.png"); 
        fishImages[1][1] = new GreenfootImage("fish2.2.png"); 
        
        fishImages[2] = new GreenfootImage[2]; 
        fishImages[2][0] = new GreenfootImage("fish3.1.png"); 
        fishImages[2][1] = new GreenfootImage("fish3.2.png"); 
        
        currentAnimationIndex = Greenfoot.getRandomNumber(fishImages.length);
        setImage(fishImages[currentAnimationIndex][currentImageIndex]);
        
        initialDirection = Greenfoot.getRandomNumber(360);
        setRotation(initialDirection);
    }

    public void act() {
        moveAround();
        animate();
    }

    private void moveAround() {
        move(speed);
        if (isAtEdge()) {
            int randomTurn = Greenfoot.getRandomNumber(180);
            turn(randomTurn);
            move(speed * 3); 
        }
    }

    private void animate() {
        if (delayCount >= animationDelay) {
            currentImageIndex = (currentImageIndex + 1) % fishImages[currentAnimationIndex].length;
            setImage(fishImages[currentAnimationIndex][currentImageIndex]);
            delayCount = 0; 
        } else {
            delayCount++;
        }
    }
}
