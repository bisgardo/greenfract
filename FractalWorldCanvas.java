import greenfoot.*;

/**
 * Canvas implementation for the Greenfoot Fractal World.
 * 
 * @author Michael Bisgaard Olesen
 */
public class FractalWorldCanvas implements FractalDrawer.Canvas {
    private static final ColorResolver COLOR_RESOLVER = new DefaultColorResolver();
    
    private final GreenfootImage sheet;
    
    public FractalWorldCanvas(GreenfootImage sheet) {
        this.sheet = sheet;
    }
    
    @Override
    public int getWidth() {
        return sheet.getWidth();
    }
    
    @Override    
    public int getHeight() {
        return sheet.getHeight();
    }
    
    @Override
    public void setValue(int x, int y, int value) {
        Color color = COLOR_RESOLVER.resolveColor(value);
        sheet.setColorAt(x, y, color);
    }
}
