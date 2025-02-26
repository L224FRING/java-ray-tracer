package Pack;

public class Interval {
    public double min, max;

    public Interval() {
        this.min = RTWeekend.infinity;
        this.max = -RTWeekend.infinity;
    }

    public Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public Interval(Interval a,Interval b)
    {
    	
    	min=Math.min(a.min(), b.min());
    	max=Math.max(a.max(), b.max());
    }
    
    public double size() {
        return max - min;
    }
    
    public double min()
    {
    	return min;
    }
    
    public double max()
    {
    	return max;
    }
    
    public boolean contains(double x) {
        return min <= x && x <= max;
    }

    public boolean surrounds(double x) {
        return min < x && x < max;
    }
    
    double clamp(double x)  {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
    
    public Interval expand(double delta)
    {
    	double padding = delta/2;
    	return new Interval(min-padding,max+padding);
    }

    public static final Interval empty = new Interval(RTWeekend.infinity, -RTWeekend.infinity);
    public static final Interval universe = new Interval(-RTWeekend.infinity, RTWeekend.infinity);
}
