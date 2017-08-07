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
                world.reset();
                
                saveDir = new File(Saver.name(world));
                if (saveDir.mkdirs()) {
                    System.out.println(String.format("Created directory '%s'", saveDir));
                }
            }
            return;
        }
        
        // Move animation one step closer to target.
        double dxLeft = target.xMin - area.xMin;
        double dyTop = target.yMin - area.yMin;
        double dxRight = area.xMax - target.xMax;
        double dyBottom = area.yMax - target.yMax;
        
        Area nextArea = new Area(
            area.xMin + dxLeft / 20,
            area.xMax - dxRight / 20,
            area.yMin + dyTop / 20,
            area.yMax - dyBottom / 20
        );
        world.drawFractal(nextArea);
        
        if (saveDir != null) {
            Saver.save(new File(saveDir, String.format("%04d.png", index++)), world);
        }
        
        if (zero(dxLeft) || zero(dxLeft) || zero(dxLeft) || zero(dxLeft)) {
            target = null;
        }
    }
    
    private static boolean zero(double value) {
        return value < 1e-10;
    }
}
