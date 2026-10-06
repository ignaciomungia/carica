package crazy.photo.warp.Textures;

import crazy.photo.warp.IImageFilter;
import crazy.photo.warp.Image;

public class TexturerDibuFilter  implements IImageFilter{
	 // texture generator
	private ITextureGenerator textureGenerator;
    // generated texture
	private float[][] texture = null;

    // filtering factor
	private double filterLevel = 0.5;
    // preservation factor
    private double preserveLevel = 0.5;
    
    // preservation factor
    private int rgbMax = 0;


    /// <summary>
    /// Initializes a new instance of the <see cref="Texturer"/> class
    /// </summary>
    /// 
    /// <param name="texture">Generated texture</param>
    /// 
	public TexturerDibuFilter( float[][] texture )
	{
		this.texture = texture;
	}

    /// <summary>
    /// Initializes a new instance of the <see cref="Texturer"/> class
    /// </summary>
    /// 
    /// <param name="texture">Generated texture</param>
    /// <param name="filterLevel">Filter level value</param>
    /// <param name="preserveLevel">Preserve level value</param>
    /// 
	public TexturerDibuFilter( float[][] texture, double filterLevel, double preserveLevel )
	{
		this.texture        = texture;
		this.filterLevel    = Math.max( 0.0, Math.min( 1.0, filterLevel ) );
        this.preserveLevel  = Math.max( 0.0, Math.min( 1.0, preserveLevel ) );
	}

    /// <summary>
    /// Initializes a new instance of the <see cref="Texturer"/> class
    /// </summary>
    /// 
    /// <param name="generator">Texture generator</param>
    /// 
	public TexturerDibuFilter( ITextureGenerator generator )
	{
		this.textureGenerator = generator;
	}

    /// <summary>
    /// Initializes a new instance of the <see cref="Texturer"/> class
    /// </summary>
    /// 
    /// <param name="generator">Texture generator</param>
    /// <param name="filterLevel">Filter level value</param>
    /// <param name="preserveLevel">Preserve level value</param>
    /// 
    public TexturerDibuFilter(ITextureGenerator generator, double filterLevel, double preserveLevel, int rgbMax)
	{
		this.textureGenerator   = generator;
		this.filterLevel        = Math.max( 0.0, Math.min( 1.0, filterLevel ) );
        this.preserveLevel      = Math.max( 0.0, Math.min( 1.0, preserveLevel ) );
        this.rgbMax = rgbMax;
	}

	
    public Image process(Image imageIn) 
    {
        // get source image size
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();

        // processing region's dimension
        int widthToProcess = width;
        int heightToProcess = height;

        // if generator was specified, then generate a texture
        // otherwise use provided texture
        if ( textureGenerator != null )
        {
            texture = textureGenerator.Generate( width, height );
        }
        else
        {
            widthToProcess = width;//Math.Min( width, texture.GetLength( 1 ) );
            heightToProcess = height;//Math.Min( height, texture.GetLength( 0 ) );
        }

        int r, g, b;
     
        // texture
        for ( int y = 0; y < heightToProcess; y++ )
        {
            for ( int x = 0; x < widthToProcess; x++ )
            {
                double t = texture[y][x];
                r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y);
                // process each pixel
                if ((r + g + b) < rgbMax) {
                	r = (byte) Math.min( 255.0f, ( preserveLevel * r) + ( filterLevel * r) * t );
                	g = (byte) Math.min( 255.0f, ( preserveLevel * g) + ( filterLevel * g) * t );
                	b = (byte) Math.min( 255.0f, ( preserveLevel * b) + ( filterLevel * b) * t );
                }
                imageIn.setPixelColor(x, y, r, g, b);                
            }
        }
        return imageIn;
    }
}
