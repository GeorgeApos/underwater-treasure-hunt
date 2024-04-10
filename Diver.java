import greenfoot.*;

public class Diver extends SmoothMover {
    
    // Array to hold images for animation
    private GreenfootImage[] images;
    private int currentImageIndex;
    private int animationDelay = 5; // Adjust animation speed as needed
    private int delayCount = 0;
    private int lives = 3; // Initial number of lives
    private int score = 0;
    private boolean gameEnded = false; // Flag to indicate if the game has ended
    private HealthBar healthBar;
    private double oxygenUsageRate = 0.1; // Adjust as needed
    private int oxygenRefillAmount = 10; // Adjust as needed

    public Diver(HealthBar bar){
        healthBar = bar;
        // Load images for animation
        images = new GreenfootImage[2]; // Assuming two images for animation
        images[0] = new GreenfootImage("diver1.png"); // Replace with actual image file
        images[1] = new GreenfootImage("diver2.png"); // Replace with actual image file
        currentImageIndex = 0; // Start with the first image
        setImage(images[currentImageIndex]);
    }

    public void act() {
        if (!gameEnded) {
            animate();
            moveWithKeys();
            checkCollision();
            displayLives();
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
            // Increment image index for animation
            currentImageIndex = (currentImageIndex + 1) % images.length;
            setImage(images[currentImageIndex]);
            delayCount = 0; // Reset delay count
        } else {
            delayCount++; // Increment delay count
        }
    }
    
    private void moveWithKeys() {
        // Move the diver based on keyboard inputs
        if (Greenfoot.isKeyDown("up")) {
            if (Greenfoot.isKeyDown("left")) {
                setLocation(getX() - 1, getY() - 1); // Move diagonally up-left
                setRotation(225); // Set rotation to 225 degrees (up-left)
            } else if (Greenfoot.isKeyDown("right")) {
                setLocation(getX() + 1, getY() - 1); // Move diagonally up-right
                setRotation(315); // Set rotation to 315 degrees (up-right)
            } else {
                setLocation(getX(), getY() - 1); // Move up
                setRotation(270); // Set rotation to 270 degrees (up)
            }
        } else if (Greenfoot.isKeyDown("down")) {
            if (Greenfoot.isKeyDown("left")) {
                setLocation(getX() - 1, getY() + 1); // Move diagonally down-left
                setRotation(135); // Set rotation to 135 degrees (down-left)
            } else if (Greenfoot.isKeyDown("right")) {
                setLocation(getX() + 1, getY() + 1); // Move diagonally down-right
                setRotation(45); // Set rotation to 45 degrees (down-right)
            } else {
                setLocation(getX(), getY() + 1); // Move down
                setRotation(90); // Set rotation to 90 degrees (down)
            }
        } else if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 1, getY()); // Move left
            setRotation(180); // Set rotation to 180 degrees (left)
        } else if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 1, getY()); // Move right
            setRotation(0); // Set rotation to 0 degrees (right)
        }
    }
    
    private void checkCollision() {
        // Check for collision with fishes
        if (isTouching(Fish.class)) {
            // Decrease lives when colliding with a fish
            lives--;
            // Remove the fish after collision
            removeTouching(Fish.class);
            
            // Change the color of the diver to red
            setImage("diver_red.png"); // Assuming you have a red diver image
            
            // Delay to show the red color briefly
            Greenfoot.delay(20); // Adjust the delay as needed
            
            // Change back to the original image after the delay
            setImage(images[currentImageIndex]);
        }
        
        // Check for collision with treasure
        if (isTouching(Treasure.class)) {
            // Increase score
            score++;
            // Remove the treasure after being collected
            removeTouching(Treasure.class);
            // Update display of score
            displayScore();
        }
    }

    
    private void displayLives() {
        // Display remaining lives
        getWorld().showText("Lives: " + lives, 50, 20);
    }
    
    private void displayScore() {
    // Display the score on the screen
    getWorld().showText("Score: " + score, 50, 50);
    }

    
    private void checkEndGame() {
        // Check if the game should end
        if (lives < 1) {
    
            // Display end game text
            getWorld().showText("Game Over", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            // Set the flag to indicate the game has ended
            gameEnded = true;
        }
    }
    
    public void decreaseLives(int amount) {
        lives -= amount;
        if (lives < 0) {
            lives = 0;
        }
    }

}
