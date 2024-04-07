import greenfoot.*;

public class Diver extends Actor {
    
    // Array to hold images for animation
    private GreenfootImage[] images;
    private int currentImageIndex;
    private int animationDelay = 5; // Adjust animation speed as needed
    private int delayCount = 0;

    public Diver() {
        // Load images for animation
        images = new GreenfootImage[2]; // Assuming two images for animation
        images[0] = new GreenfootImage("diver1.png"); // Replace with actual image file
        images[1] = new GreenfootImage("diver2.png"); // Replace with actual image file
        currentImageIndex = 0; // Start with the first image
        setImage(images[currentImageIndex]);
    }

    public void act() {
        animate();
        moveWithKeys();
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
            setLocation(getX(), getY() - 1); // Move up
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 1); // Move down
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 1, getY()); // Move left
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 1, getY()); // Move right
        }
    }
}
