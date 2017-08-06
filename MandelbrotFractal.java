/**
 * Class for computing pixel values of the Mandelbrot fractal.
 * 
 * @author Michael Bisgaard Olesen
 */
public class MandelbrotFractal implements Fractal {
    
    @Override
    public int getValue(double x, double y) {
        double z_re = 0, z_im = 0;
        double c_re = x, c_im = y;
        
        int iterations = 0;
        
        double z_re2 = z_re*z_re;
        double z_im2 = z_im*z_im;
        
        while (needsMoreIterations(iterations) && isInCircle(z_re2, z_im2)) {
            double tmp = 2*z_re*z_im;
            z_re = (z_re2 - z_im2) + c_re;
            z_im = tmp + c_im;
            
            // Update squared components.
            z_re2 = z_re*z_re;
            z_im2 = z_im*z_im;
            
            iterations++;
        }
        
        return iterations;
    }
    
    private boolean needsMoreIterations(int iterations) {
        return iterations < Settings.MAX_ITERATIONS;
    }
    
    private boolean isInCircle(double z_re2, double z_im2) {
        return z_re2 + z_im2 <= Settings.BOUND_RADIUS_SQUARED;
    }
}
