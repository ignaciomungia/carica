package crazy.photo.warp;

import android.app.Activity;

public class FillPatternFilter4  implements IImageFilter{
	
	private Image pattern;
	
	private float Mixture = 0.2f;//0.5f
	
	private int rgbmax = 765;//0.5f
	
    public FillPatternFilter4(Activity activity, int resourceId)
    {
        this.pattern = Image.LoadImage(activity, resourceId);
    }
    
    public FillPatternFilter4(Activity activity, int resourceId, int Mixture, int rgbmax)
    {
    	this.pattern = Image.LoadImage(activity, resourceId);
    
        this.Mixture = 0.2f;
        this.rgbmax = rgbmax;
    }

    public Image process(Image imageIn)
    {
    	
        int r, g, b, alfa;
        for (int x = 0; x < imageIn.getWidth(); x++)
        {
            for (int y = 0; y < imageIn.getHeight(); y++)
            {

                r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y); 
                
                if (r+g+b > 750){
                	alfa = 250;
                } else if (r+g+b > 700){
                	alfa = 220;
                } else if (r+g+b > 600){
                	alfa = 200;
                } else if (r+g+b > 400){
                	alfa = 180;
                } else {
                	alfa = 160;
                } 
		        imageIn.setPixelAColor(x, y, alfa, r, g, b);
              
                	// int rgbcolor = (255 << 24) + (r << 16) + (g << 8) + b;
              
                	       
		       
           }
        }
        return imageIn;
    }
}
