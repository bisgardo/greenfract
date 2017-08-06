import greenfoot.Color;

/**
 * Default implementation of the {@link ColorResolver} interface.
 * 
 * @author Michael Bisgaard Olesen.
 */
public class DefaultColorResolver implements ColorResolver {
    
    @Override
    public Color resolveColor(int val) {
        float frac = 1.0f * val / Settings.MAX_ITERATIONS;
        float hue = 1.0f - frac;
        // TODO Should use java.awt.Color.HSBtoRGB().
        java.awt.Color c = java.awt.Color.getHSBColor(-hue, hue, hue);
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }
}
