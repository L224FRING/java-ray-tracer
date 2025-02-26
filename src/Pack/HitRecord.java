package Pack;


public class HitRecord {
    public Vec3 p;
    public Vec3 normal;
    public double t;
    boolean frontFace;
    public Material mat;
    
    void setFaceNormal(Ray r,Vec3 outwardNormal) {
    	frontFace=Vec3.dot(r.direction,outwardNormal)<0;
    	normal = frontFace ? outwardNormal : Vec3.Negate(outwardNormal);
    }
    
    public void copyFrom(HitRecord other) {
        this.t = other.t;
        this.p = other.p;
        this.normal = other.normal;
        this.mat = other.mat;
    }
    
}


