package Pack;

import java.util.ArrayList;
import java.util.List;

public class HittableList extends Hittable {
    private List<Hittable> objects;
    private aabb bbox;
    public HittableList() {
        objects = new ArrayList<>();
    
    }
    
    public HittableList(List<Hittable> objects)
    {
    	this.objects=objects;
    }

    public HittableList(Hittable object) {
        objects = new ArrayList<>();
        add(object);
    }

    public void clear() {
        objects.clear();
      
    }

    public void add(Hittable object) {
        objects.add(object);
        if(bbox!= null)
        {
        	bbox=new aabb(bbox,object.boundingBox());
        	
        }
        else
        {
        	bbox=object.boundingBox();
        }
        
        
    }
    
    public List<Hittable> getObjects() {
        return objects;
    }

    @Override
    public boolean hit(Ray r, Interval ray_t, HitRecord rec) {
        HitRecord temp_rec = new HitRecord();
        boolean hitAnything = false;
        double closest_so_far = ray_t.max;

        for (Hittable object : objects) {
            if (object.hit(r, new Interval(ray_t.min, closest_so_far), temp_rec)) {
                hitAnything = true;
                closest_so_far = temp_rec.t;
                rec.p = temp_rec.p;
                rec.normal = temp_rec.normal;
                rec.t = temp_rec.t;
                rec.frontFace = temp_rec.frontFace;
                rec.mat=temp_rec.mat;
            }
        }

        return hitAnything;
    }

	@Override
	public aabb boundingBox() {
		
		return bbox;
		
	}
}

