package Pack;

import java.lang.Math;
import java.util.*;

;
//Constants
public final class RTWeekend {
	
	
	public static double randomDouble(double min, double max) 
	{
		
		Random random= new Random();
	    
	    return min + (max - min) * random.nextDouble();
	}
 public static final double infinity = Double.POSITIVE_INFINITY;
 public static final double pi = Math.PI;

 private RTWeekend() {}  // Private constructor to prevent instantiation

 // Utility function
 public static double degreesToRadians(double degrees) {
     return degrees * Math.PI / 180.0;
 }
}