/**
 * Class for computing pixel values of the Mandelbrot fractal.
 * 
 * @author Michael Bisgaard Olesen
 */
public class MandelbrotFractal implements Fractal {
    
    @Override
    public int getValue(double x, double y) {
        double z_re = x;
        double z_im = y;
        
        double z_re2 = z_re*z_re;
        double z_im2 = z_im*z_im;
        
        int iterations = 1;
        while (needsMoreIterations(iterations) && isInCircle(z_re2, z_im2)) {
            double tmp = 2*z_re*z_im;
            z_re = (z_re2 - z_im2) + x;
            z_im = tmp + y;
            
            // Update squared components.
            z_re2 = z_re*z_re;
            z_im2 = z_im*z_im;
            
            iterations++;
        }
        
        return iterations;
    }
    
    private static boolean needsMoreIterations(int iterations) {
        return iterations < Settings.MAX_ITERATIONS;
    }
    
    private static boolean isInCircle(double z_re2, double z_im2) {
        return z_re2 + z_im2 <= Settings.BOUND_RADIUS_SQUARED;
    }
}
