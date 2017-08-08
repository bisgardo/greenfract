import greenfoot.*;
import java.io.File;

/**
 * Dummy actor that listens to keystrokes and starts an animation to
 * zoom down to the current level from the top level.
 * 
 * @author Michael Bisgaard Olesen
 */
public class Animator extends Actor {
    private Area target = null;
    private File saveDir;
    private int index;
    
    @Override
    public void act() {
        FractalWorld world = (FractalWorld) getWorld();
        Area area = world.getArea();
        
        if (target == null) {
            if (Greenfoot.isKeyDown("a")) {
                target = area;
                
                index = 0;
                saveDir = new File(Saver.name(world));
                if (saveDir.mkdirs()) {
                    System.out.println(String.format("Created directory '%s'", saveDir));
                }
                
                world.reset();
            }
            return;
        }
        
        // Move animation one step closer to target.
        double dxLeft = target.xMin - area.xMin;
        double dyTop = target.yMin - area.yMin;
        double dxRight = area.xMax - target.xMax;
        double dyBottom = area.yMax - target.yMax;
        
        Area nextArea = new Area(
            area.xMin + dxLeft / Settings.ANIMATION_RESOLUTION,
            area.xMax - dxRight / Settings.ANIMATION_RESOLUTION,
            area.yMin + dyTop / Settings.ANIMATION_RESOLUTION,
            area.yMax - dyBottom / Settings.ANIMATION_RESOLUTION
        );
        world.drawFractal(nextArea);
        
        if (saveDir != null) {
            Saver.save(new File(saveDir, String.format("%04d.png", index++)), world);
        }
        
        if (zero(dxLeft) || zero(dxLeft) || zero(dxLeft) || zero(dxLeft)) {
            target = null;
            // TODO Can images be streamed into ffmpeg one at a time, as they're produced?
            System.out.println(
                String.format(
                    "Run a command like '%s' to combine pictures into a video",
                    "ffmpeg -i %04d.png -c:v libx264 -preset veryslow -crf 0 output.mkv"
                )
            );
        }
    }
    
    private static boolean zero(double value) {
        return value < 1e-10;
    }
}
