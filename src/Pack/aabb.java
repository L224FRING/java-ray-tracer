package Pack;

public class aabb {
	
	Interval x;
    Interval y;
    Interval z;
	
	public aabb() {
		
	}
	
	public aabb(Interval x,Interval y,Interval z) {
		this.x = x;
        this.y = y;
        this.z = z;
	}
	
	public aabb(Vec3 a,Vec3 b)
	{
		this.x = (a.e[0]<=b.e[0])? new Interval(a.x(),b.x()):new Interval(b.x(),a.x());
		this.y = (a.e[1]<=b.e[1])? new Interval(a.y(),b.y()):new Interval(b.y(),a.y());       
		this.z = (a.e[2]<=b.e[2])? new Interval(a.z(),b.z()):new Interval(b.z(),a.z());    }
	
	public aabb(aabb box0,aabb box1)
	{
		
		this.x=new Interval(box0.x,box1.y);
		this.y=new Interval(box0.y,box1.y);
		this.z=new Interval(box0.z,box1.z);
	}
	
	public Interval axisInterval(int n) {
        switch (n) {
            case 1:
                return y;
            case 2:
                return z;
            default:
                return x;
                
        }
        }
	 public boolean hit(Ray r, Interval rayT) {
		 
		 

	        Vec3 rayDir = r.direction();
	        Vec3 rayOrig=r.origin();

	        for (int axis = 0; axis < 3; axis++) {
	            Interval ax = axisInterval(axis);
	            double adinv = 1.0 / rayDir.e[axis];

	            double t0 = (ax.min - rayOrig.get(axis)) * adinv;
	            double t1 = (ax.max - rayOrig.get(axis)) * adinv;

	            if(adinv < 0.0f){    
	                double temp = t0;
	                t0 = t1;
	                t1 = temp;
	            }
	            rayT.min = t0>rayT.min ? t0:rayT.min;   
	            rayT.max = t1<rayT.max ? t1:rayT.max;   
	            if(rayT.max <= rayT.min){
	                return false;
	            }}
	             return true;
	       }
	
	 }


