/**
 * Represenation of a selection.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Selection {
    private Pixel from = null;
    private Pixel to = null;
    
    private Pixel center = null;
    
    public static Selection from(Pixel origin) {
        Selection selection = new Selection();
        selection.from = origin;
        selection.to = origin;
        return selection;
    }
    
    public Pixel getFrom() {
        return from;
    }
    
    public Pixel getTo() {
        return to;
    }
    
    public void setCursor(int x, int y, double ratio, boolean translate, boolean expandFromCenter) {
        // TODO Clean up control flow of this method.
        Pixel oldTo = to;
        if (translate) {
            to = new Pixel(x, y);
            from = from.translated(x - oldTo.x, y - oldTo.y);
            center = null;
            return;
        }
        if (expandFromCenter) {
            if (center == null) {
                // Store center location to prevent flappy feedback loop.
                center = Pixel.midpointOf(from, to);
            }
            to = center.resolveTo(x, y, ratio);
            from = from.translated(oldTo.x - to.x, oldTo.y - to.y);
            return;
        }
        
        center = null;
        to = from.resolveTo(x, y, ratio);
    }
}
