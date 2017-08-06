import greenfoot.Color;

/**
 * Delegator that maps the input value to a corresponding color for drawing.
 * This input value is usually the number of iterations before some condition
 * is satisfied.
 * 
 * @author Michael Bisgaard Olesen
 */
public interface ColorResolver {
    
    Color resolveColor(int value);
}
