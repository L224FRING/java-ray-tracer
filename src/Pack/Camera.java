package Pack;

import java.io.*;
import java.util.Random;

public class Camera {
	
	double aspectRatio = 16.0 / 9.0;
    int imageWidth = 400;
    
    private int imageHeight;
    private Vec3 cameraCenter;
    private Vec3 pixel00Loc;
    private Vec3 pixelDeltaU;
    private Vec3 pixelDeltaV;
    int  samplesPerPixel =10;  
    private double pixelSampleScale;
    int maxDepth=10;
    Random r = new Random();
    double vfov=90;
    Vec3 lookfrom= new Vec3();
    Vec3 lookAt=new Vec3(0,0,-1);
    Vec3 vup=new Vec3(0,1,0);
    private Vec3 u, v, w;
    
    void render(Hittable World)
    {
    	Initialize();
    	
    	String fileName = "output.ppm";
    	
        // Render
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int j = 0; j < imageHeight; j++) {
             	System.out.println("Scan Line Rendered: "+(j+1));
                for (int i = 0; i < imageWidth; i++) {
                	 Colour pixelColor= new Colour(0,0,0);
                	 for(int sample=0;sample<samplesPerPixel;sample++)
                	 {
                		 Ray R=getRay(i,j);
                		 pixelColor=pixelColor.add(RayColor(R,maxDepth,World));
                	 }
                    writer.write(pixelColor.multiply(pixelSampleScale).writeColor());
                }
            }

            writer.close();
            System.out.println("PPM file created: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    void Initialize()
    {
    	imageHeight = Math.max(1, (int) (imageWidth / aspectRatio));
    	
    	pixelSampleScale=1.0/samplesPerPixel;
    	
    	cameraCenter = lookfrom;
    	
    	double focalLength = (lookfrom.subtract(lookAt)).length();
    	double theta =Math.toRadians(vfov);
    	double h=Math.tan(theta/2);
        double viewportHeight = 2*h*focalLength;
        double viewportWidth = viewportHeight * (double) imageWidth / imageHeight;
        
        w=Vec3.unitVector(lookfrom.subtract(lookAt));
        u=Vec3.unitVector(Vec3.cross(vup,w));
        v=Vec3.cross(w, u);
        
        Vec3 viewportU = u.multiply(viewportWidth);
        Vec3 viewportV = (v.negated()).multiply(viewportHeight);
        pixelDeltaU = viewportU.multiply(1.0 / imageWidth);
        pixelDeltaV = viewportV.multiply(1.0 / imageHeight);
        
        Vec3 viewportUpperLeft = cameraCenter.subtract(w.multiply(focalLength))
                .subtract(viewportU.multiply(0.5))
                .subtract(viewportV.multiply(0.5));
        pixel00Loc = viewportUpperLeft.add(pixelDeltaU.multiply(0.5)).add(pixelDeltaV.multiply(0.5));
    }
    
    Ray getRay(int i,int j)
    {
    	Vec3 offset = sampleSquare();
    	Vec3 pixelSample=pixel00Loc.add(pixelDeltaU.multiply(i+offset.x())).add((pixelDeltaV).multiply(j+offset.y()));
    	Vec3 rayOrigin = cameraCenter;
    	Vec3 rayDirection=pixelSample.subtract(rayOrigin);
    	return new Ray(rayOrigin,rayDirection);
    }
    
    Vec3 sampleSquare()
    {
    	
    	return new Vec3(r.nextDouble()-0.5,r.nextDouble()-0.5,0);
    }
	
	Colour RayColor(Ray r,int depth,Hittable world)
	{
		if(depth<=0)
			return new Colour(0,0,0);
		HitRecord rec = new HitRecord();
		
		
		if (world.hit(r, new Interval(0.001,RTWeekend.infinity), rec)) {
			
			Wrapper wrapper=new Wrapper();
			
			if(rec.mat.scatter(r, rec, wrapper)) 
			{

				return wrapper.attenuation.multiply(RayColor(wrapper.scattered,depth-1,world));
			}
		}

		Vec3 unitDirection = Vec3.unitVector(r.direction);
        double a = 0.5 * (unitDirection.y() + 1.0);
        Colour white = new Colour(1.0, 1.0, 1.0);
        Colour blue = new Colour(0.5, 0.7, 1.0);
        return white.multiply(1.0 - a).add(blue.multiply(a));
	}
	
	

}
