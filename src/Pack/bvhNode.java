package Pack;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class bvhNode extends Hittable {
    Hittable left;
    Hittable right;
    aabb bbox;
    
    public bvhNode(HittableList list) {
    	this(list.getObjects(),0,list.getObjects().size());
    	
    }

    public bvhNode(List<Hittable> objects, int start, int end) {
    	Random random=new Random();
 
    	int axis =  (int) (2*random.nextDouble());

        Comparator<Hittable> comparator = (axis == 0) ? new Box_x_compare() 
                : (axis == 1) ? new Box_y_compare() 
                : new Box_z_compare();
        int objectSpan = end - start;

        if (objectSpan == 1) {
            left = right = objects.get(start);
        } else if (objectSpan == 2) {
            left = objects.get(start);
            right = objects.get(start + 1);
        } else {
            Collections.sort(objects.subList(start, end), comparator);
            int mid = start + objectSpan / 2;
            left = new bvhNode(objects, start, mid);
            right = new bvhNode(objects, mid, end);
        }

        bbox=new aabb(left.boundingBox(),right.boundingBox());
    }
    
    class Box_x_compare implements Comparator<Hittable> {
        public int compare(Hittable a, Hittable b) {
            return box_compare(a, b, 0);
        }
    }

    class Box_y_compare implements Comparator<Hittable> {
        public int compare(Hittable a, Hittable b) {
            return box_compare(a, b, 1);
        }
    }

    class Box_z_compare implements Comparator<Hittable> {
        public int compare(Hittable a, Hittable b) {
            return box_compare(a, b, 2);
        }
    }

    private int box_compare(Hittable a, Hittable b, int axis) {
       Interval aaxisinterval=a.boundingBox().axisInterval(axis);
       Interval baxisinterval=b.boundingBox().axisInterval(axis);
       return aaxisinterval.min<baxisinterval.min?-1:1;
    }

    @Override
    public boolean hit(Ray r, Interval t, HitRecord rec) {
    	
        if(!bbox.hit(r, t)) {
        
           return false;
        }
       
        boolean hitLeft=left.hit(r, t, rec);
        
        boolean hitRight=right.hit(r,new Interval(t.min,hitLeft?rec.t:t.max),rec);
        
        
        return hitLeft || hitRight;
    }	

    @Override
    public aabb boundingBox() {
        return bbox;
    }

    
}
