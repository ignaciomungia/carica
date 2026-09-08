package crazy.photo.warp.Textures;

public interface ITextureGenerator {
	
	float[][] Generate(int width, int height);

	void Reset();

}
