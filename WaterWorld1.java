import greenfoot.*;

public class WaterWorld1 extends World {

    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private int treasureCount = 0; 
    private int score = 0;
    private int currentLevel = 1; 
    private int numberOfFishes = 7; 
    private boolean gameStarted = false; // Flag to indicate if the game has started
    private SimpleTimer timer = new SimpleTimer(); // Step 1: Declare the timer


    public WaterWorld1() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        drawWorld(); 
        if (!gameStarted) {
            showRules();
        } else {
            startGame();
        }
    }

    private void showRules() {
        // Display rules on the welcome screen
        String rulesText = "Welcome to the Fish Game!\n\n";
        rulesText += "Rules:\n";
        rulesText += "- Click anywhere to start the game.\n";
        rulesText += "- Avoid collisions with other fishes.\n";
        rulesText += "- Eat food to gain points.\n";
        rulesText += "- Have fun!";
        
        showText(rulesText, getWidth() / 2, getHeight() / 2);
    }

public void act() {
    if (Greenfoot.mouseClicked(null) && !gameStarted) {
        startGame();
        Greenfoot.playSound("sea_sound.wav");
    }

    // Step 2 & 3: Mark time and check elapsed time
    int elapsed = timer.millisElapsed();

    // Show the elapsed time on the screen
    showText("Time: " + (elapsed / 1000), 50, 80); // Adjust position as needed
}


    private void startGame() {
        showText("", getWidth() / 2, getHeight() / 2);
        
        HealthBar healthBar = new HealthBar(100); 
        SimpleTimer simpleTimer = new SimpleTimer();
        
        Diver diver = new Diver(healthBar);
        
        addObject(diver, getHeight() / 2, getWidth() / 2);
        addObject(healthBar, getWidth() / 2, 20);
        
        for (int i = 0; i < numberOfFishes; i++) {
            addObject(new Fish(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        for (int i = 0; i < 3; i++) {
            addObject(new Treasure(diver, this, false), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        score = 0;
        
        gameStarted = true; 
        timer.mark(); // Start the game timer
        Greenfoot.start();
    }

    
    private void drawWorld() {
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(0, 0, 128);
        for (int y = 0; y < getHeight(); y++) {
            int r = lightBlue.getRed() + (int) ((double) (darkBlue.getRed() - lightBlue.getRed()) * y / getHeight());
            int g = lightBlue.getGreen() + (int) ((double) (darkBlue.getGreen() - lightBlue.getGreen()) * y / getHeight());
            int b = lightBlue.getBlue() + (int) ((double) (darkBlue.getBlue() - lightBlue.getBlue()) * y / getHeight());
            background.setColor(new Color(r, g, b));
            background.drawLine(0, y, getWidth() - 1, y);
        }
        setBackground(background);
    }
    
}