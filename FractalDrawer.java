/**
 * Interface for class that can draw a fractal onto a canvas.
 * 
 * @author Michael Bisgaard Olesen
 */
public interface FractalDrawer {
    
    interface Canvas {
        int getWidth();
        int getHeight();
        void setValue(int x, int y, int value);
    }
    
    void draw(Fractal fractal, Canvas canvas, Area area);
}
