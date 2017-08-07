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
    
    public static Pixel midpointOf(Pixel from, Pixel to) {
        return new Pixel(
            (int) Math.round((from.x + to.x) / 2.0),
            (int) Math.round((from.y + to.y) / 2.0)
        );
    }
    
    public Pixel translated(int xDiff, int yDiff) {
        return new Pixel(x + xDiff, y + yDiff);
    }
    
    public Pixel resolveTo(int xMax, int yMax, double ratio) {
        // Ensure that aspect ratio is kept.
        int xDiff = xMax - x;
        int yDiff = yMax - y;
        if (xDiff * ratio > yDiff) {
            yMax = (int) Math.round(xDiff * ratio + y);
        } else {
            xMax = (int) Math.round(yDiff / ratio + x);
        }
        return new Pixel(xMax, yMax);
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
