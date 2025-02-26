package Pack;

public class Ray {
    public Vec3 origin;
    public Vec3 direction;

    public Ray() {
        this.origin = new Vec3();
        this.direction = new Vec3();
    }

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vec3 origin() {
        return origin;
    }

    public Vec3 direction() {
        return direction;
    }

    public Vec3 at(double t) {
        return origin.add(direction.multiply(t));
    }
}
