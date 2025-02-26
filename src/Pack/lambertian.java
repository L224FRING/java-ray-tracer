package Pack;

public class lambertian extends Material {
	
	private Colour albedo;

    public lambertian(Colour albedo) {	
 
        this.albedo = albedo;
    }
    
    public boolean scatter(Ray r_in,HitRecord rec,Wrapper wrapper)
    {
    	
    	Vec3 scatterDirection = rec.normal.add(Vec3.randomUnitVector());
    	if(scatterDirection.nearZero())
    		scatterDirection=rec.normal;
    	wrapper.scattered=new Ray(rec.p,scatterDirection);
    	wrapper.attenuation=albedo;
    	return true;
    }

    
}
