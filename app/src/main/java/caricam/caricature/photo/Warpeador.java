package caricam.caricature.photo;

import java.io.PrintStream;
import java.util.LinkedList;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;


/**
 * Clase que warpea la imagen.
 * Extiene de Procesador.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public class Warpeador {
    private LinkedList<Matrix> matrices;

    /**
     * @return matrices de warpeo de todos los pasos del procesamiento.
     */
    public LinkedList<Matrix> getMatrices() {
        return matrices;
    }

    /**
     * @param matrices de warpeo de todos los pasos del procesamiento.
     */
    public void setMatrices(LinkedList<Matrix> matrices) {
        this.matrices = matrices;
    }

    /**
     * Constructor de Warpeador.
     * Crea un arreglo de matrices vacio.
     * @param warpature a la que pertenece.
     */
    public Warpeador(){
        setMatrices(new LinkedList<Matrix>());
       // Matrix mbp = new Matrix(bitmapPrimero.getWidth(), bitmapPrimero.getHeight());
      //get the ARGB value from each pixel of the image and store it into the array  
       // for(int i=0; i < bitmapPrimero.getWidth(); i++)  
       //  {  
       //     for(int j=0; j < bitmapPrimero.getHeight(); j++)  
      //       {  
        //This is a great opportunity to filter the ARGB values  
      //         mbp.setM(i, j, bitmapPrimero.getPixel(i, j));  
      //   }  
      //  }  
       
	//	getMatrices().addLast(mbp);
    }

    public void setImagenEscalada(int w, int h){
        getMatrices().clear();
        Matrix m=new Matrix((short)w, (short)h);
        m.init();
        getMatrices().addFirst(m);
    }

    public Bitmap volver(Bitmap src){
        if(matrices.size()>1){
			int alto =  src.getHeight();
			int ancho = src.getWidth();
            matrices.removeLast();
            Matrix m = matrices.getLast();
            int xint;
            int yint;
            double px;
            double py;
            for(short i=0;i<ancho;i++){
                for(short j=0;j<alto;j++){
                	
                    xint=(int)m.getX(i,j);
                    yint=(int)m.getY(i,j);
                	
                    
                    px=m.getX(i,j)-((double)xint);
                    py=m.getY(i,j)-((double)yint);
                   
                    int rgb=0;
                    try{
                        rgb=((((int)((1.0-px)*(1.0-py)*((double)((src.getPixel(xint,yint)>>0)&0xFF))+px*(1.0-py)*((double)((src.getPixel(xint+1,yint)>>0)&0xFF))+(1.0-px)*py*((double)((src.getPixel(xint,yint+1)>>0)&0xFF))+px*py*((double)((src.getPixel(xint+1,yint+1)>>0)&0xFF))))&0xFF)<<0)|((((int)((1.0-px)*(1.0-py)*((double)((src.getPixel(xint,yint)>>8)&0xFF))+px*(1.0-py)*((double)((src.getPixel(xint+1,yint)>>8)&0xFF))+(1.0-px)*py*((double)((src.getPixel(xint,yint+1)>>8)&0xFF))+px*py*((double)((src.getPixel(xint+1,yint+1)>>8)&0xFF))))&0xFF)<<8)|((((int)((1.0-px)*(1.0-py)*((double)((src.getPixel(xint,yint)>>16)&0xFF))+px*(1.0-py)*((double)((src.getPixel(xint+1,yint)>>16)&0xFF))+(1.0-px)*py*((double)((src.getPixel(xint,yint+1)>>16)&0xFF))+px*py*((double)((src.getPixel(xint+1,yint+1)>>16)&0xFF))))&0xFF)<<16)|0xFF000000;
                        String strHexNumber = Integer.toHexString(rgb);
                   	 	String R = strHexNumber.substring(0, 2);
                   	 	String G = strHexNumber.substring(2, 2);
                   	 	String B = strHexNumber.substring(4, 2);
                   	 	int rInt = Integer.parseInt(R, 16);
                   	 	int gInt = Integer.parseInt(G, 16);
                   	 	int bInt = Integer.parseInt(B, 16);
                   	 	src.setPixel(i, j, Color.argb(255, rInt, gInt, bInt));
                    }catch(Exception ex){
                    }
                }
            }
            //setChanged();
            //notifyObservers();
			return src;
        }
        return src;
    }

    public synchronized Bitmap procesar(Bitmap src, short xi, short yi, short xf,short yf, int radio){
	    short alto =  (short)src.getHeight();
		short ancho = (short)src.getWidth();
		Bitmap result = Bitmap.createBitmap(ancho, alto, src.getConfig());
        Matrix ma=getMatrices().getLast();
        Matrix mb = new Matrix(ancho, alto);
        
        float peso;
        float xd;
        float yd;
        int xint;
        int yint;
        float px;
        float py;
        float d=(float)(Math.sqrt(Math.pow((xi-xf),2.0)+Math.pow((yi-yf),2.0)));
        for(short i=0;i<ancho;i++){ 
            for(short j=0;j<alto;j++){
                //peso=Math.exp(-((4.0*radio+d)*Math.sqrt(Math.pow((double)(i-xf),2.0)+Math.pow((double)(j-yf),2.0))-((double)(i-xf))*((double)(xi-xf))-((double)(j-yf))*((double)(yi-yf)))/(2.0*radio*(d+2.0*radio)));
            	 peso=(float)(Math.exp(-((4.0*radio+d)*Math.sqrt(Math.pow((i-xf),2.0)+Math.pow((j-yf),2.0))-((i-xf))*((xi-xf))-((j-yf))*((yi-yf)))/(2.0*radio*(d+2.0*radio))));
            	 xd=(float) (ma.getX(i,j)+peso*(ma.getX(xi,yi)-ma.getX(xf,yf)));
                 yd=(float) (ma.getY(i,j)+peso*(ma.getY(xi,yi)-ma.getY(xf,yf)));
                 //xd=(xd<0.0)?0.0:((xd>=((double)(ancho-1)))?(((double)(ancho-1))-0.000001):xd);
                 //yd=(yd<0.0)?0.0:((yd>=((double)(alto-1)))?(((double)(alto-1))-0.000001):yd);
                 xd=(float) ((xd<0.0)?0.0:((xd>=((ancho-1)))?(((ancho-1))-0.000001):xd));
                 yd=(float) ((yd<0.0)?0.0:((yd>=((alto-1)))?(((alto-1))-0.000001):yd));
                
                 mb.setX(i,j,xd);
                mb.setY(i,j,yd);
                
                xint=(int) xd;
                yint=(int) yd;
                px=(xd-(xint));
                py=yd-(yint);
                int rgb=0;
                try{
                	
                	 rgb=((((int)((1.0-px)*(1.0-py)*((float)((src.getPixel(xint,yint)>>0)&0xFF))+px*(1.0-py)*((float)((src.getPixel(xint+1,yint)>>0)&0xFF))+(1.0-px)*py*((float)((src.getPixel(xint,yint+1)>>0)&0xFF))+px*py*((float)((src.getPixel(xint+1,yint+1)>>0)&0xFF))))&0xFF)<<0)|((((int)((1.0-px)*(1.0-py)*((float)((src.getPixel(xint,yint)>>8)&0xFF))+px*(1.0-py)*((float)((src.getPixel(xint+1,yint)>>8)&0xFF))+(1.0-px)*py*((float)((src.getPixel(xint,yint+1)>>8)&0xFF))+px*py*((float)((src.getPixel(xint+1,yint+1)>>8)&0xFF))))&0xFF)<<8)|((((int)((1.0-px)*(1.0-py)*((float)((src.getPixel(xint,yint)>>16)&0xFF))+px*(1.0-py)*((float)((src.getPixel(xint+1,yint)>>16)&0xFF))+(1.0-px)*py*((float)((src.getPixel(xint,yint+1)>>16)&0xFF))+px*py*((float)((src.getPixel(xint+1,yint+1)>>16)&0xFF))))&0xFF)<<16)|0xFF000000;
         
                	 result.setPixel(i, j, rgb);
                }catch(Exception ex){
                	ex.printStackTrace();
                }
            }
        }
        getMatrices().addLast(mb);
        if(getMatrices().size()>2){
            getMatrices().removeFirst();
        }
        //setChanged();
       // notifyObservers();
		return result;
    }
}