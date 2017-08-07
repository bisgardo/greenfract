import greenfoot.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.text.DecimalFormat;

/**
 * Dummy actor ("savior"?) that listens to keystrokes and saves the current fractal image
 * to a file named by the canvas coordinates.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Saver extends Actor {
    private final File dir;
    
    public Saver(File dir) {
        this.dir = dir;
        
        if (dir.mkdirs()) {
            System.out.println(String.format("Created directory '%s'", dir));
        }
    }
    
    @Override
    public void act() {
        if (!Greenfoot.isKeyDown("s")) {
            return;
        }
        
        FractalWorld world = (FractalWorld) getWorld();
        File file = new File(dir, name(world) + ".png");
        save(dir, world);
        System.out.println("Saving to file " + file);
    }
    
    public static String name(FractalWorld world) {
        Area area = world.getArea();
        
        return String.format(
            "%s_%sx%s_%s",
            formatDecimal(area.xMin),
            formatDecimal(area.xMax),
            formatDecimal(area.yMin),
            formatDecimal(area.yMax)
        );
    }
    
    private static String formatDecimal(double value) {
        return String.format("%1.16f", value).replace('.', ',');
    }
    
    public static void save(File file, FractalWorld world) {
        try {
            BufferedImage image = world.getBackground().getAwtImage();
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
