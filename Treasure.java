import greenfoot.*;

public class Treasure extends Actor {
    
    private GreenfootImage[] treasureImages; // Array to hold images for the treasure
    private int currentImageIndex; // Index for current image in the array
    
    public Treasure() {
        // Load images for the treasure
        treasureImages = new GreenfootImage[2]; // Assuming two images for the treasure
        treasureImages[0] = new GreenfootImage("treasure1.png"); // Replace with actual image file
        treasureImages[1] = new GreenfootImage("treasure2.png"); // Replace with actual image file
        currentImageIndex = 0; // Start with the first image
        setImage(treasureImages[currentImageIndex]);
    }
    
    public void act() {
        // Check for collision with diver
        checkCollision();
    }
    
    private void checkCollision() {
        // Check for collision with diver
        if (isTouching(Diver.class)) {
            // Change the image to the second one if certain conditions are met
            if (shouldChangeImage()) {
                currentImageIndex = 1; // Change to the second image
                setImage(treasureImages[currentImageIndex]);
                // Add any additional logic for updating the treasure
            }
            // Remove the treasure after being collected
            getWorld().removeObject(this);
        }
    }
    
    private boolean shouldChangeImage() {
        // Add logic to determine when to change the treasure image
        // For example, based on certain checkpoints reached by the diver
        // You can use a counter, score, or any other game parameter
        // Return true when the conditions are met, false otherwise
        return false; // Placeholder, change this according to your game logic
    }
}
