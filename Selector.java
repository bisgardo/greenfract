import greenfoot.*;

/**
 * An "actor" component overlay of the entire image that listens to
 * mouse events, draws a selection rectangle, and initiates a redraw
 * of the world when selection has completed.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Selector extends Actor {
    private Pixel from = null;
    private Pixel to = null;
    
    public Selector(int width, int height) {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.GRAY);
        setImage(img);
    }
    
    @Override
    public void act() {
        if (from == null) {
            if (Greenfoot.mouseClicked(this)) {
                beginSelection();
            }
        } else if (Greenfoot.mouseClicked(this)) {
            endSelection();
        } else if (Greenfoot.isKeyDown("escape")) {
            clearSelection();
        } else if (Greenfoot.mouseMoved(this)) {
            updateSelection();
        }
    }
    
    private void beginSelection() {
        MouseInfo info = Greenfoot.getMouseInfo();
        if (info == null) {
            System.err.println("Clicked outside world: " + getWorld());
            return;
        }
        
        from = new Pixel(info.getX(), info.getY());
    }
    
    private void endSelection() {
        FractalWorld world = (FractalWorld) getWorld();
        int width = world.getWidth();
        int height = world.getHeight();
        
        Area area = world.getArea();
        double x1 = area.xMin;
        double y1 = area.yMin;
        double x2 = area.xMax;
        double y2 = area.yMax;
        
        // TODO Make a method on Area.
        double newX1 = from.x * (x2 - x1) / width + x1;
        double newY1 = from.y * (y2 - y1) / height + y1;
        double newX2 = to.x * (x2 - x1) / width + x1;
        double newY2 = to.y * (y2 - y1) / height + y1;
        
        clearSelection();
        world.drawFractal(new Area(newX1, newX2, newY1, newY2));
    }
    
    private void updateSelection() {
        FractalWorld world = (FractalWorld) getWorld();
        double ratio = world.getRatio();
        
        MouseInfo mouse = Greenfoot.getMouseInfo();
        to = from.resolveTo(mouse.getX(), mouse.getY(), ratio);
        
        drawSelection();
    }
    
    private void drawSelection() {
        GreenfootImage img = getImage();
        img.clear();
        img.drawRect(from.x, from.y, to.x - from.x, to.y - from.y);
        
        // TODO Use 'img.setColor' and 'img.drawString' to print coordinates in corners.
    }
    
    private void clearSelection() {
        GreenfootImage img = getImage();
        img.clear();
        from = null;
        to = null;
    }
}
