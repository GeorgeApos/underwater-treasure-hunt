import greenfoot.*;

public class WaterWorld1 extends World {

    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private int currentLevel = 1; // Initial level
    private int numberOfFishes = 7; // Initial number of fishes for level 1
    private boolean gameStarted = false; // Flag to indicate if the game has started

    public WaterWorld1() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        drawWorld(); // Ensure the world is drawn
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
        // Start the game when the user clicks anywhere
        if (Greenfoot.mouseClicked(null) && !gameStarted) {
            startGame();
        }
    }

    private void startGame() {
        // Remove rules text
        showText("", getWidth() / 2, getHeight() / 2);
        
        // Add objects and start the game
        addObject(new Diver(), getWidth() / 2, getHeight() / 2);
        
        // Add fishes based on the current level
        for (int i = 0; i < numberOfFishes; i++) {
            addObject(new Fish(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        
        // Add more initialization as needed for your game
        
        // Start the game
        gameStarted = true;
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
