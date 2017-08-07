import greenfoot.*;

/**
 * World that acts as a sheet of paper on which we can draw pixels.
 * 
 * @author Michael Bisgaard Olesen
 */
public class FractalWorld extends World {
    private final Fractal fractal = new MandelbrotFractal();
    private final FractalDrawer drawer = new SequentialFractalDrawer();
    
    private Area area;
    
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
        
        drawFractal(new Area(xMin, xMax, yMin, yMax));
        
        Greenfoot.setSpeed(50);
        
        // For some reason, after resetting we need to manually pause and
        // resume execution for click events to go through.
        Greenfoot.stop();
        
        Greenfoot.start();
    }
    
    public void drawFractal(Area area) {
    	// TODO Might want to compute/return area that is guaranteed to have correct aspect ratio.
    	this.area = area;
    	
        //long time = System.currentTimeMillis();
        drawer.draw(fractal, new FractalWorldCanvas(getBackground()), area);
        //System.out.println(String.format("Fractal drawn in %d ms", System.currentTimeMillis() - time));
        
        // TODO Use 'sheet.setColor' and 'sheet.drawString' to print coordinates in corners.
    }
    
    public Area getArea() {
        return area;
    }
    
    public double getRatio() {
        return 1.0 * getHeight() / getWidth();
    }
}
