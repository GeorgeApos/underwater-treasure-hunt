import greenfoot.*;

public class WaterWorld extends World {

    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;

    public WaterWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        drawWorld();
        prepare();
    }

    private void prepare() {
        addObject(new Diver(), getWidth() / 2, getHeight() / 2);
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
