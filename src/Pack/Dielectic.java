package Pack;

public class Dielectic extends Material{

    double ref_idx; //ref_idx是指光密介质的折射指数和光疏介质的折射指数的比值

    public Dielectic() {
    }
    public Dielectic(double ref_idx) {
        this.ref_idx = ref_idx;
    }

    @Override
    public boolean scatter(Ray r, HitRecord rec, Wrapper wrapper) {
        Vec3 outward_normal;    //入射时的法向量
        Vec3 reflected = reflect(r.direction(), rec.normal);
        double ni_over_nt; //sin_a2 / sin_a1 折射介质的折射指数和入射介质的入射指数的比值
        double  reflect_prob; //反射系数
        double cosine;
        wrapper.attenuation = new Colour(1,1,1);


        if (Vec3.dot(r.direction(),(rec.normal)) > 0) {
            outward_normal = rec.normal.multiply(-1);
            ni_over_nt = ref_idx;
            cosine = Vec3.dot(r.direction(),(rec.normal)) / ( r.direction().length() * rec.normal.length() );      //入射角余弦
        }
        else {
            outward_normal = rec.normal;
            ni_over_nt = 1.0f / ref_idx;
            cosine = - Vec3.dot(r.direction(),(rec.normal)) / ( r.direction().length() * rec.normal.length() );      //入射角余弦
        }
        if (refract(r.direction(), outward_normal, ni_over_nt, wrapper)) {
            reflect_prob = schlick(cosine, ref_idx);
        }
        else {
          
            reflect_prob = 1.0f;
       
        }
       
        if (Math.random() < reflect_prob) {
            wrapper.scattered = new Ray(rec.p, reflected);
        }
        else {
            wrapper.scattered = new Ray(rec.p, wrapper.refracted);
        }
        return true;
    }


    public boolean refract(Vec3 v, Vec3 n, double nt, Wrapper wrapper){
        Vec3 uv = Vec3.unitVector(v);
        double cos_a1 = -1.0f * Vec3.dot(uv,n);
        double temp = 1.0f - nt*nt*(1.0f-cos_a1*cos_a1);
        if(temp > 0.0f){
            wrapper.refracted = uv.multiply(nt).add(n.multiply((float)(nt*cos_a1 - Math.sqrt(temp))));
            return true;
        }
        else {
            return false;
        }
    }

  
    Vec3 reflect(Vec3 v, Vec3 n)
    {
        return v.subtract(n.multiply(2*Vec3.dot(v,n)));
        //return v - 2 * dot(v, n)*n;
    }

   
    double schlick(double cosine, double ref_idx) {
        double r0 = (1-ref_idx) / (1+ref_idx);
        r0 = r0*r0;
        return (r0 + (1-r0)*Math.pow((1 - cosine),5));
    }
}