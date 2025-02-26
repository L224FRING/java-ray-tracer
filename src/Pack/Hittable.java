package Pack;

public abstract class Hittable {
    abstract boolean hit(Ray r, Interval ray_t, HitRecord rec);
    abstract aabb boundingBox();
    
    
}
