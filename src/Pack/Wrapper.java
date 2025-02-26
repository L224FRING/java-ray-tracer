package Pack;

public class Wrapper {
	public Ray scattered;
	public Colour attenuation;
	public Vec3 refracted;
	public aabb box;
	
	public Wrapper()
	{
		scattered= new Ray();
		attenuation = new Colour();
		refracted = new Vec3();
		box = new aabb();
	}

}
