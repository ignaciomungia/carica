package crazy.photo.warp;

import crazy.photo.warp.ImageBlender.BlendMode;


public class ComicFilter implements IImageFilter{

	 SaturationModifyFilter saturationFx = new SaturationModifyFilter();
     GaussianBlurFilter blurFx = new GaussianBlurFilter();
     ImageBlender blender = new ImageBlender();
     ParamEdgeDetectFilter edgeDetectionFx = new ParamEdgeDetectFilter();
     ImageBlender edgeBlender = new ImageBlender();

     public ComicFilter()
     {
         saturationFx.SaturationFactor = 1f;
         blurFx.Sigma = 1f;
         blender.Mixture = 1f;
         blender.Mode = BlendMode.Lighten;
         edgeDetectionFx.Threshold = 0.25f;
         edgeDetectionFx.DoGrayConversion = true;
         edgeBlender.Mixture = 0.8f;
         edgeBlender.Mode = BlendMode.Lighten;
     }

     public Image process(Image input)
     {
         Image saturated = saturationFx.process(input.clone());
         Image blurred = blurFx.process(saturated);
         input = blender.Blend(saturated, blurred);
         Image edge = edgeDetectionFx.process(input.clone());
         return edgeBlender.Blend(input, edge);
     }
}
