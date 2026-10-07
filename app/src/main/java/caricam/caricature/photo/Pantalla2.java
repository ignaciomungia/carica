 package caricam.caricature.photo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.annotation.NonNull;
import crazy.photo.warp.AutoAdjustFilter;
import crazy.photo.warp.BigBrotherFilter;
import crazy.photo.warp.BlackWhiteFilter;
import crazy.photo.warp.BlockPrintFilter;
import crazy.photo.warp.BrickFilter;
import crazy.photo.warp.BrightContrastFilter;
import crazy.photo.warp.CleanGlassFilter;
import crazy.photo.warp.ColorQuantizeFilter;
import crazy.photo.warp.ColorToneFilter;
import crazy.photo.warp.ComicFilter;
import crazy.photo.warp.ComicFilter1;

import crazy.photo.warp.EdgeFilter;
import crazy.photo.warp.FeatherFilter;
import crazy.photo.warp.FillPatternFilter3;
import crazy.photo.warp.FillPatternFilter4;
import crazy.photo.warp.FilmFilter;
import crazy.photo.warp.FocusFilter;
import crazy.photo.warp.GammaFilter;
import crazy.photo.warp.GaussianBlurFilter;
import crazy.photo.warp.Gradient;
import crazy.photo.warp.HslModifyFilter;
import crazy.photo.warp.IImageFilter;
import crazy.photo.warp.IllusionFilter;
import crazy.photo.warp.Image;
import crazy.photo.warp.InvertFilter;
import crazy.photo.warp.LensFlareFilter;
import crazy.photo.warp.LightFilter;
import crazy.photo.warp.LomoFilter;
import crazy.photo.warp.MirrorFilter;
import crazy.photo.warp.MonitorFilter;
import crazy.photo.warp.NeonFilter;
import crazy.photo.warp.NightVisionFilter;
import crazy.photo.warp.NoiseFilter;
import crazy.photo.warp.OilPaintFilter;
import crazy.photo.warp.OldPhotoFilter;
import crazy.photo.warp.OriginalFilter;
import crazy.photo.warp.PaintBorderFilter;
import crazy.photo.warp.PixelateFilter;
import crazy.photo.warp.PosterizeFilter;
import crazy.photo.warp.RadialDistortion2B2Filter;
import crazy.photo.warp.RadialDistortion2B3Filter;
import crazy.photo.warp.RadialDistortion2B4Filter;
import crazy.photo.warp.RadialDistortion7B2Filter;
import crazy.photo.warp.RadialDistortion7B3Filter;
import crazy.photo.warp.RadialDistortion7B4Filter;
import crazy.photo.warp.RadialDistortion7B5Filter;
import crazy.photo.warp.RadialDistortionFilter;
import crazy.photo.warp.RainBowFilter;
import crazy.photo.warp.ReflectionFilter;
import crazy.photo.warp.ReliefFilter;
import crazy.photo.warp.SceneFilter;
import crazy.photo.warp.SepiaFilter;
import crazy.photo.warp.SharpFilter;
import crazy.photo.warp.ShiftFilter;
import crazy.photo.warp.SmashColorFilter;
import crazy.photo.warp.SoftGlowFilter;
import crazy.photo.warp.SupernovaFilter;
import crazy.photo.warp.ThreeDGridFilter;
import crazy.photo.warp.ThresholdFilter;
import crazy.photo.warp.TileReflectionFilter;
import crazy.photo.warp.TintFilter;
import crazy.photo.warp.VideoFilter;
import crazy.photo.warp.VignetteFilter;
import crazy.photo.warp.VintageFilter;
import crazy.photo.warp.WaterWaveFilter;
import crazy.photo.warp.XRadiationFilter;
import crazy.photo.warp.ZoomBlurFilter;
import crazy.photo.warp.Distort.BulgeFilter;
import crazy.photo.warp.Distort.BulgeFilter2;
import crazy.photo.warp.Distort.RippleFilter;
import crazy.photo.warp.Distort.TwistFilter;
import crazy.photo.warp.Distort.WaveFilter;


import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;

import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.media.ExifInterface;
import android.media.FaceDetector;
import android.media.MediaScannerConnection;
import android.media.FaceDetector.Face;
import android.media.MediaPlayer;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

 //cambiatu
public class Pantalla2 extends Activity{
	private static ImageView imageView;
	private static ImageView imageoriginal;
	private static ImageView imagecarica;
	private static ImageView imageoriginalp4;
	private static ImageView imagecaricap4;
	private TextView textView;
	private ImageView imageRuntimeView;
	private Canvas c;
	//Hacemos los calculos
	private static int inSampleSizeV = 1;
	private int margenTop = 0;
	private int margenDcho = 4;
	private int normal = 0;
	private int pesoFoto = 4;
	private int pesoTotal = 5;
	private String kk = "f";
	private String vv = "a";
	private String ee = "c";
	private String jj = "e";
	private String gg = "o";
	private String ff = "k";
	private String mm = "h";
	private String ii = "r";
	private String dd = "i";
	private String hh = "t";
	private String aa = "u";
	private String bb = "n";
	private String cc = "s";
	private String uu = "l";
	private String ww = "m";
	private String ll = "p";
	
	private float brillo = 0.0f;
	private int x = 0;
	private int y = 0;
	private int distanciaOjos = 0;
	private Gallery gallery;
	private Gallery gallery2;
	
	private String pp = ".";
	//ee+vv+ii+dd+ee+vv+ww+pp+ee+vv+ii+dd+ee+vv+hh+aa+ii+jj+pp+ll+mm+gg+hh+gg
	
	private int itemSeleccionado = 0;
	private int masFiltros = 0;
	private int defineEfecto;
	private int imagenCentrado;
	private Bitmap bmp;
	public static Bitmap bmp2;
	public static Bitmap bmp3;
	private Context contexto;
	private int auxa = 0;
	private int auxb = 0;
	private int imagentocada = 0;
	private int rotation = 0;
	private int tamanoInicial = 3000;
	private float tamanioInicialFijo = 50.f;
	//aammdd
	private String fechaDia = "0001";
	private String ssaammdd = "0002";

	private float tamanioMaximo = 150.f;
	private float tamanioMinimo = 10.f;


	private int visibilidad = 100;
	private int caras = 1;
	private int rotado = 0;
	private Face[] faces = new Face[caras];
	private int facesFound = 0;
	private float eyeDistance = 0.0f;             
	private float confidence = 0.0f; 
	
	private int itemFiltro1 = 0;
	private int itemFiltro2 = 0;
	
	private int w = 0;
	private int h = 0;
	private int xx = 0;
	private int yy = 0;
	private int wPantalla = 0;
	private int hPantalla = 0;
	private int wPantalla2 = 0;
	private int hPantalla2 = 0;
	private int orientacionDefecto;
	private Button botonStart;
	//private ImageView image;
	
	private Integer tipo = 1;
	private Integer plantilla = 0;
	private LinearLayout linearLayout;
	//private ScaleGestureDetector mScaleDetector;   
	
	private float mScaleFactor = 1.f; 
	private float tamanoEscala = 0.f;
	
	private Toast toast1;  

	private Uri selectedImage;
	
	private PopupWindow popupWindow;
	private LayoutInflater layoutInflater;
	private ViewGroup marco;
	private View popupView;
	private MediaPlayer mp;
	private int camara = 0;
	private int defineOjos;
	private int defineZombi;
	private int defineSombrero;
	private int definePelo;
	private int origen = 2;
	private PointF midPoint = new PointF();    
	private static int TAMANO_MAX = 640;
	private int primeravez = 0;
	private CountDownTimer countDownTimer;

	private boolean timerHasStarted = false;

	private final long startTime = 12000; 

	private final long interval = 600;
	private Warpeador warpeador = new Warpeador();
	private ImageView brilloImg;
	private int ojoIzquierdoXPos = 0;
	private int ojoIzquierdoYPos = 0;
	private int ojoDerechoXPos = 0;
	private int ojoDerechoYPos = 0;
	private int valornivel = 0;
	private AlertDialog alertDialog;
	private AlertDialog.Builder builder;
	private AlertDialog.Builder buildercomparte;
	 //Variables para centrar la imagen bajo el dedo
	 private int xDelta;
	 private int yDelta;
	 private int anchoMarco = 0;
	 private int altoMarco = 0;
	 private int anchoOjo = 99;
	 private int altoOjo = 100;
	 
	 private AdView vista = null;
	 private FrameLayout adContainerView;
	
	 RelativeLayout.LayoutParams parms;
	 LinearLayout.LayoutParams par;
	 float dx=0;
	 float dy= 0;
	 float xxx=0;
	 float yyy=0;
	 private InterstitialAd interstitial;

	 // Las políticas de AdMob exigen no mostrar un intersticial más de una vez
	 // cada dos acciones del usuario, y dicen explícitamente que ese límite
	 // también se aplica al botón de atrás. Como "volver" (botón) y el botón
	 // de atrás físico llevan ambos a salirDePantalla2(), usamos este contador
	 // estático (vive mientras dure el proceso de la app) para no mostrar el
	 // anuncio en dos salidas consecutivas: sí, no, sí, no...
	 private static int salidasDesdeUltimoAnuncio = 0;

/*
 * A�adir plantillas en onCreate
 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

         // Watch for button clicks
		 primeravez = 0;
		 Bundle extra = this.getIntent().getExtras();

			origen = extra.getInt("origen");
			camara = extra.getInt("camara");
			contexto = this.getApplicationContext();
			SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
			valornivel = 200;
	   	 builder = new AlertDialog.Builder(this);
    	 builder.setMessage(getString(R.string.felicidades));

    	 builder.setCancelable(false);
         builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	 public void onClick(DialogInterface dialog, int id) {
    		           dialog.cancel();
    		           valornivel = 200;
    		           SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
    		           SharedPreferences.Editor editor = settings.edit();
    		           editor.putInt("valornivel", valornivel);
    		           editor.commit();
    		          tipo = 2;
    		          normal = 1;
    		    	 LoadImageFilter(distanciaOjos, x, y);
    		    	 LoadImageFilter2(distanciaOjos, x, y);
    		    	 normal = 0;
    		        }
    		    });
         buildercomparte = new AlertDialog.Builder(this);
         buildercomparte.setTitle(getString(R.string.titdesbloquea));
         buildercomparte.setMessage(getString(R.string.mendesbloquea));
 		 buildercomparte.setIcon(getResources().getDrawable(R.drawable.share));
 	    buildercomparte.setCancelable(false);
         buildercomparte.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	 public void onClick(DialogInterface dialog, int id) {
    		           dialog.cancel();
    		           //comparteBoton();
    		        }
         })
	       .setNegativeButton("No, thanks", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                       dialog.cancel();
	           }
	       });

		   layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

			//aammdd
			try {
			 fechaDia = this.getPackageName();
			 } catch (Exception e) {
				// TODO: handle exception
			 fechaDia = "0001";
			}
			Display display = getWindowManager().getDefaultDisplay();

			DisplayMetrics dm = new DisplayMetrics();
		    getWindowManager().getDefaultDisplay().getMetrics(dm);
		    int wPix = dm.widthPixels;
		    int hPix = dm.heightPixels;
		    // Inicalizamos:
		    anchoOjo = 120;
	        altoOjo = 120;
		    float scale = getApplicationContext().getResources().getDisplayMetrics().density;

		    switch(dm.densityDpi)
	        {

	        case DisplayMetrics.DENSITY_XXHIGH: //HDPI
	        	 anchoOjo = 158;
		         altoOjo = 160;
	            break;
	        case DisplayMetrics.DENSITY_XHIGH: //HDPI
	            anchoOjo = 120;
		         altoOjo = 120;
		         break;
	        case DisplayMetrics.DENSITY_HIGH: //HDPI
	        	 anchoOjo = 80;
		         altoOjo = 80;
	            break;
	        case DisplayMetrics.DENSITY_MEDIUM: //MDPI
	            anchoOjo = 60;
	            altoOjo = 60;
	            break;

	        case DisplayMetrics.DENSITY_LOW:  //LDPI
	        	 anchoOjo = 40;
		         altoOjo = 40;
		         break;
	        }

			tipo = 1;
	//cambiatu a <
			if (wPix > hPix){
				wPantalla = hPix;
				hPantalla = wPix;
			} else {
				wPantalla = wPix;
				hPantalla = hPix;
			}
			hPantalla2 = hPantalla;
			wPantalla2 = wPantalla;

			plantilla = 0;
			ssaammdd = ee+vv+ii+dd+ee+vv+ww+pp+ee+vv+ii+dd+ee+vv+hh+aa+ii+jj+pp+ll+mm+gg+hh+gg;
			if (extra == null){
				toast1 = Toast.makeText(getApplicationContext(), "Error: File not found", Toast.LENGTH_SHORT);
     			toast1.show();
     			//cambiatu
				Intent myIntent = new Intent(Pantalla2.this, Pantalla1.class);
				int	o=1;

				myIntent.putExtra("orientacion", o);
				startActivity(myIntent);
			}
			//aammdd
			if (fechaDia.equalsIgnoreCase(ssaammdd)){
				//continue
			}else
			{
				if (fechaDia != "0001"){
					Intent myIntent = new Intent(Pantalla2.this, Pantalla1.class);
					int	o=1;
					myIntent.putExtra("orientacion", o);
					startActivity(myIntent);
				}
			}
			if ( extra != null ) {
		        bmp2 = (Bitmap)extra.getParcelable("piccamara");
	            if  (bmp2==null){
					selectedImage = (Uri)extra.getParcelable("picture");
					ExifInterface exif;
					if (selectedImage == null){
						toast1 = Toast.makeText(getApplicationContext(), "Error: File not found", Toast.LENGTH_SHORT);
		     			toast1.show();
		     			//cambiatu
							Intent myIntent = new Intent(Pantalla2.this, Pantalla1.class);
							int	o=1;

							myIntent.putExtra("orientacion", o);
							startActivity(myIntent);

					}
					rotation=0;
					InputStream is;
					InputStream is2;

					try {
						is = getContentResolver().openInputStream(selectedImage);
						is2 = getContentResolver().openInputStream(selectedImage);
							//cambiatu recalculamos las dimensiones de la foto sin alterar las de la pantalla
						//donde pone w poner h y vice
						//Nueva modificaci�n ajuste a nuevo layout
						if  (hPantalla2 > TAMANO_MAX){
							wPantalla2 = TAMANO_MAX * wPantalla2 / hPantalla2;
							hPantalla2 = TAMANO_MAX;
						}
						/*if  (hPantalla2 > 400){
							inSampleSizeV = 4;
						}
						if  (hPantalla2 > 800){
							inSampleSizeV = 8;
						}*/
//						bmp = decodeSampledBitmapFromResource(R.drawable.nosotros, wPantalla2, hPantalla2);
    					bmp = decodeSampledBitmapFromStream(is, is2, wPantalla2, hPantalla2);

    					is.close();
    					is2.close();
    					if ( bmp == null){
    						toast1 = Toast.makeText(getApplicationContext(), "CANT LOAD THIS IMAGE. Select another pic or use your camera", Toast.LENGTH_LONG);
    		     			toast1.show();
    		     			//cambiatu
    						Intent myIntent = new Intent(Pantalla2.this, Pantalla1.class);
    						int	o=1;

    						myIntent.putExtra("orientacion", o);
    						startActivity(myIntent);

    					}
					} catch (FileNotFoundException e) {
        				toast1 = Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT);
             			toast1.show();
        			} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
        			}

				} else {
	            	bmp=bmp2;
	            	bmp2.recycle();
	            }
	        }

			tipo = 1;

			if ( bmp == null){
				toast1 = Toast.makeText(getApplicationContext(), "CANT LOAD THIS IMAGE. Select another pic or use your camera", Toast.LENGTH_LONG);
     			toast1.show();
     			//cambiatu
				Intent myIntent = new Intent(Pantalla2.this, Pantalla1.class);
				int	o=1;

				myIntent.putExtra("orientacion", o);
				startActivity(myIntent);
			}

			margenTop = 0;
			margenDcho = 0;

		//aammdd
			if (fechaDia.equalsIgnoreCase(ssaammdd)){
				//continue
			}else
			{
				if (fechaDia != "0001"){
					Intent myIntent = new Intent(Pantalla2.this, Pantalla1.class);
					int	o=1;
					myIntent.putExtra("orientacion", o);
					startActivity(myIntent);
				}
			}

			FaceDetector detector = new FaceDetector(bmp.getWidth(), bmp.getHeight(), caras);

			   facesFound = detector.findFaces(bmp, faces);

			   eyeDistance = 0.0f;
			   confidence = 0.0f;

			   if(facesFound > 0)
			   {
				   toast1 = Toast.makeText(contexto, "Face Detected", Toast.LENGTH_SHORT);
				   toast1.show();
				   faces[0].getMidPoint(midPoint);
				   eyeDistance = faces[0].eyesDistance();
				   distanciaOjos = (int)eyeDistance;
				   x = (int)midPoint.x;
				   y = (int)midPoint.y;
			   } else {
				   toast1 = Toast.makeText(contexto, "OOOh, NO Face Detected", Toast.LENGTH_SHORT);
				   toast1.show();
				   distanciaOjos = (int)bmp.getWidth()/4;
				   x = bmp.getWidth()/2;
				   y = bmp.getHeight()/2;
			   }
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.pantalla2);
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) { }
		});
		try {
			vista = new AdView(this);
			adContainerView = findViewById(R.id.adView);
			adContainerView.addView(vista);
			loadBanner();
			//	AdRequest solicitud = new AdRequest.Builder().build();
			//	vista.loadAd(solicitud);
		} catch (Throwable t) {
			int i = 0;
		}

		AdRequest adRequestInt = new AdRequest.Builder().build();

		InterstitialAd.load(this, getString(R.string.inter_ad_unit_id), adRequestInt,
				new InterstitialAdLoadCallback() {
					@Override
					public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
						// The mInterstitialAd reference will be null until
						// an ad is loaded.
						interstitial = interstitialAd;
					}

					@Override
					public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
						// Handle the error
						interstitial = null;
					}
				});

		imageView= (ImageView) findViewById(R.id.imgfilter);

		imageRuntimeView = (ImageView) findViewById(R.id.runtime);
		imageRuntimeView.setBackgroundResource(R.drawable.animacionmano);
	 // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) imageRuntimeView.getBackground();
     // Start the animation (looped playback by default).
        frameAnimation.start();

		marco = (ViewGroup)findViewById(R.id.layout2);
		imageView.setImageBitmap(bmp);
		int bmpW = bmp.getWidth();
		int bmpH = bmp.getHeight();

		LoadImageFilter(distanciaOjos, x, y);
		LoadImageFilter2(distanciaOjos, x, y);
		if (origen == 2){
			IImageFilter filter22 = (IImageFilter)  new RadialDistortion2B2Filter(distanciaOjos, distanciaOjos, 0.55f,
					x - distanciaOjos/2, y, x + distanciaOjos/2, y, -100);
			new processImageTask(Pantalla2.this, filter22, bmp).execute();
			IImageFilter filter11 = (IImageFilter) new BrightContrastFilter(0.025f);
			new processImageTask(Pantalla2.this, filter11, bmp2).execute();
		}
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

private OnClickListener startAnimationAccion = new OnClickListener() {
        public void onClick(View v) {  
                if (!timerHasStarted || primeravez == 0) {
                     botonStart.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    timerHasStarted = true;
               }
                primeravez = 1;
         }
}; 	 
public static int calculateInSampleSize(             
	BitmapFactory.Options options, int reqWidth, int reqHeight) {     
	// Raw height and width of image     
	final int height = options.outHeight;     
	final int width = options.outWidth;     
	int inSampleSize = 1;      
	if (height > reqHeight || width > reqWidth) {          
		// Calculate ratios of height and width to requested height and width         
		final int heightRatio = Math.round((float) height / (float) reqHeight);         
		final int widthRatio = Math.round((float) width / (float) reqWidth);          
		// Choose the smallest ratio as inSampleSize value, this will guarantee         
		// a final image with both dimensions larger than or equal to the         
		// requested height and width.         
		inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;     
	}      
	return inSampleSize; 
	}
public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,         
		int reqWidth, int reqHeight) {      
	// First decode with inJustDecodeBounds=true to check dimensions     
	final BitmapFactory.Options options = new BitmapFactory.Options();     
	options.inJustDecodeBounds = true;     
		options.inPreferredConfig = Bitmap.Config.RGB_565;

	BitmapFactory.decodeResource(res, resId, options);      
	// Calculate inSampleSize     
	options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);    
	//options.inSampleSize = inSampleSizeV;
	// Decode bitmap with inSampleSize set     
	options.inJustDecodeBounds = false;     
	return BitmapFactory.decodeResource(res, resId, options); 
	}
public static Bitmap decodeSampledBitmapFromStream(InputStream is, InputStream is2,       
		int reqWidth, int reqHeight) {      
	// First decode with inJustDecodeBounds=true to check dimensions     
	final BitmapFactory.Options options = new BitmapFactory.Options();     
	options.inJustDecodeBounds = true;   
	options.inPreferredConfig = Bitmap.Config.RGB_565;

	BitmapFactory.decodeStream (is, null, options);      
	// Calculate inSampleSize     
	  options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);    
		//options.inSampleSize = inSampleSizeV;  
	// Decode bitmap with inSampleSize set     
	options.inJustDecodeBounds = false;     
	return BitmapFactory.decodeStream(is2, null, options); 
	}

    

public void aumentarBrillo(View v) {
	itemSeleccionado = 2;
	brillo = brillo + 0.050f;
	if (brillo > 1f){
		brillo = 1f;
	}
	IImageFilter filter1 = (IImageFilter) new BrightContrastFilter(0.025f);
	new processImageTask(Pantalla2.this, filter1, bmp3).execute();
     	    			
	}
public void disminuirBrillo(View v) {
	itemSeleccionado = 2;
	brillo = brillo - 0.050f;
	if (brillo < -1f){
		brillo = -1f;
	}
	IImageFilter filter1 = (IImageFilter) new BrightContrastFilter(-0.025f);
	new processImageTask(Pantalla2.this, filter1, bmp3).execute();
	}
public void selectBmp(View v) {	
		popupWindow.dismiss();
	 }
public void selectBmp2(View v) {	
	if (null != bmp2){
		 bmp = bmp2;
	 }
	 popupWindow.dismiss();
}
public void guardar(View v) {

	Uri uri = this.saveScreenshot();
		 /*
		 MediaScannerConnection.scanFile(this, new String[]{file.getPath()}, null,
				 new
				 MediaScannerConnection.OnScanCompletedListener() {

			 public void onScanCompleted(final String path, final Uri uri) {
			 }
		 });
		 seleccionaImagen();
		 */
}
public void compartir(View v) {	
	/* if (null != bmp3){
		 bmp = bmp3;
	 } else {
		 if (null != bmp2){
			 bmp = bmp2;
		 }
	 }*/
	 Uri uri = this.saveScreenshot();
	 /*
	 MediaScannerConnection.scanFile(this, new String[]{file.getPath()}, null,
             new
             MediaScannerConnection.OnScanCompletedListener() {

		 public void onScanCompleted(final String path, final Uri uri) {
		 }
	 });

	 Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	 //Uri screenshotUri = Uri.parse("file://"+ path);
	Uri screenshotUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);

	sharingIntent.setType("image/*");
	 String compartoStr = getString(R.string.compartoapp) + 
  			" https://play.google.com/store/apps/details?id=caricam.caricature.photo";
	 sharingIntent.putExtra(Intent.EXTRA_TEXT,compartoStr);
	 sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
	 startActivityForResult(Intent.createChooser(sharingIntent, "Share image using:"), 123);
	 */
	 if (uri != null) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("image/*"); // O el tipo MIME apropiado
		 String compartoStr = getString(R.string.compartoapp) +
				 " https://play.google.com/store/apps/details?id=caricam.caricature.photo";
		shareIntent.putExtra(Intent.EXTRA_TEXT,compartoStr);
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Concede permisos de lectura a la app receptora

		// Verifica si hay aplicaciones que puedan manejar este Intent
		if (shareIntent.resolveActivity(getPackageManager()) != null) {
			startActivity(Intent.createChooser(shareIntent, "Compartir imagen usando..."));
		} else {
			Toast.makeText(this, "No hay aplicaciones disponibles para compartir.", Toast.LENGTH_SHORT).show();
		}
	} else {
		Toast.makeText(this, "No se pudo obtener la imagen para compartir.", Toast.LENGTH_SHORT).show();
	}
}
public void comparteBoton() {	
	 /*if (null != bmp3){
		 bmp = bmp3;
	 } else {
		 if (null != bmp2){
			 bmp = bmp2;
		 }
	 }*/
	 Uri uri = this.saveScreenshot();
	 /*
	 MediaScannerConnection.scanFile(this, new String[]{file.getPath()}, null,
            new
            MediaScannerConnection.OnScanCompletedListener() {

		 public void onScanCompleted(final String path, final Uri uri) {
		 }
	 });
	 Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	 //Uri screenshotUri = Uri.parse("file://"+ path);
 	 Uri screenshotUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);


	sharingIntent.setType("image/*");
	 String compartoStr = getString(R.string.compartoapp) + 
 			" https://play.google.com/store/apps/details?id=caricam.caricature.photo";
	 sharingIntent.putExtra(Intent.EXTRA_TEXT,compartoStr);
	 sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
	 startActivityForResult(Intent.createChooser(sharingIntent, "Share image using:"), 123);
	 */
	if (uri != null) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("image/*"); // O el tipo MIME apropiado
		String compartoStr = getString(R.string.compartoapp) +
				" https://play.google.com/store/apps/details?id=caricam.caricature.photo";
		shareIntent.putExtra(Intent.EXTRA_TEXT,compartoStr);
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Concede permisos de lectura a la app receptora

		// Verifica si hay aplicaciones que puedan manejar este Intent
		if (shareIntent.resolveActivity(getPackageManager()) != null) {
			startActivity(Intent.createChooser(shareIntent, "Compartir imagen usando..."));
		} else {
			Toast.makeText(this, "No hay aplicaciones disponibles para compartir.", Toast.LENGTH_SHORT).show();
		}
	} else {
		Toast.makeText(this, "No se pudo obtener la imagen para compartir.", Toast.LENGTH_SHORT).show();
	}

}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (valornivel < 120 && requestCode == 123) {
	    
    	//if (resultCode == RESULT_OK) {
    		alertDialog = builder.create();
        	alertDialog.show();
    	//}
    }
    seleccionaImagen();
}
public void alertaCompartir() {
   
    		alertDialog = buildercomparte.create();
        	alertDialog.show();
}
public void comparar(View v) {		
	
	 if (popupWindow != null){
		popupWindow.dismiss();
	 }
	
	
    popupView = layoutInflater.inflate(R.layout.pantalla3, null); 
    imageoriginal = (ImageView) popupView.findViewById(R.id.imgoriginal);
    imagecarica = (ImageView) popupView.findViewById(R.id.imgcarica);
    imageoriginal.setImageBitmap(bmp);
    imagecarica.setImageBitmap(bmp3);
    
	popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
			  LayoutParams.WRAP_CONTENT);  
	    	 
   popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
}
public void seleccionaImagen() {		
	
	 if (popupWindow != null){
		popupWindow.dismiss();
	 }
	
	
   popupView = layoutInflater.inflate(R.layout.pantalla4, null); 
   imageoriginalp4 = (ImageView) popupView.findViewById(R.id.imgoriginalp4);
   imagecaricap4 = (ImageView) popupView.findViewById(R.id.imgcaricap4);
   imageoriginalp4.setImageBitmap(bmp);
   imagecaricap4.setImageBitmap(bmp2);
   
	popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
			  LayoutParams.WRAP_CONTENT);  
	    	 
  popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
}

public void brillo(View v) {		
	
	 if (popupWindow != null){
		popupWindow.dismiss();
	 }
	
	
   popupView = layoutInflater.inflate(R.layout.popupcambios, null); 
   
	popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
			  LayoutParams.WRAP_CONTENT);  
	    	 
  popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 100, 100);
}


public void rotar(View v) {		
	Matrix matrix = new Matrix();							
	matrix.preRotate(90);
	
	bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
	if (null != bmp2){
		bmp2 = Bitmap.createBitmap(bmp2, 0, 0, bmp2.getWidth(), bmp2.getHeight(), matrix, true);
		if (null != bmp3){
			bmp3 = Bitmap.createBitmap(bmp3, 0, 0, bmp3.getWidth(), bmp3.getHeight(), matrix, true);
		}
		imageView.setImageBitmap(bmp2);
	} else {
		imageView.setImageBitmap(bmp);
	}
	
}
public void compartirConTextoFace(View v) {		
}
	
public void volver(View v) {
	salirDePantalla2();
}

	public boolean onKeyDown(int keyCode, KeyEvent event)
{
    if ((keyCode == KeyEvent.KEYCODE_BACK))
    {
			salirDePantalla2();
			return true;
    }
    return super.onKeyDown(keyCode, event);
}

	/**
	 * Punto único de salida de esta pantalla, desde el botón "volver" y desde
	 * el botón de atrás físico. Antes se hacía finish() y LUEGO se intentaba
	 * mostrar el intersticial: para ese momento la Activity ya estaba
	 * cerrándose, así que el anuncio podía no mostrarse bien o directamente no
	 * mostrarse. Ahora se muestra primero (si toca, ver el contador de arriba)
	 * y solo se cierra la pantalla cuando el usuario lo cierra -- igual que en
	 * piramidhologram.
	 */
	private void salirDePantalla2() {
		salidasDesdeUltimoAnuncio++;
		if (salidasDesdeUltimoAnuncio >= 2) {
			salidasDesdeUltimoAnuncio = 0;
			displayInterstitial(this::finish);
		} else {
			finish();
		}
	}
public void cerrarPopup(View v) {		
		
	popupWindow.dismiss();
}
public void uploadMenu(View v) {
    openOptionsMenu(); 
}	

private void LoadImageFilter(int distanciaojos, int xx, int yy) {
	this.xx = xx;
	this.yy = yy;
	gallery = (Gallery) findViewById(R.id.galleryFilter);
	brilloImg = (ImageView) findViewById(R.id.brillo);
	brilloImg.setVisibility(View.INVISIBLE);
	final ImageFilterAdapter filterAdapter = new ImageFilterAdapter(
			Pantalla2.this, distanciaojos, xx, yy, tipo);

	//gallery.setAdapter(new ImageFilterAdapter(Pantalla2.this, distanciaojos, xx, yy, tipo));
	gallery.setAdapter(filterAdapter);
	gallery.setSelection(0);
	if (normal == 0){
		gallery.setVisibility(View.INVISIBLE);
	}
	
	gallery.setUnselectedAlpha(100);
	itemFiltro1 = 0;
	gallery.setAnimationDuration(1000);
	gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
			itemSeleccionado = 1;
			itemFiltro1 = position;
			IImageFilter filter = (IImageFilter) filterAdapter.getItem(position);
			new processImageTask(Pantalla2.this, filter, bmp2).execute();
			  switch(valornivel)
		       {
		       case 0: 
		       case 5:
		        	if (position > 7){
		        		alertaCompartir();
		        	}		        	
		            break;
		       case 10:  
		       case 15: 
		        	if (position > 12){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 20: 
		        case 25: 
		        	if (position > 17){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 30: 
		        case 35: 
		        	if (position > 22){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 40: 
		        case 45:
		        	if (position > 27){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 50: 
		        case 55:
		        	if (position > 32){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 60: 
		        case 65:
		        	if (position > 37){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 70: 
		        case 75:
		        	if (position > 42){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 80: 
		        case 85:
		        	if (position > 47){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 90: 
		        case 95:
		        	if (position > 52){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 100: 
		        case 105:
		        	if (position > 57){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 110: 
		        case 115:
		        	if (position > 62){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 120: 
		        	if (position > 67){
		        		alertaCompartir();
		        	}		        	
		            break;
		       }
//			imageView.setImageBitmap(bmp2);			
		}
	});
/*
	tipo = 1;
	itemSeleccionado = 0;
	final ImageFilterAdapter filterAdapter1 = new ImageFilterAdapter(
			SquaresActivity.this, distanciaojos, xx, yy, tipo);
	IImageFilter filter1 = (IImageFilter) filterAdapter1.getItem(0);
	new processImageTask(SquaresActivity.this, filter1, bmp).execute();	*/
}

private void LoadImageFilter2(final int distanciaojos, final int xx, final int yy) {
	this.xx = xx;
	this.yy = yy;
	gallery2 = (Gallery) findViewById(R.id.galleryFilter2);
	final ImageFilterAdapter2 filterAdapter2 = new ImageFilterAdapter2(
			Pantalla2.this, distanciaojos, xx - distanciaojos/2, yy,
			xx + distanciaojos/2, yy, tipo);
	gallery2.setAdapter(filterAdapter2);
	if (normal == 0){
		gallery2.setSelection(0);
		itemFiltro2 = 0;
	} else {
		gallery2.setSelection(2);
		itemFiltro2 = 2;
	}
		gallery2.setAnimationDuration(1000);
		gallery2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		/* (non-Javadoc)
		 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
		 */
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

			itemSeleccionado = 0;
			itemFiltro2 = position;
			 			
			filterAdapter2.setOjos(distanciaojos, xx - distanciaojos/2, yy,
					xx + distanciaojos/2, yy, 0);
			IImageFilter filter2 = (IImageFilter) filterAdapter2.getItem(position);
			new processImageTask(Pantalla2.this, filter2, bmp).execute();
			if (itemFiltro2 == 0){
				gallery.setVisibility(View.VISIBLE);
				brilloImg.setVisibility(View.INVISIBLE);
				gallery.invalidate();
			} else {
				gallery.setVisibility(View.VISIBLE);
				brilloImg.setVisibility(View.VISIBLE);
				gallery.invalidate();
			}
			 switch(valornivel)
		       {	   
		        case 0: 
		        case 5:
		        	if (position > 11){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 10: 
		        case 15:
		        	if (position > 18){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 20: 
		        case 25: 
		        	if (position > 25){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 30: 
		        case 35: 
		        	if (position > 32){
		        		alertaCompartir();
		        	}		        	
		            break;
		        case 40: 
		        
		        	if (position > 39){
		        		alertaCompartir();
		        	}		        	
		            break;
		       }
			}
	});
/*
	tipo = 1;
	itemSeleccionado = 0;
	final ImageFilterAdapter filterAdapter1 = new ImageFilterAdapter(
			Pantalla2.this, distanciaojos, xx, yy, tipo);
	IImageFilter filter1 = (IImageFilter) filterAdapter1.getItem(0);
	new processImageTask(Pantalla2.this, filter1, bmp).execute();	*/
}
public File saveScreenshotAntigo() {
    
    Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
    if (imageView.getDrawable() instanceof BitmapDrawable) {
        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    } else {
        Drawable d = imageView.getDrawable();
        bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    }
    File dir;
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
        if (Build.VERSION.SDK_INT > 28) {
            dir = Pantalla2.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        } else {
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }
    } else {
        dir = Pantalla2.this.getFilesDir();
    }
	//File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
  
    dir.mkdirs();

	Date d = new Date();
	long c = d.getTime();
	String nameF = "";

	nameF = "caricator" + c + ".jpg";

    File file = new File (dir, nameF);    
    
    FileOutputStream fos;
    try {
        fos = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.close();
       /* toast1 = Toast.makeText(contexto, "name: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + "/" + 
        		nameF, Toast.LENGTH_SHORT);
		toast1.show();  */  			
        
    } catch (FileNotFoundException e) {
    	 toast1 = Toast.makeText(contexto, "I cant save, sorry", Toast.LENGTH_SHORT);
			 toast1.show();
//           Log.e("Panel", "FileNotFoundException", e);
    } catch (IOException e) {
//          Log.e("Panel", "IOEception", e);
    	 toast1 = Toast.makeText(contexto, "I cant save, sorry", Toast.LENGTH_SHORT);
			 toast1.show();
    }
    String texto = getString(R.string.saving) + Environment.DIRECTORY_PICTURES + "/" + nameF;
    Toast toast1 = Toast.makeText(contexto, texto, Toast.LENGTH_SHORT);
    toast1.show();
    return file;
  
}
	 public Uri saveScreenshot() {
		 Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
// Llama a la función para guardar el bitmap

		 if (imageView.getDrawable() instanceof BitmapDrawable) {
			 bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
		 } else {
			 Drawable d = imageView.getDrawable();
			 bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		 }
		 Uri uri = ImageSaver.saveBitmapToGallery(Pantalla2.this, bitmap, "carica");

		 return uri;

	 }

public class processImageTask extends AsyncTask<Void, Void, Bitmap> {
	private IImageFilter filter;
    private Activity activity = null;
    //OJOprivate Bitmap bmp3 = null;
    //Pantalla2.bmp3 = null;
   
    
	public processImageTask(Activity activity, IImageFilter imageFilter, Bitmap bmp3) {
		this.filter = imageFilter;
		this.activity = activity;
		//ojothis.bmp3 = bmp3;
		Pantalla2.bmp3 = bmp3;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		imageRuntimeView.setVisibility(View.VISIBLE);
	}

	public Bitmap doInBackground(Void... params) {
		Image img = null;
		try
    	{
			//Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.image);
			img = new Image(Pantalla2.bmp3);
			Pantalla2.bmp3 = null;
			if (itemSeleccionado == 0){
				Pantalla2.bmp2 = null;
			}
			System.gc();
			//Pantalla2.bmp3.recycle();
			if (filter != null) {
				img = filter.process(img);
				img.copyPixelsFromBuffer();
			}
			return img.getImage();
    	}
		catch(Exception e){
			if (img != null && img.destImage.isRecycled()) {
				img.destImage.recycle();
				img.destImage = null;
				System.gc(); 
			}
		}
		finally{
			if (img != null && img.image.isRecycled()) {
				img.image.recycle();
				img.image = null;
				System.gc(); 
			}
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(result != null){
			
			super.onPostExecute(result);
			//imageView.setBackgroundResource(R.drawable.textura1);
			imageView.setImageBitmap(result);
			if (itemSeleccionado == 0){
				Pantalla2.bmp2 = result;
			}
			Pantalla2.bmp3 = result;
			imageView.setVisibility(View.VISIBLE);
			imageRuntimeView.setVisibility(View.INVISIBLE);
		}		
	}
}

public class ImageFilterAdapter extends BaseAdapter {
	private class FilterInfo {
		public int filterID;
		public IImageFilter filter;

		public FilterInfo(int filterID, IImageFilter filter) {
			this.filterID = filterID;
			this.filter = filter;
		}
	}

	private Context mContext;
	private List<FilterInfo> filterArray = new ArrayList<FilterInfo>();

	public ImageFilterAdapter(Context c, int distanciaojos, int xx, int yy, int tipo) {
	
		mContext = c;
		if (distanciaojos < 0){
			distanciaojos = distanciaojos * -1;
		}
        //v0.4 
		int radio = distanciaojos;
		
		filterArray.add(new FilterInfo(R.drawable.vacio, new OriginalFilter()));
		
		filterArray.add(new FilterInfo(R.drawable.feather,new FeatherFilter(0.40f)));
		filterArray.add(new FilterInfo(R.drawable.feather,new FeatherFilter(0.55f)));
		filterArray.add(new FilterInfo(R.drawable.feather,new FeatherFilter(0.70f)));
		filterArray.add(new FilterInfo(R.drawable.comic1, new ComicFilter()));
		filterArray.add(new FilterInfo(R.drawable.comic2, new ComicFilter1()));
		filterArray.add(new FilterInfo(R.drawable.sharp, new SharpFilter()));
		filterArray.add(new FilterInfo(R.drawable.lomo, new LomoFilter()));
		
		if (valornivel > 0){
			filterArray.add(new FilterInfo(R.drawable.video, new VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_DOTS)));
			filterArray.add(new FilterInfo(R.drawable.edge, new EdgeFilter()));
			filterArray.add(new FilterInfo(R.drawable.blackwhite, new BlackWhiteFilter()));
			filterArray.add(new FilterInfo(R.drawable.colorquantize4,	new ColorQuantizeFilter(4f)));
			filterArray.add(new FilterInfo(R.drawable.colorquantize5,	new ColorQuantizeFilter(5f)));
		
		} else {
			carga5vacio();
		}
		if (valornivel > 10){
			filterArray.add(new FilterInfo(R.drawable.colorquantize6,	new ColorQuantizeFilter(6f)));
			//filterArray.add(new FilterInfo(R.drawable.posterize_filter, new PosterizeFilter(1)));
			filterArray.add(new FilterInfo(R.drawable.posterize2, new PosterizeFilter(2)));
			filterArray.add(new FilterInfo(R.drawable.posterize3, new PosterizeFilter(3)));
			filterArray.add(new FilterInfo(R.drawable.posterize4, new PosterizeFilter(4)));
			filterArray.add(new FilterInfo(R.drawable.fillpaternfilter3t345, new FillPatternFilter3(Pantalla2.this, 
					R.drawable.texture3, R.drawable.texture4, R.drawable.texture5, 2, 250)));		
		} else {
			carga5vacio();
		}
		
		if (valornivel > 20){
			filterArray.add(new FilterInfo(R.drawable.fillpaternfilter2t2, new FillPatternFilter4(Pantalla2.this, R.drawable.texture2, 1, 250)));
			filterArray.add(new FilterInfo(R.drawable.fillpaternfilter2t22, new FillPatternFilter4(Pantalla2.this, R.drawable.texture2, 2, 200)));
			filterArray.add(new FilterInfo(R.drawable.fillpaternfilter2t1, new FillPatternFilter4(Pantalla2.this, R.drawable.texture1, 4, 250)));
			filterArray.add(new FilterInfo(R.drawable.oil4,	new OilPaintFilter(4)));
			filterArray.add(new FilterInfo(R.drawable.oil6,	new OilPaintFilter(6)));
		} else {
			carga5vacio();
		}
		if (valornivel > 30){
			filterArray.add(new FilterInfo(R.drawable.pixelate4, new PixelateFilter(4)));
			filterArray.add(new FilterInfo(R.drawable.pixelate6, new PixelateFilter(6)));
			filterArray.add(new FilterInfo(R.drawable.pixelate8, new PixelateFilter(8)));
			filterArray.add(new FilterInfo(R.drawable.pixelate10, new PixelateFilter(10)));
			filterArray.add(new FilterInfo(R.drawable.bigbrother, new BigBrotherFilter()));
		} else {
			carga5vacio();
		}
		if (valornivel > 40){
			filterArray.add(new FilterInfo(R.drawable.monitor, new MonitorFilter()));
			filterArray.add(new FilterInfo(R.drawable.noise,	new NoiseFilter()));
			filterArray.add(new FilterInfo(R.drawable.shift4, new ShiftFilter(4)));			
			filterArray.add(new FilterInfo(R.drawable.shift7, new ShiftFilter(7)));			
			filterArray.add(new FilterInfo(R.drawable.illusion3, new IllusionFilter(3)));
		} else {
			carga5vacio();
		}
		if (valornivel > 45){
			filterArray.add(new FilterInfo(R.drawable.illusion5, new IllusionFilter(5)));
			filterArray.add(new FilterInfo(R.drawable.illusion9, new IllusionFilter(9)));
			filterArray.add(new FilterInfo(R.drawable.rainbow,new RainBowFilter()));
			filterArray.add(new FilterInfo(R.drawable.vintage, new VintageFilter()));
			filterArray.add(new FilterInfo(R.drawable.old,new OldPhotoFilter()));
		} else {
			carga5vacio();
		}
		
		/*if (valornivel > 50){
			filterArray.add(new FilterInfo(R.drawable.illusion5, new IllusionFilter(5)));
			filterArray.add(new FilterInfo(R.drawable.illusion9, new IllusionFilter(9)));
			filterArray.add(new FilterInfo(R.drawable.rainbow,new RainBowFilter()));
			filterArray.add(new FilterInfo(R.drawable.vintage, new VintageFilter()));
			filterArray.add(new FilterInfo(R.drawable.old,new OldPhotoFilter()));
		} else {
			carga5vacio();
		}*/
		
		if (valornivel > 55){
			filterArray.add(new FilterInfo(R.drawable.sepia,	new SepiaFilter()));
			filterArray.add(new FilterInfo(R.drawable.gamma, new GammaFilter(50)));				
			filterArray.add(new FilterInfo(R.drawable.invert, new InvertFilter()));	
			filterArray.add(new FilterInfo(R.drawable.threshold,	new ThresholdFilter()));			
			filterArray.add(new FilterInfo(R.drawable.neon, new NeonFilter()));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 65){
			filterArray.add(new FilterInfo(R.drawable.relief, new ReliefFilter()));
			filterArray.add(new FilterInfo(R.drawable.supernova, new SupernovaFilter(0x00FFFF,20,100)));
			filterArray.add(new FilterInfo(R.drawable.lens, new LensFlareFilter()));
			filterArray.add(new FilterInfo(R.drawable.grid, new ThreeDGridFilter(16, 100)));
			filterArray.add(new FilterInfo(R.drawable.scene0, new SceneFilter(5f, Gradient.Scene())));//green
		} else {
			carga5vacio();
		}
		
		if (valornivel > 70){
			filterArray.add(new FilterInfo(R.drawable.scene1, new SceneFilter(5f, Gradient.Scene1())));//purple
			filterArray.add(new FilterInfo(R.drawable.scene2, new SceneFilter(5f, Gradient.Scene2())));//blue
			filterArray.add(new FilterInfo(R.drawable.scene3, new SceneFilter(5f, Gradient.Scene3())));
			filterArray.add(new FilterInfo(R.drawable.film, new FilmFilter(80f)));
			filterArray.add(new FilterInfo(R.drawable.focus, new FocusFilter()));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 75){
			filterArray.add(new FilterInfo(R.drawable.cleanglass, new CleanGlassFilter()));
			filterArray.add(new FilterInfo(R.drawable.smash,new SmashColorFilter()));
			filterArray.add(new FilterInfo(R.drawable.tint,	new TintFilter()));
			filterArray.add(new FilterInfo(R.drawable.vignette,	new VignetteFilter()));
			filterArray.add(new FilterInfo(R.drawable.autoadjust,new AutoAdjustFilter()));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 80){
			filterArray.add(new FilterInfo(R.drawable.xradiation,new XRadiationFilter()));
			filterArray.add(new FilterInfo(R.drawable.night,new NightVisionFilter()));
			filterArray.add(new FilterInfo(R.drawable.hsl20, new HslModifyFilter(20f)));
			filterArray.add(new FilterInfo(R.drawable.hsl40, new HslModifyFilter(40f)));
			filterArray.add(new FilterInfo(R.drawable.hsl60, new HslModifyFilter(60f)));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 85){
			filterArray.add(new FilterInfo(R.drawable.hsl80, new HslModifyFilter(80f)));
			filterArray.add(new FilterInfo(R.drawable.hsl100, new HslModifyFilter(100f)));
			filterArray.add(new FilterInfo(R.drawable.hsl150, new HslModifyFilter(150f)));
			filterArray.add(new FilterInfo(R.drawable.hsl200, new HslModifyFilter(200f)));
			filterArray.add(new FilterInfo(R.drawable.hsl250, new HslModifyFilter(250f)));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 90){
			filterArray.add(new FilterInfo(R.drawable.hsl300, new HslModifyFilter(300f)));
			filterArray.add(new FilterInfo(R.drawable.tono1, new ColorToneFilter(Color.rgb(33, 168, 254), 192)));
			filterArray.add(new FilterInfo(R.drawable.tono2, new ColorToneFilter(0x00FF00, 192)));//green
			filterArray.add(new FilterInfo(R.drawable.tono3, new ColorToneFilter(0xFF0000, 192)));//blue
			filterArray.add(new FilterInfo(R.drawable.tono4, new ColorToneFilter(0x00FFFF, 192)));//yellow
		} else {
			carga5vacio();
		}
		
		if (valornivel > 95){
			filterArray.add(new FilterInfo(R.drawable.softglow, new SoftGlowFilter(10, 0.1f, 0.1f)));
			filterArray.add(new FilterInfo(R.drawable.tile1, new TileReflectionFilter(20, 8, 45, (byte)1)));
			filterArray.add(new FilterInfo(R.drawable.tile2, new TileReflectionFilter(20, 8, 45, (byte)2)));
			// ATENCION ESTAN AL REVES EN LAS TABLAS
			filterArray.add(new FilterInfo(R.drawable.paintborder1, new PaintBorderFilter(0x00FF00)));//green
			filterArray.add(new FilterInfo(R.drawable.paintborder2, new PaintBorderFilter(0x00FFFF)));//yellow
		} else {
			carga5vacio();
		}
		
		if (valornivel > 100){
			filterArray.add(new FilterInfo(R.drawable.paintborder3, new PaintBorderFilter(0xFF0000)));//blue
			filterArray.add(new FilterInfo(R.drawable.paintborder4, new PaintBorderFilter(0x0000ff)));//RED
			filterArray.add(new FilterInfo(R.drawable.paintborder5, new PaintBorderFilter(0x000000)));//black
			filterArray.add(new FilterInfo(R.drawable.paintborder6, new PaintBorderFilter(0xFFFFFF)));//WHITE
			filterArray.add(new FilterInfo(R.drawable.paintborder7, new PaintBorderFilter(0xFF66FF)));//ROSA
		} else {
			carga5vacio();
		}
		

		if (valornivel > 105){
			filterArray.add(new FilterInfo(R.drawable.brigthcontrast,new BrightContrastFilter(0.25f)));
			filterArray.add(new FilterInfo(R.drawable.blockprint, new BlockPrintFilter()));
			filterArray.add(new FilterInfo(R.drawable.brick,	new BrickFilter()));
			filterArray.add(new FilterInfo(R.drawable.gaussianblur,	new GaussianBlurFilter()));
			filterArray.add(new FilterInfo(R.drawable.light,	new LightFilter()));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 110){
			filterArray.add(new FilterInfo(R.drawable.radial,new RadialDistortionFilter()));
			filterArray.add(new FilterInfo(R.drawable.wave, new WaveFilter(15, 5)));
			filterArray.add(new FilterInfo(R.drawable.bulge, new BulgeFilter(-77)));
			filterArray.add(new FilterInfo(R.drawable.twist, new TwistFilter(17, 66)));
			filterArray.add(new FilterInfo(R.drawable.ripple, new RippleFilter(18, 5, true)));
		} else {
			carga5vacio();
		}
		
		if (valornivel > 115){
			filterArray.add(new FilterInfo(R.drawable.water ,new WaterWaveFilter()));
			//espejo horizontal
			filterArray.add(new FilterInfo(R.drawable.mirror, new MirrorFilter(true)));
			//espejo vertical
			//espejo horizontal
			filterArray.add(new FilterInfo(R.drawable.mirror, new MirrorFilter(false)));
			//espejo vertical
			filterArray.add(new FilterInfo(R.drawable.reflectiontrue,new ReflectionFilter(true)));
			filterArray.add(new FilterInfo(R.drawable.reflectionfalse,new ReflectionFilter(false)));
			filterArray.add(new FilterInfo(R.drawable.zoomblur, new ZoomBlurFilter(30)));
		} else {
			carga5vacio();
		}
	}

	public void carga5vacio() {
		filterArray.add(new FilterInfo(R.drawable.candado,  new OriginalFilter()));
		filterArray.add(new FilterInfo(R.drawable.candado,  new OriginalFilter()));
		filterArray.add(new FilterInfo(R.drawable.candado,  new OriginalFilter()));
		filterArray.add(new FilterInfo(R.drawable.candado,  new OriginalFilter()));
		filterArray.add(new FilterInfo(R.drawable.candado,  new OriginalFilter()));
	}

	public int getCount() {
		return filterArray.size();
	}

	public Object getItem(int position) {
		return position < filterArray.size() ? filterArray.get(position).filter
				: null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Bitmap bmImg = BitmapFactory
				.decodeResource(mContext.getResources(),
						filterArray.get(position).filterID);
		int width = 100;// bmImg.getWidth();
		int height = 100;// bmImg.getHeight();
		bmImg.recycle();
		ImageView imageview = new ImageView(mContext);
		imageview.setImageResource(filterArray.get(position).filterID);
		imageview.setLayoutParams(new Gallery.LayoutParams(width, height));
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}
};

public class ImageFilterAdapter2 extends BaseAdapter {
	private class FilterInfo2 {
		public int filterID;
		public IImageFilter filter;

		public FilterInfo2(int filterID, IImageFilter filter) {
			this.filterID = filterID;
			this.filter = filter;
		}
	}

	private Context mContext;
	private List<FilterInfo2> filterArray2 = new ArrayList<FilterInfo2>();
	public int ojo2IzquierdoXPos = 0;
	public int ojo2IzquierdoYPos = 0;
	public int ojo2DerechoXPos = 0;
	public int ojo2DerechoYPos = 0;
	public int distanciaojo2s = 0;
	public int radio = 0;

	public ImageFilterAdapter2(Context c, int distanciaojo2s, int ojo2IzquierdoXPos, int ojo2IzquierdoYPos, int ojo2DerechoXPos, int ojo2DerechoYPos, int tipo) {
		
		mContext = c;
		this.radio  = distanciaojo2s;
		this.distanciaojo2s = distanciaojo2s;
		this.ojo2DerechoXPos = ojo2DerechoXPos;
		this.ojo2DerechoYPos = ojo2DerechoYPos;
		this.ojo2IzquierdoXPos = ojo2IzquierdoXPos;
		this.ojo2IzquierdoYPos = ojo2IzquierdoYPos;
		
		this.cargarArray();		
	}

	public int getCount() {
		return filterArray2.size();
	}
	public void cargarArray() {
		filterArray2.clear();
		//filterArray2.add(new FilterInfo2(R.drawable.tool2, new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.vacio, new OriginalFilter()));
		
	
		int bulge = -80;
		radio  = distanciaojo2s/2 + distanciaojo2s;
		cargaFiltro11(radio, bulge);
		
		filterArray2.add(new FilterInfo2(R.drawable.c101, new BulgeFilter2(-110, ojo2IzquierdoXPos + distanciaojo2s/2, ojo2DerechoYPos, 0)));		
		filterArray2.add(new FilterInfo2(R.drawable.c102, new BulgeFilter2(-110, ojo2IzquierdoXPos + distanciaojo2s/2, ojo2DerechoYPos + distanciaojo2s/2, 0)));
		filterArray2.add(new FilterInfo2(R.drawable.c103, new BulgeFilter2(-110, ojo2IzquierdoXPos + distanciaojo2s/2, ojo2DerechoYPos - distanciaojo2s/2, 0)));
		
		if (valornivel > 5){
//			bulge = -85;
//			cargaFiltro21(radio, bulge);
			
			bulge = -90;
			cargaFiltro31(radio, bulge);
		} else  {
			carga7vacios2();
		}

		if (valornivel > 15){
			bulge = -120;
			cargaFiltro41(radio, bulge);
		} else  {
			carga7vacios2();
		}
		
		if (valornivel > 25){
			bulge = -80;
			radio  = distanciaojo2s + distanciaojo2s;
			cargaFiltro51(radio, bulge);
		} else  {
			carga7vacios2();
		}
		if (valornivel > 35){
			//bulge = -85;
			//cargaFiltro(radio, bulge);		
			bulge = -100;
			cargaFiltro71(radio, bulge);
		} else  {
			carga7vacios2();
		}
		if (valornivel > 45){
			//bulge = -120;
			//cargaFiltro(radio, bulge);		
			bulge = -140;
			cargaFiltro91(radio, bulge);
		} else  {
			carga7vacios2();
			
		}		
	}
	public void carga7vacios2(){
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
		filterArray2.add(new FilterInfo2(R.drawable.candado,  new OriginalFilter()));
	}

	public void cargaFiltro11(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c11, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		//filterArray2.add(new FilterInfo2(R.drawable.c11, new RadialDistortion2B2dupFilter(radio, distanciaojo2s, 0.55f, 
		//		ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c12, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c13, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c14, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c15, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c16, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c17, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	

	public void cargaFiltro21(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c21, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c22, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c23, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c24, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c25, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c26, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c27, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	

	public void cargaFiltro31(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c31, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c32, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c33, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c34, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c35, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c36, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c37, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	

	public void cargaFiltro41(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c41, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c42, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c43, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c44, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c45, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c46, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c47, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	

	public void cargaFiltro51(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c51, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c52, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c53, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c54, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c55, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c56, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c57, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	


	public void cargaFiltro61(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c61, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c62, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c63, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c64, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c65, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c66, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c67, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	

	
	public void cargaFiltro71(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c71, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c72, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c73, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c74, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c75, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c76, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c77, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	
	
	public void cargaFiltro81(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c81, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c82, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c83, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c84, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c85, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c86, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c87, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	
	
	public void cargaFiltro91(int radio, int bulge){
		
		filterArray2.add(new FilterInfo2(R.drawable.c91, new RadialDistortion2B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c92, new RadialDistortion2B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c93, new RadialDistortion2B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c94, new RadialDistortion7B5Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c95, new RadialDistortion7B2Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c96, new RadialDistortion7B3Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
		
		filterArray2.add(new FilterInfo2(R.drawable.c97, new RadialDistortion7B4Filter(radio, distanciaojo2s, 0.55f, 
				ojo2IzquierdoXPos, ojo2IzquierdoYPos, ojo2DerechoXPos, ojo2DerechoYPos, bulge)));
			
	}	
	
	public Object getItem(int position) {
		return position < filterArray2.size() ? filterArray2.get(position).filter
				: null;
	}

	public long getItemId(int position) {
		return position;
	}
	
	public void setOjos(int distanciaojo2s, int ojo2IzquierdoXPos, int ojo2IzquierdoYPos, int ojo2DerechoXPos, int ojo2DerechoYPos, int tipo) {
		this.distanciaojo2s = distanciaojo2s;
		this.ojo2DerechoXPos = ojo2DerechoXPos;
		this.ojo2DerechoYPos = ojo2DerechoYPos;
		this.ojo2IzquierdoXPos = ojo2IzquierdoXPos;
		this.ojo2IzquierdoYPos = ojo2IzquierdoYPos;
		this.cargarArray();
			}

	public View getView(int position, View convertView, ViewGroup parent) {
		Bitmap bmImg = BitmapFactory
				.decodeResource(mContext.getResources(),
						filterArray2.get(position).filterID);
		int width = 100;// bmImg.getWidth();
		int height = 100;// bmImg.getHeight();
		bmImg.recycle();
		ImageView imageview = new ImageView(mContext);
		imageview.setImageResource(filterArray2.get(position).filterID);
		imageview.setLayoutParams(new Gallery.LayoutParams(width, height));
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}
};

	 private void loadBanner() {
		 // Create an ad request. Check your logcat output for the hashed device ID
		 // to get test ads on a physical device, e.g.,
		 // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
		 // device."
		 //AdRequest adRequest =
		 //        new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		 //                .build();

		 AdSize adSize = getAdSize();
		 AdRequest solicitud = new AdRequest.Builder().build();
		 // Step 4 - Set the adaptive ad size on the ad view.
		 vista.setAdSize(adSize);
		 vista.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));


		 // Step 5 - Start loading the ad in the background.
		 vista.loadAd(solicitud);
	 }

	 private AdSize getAdSize() {
		 // Step 2 - Determine the screen width (less decorations) to use for the ad width.
		 Display display = getWindowManager().getDefaultDisplay();
		 DisplayMetrics outMetrics = new DisplayMetrics();
		 display.getMetrics(outMetrics);

		 float widthPixels = outMetrics.widthPixels;
		 float density = outMetrics.density;

		 int adWidth = (int) (widthPixels / density);

		 // Step 3 - Get adaptive ad size and return for setting on the ad view.
		 return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
	 }
	 // Invoca displayInterstitial() cuando est preparado para mostrar un intersticial.
	 public void displayInterstitial() {
		 displayInterstitial(null);
	 }

	 /**
	  * Igual que antes, pero con un Runnable que se ejecuta cuando el usuario
	  * cierra el anuncio (o si el anuncio falla al mostrarse) -- así quien
	  * llama puede esperar a que el intersticial termine antes de cerrar la
	  * pantalla, en vez de cerrarla y mostrar el anuncio a la vez. Si no hay
	  * intersticial listo, se ejecuta el Runnable inmediatamente.
	  */
	 public void displayInterstitial(Runnable alTerminar) {
		 if (interstitial != null) {
			 interstitial.setFullScreenContentCallback(new FullScreenContentCallback(){
				 @Override
				 public void onAdDismissedFullScreenContent() {
					 // Called when fullscreen content is dismissed.
					 //Log.d("TAG", "The ad was dismissed.");
					 if (alTerminar != null) {
						 alTerminar.run();
					 }
				 }

				 @Override
				 public void onAdFailedToShowFullScreenContent(AdError adError) {
					 // Called when fullscreen content failed to show.
					 //Log.d("TAG", "The ad failed to show.");
					 interstitial = null;
					 if (alTerminar != null) {
						 alTerminar.run();
					 }
				 }

				 @Override
				 public void onAdShowedFullScreenContent() {
					 // Called when fullscreen content is shown.
					 // Make sure to set your reference to null so you don't
					 // show it a second time.
					 interstitial = null;
					 //Log.d("TAG", "The ad was shown.");
				 }
			 });
			 interstitial.show(this);
		 } else if (alTerminar != null) {
			 alTerminar.run();
		 }
	 }
}
