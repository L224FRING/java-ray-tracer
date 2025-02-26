
package Pack;









public class RayTracer {
	
	

	
    public static void main(String[] args) {
        // Image
    	 
    	  HittableList world = new HittableList();
    	// Hittable world=randomScene();
    	    Material groundMaterial = new lambertian(new Colour(0.8, 0.8, 0.8));
    	 world.add(new Sphere(new Vec3(0, -1000, 0), 1000, groundMaterial));

    	    

    	 Material material1 = new Dielectic(1.5);
    	  world.add(new Sphere(new Vec3(0, 1, 0), 1.0, material1));

    	  Material material2 = new lambertian(new Colour(0.9, 0.2, 0.1));
    	   world.add(new Sphere(new Vec3(-4, 1, 0), 1.0, material2));

    	   Material material3 = new Metal(new Colour(0.7, 0.6, 0.5), 0.0);
    	   world.add(new Sphere(new Vec3(4, 1, 0), 1.0, material3));
    	    
    	  
    	   

    	   
    	   
    	   

    	    // Camera
    	    Camera cam = new Camera();

    	    cam.aspectRatio = 16.0 / 9.0;
    	    cam.imageWidth = 400;
    	    cam.samplesPerPixel = 500;
    	    cam.maxDepth = 50;

    	    cam.vfov = 20;
    	    cam.lookfrom = new Vec3(13, 2, 3);
    	    cam.lookAt = new Vec3(0, 0, 0);
    	    cam.vup = new Vec3(0, 1, 0);

    	    

    	    cam.render(world);
    	}
}
