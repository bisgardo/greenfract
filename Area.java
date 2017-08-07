/**
 * Value class for representing a rendered area as a range of coordinate values.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Area {
    public final double xMin;
    public final double xMax;
    public final double yMin;
    public final double yMax;
    
    public Area(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
    
    public Area subArea(Pixel from, Pixel to, int width, int height) {
        double dx = xMax - xMin;
        double dy = yMax - yMin;
        
        return new Area(
            from.x * dx / width + xMin,
            to.x * dx / width + xMin,
            from.y * dy / height + yMin,
            to.y * dy / height + yMin
        );
    }
    
    @Override
    public String toString() {
        return String.format("[%f, %f]x[%f, %f]", xMin, xMax, yMin, yMax);
    }
}
