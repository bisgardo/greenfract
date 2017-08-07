import greenfoot.*;

/**
 * An "actor" component overlay of the entire image that listens to
 * mouse events, draws a selection rectangle, and initiates a redraw
 * of the world when selection has completed.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Selector extends Actor {
    private Selection selection;
    
    public Selector(int width, int height) {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.GRAY);
        setImage(img);
    }
    
    @Override
    public void act() {
        if (selection == null) {
            if (Greenfoot.mouseClicked(this)) {
                beginSelection();
            }
        } else if (Greenfoot.mouseClicked(this)) {
            endSelection();
        } else if (Greenfoot.isKeyDown("escape")) {
            clearSelection();
        } else {
            updateSelection();
        }
    }
    
    private void beginSelection() {
        MouseInfo info = Greenfoot.getMouseInfo();
        if (info == null) {
            System.err.println("Clicked outside world: " + getWorld());
            return;
        }
        
        selection = Selection.from(new Pixel(info.getX(), info.getY()));
    }
    
    private void endSelection() {
        FractalWorld world = (FractalWorld) getWorld();
        int w = world.getWidth();
        int h = world.getHeight();
        
        Area area = world.getArea();
        Pixel from = selection.getFrom();
        Pixel to = selection.getTo();
        
        clearSelection();
        world.drawFractal(area.subArea(from, to, w, h));
    }
    
    private void updateSelection() {
        boolean ctrl = Greenfoot.isKeyDown("control");
        boolean shift = Greenfoot.isKeyDown("shift");
        if (!Greenfoot.mouseMoved(this) && !ctrl && !shift) {
            return;
        }
        
        FractalWorld world = (FractalWorld) getWorld();
        double ratio = world.getRatio();
        
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) {
            return;
        }
        
        int x = mouse.getX();
        int y = mouse.getY();
        
        selection.setCursor(x, y, ratio, shift, ctrl);
        
        drawSelection();
    }
    
    private void drawSelection() {
        GreenfootImage img = getImage();
        img.clear();
        
        Pixel from = selection.getFrom();
        Pixel to = selection.getTo();
        img.drawRect(from.x, from.y, to.x - from.x, to.y - from.y);
        
        // TODO Use 'img.setColor' and 'img.drawString' to print coordinates in corners.
    }
    
    private void clearSelection() {
        GreenfootImage img = getImage();
        img.clear();
        selection = null;
    }
}
