import greenfoot.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Dummy actor ("savior"?) that listens to keystrokes and saves the current fractal image
 * to a file named by the canvas coordinates.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Saver extends Actor {
    private volatile boolean saving = false;
    
    private final File dir;
    
    public Saver(String dirName) {
        dir = new File(dirName);
        
        if (dir.mkdirs()) {
            System.out.println(String.format("Created directory '%s'", dir));
        }
    }
    
    @Override
    public synchronized void act() {
        FractalWorld world = (FractalWorld) getWorld();
        if (Greenfoot.isKeyDown("s") && !saving) {
            saving = true;
            
            Area area = world.getArea();
            BufferedImage image = world.getBackground().getAwtImage();
            
            String fileName = String.format(
                "%s_%s-%s_%s.png",
                formatDecimal(area.xMin),
                formatDecimal(area.xMax),
                formatDecimal(area.yMin),
                formatDecimal(area.yMax)
            );
            
            File file = new File(dir, fileName);
            try {
                ImageIO.write(image, "png", file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            System.out.println("Saving to file " + file);
            saving = false;
        }
    }
    
    private static String formatDecimal(double value) {
        return String.valueOf(value).replace('.', ',');
    }
}
