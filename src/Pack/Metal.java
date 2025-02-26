package Pack;

public class Metal extends Material{
	
	private Colour albedo;
	private double fuzz;

    public Metal(Colour albedo,double fuzz) {
    	
       this.albedo=albedo;
       this.fuzz=fuzz;
    }

	public boolean scatter(Ray r_in, HitRecord rec, Wrapper wrapper) {
		
		Vec3 reflected = Vec3.reflect(r_in.direction(), rec.normal);
		reflected = Vec3.unitVector(reflected).add(Vec3.randomUnitVector().multiply(fuzz));
        wrapper.scattered = new Ray(rec.p, reflected);
        wrapper.attenuation=albedo;
        return Vec3.dot(wrapper.scattered.direction(), rec.normal)>0;

	}
	
	
}
