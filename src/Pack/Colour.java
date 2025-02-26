package Pack;

import java.util.Random;

public class Colour extends Vec3 {
	
	public Colour() {
		this.e[0]=0;
		this.e[1]=0;
		this.e[2]=0;
	}
	
	 public Colour(double d, double e, double f) {
		super(d,e,f);
	}

	 public double linearToGamma(double linearComponent)
	 {
		 if(linearComponent>0)
			 return Math.sqrt(linearComponent);
		 return 0;
	 }
	 public String writeColor() {
	        double r = this.x();
	        double g = this.y();
	        double b = this.z();
	        
	        r=linearToGamma(r);
	        g=linearToGamma(g);
	        b=linearToGamma(b);
	        Interval intensity = new Interval(0.000,0.999);

	        // Translate the [0,1] component values to the byte 	range [0,255].
	        int rByte = (int) (255.999 * intensity.clamp(r));
	        int gByte = (int) (255.999 * intensity.clamp(g));
	        int bByte = (int) (255.999 * intensity.clamp(b));

	        // Write out the pixel color components.
	        return (rByte + " " + gByte + " " + bByte+"\n");
	    }
	public Colour add(Colour c) {
        return new Colour(e[0] + c.e[0], e[1] + c.e[1], e[2] + c.e[2]);
    }

    public Colour subtract(Colour c) {
        return new Colour(e[0] - c.e[0], e[1] - c.e[1], e[2] - c.e[2]);
    }

    public Colour multiply(double t) {
        return new Colour(e[0] * t, e[1] * t, e[2] * t);
    }
    
    public Colour multiply(Colour r) {
        return new Colour(e[0] * r.e[0], e[1] * r.e[1], e[2] * r.e[2]);
    }
     

    public Colour divide(double t) {
        return this.multiply(1 / t);
    }
    
    public static Colour random()
    {
    	Random r = new Random();
    	return new Colour(r.nextDouble(),r.nextDouble(),r.nextDouble());
    }
    
    public static Colour random(double min,double max)
    {
    	double factor=min+(max-min);
    	Random r = new Random();
    	return new Colour(factor*r.nextDouble(),factor*r.nextDouble(),factor*r.nextDouble());
    }

}
