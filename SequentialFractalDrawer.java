/**
 * @author Michael Bisgaard Olesen
 */
public class SequentialFractalDrawer implements FractalDrawer {
    
	@Override
    public void draw(Fractal fractal, Canvas canvas, Area area) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        
        double xMin = area.xMin;
        double xMax = area.xMax;
        double yMin = area.yMin;
        double yMax = area.yMax;
        
        for (int i = 0; i < width; i++) {
            double temp1 = (xMax - xMin) / width;
            double temp2 = (yMax - yMin) / height;
            for (int j = 0; j < height; j++) {
                double x = i * temp1 + xMin;
                double y = j * temp2 + yMin;
                int val = fractal.getValue(x, y);
                canvas.setValue(i, j, val);
            }
        }
    }
}
