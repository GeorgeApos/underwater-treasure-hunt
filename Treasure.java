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
        
        // Set the size of the treasure
        int newWidth = 30; // Adjust width as needed
        int newHeight = 30; // Adjust height as needed
        for (int i = 0; i < treasureImages.length; i++) {
            treasureImages[i].scale(newWidth, newHeight);
        }
        
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
        // Store the current world reference to avoid accessing it after removal
        World world = getWorld();
        // Remove the treasure after being collected
        world.removeObject(this);
        // Delay the spawning of the new treasure
        Greenfoot.delay(1);
        // Spawn the new treasure after a short delay
        spawnTreasure(world);
    }
}

private void spawnTreasure(World world) {
    int worldWidth = world.getWidth();
    int worldHeight = world.getHeight();
    
    // Generate random coordinates within the world bounds
    int randomX = Greenfoot.getRandomNumber(worldWidth);
    int randomY = Greenfoot.getRandomNumber(worldHeight);
    
    // Ensure the treasure stays within the world bounds
    int minX = 0;
    int minY = 0;
    int maxX = worldWidth - 1;
    int maxY = worldHeight - 1;
    
    // Adjust the random coordinates if they exceed the world boundaries
    if (randomX < minX) randomX = minX;
    if (randomX > maxX) randomX = maxX;
    if (randomY < minY) randomY = minY;
    if (randomY > maxY) randomY = maxY;
    
    // Spawn the treasure at the adjusted coordinates
    Treasure treasure = new Treasure();
    world.addObject(treasure, randomX, randomY);
}


    
    private boolean shouldChangeImage() {
        // Add logic to determine when to change the treasure image
        // For example, based on certain checkpoints reached by the diver
        // You can use a counter, score, or any other game parameter
        // Return true when the conditions are met, false otherwise
        return false; // Placeholder, change this according to your game logic
    }
}
