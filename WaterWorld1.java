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
    private GreenfootSound backgroundMusic;


    public WaterWorld1() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        backgroundMusic = new GreenfootSound("sea_sound.wav"); // Initialize backgroundMusic
        drawWorld(); 
        if (!gameStarted) {
            showRules();
        } else {
            startGame();
        }
    }

    private void showRules() {
        String rulesText = "Welcome to the Underwater Treasure Hunt!\n\n";
        rulesText += "Rules:\n";
        rulesText += "- Click anywhere to start the game.\n";
        rulesText += "- Avoid collisions with other fishes.\n";
        rulesText += "- Capture the treasure to gain points.\n";
        rulesText += "- Big treasures give 2 points!\n";
        rulesText += "- Each captured treasure increases the game's difficulty!\n";
        rulesText += "- Swim up for oxygen!\n";
        rulesText += "- Score as much as you can in a limited time!\n";
    
        showText(rulesText, getWidth() / 2, getHeight() / 2);
    }

public void act() {
    if (Greenfoot.mouseClicked(null) && !gameStarted) {
        startGame();
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.playLoop();
        }
    }

    int elapsed = timer.millisElapsed();

    showText("Time: " + (elapsed / 1000), 50, 80); 
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
        timer.mark(); 
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