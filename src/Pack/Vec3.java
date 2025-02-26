package Pack;

import java.util.Random;

public class Vec3 {
    public double[] e;

    public Vec3() {
        this.e = new double[]{0, 0, 0};
    }

    public Vec3(double e0, double e1, double e2) {
        this.e = new double[]{e0, e1, e2};
    }

    public double x() {
        return e[0];
    }

    public double y() {
        return e[1];
    }

    public double z() {
        return e[2];
    }

    public Vec3 negated() {
        return new Vec3(-e[0], -e[1], -e[2]);
    }

    public double get(int i) {
        return e[i];
    }

    public void set(int i, double value) {
        e[i] = value;
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }
    
    public Colour add(Colour v) {
        return new Colour(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }

    public Vec3 subtract(Vec3 v) {
        return new Vec3(e[0] - v.e[0], e[1] - v.e[1], e[2] - v.e[2]);
    }

    public Vec3 multiply(double t) {
        return new Vec3(e[0] * t, e[1] * t, e[2] * t);
    }
    

    public Vec3 divide(double t) {
        return this.multiply(1 / t);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
        return e[0] * e[0] + e[1] * e[1] + e[2] * e[2];
    }

    @Override
    public String toString() {
        return e[0] + " " + e[1] + " " + e[2];
    }

    public static Vec3 add(Vec3 u, Vec3 v) {
        return u.add(v);
    }

    public static Vec3 subtract(Vec3 u, Vec3 v) {
        return u.subtract(v);
    }

    public static Vec3 multiply(Vec3 u, double t) {
        return u.multiply(t);
    }

    public static Vec3 divide(Vec3 u, double t) {
        return u.divide(t);
    }

    public static double dot(Vec3 u, Vec3 v) {
        return u.e[0] * v.e[0] + u.e[1] * v.e[1] + u.e[2] * v.e[2];
    }

    public static Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
                        u.e[2] * v.e[0] - u.e[0] * v.e[2],
                        u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.divide(v.length());
    }
    
    public static Vec3 Negate(Vec3 v) {
    	return new Vec3(v.e[0],v.e[1],v.e[2]);
    }
    
    public static Vec3 random()
    {
    	Random r = new Random();
    	return new Vec3(r.nextDouble(),r.nextDouble(),r.nextDouble());
    }
    
    public static Vec3 random(double min,double max)
    {
    	double factor=min+(max-min);
    	Random r = new Random();
    	return new Vec3(factor*r.nextDouble(),factor*r.nextDouble(),factor*r.nextDouble());
    }
    
    public static Vec3 randomInUnitSphere() {
    	while(true)
    	{
    		Vec3 p = random(-1,1);
    		if(p.lengthSquared()<1)
    		{
    			return p;
    		}
    	}
    }
    
    public static Vec3 randomUnitVector()
    {
    	return unitVector(randomInUnitSphere());
    }
    
    public static Vec3 randomOnHemisphere(Vec3 normal)
    {
    	Vec3 onUnitSphere = randomUnitVector();
    	if(dot(onUnitSphere,normal)>0)
    	{
    		return onUnitSphere;
    	}
    	else
    	{
    		return onUnitSphere.negated();
    	}
    }
    
    public boolean nearZero() {
        double s = 1e-8;
        return (Math.abs(e[0]) < s) && (Math.abs(e[1]) < s) && (Math.abs(e[2]) < s);
    }
    
    public static Vec3 reflect(Vec3 v,Vec3 n)
    {
    	return v.subtract(n.multiply(dot(v,n)*2));
    }
    
    public static Vec3 refract(Vec3 uv, Vec3 n, double etaiOverEtat) {
        double cosTheta = Math.min(dot(uv.negated(),n), 1.0);
        Vec3 rOutPerp = (uv.add(n.multiply(cosTheta))).multiply(etaiOverEtat);
        Vec3 rOutParallel = n.multiply(-Math.sqrt(Math.abs(1.0 - rOutPerp.lengthSquared())));
        return rOutPerp.add(rOutParallel);
    }
}
    


