/**
 * @author Michael Bisgaard Olesen
 */
public class SequentialFractalDrawer implements FractalDrawer {
    
	@Override
    public void draw(Fractal fractal, Canvas canvas, double x1, double y1, double x2, double y2) {
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
