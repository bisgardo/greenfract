/**
 * Class for drawing a fractal onto a Greenfoot image.
 * 
 * @author Michael Bisgaard Olesen
 */
public interface FractalDrawer {
    
    interface Canvas {
        int getWidth();
        int getHeight();
        void setValue(int x, int y, int value);
    }
    
    // TODO Make 'Selection' type with the coordinates.
    void draw(Fractal fractal, Canvas canvas, double x1, double y1, double x2, double y2);
}
