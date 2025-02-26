package Pack;

public class Sphere extends Hittable {
    private Vec3 center;
    private double radius;
    private Material mat;
    aabb bbox;

    public Sphere(Vec3 center, double radius, Material mat) {
        this.center = center;
        this.radius = Math.max(0, radius);
        this.mat=mat;
        Vec3 rvec= new Vec3(radius,radius,radius);
        bbox = new aabb(this.center.subtract(rvec),this.center.add(rvec));
    }

    @Override
    public boolean hit(Ray r, Interval ray_t, HitRecord rec) {
    	
        Vec3 oc = center.subtract(r.origin());
        double a = r.direction().lengthSquared();
        double h = Vec3.dot(r.direction, oc);
        double c = oc.lengthSquared() - radius * radius;

        double discriminant = h * h - a * c;
        if (discriminant < 0)
            return false;

        double sqrtd = Math.sqrt(discriminant);

        // Find the nearest root that lies in the acceptable range.
        double root = (h - sqrtd) / a;
        if (!ray_t.surrounds(root)) {
            root = (h + sqrtd) / a;
            if (!ray_t.surrounds(root))
                return false;
        }

        rec.t = root;
        rec.p = r.at(rec.t);
        Vec3 outwardNormal= (rec.p.subtract(center)).divide(radius);
        rec.setFaceNormal(r, outwardNormal);
        rec.mat=mat;
 

        return true;
    }

	
	

	@Override
	aabb boundingBox() {
		
		return bbox;
	}
}