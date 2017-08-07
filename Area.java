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
}
