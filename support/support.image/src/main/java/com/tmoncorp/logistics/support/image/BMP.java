package in.java.support.image;

import java.io.IOException;

import javax.imageio.ImageReader;

import in.java.support.image.Bitmap.Format;
import in.java.support.image.options.AbWriteOption;

public class BMP extends BitmapImage
{
	BMP(ImageReader reader, Format format) throws IOException
	{
		super(reader, format);
	}

	public static class WriteOption extends AbWriteOption
	{
		@Override
		public Format format()
		{
			return Format.BMP;
		}
	}
}
