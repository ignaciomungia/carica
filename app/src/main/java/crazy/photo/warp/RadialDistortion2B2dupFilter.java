package crazy.photo.warp;

import crazy.photo.warp.RadialDistortionFilter.Point;
import crazy.photo.warp.Distort.BulgeFilter2;

public class RadialDistortion2B2dupFilter  implements IImageFilter{
	
	int realxpos;
	int distOjos;
    int realypos;
    int xi;
    int yi;
    int xd;
    int yd;
    int tipos;
    public float Radius = 0.5f;
    public float Distortion = 2.5f;
    public Point Center = new Point(0.5f, 0.5f);
    int r, g, b;
    int width;
    int height;
    float realradius;
    Image clone;
    Image imagen2;
	BulgeFilter2 bulgeF2;
	/*ColorQuantizeFilter colorQuantizeFilter = new ColorQuantizeFilter();
	OilPaintFilter oilPaintFilter = new OilPaintFilter(3);
	ImageBlender blender = new ImageBlender();
	ParamEdgeDetectFilter edgeDetectionFx = new ParamEdgeDetectFilter();
	ImageBlender edgeBlender = new ImageBlender();*/
	    
	public RadialDistortion2B2dupFilter(int radius, int distOjos, float distorsion, int xi, int yi, int xd, int yd, int tipo){
		this.distOjos = distOjos;
		Distortion = distorsion;
		this.realradius = (float)radius;
		this.xi = xi;
		this.yi = yi;
		this.xd = xd;
		this.yd = yd;
		this.tipos = tipo;
		this.bulgeF2 =  new BulgeFilter2(tipos, xi + distOjos/2, yi, 0);
		//bulgeF2._hh = yi;
		//bulgeF2._hw = xi + distOjos/2;
		
		//bulgeF2._amount = tipo;
		
//	         blender.Mixture = 1f;
//	       //PROBANDO
//	         blender.Mode = 7;
//	         edgeDetectionFx.Threshold = 0.25f;
//	         edgeDetectionFx.DoGrayConversion = true;
//	         edgeBlender.Mixture = 0.8f;
//	         //PROBANDO
//	         edgeBlender.Mode = 7;
//		colorQuantizeFilter.levels = 5;
	}
	
    //@Override
    public Image process(Image imageIn) {
    	imagen2 = imageIn;
        width = imagen2.getWidth();
        height = imagen2.getHeight();
        
        realxpos = xi;
        realypos = yi;
    
        clone = imagen2.clone();
        this.procesaRadial();
        imagen2.copyPixelsFromBuffer();
        
        //PASADA 2
		realxpos = xd;
		realypos = yd;
		imagen2.setImage(imagen2.getImage());
	    clone = imagen2.clone();
	    this.procesaRadial();
        imagen2.copyPixelsFromBuffer();
        
        //PASADA 3
        realxpos = xi + (xd - xi) /2;
		realypos = yd + (int)distOjos;
        imagen2.setImage(imagen2.getImage());
        clone = imagen2.clone();
        this.procesaRadial();
        imagen2.copyPixelsFromBuffer();
        
   
		imagen2 = bulgeF2.process(imagen2);
		
        realxpos = xi;
        realypos = yi;
    
        clone = imagen2.clone();
        this.procesaRadial();
        imagen2.copyPixelsFromBuffer();
        
        //PASADA 2
		realxpos = xd;
		realypos = yd;
		imagen2.setImage(imagen2.getImage());
	    clone = imagen2.clone();
	    this.procesaRadial();
        imagen2.copyPixelsFromBuffer();
        
        //PASADA 3
        realxpos = xi + (xd - xi) /2;
		realypos = yd + (int)distOjos;
        imagen2.setImage(imagen2.getImage());
        clone = imagen2.clone();
        this.procesaRadial();
        imagen2.copyPixelsFromBuffer();
        
   
		imagen2 = bulgeF2.process(imagen2);
	    //imagen2 = colorQuantizeFilter.process(imagen2);
		//imagen2 = oilPaintFilter.process(imagen2);
	            
	    //Image edge = edgeDetectionFx.process(imagen2);
	    //return edgeBlender.Blend(imagen2, edge);
		return imagen2;

    }
    //@Override
    public void procesaRadial() {
    	 for (int x = 0; x < width; x++)
         {
 	        for (int y = 0; y < height; y++)
 	        {
                 float pos = 1f - ((float)Math.sqrt((double)(((x - realxpos) * (x - realxpos)) + (y - realypos) * (y - realypos))) / realradius);
                 if (pos > 0f)
                 {
                     pos = 1f - (Distortion * pos * pos);
                     float pos1 = (x - realxpos) * pos + realxpos;
                     float pos2 = (y - realypos) * pos + realypos;
                     int x1 = (int)pos1;
                     float pos3 = pos1 - x1;
                     int x2 = (pos3 > 0f) ? (x1 + 1) : x1;
                     int y1 = (int)pos2;
                     float pos4 = pos2 - y1;
                     int y2 = (pos4 > 0f) ? (y1 + 1) : y1;
                     if (x1 < 0){
                         x1 = 0;
                     }
                     else if (x1 >= width){
                         x1 = width - 1;
                     }
                     if (x2 < 0){
                         x2 = 0;
                     }
                     else if (x2 >= width){
                         x2 = width - 1;
                     }
                     if (y1 < 0){
                         y1 = 0;
                     }
                     else if (y1 >= height){
                         y1 = height - 1;
                     }
                     if (y2 < 0){
                         y2 = 0;
                     }
                     else if (y2 >= height){
                         y2 = height - 1;
                     }
                     r = clone.getRComponent(x1, y1);
                     g = clone.getGComponent(x1, y1);
                     b = clone.getBComponent(x1, y1);
                   
                     int r2 = clone.getRComponent(x2, y1);
                     int g2 = clone.getGComponent(x2, y1);
                     int b2 = clone.getBComponent(x2, y1);           
                     int r3 = clone.getRComponent(x2, y2);
                     int g3 = clone.getGComponent(x2, y2);
                     int b3 = clone.getBComponent(x2, y2);
                     int r4 = clone.getRComponent(x1, y2);
                     int g4 = clone.getGComponent(x1, y2);
                     int b4 = clone.getBComponent(x1, y2);
                     r = (int)((r * (1f - pos4) * (1f - pos3) + r2 * (1f - pos4) * pos3 + r3 * pos4 * pos3) + r4 * pos4 * (1f - pos3));
                     g = (int)((g * (1f - pos4) * (1f - pos3) + g2 * (1f - pos4) * pos3 + g3 * pos4 * pos3) + g4 * pos4 * (1f - pos3));
                     b = (int)((b * (1f - pos4) * (1f - pos3) + b2 * (1f - pos4) * pos3 + b3 * pos4 * pos3) + b4 * pos4 * (1f - pos3));
                 }
                 else {
                     r = clone.getRComponent(x, y);
                     g = clone.getGComponent(x, y);
                     b = clone.getBComponent(x, y);                   
                 }
                 imagen2.setPixelColor(x,y,r,g,b);
             }
         }
    }

    public void procesaSemiCirculoSuperior() {
    	 for (int x = 0; x < width; x++)
         {
 	        for (int y = 0; y < height; y++)
 	        {
                           
 	        	float pos = 1f - ((float)Math.sqrt((double)(((x - realxpos) * (x - realxpos)) + (y - realypos) * (y - realypos))) / realradius);
                 if (pos > 0f && y <= realypos)
                 {
                     pos = 1f - (Distortion * pos * pos);
                     float pos1 = (x - realxpos) * pos + realxpos;
                     float pos2 = (y - realypos) * pos + realypos;
                     int x1 = (int)pos1;
                     float pos3 = pos1 - x1;
                     int x2 = (pos3 > 0f) ? (x1 + 1) : x1;
                     int y1 = (int)pos2;
                     float pos4 = pos2 - y1;
                     int y2 = (pos4 > 0f) ? (y1 + 1) : y1;
                     if (x1 < 0){
                         x1 = 0;
                     }
                     else if (x1 >= width){
                         x1 = width - 1;
                     }
                     if (x2 < 0){
                         x2 = 0;
                     }
                     else if (x2 >= width){
                         x2 = width - 1;
                     }
                     if (y1 < 0){
                         y1 = 0;
                     }
                     else if (y1 >= height){
                         y1 = height - 1;
                     }
                     if (y2 < 0){
                         y2 = 0;
                     }
                     else if (y2 >= height){
                         y2 = height - 1;
                     }
                     r = clone.getRComponent(x1, y1);
                     g = clone.getGComponent(x1, y1);
                     b = clone.getBComponent(x1, y1);
                   
                     int r2 = clone.getRComponent(x2, y1);
                     int g2 = clone.getGComponent(x2, y1);
                     int b2 = clone.getBComponent(x2, y1);           
                     int r3 = clone.getRComponent(x2, y2);
                     int g3 = clone.getGComponent(x2, y2);
                     int b3 = clone.getBComponent(x2, y2);
                     int r4 = clone.getRComponent(x1, y2);
                     int g4 = clone.getGComponent(x1, y2);
                     int b4 = clone.getBComponent(x1, y2);
                     r = (int)((r * (1f - pos4) * (1f - pos3) + r2 * (1f - pos4) * pos3 + r3 * pos4 * pos3) + r4 * pos4 * (1f - pos3));
                     g = (int)((g * (1f - pos4) * (1f - pos3) + g2 * (1f - pos4) * pos3 + g3 * pos4 * pos3) + g4 * pos4 * (1f - pos3));
                     b = (int)((b * (1f - pos4) * (1f - pos3) + b2 * (1f - pos4) * pos3 + b3 * pos4 * pos3) + b4 * pos4 * (1f - pos3));
                 }
                 else {
                     r = clone.getRComponent(x, y);
                     g = clone.getGComponent(x, y);
                     b = clone.getBComponent(x, y);                   
                 }
                 imagen2.setPixelColor(x,y,r,g,b);
             }
         }
    }
    public void procesaSemiCirculoInferior() {
   	 for (int x = 0; x < width; x++)
        {
	        for (int y = 0; y < height; y++)
	        {
                          
	        	float pos = 1f - ((float)Math.sqrt((double)(((x - realxpos) * (x - realxpos)) + (y - realypos) * (y - realypos))) / realradius);
                if (pos > 0f && y >= realypos)
                {
                    pos = 1f - (Distortion * pos * pos);
                    float pos1 = (x - realxpos) * pos + realxpos;
                    float pos2 = (y - realypos) * pos + realypos;
                    int x1 = (int)pos1;
                    float pos3 = pos1 - x1;
                    int x2 = (pos3 > 0f) ? (x1 + 1) : x1;
                    int y1 = (int)pos2;
                    float pos4 = pos2 - y1;
                    int y2 = (pos4 > 0f) ? (y1 + 1) : y1;
                    if (x1 < 0){
                        x1 = 0;
                    }
                    else if (x1 >= width){
                        x1 = width - 1;
                    }
                    if (x2 < 0){
                        x2 = 0;
                    }
                    else if (x2 >= width){
                        x2 = width - 1;
                    }
                    if (y1 < 0){
                        y1 = 0;
                    }
                    else if (y1 >= height){
                        y1 = height - 1;
                    }
                    if (y2 < 0){
                        y2 = 0;
                    }
                    else if (y2 >= height){
                        y2 = height - 1;
                    }
                    r = clone.getRComponent(x1, y1);
                    g = clone.getGComponent(x1, y1);
                    b = clone.getBComponent(x1, y1);
                  
                    int r2 = clone.getRComponent(x2, y1);
                    int g2 = clone.getGComponent(x2, y1);
                    int b2 = clone.getBComponent(x2, y1);           
                    int r3 = clone.getRComponent(x2, y2);
                    int g3 = clone.getGComponent(x2, y2);
                    int b3 = clone.getBComponent(x2, y2);
                    int r4 = clone.getRComponent(x1, y2);
                    int g4 = clone.getGComponent(x1, y2);
                    int b4 = clone.getBComponent(x1, y2);
                    r = (int)((r * (1f - pos4) * (1f - pos3) + r2 * (1f - pos4) * pos3 + r3 * pos4 * pos3) + r4 * pos4 * (1f - pos3));
                    g = (int)((g * (1f - pos4) * (1f - pos3) + g2 * (1f - pos4) * pos3 + g3 * pos4 * pos3) + g4 * pos4 * (1f - pos3));
                    b = (int)((b * (1f - pos4) * (1f - pos3) + b2 * (1f - pos4) * pos3 + b3 * pos4 * pos3) + b4 * pos4 * (1f - pos3));
                }
                else {
                    r = clone.getRComponent(x, y);
                    g = clone.getGComponent(x, y);
                    b = clone.getBComponent(x, y);                   
                }
                imagen2.setPixelColor(x,y,r,g,b);
            }
        }
   }

}
