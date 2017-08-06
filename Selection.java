import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Selection here.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Selection extends Actor {
    private boolean selectionStarted = false;
    
    private int selectionX1;
    private int selectionY1;
    private int selectionX2;
    private int selectionY2;
    
    public Selection(int width, int height) {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.GRAY);
        setImage(img);
    }
    
    @Override
    public void act() {
        if (!selectionStarted) {
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
        
        selectionX1 = info.getX();
        selectionY1 = info.getY();
        selectionStarted = true;
    }
    
    private void endSelection() {
        FractalWorld world = (FractalWorld) getWorld();
        int width = world.getWidth();
        int height = world.getHeight();
        
        double x1 = world.getCurrentX1();
        double y1 = world.getCurrentY1();
        double x2 = world.getCurrentX2();
        double y2 = world.getCurrentY2();
        
        double newX1 = selectionX1 * (x2 - x1) / width + x1;
        double newY1 = selectionY1 * (y2 - y1) / height + y1;
        double newX2 = selectionX2 * (x2 - x1) / width + x1;
        double newY2 = selectionY2 * (y2 - y1) / height + y1;
        
        world.drawFractal(newX1, newY1, newX2, newY2);
        clearSelection();
    }
    
    private void updateSelection() {
        FractalWorld world = (FractalWorld) getWorld();
        double ratio = world.getRatio();
        
        MouseInfo info = Greenfoot.getMouseInfo();
        int newX = info.getX();
        int newY = info.getY();
        
        // Ensure that aspect ratio is kept.
        if ((newX - selectionX1) * ratio > newY - selectionY1) {
            selectionX2 = newX;
            selectionY2 = (int) Math.round(
                    (newX - selectionX1) * ratio + selectionY1
            );
        } else {
            selectionX2 = (int) Math.round(
                    (newY - selectionY1) / ratio + selectionX1
            );
            selectionY2 = newY;
        }
        
        drawSelection(selectionX1, selectionY1, selectionX2, selectionY2);
    }
    
    private void drawSelection(int x1, int y1, int x2, int y2) {
        GreenfootImage img = getImage();
        img.clear();
        img.drawRect(x1, y1, x2 - x1, y2 - y1);
        
        // TODO Use 'img.setColor' and 'img.drawString' to print coordinates in corners.
    }
    
    private void clearSelection() {
        GreenfootImage img = getImage();
        img.clear();
        selectionStarted = false;
    }
}
