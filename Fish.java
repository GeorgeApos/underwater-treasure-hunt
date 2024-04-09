import greenfoot.*;

public class Fish extends SmoothMover {
    
    private GreenfootImage[][] fishImages; // Array to hold arrays of images for the fish animations
    private int speed = 1; // Adjust speed as needed
    private int animationDelay = 10; // Adjust animation speed as needed
    private int delayCount = 0; // Counter for animation delay
    private int currentAnimationIndex = 0; // Index for current animation set
    private int currentImageIndex = 0; // Index for current image in animation set
    private int initialDirection; // Initial direction for the fish

    public Fish() {
        // Load arrays of images for the fish animations
        fishImages = new GreenfootImage[3][]; // Assuming three sets of animations for the fish
        fishImages[0] = new GreenfootImage[2]; // Two images for the first animation set
        fishImages[0][0] = new GreenfootImage("fish1.1.png"); // Replace with actual image file
        fishImages[0][1] = new GreenfootImage("fish1.2.png"); // Replace with actual image file
        
        fishImages[1] = new GreenfootImage[2]; // Two images for the second animation set
        fishImages[1][0] = new GreenfootImage("fish2.1.png"); // Replace with actual image file
        fishImages[1][1] = new GreenfootImage("fish2.2.png"); // Replace with actual image file
        
        fishImages[2] = new GreenfootImage[2]; // Two images for the third animation set
        fishImages[2][0] = new GreenfootImage("fish3.1.png"); // Replace with actual image file
        fishImages[2][1] = new GreenfootImage("fish3.2.png"); // Replace with actual image file
        
        // Randomly select an animation set for the fish
        currentAnimationIndex = Greenfoot.getRandomNumber(fishImages.length);
        setImage(fishImages[currentAnimationIndex][currentImageIndex]);
        
        // Randomly set initial direction
        initialDirection = Greenfoot.getRandomNumber(360); // Random angle between 0 and 359 degrees
        setRotation(initialDirection);
    }

    public void act() {
        moveAround();
        animate();
    }

    private void moveAround() {
        // Move the fish
        move(speed);
        
        // Check if the fish is at the edge of the world
        if (isAtEdge()) {
            // If at the edge, turn the fish by a random angle
            int randomTurn = Greenfoot.getRandomNumber(180); // Random turn between 0 and 179 degrees
            turn(randomTurn);
            
            // Move away from the edge slightly to avoid getting stuck
            move(speed * 3); // Move away from the edge with a distance proportional to the speed
        }
    }

    private void animate() {
        if (delayCount >= animationDelay) {
            // Toggle between the images for animation within the current animation set
            currentImageIndex = (currentImageIndex + 1) % fishImages[currentAnimationIndex].length;
            setImage(fishImages[currentAnimationIndex][currentImageIndex]);
            delayCount = 0; // Reset delay count
        } else {
            delayCount++; // Increment delay count
        }
    }
}
