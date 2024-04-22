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
        if (amount > 0) {
            oxygenLevel += amount;
        } else {
            oxygenLevel += fillRate;
        }
        if (oxygenLevel > maxOxygenLevel) {
            oxygenLevel = maxOxygenLevel;
        }
    }

    private void updateImage() {
        GreenfootImage image = new GreenfootImage((int)(maxOxygenLevel + 2.0), 30); 
        image.setColor(Color.RED);
        image.fillRect(1, 1, (int)oxygenLevel, 18);
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, (int)(maxOxygenLevel + 1), 19);
        image.setColor(Color.BLACK);
        image.drawString("Oxygen", 5, 28);
        setImage(image);
         if (oxygenLevel <= 0) {
            if (delayCounter <= 0) {
                Diver diver = (Diver)getWorld().getObjects(Diver.class).get(0); 
                diver.setImage("diver_red.png"); 
                diver.decreaseLives(1);
                delayCounter = delayDuration; 
            } else {
                delayCounter--;
            }
        }
    }

}
