/**
 * Value class for representing the coordinates of a pixel.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Pixel {
    public final int x;
    public final int y;
    
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Pixel resolveTo(int xMax, int yMax, double ratio) {
        // Ensure that aspect ratio is kept.
        if ((xMax - x) * ratio > yMax - y) {
            yMax = (int) Math.round((xMax - x) * ratio + y);
        } else {
            xMax = (int) Math.round((yMax - y) / ratio + x);
        }
        return new Pixel(xMax, yMax);
    }
}
