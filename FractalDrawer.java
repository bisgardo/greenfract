import greenfoot.Color;

/**
 * Class for drawing a fractal onto a Greenfoot image.
 * 
 * @author Michael Bisgaard Olesen
 */
public class FractalDrawer {
    
    public interface Canvas {
        int getWidth();
        int getHeight();
        void setValue(int x, int y, int value);
    }
    
    // TODO Make 'Selection' type with the coordinates.
    public static void draw(Fractal fractal, Canvas canvas, double x1, double y1, double x2, double y2) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        
        for (int i = 0; i < width; i++) {
            double temp1 = (x2 - x1) / width;
            double temp2 = (y2 - y1) / height;
            for (int j = 0; j < height; j++) {
                double x = i * temp1 + x1;
                double y = j * temp2 + y1;
                int val = fractal.getValue(x, y);
                canvas.setValue(i, j, val);
            }
        }
    }
}
