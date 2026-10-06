package crazy.photo.warp;

import java.util.ArrayList;
import java.util.List;

import crazy.photo.warp.Gradient.TintColors;
import crazy.photo.warp.ImageBlender.BlendMode;

import android.graphics.Color;

public class XRadiationFilter implements IImageFilter{

	private GradientMapFilter gradientMapFx = new GradientMapFilter();
	private ImageBlender blender = new ImageBlender();

	public XRadiationFilter(){
	    List<Integer> colors = new ArrayList<Integer>();
	    colors.add(TintColors.LightCyan());
	    colors.add(Color.BLACK);
	    gradientMapFx.Map = new Gradient(colors);
	    blender.Mode = BlendMode.ColorBurn;
	    blender.Mixture = 0.8f;
	}

	 //@Override
    public Image process(Image imageIn) {
    	imageIn = this.gradientMapFx.process(imageIn);
    	imageIn = this.blender.Blend(imageIn, imageIn);
    	return imageIn;
    }
}
