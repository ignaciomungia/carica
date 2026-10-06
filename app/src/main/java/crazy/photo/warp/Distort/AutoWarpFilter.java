package crazy.photo.warp.Distort;

import caricam.caricature.photo.Warpeador;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import crazy.photo.warp.IImageFilter;
import crazy.photo.warp.Image;



public class AutoWarpFilter implements IImageFilter{
	Image Clone;
	int   _eyedistance ;
    int   _offsetX ;
    int   _offsetY ;
	private Warpeador warpeador = new Warpeador();
    
    public AutoWarpFilter (int eyedistance, int offsetX, int offsetY)
    {
        _eyedistance = eyedistance;
        _offsetX = offsetX;
        _offsetY = offsetY;
    }
    //@Override
    public Image process(Image imageIn) {
    	//clone = imageIn.clone();
    	 int radioOjos = _eyedistance/8;
		   warpeador.setImagenEscalada(imageIn.getWidth(), imageIn.getHeight());
		   // calculo x,y de cada ojo:
		   int xi = _offsetX - _eyedistance/2;
		   int yi = _offsetY - _eyedistance/3;
		   int xd = _offsetX + _eyedistance/2;
		   int yd = _offsetY - _eyedistance/3;
		   
//ojo izdo			   
		   int xF = xi - _eyedistance/4;
		   int yF = yi - _eyedistance/4;
		 
		   imageIn.setImage(procesarImagen(imageIn.getImage(), xi, yi, xF, yF, radioOjos));	   
	
//ojo drcho		   
		   xF = xd + _eyedistance/4;
		   yF = yd - _eyedistance/4;
		   imageIn.setImage(procesarImagen(imageIn.getImage(), xd, yd, xF, yF, radioOjos));
	
	//barbilla		   
	   xd = _offsetX; 
	   yd = _offsetY + _eyedistance + _eyedistance;
	   
	   xF = xd;
	   yF = yd + _eyedistance/2;
	   
	   imageIn.setImage(procesarImagen(imageIn.getImage(), xd, yd, xF, yF, radioOjos));		  
	//oreja dcha	   
	   xd = _offsetX + _eyedistance; 
	   yd = _offsetY + _eyedistance/2;
	   
	   xF = xd + _eyedistance; 
	   yF = yd;
	   
	   imageIn.setImage(procesarImagen(imageIn.getImage(), xd, yd, xF, yF, radioOjos));	
	//oreja izda		   
	   xd = _offsetX - _eyedistance/2; 
	   yd = _offsetY + _eyedistance/2;
	   
	   xF = xd - _eyedistance; 
	   yF = yd;
	   
	   imageIn.setImage(procesarImagen(imageIn.getImage(), xd, yd, xF, yF, radioOjos));	

	    return imageIn;
    }
private Bitmap procesarImagen(Bitmap bmp, int x, int y, int xF, int yF, int radioOjos){
	  if (x > (bmp.getWidth() - 1)){
		   x = bmp.getWidth() - 1;
	   } else {
		   if (x < 0){
			   x = 0;
		   }
	   }
	   if (yF > (bmp.getHeight() - 1)){
		   yF = bmp.getHeight() - 1;
	   } else {
		   if (yF < 0){
			   yF = 0;
		   }
	   }
	   if (xF > (bmp.getWidth() - 1)){
		   xF = bmp.getWidth() - 1;
	   } else {
		   if (xF < 0){
			   xF = 0;
		   }
	   }
	   if (y > (bmp.getHeight() - 1)){
		   y = bmp.getHeight() - 1;
	   } else {
		   if (y < 0){
			   y = 0;
		   }
	   }
	   
	   return bmp = warpeador.procesar(bmp, (short)x, (short)y, (short)xF, (short)yF, radioOjos);
	}

}
