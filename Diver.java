import greenfoot.*;

public class Diver extends SmoothMover {
    
    private GreenfootImage[] images;
    private int currentImageIndex;
    private int animationDelay = 5;
    private int delayCount = 0;
    private int lives = 3; 
    private int score = 0;
    private boolean gameEnded = false; 
    private HealthBar healthBar;
    private double oxygenUsageRate = 0.1;
    private int oxygenRefillAmount = 0;
    private int treasureCount = 0; 


    public Diver(HealthBar bar){
        healthBar = bar;
        images = new GreenfootImage[2];
        images[0] = new GreenfootImage("diver1.png");
        images[1] = new GreenfootImage("diver2.png"); 
        currentImageIndex = 0;
        setImage(images[currentImageIndex]);
    }

    public void act() {
        if (!gameEnded) {
            animate();
            moveWithKeys();
            checkCollision();
            displayLives();
            displayScore();
            checkEndGame();
            updateOxygenLevel();
        }
    }

     private void updateOxygenLevel() {
        if (!gameEnded) {
            healthBar.decreaseOxygen(oxygenUsageRate);
            if (isAboveWater()) {
                healthBar.fillOxygen(oxygenRefillAmount);
            }
        }
    }

    private boolean isAboveWater() {
        int topBoundary = getWorld().getHeight() / 10;
        return getY() <= topBoundary;
    }
    
    private void animate() {
        if (delayCount >= animationDelay) {
            currentImageIndex = (currentImageIndex + 1) % images.length;
            setImage(images[currentImageIndex]);
            delayCount = 0; 
        } else {
            delayCount++;         
        }
    }
    
    private void moveWithKeys() {
        if (Greenfoot.isKeyDown("up")) {
            if (Greenfoot.isKeyDown("left")) {
                setLocation(getX() - 1, getY() - 1);
                setRotation(225); 
            } else if (Greenfoot.isKeyDown("right")) {
                setLocation(getX() + 1, getY() - 1);
                setRotation(315); 
            } else {
                setLocation(getX(), getY() - 1); 
                setRotation(270); 
            }
        } else if (Greenfoot.isKeyDown("down")) {
            if (Greenfoot.isKeyDown("left")) {
                setLocation(getX() - 1, getY() + 1); 
                setRotation(135); 
            } else if (Greenfoot.isKeyDown("right")) {
                setLocation(getX() + 1, getY() + 1); 
                setRotation(45); 
            } else {
                setLocation(getX(), getY() + 1); 
                setRotation(90); 
            }
        } else if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 1, getY()); 
            setRotation(180); 
        } else if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 1, getY()); 
            setRotation(0);
        }
    }
    
    private void checkCollision() {
        if (isTouching(Fish.class)) {
            Greenfoot.playSound("hurt.mp3");
            lives--;
            removeTouching(Fish.class);
            setImage("diver_red.png"); 
            Greenfoot.delay(30);
            setImage(images[currentImageIndex]);
        }
    
        
        if (isTouching(Treasure.class)) {
            Treasure treasure = (Treasure) getOneIntersectingObject(Treasure.class);
            if (treasure != null) {
                if (treasure.isBigTreasure()) {
                    score += 2; 
                } else {
                    score++; 
                }
                displayScore();
                treasureCount++; 
    
                getWorld().addObject(new Fish(), Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
    
                if (treasureCount % 3 != 0) {
                    getWorld().addObject(new Treasure(this, (WaterWorld1)getWorld(), false), Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
                } else {
                    getWorld().addObject(new Treasure(this, (WaterWorld1)getWorld(), true), Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
                }
    
                healthBar.fillOxygen(15);
                getWorld().removeObject(treasure);
            }
        }
    }
        
    private void displayLives() {
        getWorld().showText("Lives: " + lives, 50, 20);
    }
    
    private void displayScore() {
    getWorld().showText("Score: " + score, 50, 50);
    }

    
    private void checkEndGame() {
        if (lives < 1) {
            Greenfoot.playSound("you_died.mp3");
            
            Actor gameOverImage = new Actor() {};
            gameOverImage.setImage("you_died.png");
            getWorld().addObject(gameOverImage, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            
            gameEnded = true;
            Greenfoot.stop();
        }
    }
    
    public void decreaseLives(int amount) {
        lives -= amount;
        if (lives < 0) {
            lives = 0;
        }
    }
}



    
