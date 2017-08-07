import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World that acts as a sheet of paper on which we can draw pixels.
 * 
 * @author Michael Bisgaard Olesen
 */
public class FractalWorld extends World {
    private final Fractal fractal = new MandelbrotFractal();
    private final FractalDrawer drawer = new SequentialFractalDrawer();
    
    private double currentX1;
    private double currentY1;
    private double currentX2;
    private double currentY2;
    
    public FractalWorld() {
        super(800, 600, 1);
        
        int w = getWidth();
        int h = getHeight();
        
        addObject(new Selector(w, h), w / 2, h / 2);
        
        double xMin = -2.2;
        double xMax = 1.0;
        double yMin = -1.2;
        double yMax = 1.2;
        
        double r = getRatio();
        double dx = xMax - xMin;
        double dy = yMax - yMin;
        if (w > h) {
            double tmp = dy / (dx * r);
            xMin *= tmp;
            xMax *= tmp;
        } else {
            double tmp = dx * r / dy;
            yMin *= tmp;
            yMax *= tmp;
        }
        
        drawFractal(xMin, yMin, xMax, yMax);
        
        Greenfoot.setSpeed(50);
        
        // For some reason, after resetting we need to manually pause and
        // resume execution for click events to go through.
        Greenfoot.stop();
        
        Greenfoot.start();
    }
    
    public void drawFractal(double x1, double y1, double x2, double y2) {
        currentX1 = x1;
        currentY1 = y1;
        currentX2 = x2;
        currentY2 = y2;
        
        //long time = System.currentTimeMillis();
        drawer.draw(fractal, new FractalWorldCanvas(getBackground()), x1, y1, x2, y2);
        //System.out.println(String.format("Fractal drawn in %d ms", System.currentTimeMillis() - time));
        
        // TODO Use 'sheet.setColor' and 'sheet.drawString' to print coordinates in corners.
    }
    
    public double getCurrentX1() {
        return currentX1;
    }
    
    public double getCurrentY1() {
        return currentY1;
    }
    
    public double getCurrentX2() {
        return currentX2;
    }
    
    public double getCurrentY2() {
        return currentY2;
    }
    
    public double getRatio() {
        return 1.0 * getHeight() / getWidth();
    }
}
