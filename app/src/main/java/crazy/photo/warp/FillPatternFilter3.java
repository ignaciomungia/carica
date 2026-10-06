package crazy.photo.warp;

import java.util.Random;

import android.app.Activity;

public class FillPatternFilter3  implements IImageFilter{
	
	private Image pattern1;
	private Image pattern2;
	private Image pattern3;
	
	private float Mixture = 0.2f;//0.5f
	
	private int rgbmax = 765;//0.5f
	
    public FillPatternFilter3(Activity activity, int resourceId1)
    {
        this.pattern1 = Image.LoadImage(activity, resourceId1);
    }
    
    public FillPatternFilter3(Activity activity, int resourceId1, int resourceId2, int resourceId3, int Mixture, int rgbmax)
    {
    	this.pattern1 = Image.LoadImage(activity, resourceId1);
    	this.pattern2 = Image.LoadImage(activity, resourceId2);
    	this.pattern3 = Image.LoadImage(activity, resourceId3);
        this.Mixture = 0.2f;
        this.rgbmax = rgbmax;
    }

    public Image process(Image imageIn)
    {
    	int mix1 = (int) (Mixture * 255f);
	    int mix2 = 255 - mix1;
        int r, g, b, r1, g1, b1;
        Random random = new Random();
        int numero;
        for (int x = 0; x < imageIn.getWidth(); x++)
        {
            for (int y = 0; y < imageIn.getHeight(); y++)
            {
                int xx = x % pattern1.getWidth();
                int yy = y % pattern1.getHeight();

                r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y); 
               
                if ((r + g + b) < rgbmax) {
                	 numero = random.nextInt(10) + 1;
                	 
         		    switch(numero)
         	        {
         	        case 0:	
         	        case 1:
         	        case 2:
         	        case 3:
         	        		r1 = Image.SAFECOLOR(r + pattern1.getRComponent(xx, yy));
                 			g1 = Image.SAFECOLOR(g + pattern1.getGComponent(xx, yy));
                 			b1 = Image.SAFECOLOR(b + pattern1.getBComponent(xx, yy));
                 			
                 			break;
         	        
         	        case 4:	
         	        case 5:
         	        case 6:
     	        
     	        		r1 = Image.SAFECOLOR(r + pattern2.getRComponent(xx, yy));
             			g1 = Image.SAFECOLOR(g + pattern2.getGComponent(xx, yy));
             			b1 = Image.SAFECOLOR(b + pattern2.getBComponent(xx, yy));
             			
             			break;
     	        
            		case 7:
            		case 8:
            		case 9:
            		case 10:
            				r1 = Image.SAFECOLOR(r + pattern3.getRComponent(xx, yy));
            				g1 = Image.SAFECOLOR(g + pattern3.getGComponent(xx, yy));
            				b1 = Image.SAFECOLOR(b + pattern3.getBComponent(xx, yy));
            				break;
            		default:
            			r1 = Image.SAFECOLOR(r + pattern3.getRComponent(xx, yy));
        				g1 = Image.SAFECOLOR(g + pattern3.getGComponent(xx, yy));
        				b1 = Image.SAFECOLOR(b + pattern3.getBComponent(xx, yy));
        				break;
         	        }
                
                	 
                } else {
                	 r1 = Image.SAFECOLOR(r);
                     g1 = Image.SAFECOLOR(g);
                     b1 = Image.SAFECOLOR(b);
                }
                r = (r * mix2) + (r1 * mix1);
		        g = (g * mix2) + (g1 * mix1);
		        b = (b * mix2) + (b1 * mix1); 
		        imageIn.setPixelColor(x, y, r >> 8, g >> 8, b >> 8);
           }
        }
        return imageIn;
    }
}
