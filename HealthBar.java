import greenfoot.*;

public class HealthBar extends Actor {
    private double oxygenLevel;
    private double maxOxygenLevel;
    private double fillRate = 0.3;
    private int delayCounter = 0;
    private int delayDuration = 180; // Adjust as needed, 60 equals 1 second

    public HealthBar(double maxOxygen) {
        maxOxygenLevel = maxOxygen;
        oxygenLevel = maxOxygenLevel;
        updateImage();
    }

    public void act() {
        updateImage();
    }

    public void decreaseOxygen(double amount) {
        oxygenLevel -= amount;
        if (oxygenLevel < 0) {
            oxygenLevel = 0;
        }
    }

    public void fillOxygen(double amount) {
        oxygenLevel += fillRate;
        if (oxygenLevel > maxOxygenLevel) {
            oxygenLevel = maxOxygenLevel;
        }
    }

    private void updateImage() {
        GreenfootImage image = new GreenfootImage((int)(maxOxygenLevel + 2.0), 30); // Increased height to accommodate the title
        // Set the color to red for the health bar
        image.setColor(Color.RED);
        image.fillRect(1, 1, (int)oxygenLevel, 18);
        // Draw the border for the health bar
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, (int)(maxOxygenLevel + 1), 19);
        // Add a title
        image.setColor(Color.BLACK);
        image.drawString("Oxygen", 5, 28); // Adjust position as needed
        setImage(image);
         if (oxygenLevel <= 0) {
            if (delayCounter <= 0) {
                Diver diver = (Diver)getWorld().getObjects(Diver.class).get(0); // Assuming there's only one diver
                diver.setImage("diver_red.png"); // Change the diver's image to red
                diver.decreaseLives(1);
                delayCounter = delayDuration; // Reset the delay counter
            } else {
                delayCounter--; // Decrement the delay counter
            }
        }
    }
}
