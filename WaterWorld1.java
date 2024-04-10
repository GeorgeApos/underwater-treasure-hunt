import greenfoot.*;

public class WaterWorld1 extends World {

    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private int treasureCount = 0; // Track the number of regular treasures collected
    private int score = 0;
    private int currentLevel = 1; // Initial level
    private int numberOfFishes = 7; // Initial number of fishes for level 1
    private boolean gameStarted = false; // Flag to indicate if the game has started

    public WaterWorld1() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        drawWorld(); // Ensure the world is drawn
        if (!gameStarted) {
            showRules();
        } else {
            GreenfootSound genericSound = new GreenfootSound("sea_sound.wav");
            genericSound.setVolume(50); // Adjust the volume as needed
            genericSound.playLoop();
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
        // Start the game when the user clicks anywhere
        if (Greenfoot.mouseClicked(null) && !gameStarted) {
            startGame();
        }
    }

    private void startGame() {
        // Remove rules text
        showText("", getWidth() / 2, getHeight() / 2);
        
        HealthBar healthBar = new HealthBar(100); // Adjust maximum oxygen level as needed
        SimpleTimer simpleTimer = new SimpleTimer();
        
        Diver diver = new Diver(healthBar);
        
        addObject(diver, getHeight() / 2, getWidth() / 2);
        addObject(healthBar, getWidth() / 2, 20);
        
        // Add fishes based on the current level
        for (int i = 0; i < numberOfFishes; i++) {
            addObject(new Fish(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        // Add treasures
        for (int i = 0; i < 3; i++) {
            spawnBigTreasure(diver);
        }
        
        // Initialize score
        score = 0;
        
        // Start the game
        gameStarted = true; // Update the gameStarted flag
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
    
    
    public void incrementTreasureCount(Diver diver) {
        treasureCount++;
        if (treasureCount % 3 == 0) {
            spawnBigTreasure(diver);
            spawnFish();
        }
    }

    private void spawnBigTreasure(Diver diver) {
        addObject(new Treasure(diver, this), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }

    private void spawnFish() {
        addObject(new Fish(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        numberOfFishes++;
    }

    public void decrementFishCount() {
        numberOfFishes--;
    }
}
