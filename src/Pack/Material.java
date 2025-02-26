package Pack;

public abstract class Material {
	
	public abstract boolean scatter(Ray r_in,HitRecord rec,Wrapper wrapper);

}

