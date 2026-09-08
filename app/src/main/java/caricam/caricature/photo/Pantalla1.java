package caricam.caricature.photo;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import com.yalantis.ucrop.UCrop;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

//import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.multidex.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Pantalla1 extends Activity {
	      
	
		//camara
		private static int TAKE_PICTURE = 1;
		private static int SELECT_PICTURE = 2;
		private static int CAPTURE_PICTURE_INTENT = 2;
	
		private String name = "";
		private int defineEfecto = 1000;
		private int camara = 0;
		private int origen = 1;
		
		//camara
		private static int RESULT_LOAD_IMAGE = 1;
		private Bitmap bmp;
		private Button botonCamara;
		private Button botonCamaras;
		private Button botonImagenes;
		private Button botonSinImagenes;
		Toast toast1 = null;
		private Uri outputFileUri;
		private RadioButton r1;
		private RadioButton r2;
		private PopupWindow popupWindow;
		private ImageButton botonEfecto;
		private ImageButton botonIzda;
		private ImageButton botonDcha;
		private ImageButton botonCentro;
		private Uri mImageCaptureUri;
		private int o;
		private int defineOjos;
		private int defineZombi;
		private int defineSombrero;
		private int definePelo;
		
		private View popupView;
		private LayoutInflater layoutInflater;
		
		private int imagenCentrado = 0;
		private static final int CAMERA_REQUEST = 1888;
		private AlertDialog alertDialog;
		private AlertDialog.Builder builder;
		private AdView vista;
		private InterstitialAd interstitial;
	    private FrameLayout adContainerView;
		private AlertDialog.Builder builderRate;
		AlertDialog alertDialogRate;
		private static final int PERMISSIONS_READ = 100;
		private static final int PERMISSIONS_WRITE = 200;
	    private static final int UCROP_REQUEST_CODE = UCrop.REQUEST_CROP; // Este es el código que UCrop usa para su resultado
	    private Uri sourceUri;
	public static final int REQUEST_CODE_GALLERY      = 0x7;
		public static final int REQUEST_CODE_CROP_IMAGE   = 0x3;
		public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
		private File      mFileTemp;
        private static final int PERMISSIONS_REQUEST_MEDIA_OR_STORAGE = 100;
        private static final int PERMISSIONS_REQUEST_CAMERA = 101;


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.pantalla1);
		//mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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
        botonCamara = (Button)findViewById(R.id.imagenCamara);
        botonCamara.setOnClickListener(imagenCamaraAccion);
        
        botonImagenes = (Button)findViewById(R.id.imagenGaleria);
        botonImagenes.setOnClickListener(imagenGaleriaAccion);
        
        layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        
        ImageView img = (ImageView)findViewById(R.id.animacionview);
        img.setBackgroundResource(R.drawable.animacion);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.setAlpha(150);
        // Start the animation (looped playback by default).
		//permisos:
        checkAndRequestAppPermissions();

		frameAnimation.start();

	   	builder = new AlertDialog.Builder(this);
	   	builder.setTitle(getString(R.string.titvertical));       
        builder.setMessage(getString(R.string.menvertical));
		builder.setIcon(getResources().getDrawable(R.drawable.phonev));
		builder.setCancelable(false);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				String state = Environment.getExternalStorageState();
					File sd;
					if (Environment.MEDIA_MOUNTED.equals(state)) {
						////mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
						//mFileTemp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), TEMP_PHOTO_FILE_NAME);
						if (Build.VERSION.SDK_INT > 28) {
							sd = Pantalla1.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
						} else {
							sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
						}
					} else {
						sd = Pantalla1.this.getFilesDir();
					}

					if (mFileTemp != null && mFileTemp.exists()) {
						mFileTemp.delete();
					}
					File mFileTemp = new File(sd, TEMP_PHOTO_FILE_NAME);
						mImageCaptureUri = null;
						try {
							mImageCaptureUri = FileProvider.getUriForFile(Pantalla1.this, getPackageName() + ".provider",mFileTemp);
							//mImageCaptureUri = FileProvider.getUriForFile(Pantalla1.this, BuildConfig.APPLICATION_ID + ".provider",mFileTemp);

						} catch (Exception e) {
							e.printStackTrace();
						}
						Intent intent = null;
						try {
							intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						} catch (Exception e) {
							e.printStackTrace();
						}
						intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
						intent.putExtra("return-data", true);
						intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
						intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
						//startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
					try {
						startActivityForResult(intent, TAKE_PICTURE);
					} catch (ActivityNotFoundException e) {

					}

			}
		});
	     builderRate = new AlertDialog.Builder(this);
			builderRate.setMessage(getString(R.string.puntualo));
		    builderRate.setCancelable(false);
		    builderRate.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog2, int id2) {
		           dialog2.cancel();
		           SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
		           SharedPreferences.Editor editor = settings.edit();
		           editor.putString("valorratecaricam", "S");
		           editor.commit();
		         startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
		            		"https://play.google.com/store/apps/details?id=caricam.caricature.photo") ) );    
		           finish();
		        }
		    })
		       .setNegativeButton("No, thanks", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                       dialog.cancel();
		                       //PARA QUE SALGA SIEMPRE HASTA QUE DE SI
		                       /*SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
				                SharedPreferences.Editor editor = settings.edit();
				                editor.putString("valorratecaricam", "S");
				                editor.commit();*/
		                       //displayInterstitial();
		                       finish();
		           }
		       });
		String state = Environment.getExternalStorageState();

		if (mFileTemp != null && mFileTemp.exists()) {
			mFileTemp.delete();
		}
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			if (Build.VERSION.SDK_INT > 28) {
				mFileTemp = new File(Pantalla1.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TEMP_PHOTO_FILE_NAME);
			} else {
				mFileTemp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), TEMP_PHOTO_FILE_NAME);
			}
		} else {
			mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
		}
    }

    private OnClickListener imagenGaleriaAccion = new OnClickListener() {
        public void onClick(View v) {
            	      			       			
          //Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
          //startActivityForResult(i, SELECT_PICTURE);
			String state = Environment.getExternalStorageState();
			if (mFileTemp.exists()) {
				mFileTemp.delete();
			}
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				if (Build.VERSION.SDK_INT > 28) {
					mFileTemp = new File(Pantalla1.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TEMP_PHOTO_FILE_NAME);
				} else {

					mFileTemp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), TEMP_PHOTO_FILE_NAME);
				}
			} else {
				mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
			}
			try {
				Uri mImageCaptureUri = null;

				if (Environment.MEDIA_MOUNTED.equals(state)) {
					Log.d("MY_APP_DEBUG", "Application ID: " + BuildConfig.APPLICATION_ID);
					Log.d("MY_APP_DEBUG", "Package Name: " + getPackageName());
					//mImageCaptureUri = FileProvider.getUriForFile(Pantalla1.this, BuildConfig.APPLICATION_ID + ".provider",mFileTemp);
					mImageCaptureUri = FileProvider.getUriForFile(Pantalla1.this, getPackageName() + ".provider",mFileTemp);
				}
				else {
  	        	/*
  	        	 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
  	        	 */
					mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
				}
			} catch (ActivityNotFoundException e) {
				toast1 = Toast.makeText(getApplicationContext(), "I cant save in your sd", Toast.LENGTH_SHORT);
				toast1.show();
			}
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
          }       	
        };    
        
	 private OnClickListener imagenCamaraAccion = new OnClickListener() {
        public void onClick(View v) { 
        	alertDialog = builder.create();
        	alertDialog.show();
        }
       };


	private void startCropImage(int requestCode) {

		Uri destinationUri = Uri.fromFile(mFileTemp);

		UCrop.of(sourceUri, destinationUri)
				.withAspectRatio(1, 1) // Opcional: Establece un ratio de aspecto (ej. 1:1 para cuadrado)
				.withMaxResultSize(1000, 1000) // Opcional: Define el tamaño máximo de la imagen resultante
				.start(this); // 'this' es tu Activity
	}

	public static void copyStream(InputStream input, OutputStream output)
			throws IOException {

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
// Primero, verifica si el requestCode es el de UCrop
		if (requestCode == UCrop.REQUEST_CROP) {
			if (resultCode == RESULT_OK) {
				// El recorte fue exitoso
				final Uri resultUri = UCrop.getOutput(data); // ¡Aquí obtienes la URI de la imagen recortada!

				// Es buena práctica verificar si resultUri no es nulo, aunque UCrop rara vez devuelve nulo aquí en éxito
				if (resultUri != null) {
					// Preparar el Intent para Pantalla2
					Intent myIntent = new Intent(Pantalla1.this, Pantalla2.class);
					myIntent.putExtra("picture", resultUri); // Pasa la URI de la imagen recortada

					// Asegúrate de que 'camara' y 'origen' estén declarados y sean accesibles aquí,
					// por ejemplo, como variables de instancia de tu Activity.
					int camara = 0; // O el valor que corresponda en este caso
					myIntent.putExtra("camara", camara);

					int origen = 1; // O el valor que corresponda
					myIntent.putExtra("origen", origen);

					// Iniciar Pantalla2
					startActivity(myIntent);

					// Puedes eliminar o mantener estos comentarios si ya has integrado la lógica:
					// toast1 = Toast.makeText(getApplicationContext(), "Please, no heads turned", Toast.LENGTH_SHORT);
					// toast1.show();
					// ¡Aquí tienes la URI de la imagen recortada!
					// Ahora puedes cargarla en un ImageView o subirla, etc.
					// ImageView imageView = findViewById(R.id.your_image_view);
					// imageView.setImageURI(resultUri);

				} else {
					// Aunque es poco probable, si resultUri es nulo después de un RESULT_OK
					Toast.makeText(this, "Error: URI de imagen recortada nula.", Toast.LENGTH_LONG).show();
				}
			} else if (resultCode == UCrop.RESULT_ERROR) { // Si el resultado no fue OK, verifica si fue un ERROR de UCrop
				// Hubo un error durante el recorte
				final Throwable cropError = UCrop.getError(data); // Obtiene el objeto Throwable del error
				if (cropError != null) {
					Toast.makeText(this, "Error al recortar la imagen: " + cropError.getMessage(), Toast.LENGTH_LONG).show();
					cropError.printStackTrace(); // Imprime el stack trace para depuración
				} else {
					Toast.makeText(this, "Error desconocido al recortar la imagen.", Toast.LENGTH_LONG).show();
				}
			} else if (resultCode == RESULT_CANCELED) { // Si el resultado no fue OK ni ERROR, podría ser CANCELADO por el usuario
				// El usuario canceló la operación de recorte
				Toast.makeText(this, "Recorte de imagen cancelado.", Toast.LENGTH_SHORT).show();
			}
		}

//Comprobamos el estado de la memoria externa (tarjeta SD)
		String estado = Environment.getExternalStorageState();

		if (estado.equals(Environment.MEDIA_MOUNTED)) {

		} else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			toast1 = Toast.makeText(getApplicationContext(), "I cant save in your sd", Toast.LENGTH_SHORT);
			toast1.show();
		} else{
			toast1 = Toast.makeText(getApplicationContext(), "I cant read and save in your sd", Toast.LENGTH_SHORT);
			toast1.show();
		}

		//   Vuelve de la camara de fotos:

		if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK){
			//sourceUri = data.getData();
			sourceUri = mImageCaptureUri;
			startCropImage(requestCode);
		} else if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
// atencion pasa por aqui:
			try {
				InputStream inputStream = getContentResolver().openInputStream(data.getData());
				FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
				copyStream(inputStream, fileOutputStream);
				fileOutputStream.close();
				inputStream.close();
				sourceUri = data.getData();
				startCropImage(requestCode);
			} catch (Exception e) {

			}

		} else if (requestCode == REQUEST_CODE_CROP_IMAGE && resultCode == RESULT_OK && data != null) {
			//String path = data.getStringExtra(CropImage.IMAGE_PATH);
			////Uri selectedImage = Uri.fromFile(new File(path));
			//Uri selectedImage = FileProvider.getUriForFile(Pantalla1.this, BuildConfig.APPLICATION_ID + ".provider", new File(path));

			final Uri selectedImage = UCrop.getOutput(data);
			Intent myIntent;

			myIntent = new Intent(Pantalla1.this, Pantalla2.class);

			myIntent.putExtra("picture", selectedImage);

			camara = 0;
			myIntent.putExtra("camara", camara);


			origen = 1;
			myIntent.putExtra("origen", origen);
			//toast1 = Toast.makeText(getApplicationContext(), "Please, no heads turned", Toast.LENGTH_SHORT);
			//toast1.show();
			startActivity(myIntent);
		}
	}
        @Override
        public void onBackPressed() {
           
          		SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
              	
              	String rate = settings.getString("valorratecaricam", "N");
              	if (rate.equalsIgnoreCase("N")){
					alertDialogRate = builderRate.create();
                  	alertDialogRate.show();
              	} else if (vista != null){  	
              		//displayInterstitial();
              		finish();
              	}	 
          }
	// Invoca displayInterstitial() cuando est preparado para mostrar un intersticial.
	public void displayInterstitial() {
		if (interstitial != null) {
			interstitial.setFullScreenContentCallback(new FullScreenContentCallback(){
				@Override
				public void onAdDismissedFullScreenContent() {
					// Called when fullscreen content is dismissed.
					//Log.d("TAG", "The ad was dismissed.");
				}

				@Override
				public void onAdFailedToShowFullScreenContent(AdError adError) {
					// Called when fullscreen content failed to show.
					//Log.d("TAG", "The ad failed to show.");
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
		}
	}
 //      public static String getRealPathFromURI(Uri contentUri, Activity activity) {
        

        //    String[] proj = {
         //       MediaStore.Images.Media.DISPLAY_NAME
         //   };

           // Cursor cursor = activity.managedQuery(contentUri, proj, null, null,
            //         null);

         //   int column_index = cursor
         //            .getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);

         //   cursor.moveToFirst();
          //  return cursor.getString(column_index);
        //}
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
    private void checkAndRequestAppPermissions() {
        // --- Manejo del permiso de Cámara ---
        int permissionCheckC = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheckC != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);
        }

        // --- Manejo de permisos de Almacenamiento/Multimedia ---
        // Comprobamos la versión de Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 (API 33) o superior
            // Para Android 13+, solicitamos los permisos de medios granulares si necesitamos leer de la galería
            // Si solo estás CREANDO archivos propios (ej. una foto que toma tu app), a menudo NO necesitas estos permisos,
            // y debes usar MediaStore API para guardar la foto directamente.
            boolean hasReadMediaImages = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
            boolean hasReadMediaVideo = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED;
            boolean hasReadMediaAudio = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED;

            // Prepara una lista de permisos que realmente necesitas y que no tienes
            String[] permissionsToRequest = new String[3]; // Max 3, ajusta según lo que necesites
            int index = 0;
            if (!hasReadMediaImages) {
                permissionsToRequest[index++] = Manifest.permission.READ_MEDIA_IMAGES;
            }
            if (!hasReadMediaVideo) {
                permissionsToRequest[index++] = Manifest.permission.READ_MEDIA_VIDEO;
            }
            if (!hasReadMediaAudio) {
                permissionsToRequest[index++] = Manifest.permission.READ_MEDIA_AUDIO;
            }

            // Si hay permisos pendientes, los solicitamos
            if (index > 0) {
                String[] finalPermissions = new String[index];
                System.arraycopy(permissionsToRequest, 0, finalPermissions, 0, index);
                ActivityCompat.requestPermissions(this,
                        finalPermissions,
                        PERMISSIONS_REQUEST_MEDIA_OR_STORAGE);
            }

            // Nota: El permiso WRITE_EXTERNAL_STORAGE ya no se solicita ni se usa para la mayoría de los casos
            // en Android 13+. Si tu aplicación CREA sus propios archivos multimedia (fotos/videos tomados por la app),
            // usa MediaStore API directamente sin solicitar permisos de almacenamiento.
            // Si CREA archivos NO multimedia (ej. PDFs, TXT) que son propios de la app, lo ideal es usar SAF o los
            // directorios específicos de la app, sin permisos de almacenamiento.

        } else { // Para versiones anteriores a Android 13 (API < 33, ej. Android 6 a Android 12)
            // Aquí es donde tu permiso WRITE_EXTERNAL_STORAGE sigue siendo relevante
            int permissionCheckW = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheckW != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_MEDIA_OR_STORAGE);
            }
            // También puedes necesitar READ_EXTERNAL_STORAGE aquí si lees archivos de otras apps
            int permissionCheckR = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheckR != PackageManager.PERMISSION_GRANTED) {
                 ActivityCompat.requestPermissions(this,
                         new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                         PERMISSIONS_REQUEST_MEDIA_OR_STORAGE);
             }
        }
    }
}
