package crazy.photo.warp;


public class ComicFilter0 implements IImageFilter{

	 SaturationModifyFilter saturationFx = new SaturationModifyFilter();
     GaussianBlurFilter blurFx = new GaussianBlurFilter();
     ImageBlender blender = new ImageBlender();
     ParamEdgeDetectFilter edgeDetectionFx = new ParamEdgeDetectFilter();
     ImageBlender edgeBlender = new ImageBlender();

     public ComicFilter0()
     {
         saturationFx.SaturationFactor = 1f;
         blurFx.Sigma = 1f;
         blender.Mixture = 1f;
       //PROBANDO
         blender.Mode = 7;
         edgeDetectionFx.Threshold = 0.25f;
         edgeDetectionFx.DoGrayConversion = true;
         edgeBlender.Mixture = 0.8f;
         //PROBANDO
         edgeBlender.Mode = 7;
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
