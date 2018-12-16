package in.java.support.image;

import java.io.IOException;

import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;

import in.java.support.image.Bitmap.Format;
import in.java.support.image.options.IWriteOption;


public class Png extends BitmapImage
{
//	public Png(Bitmap bitmap) throws IOException
//	{
//		super(bitmap);
//	}
	
	Png(ImageReader reader, Format format) throws IOException
	{
		super(reader, format);
	}
	
	public static class WriteOption implements IWriteOption
	{
		public WriteOption(Format format) {
			this.format = format;
		}

		private Format format = Format.PNG;

		private boolean ignoreMetadata = false;

		public Format format() {
			return format;
		}

		public WriteOption(boolean ignoreMetadata) {
			this.ignoreMetadata = ignoreMetadata;
		}

		public boolean ignoreMetadata() {
			return ignoreMetadata;
		}

		public WriteOption ignoreMetadata(boolean ignoreMetadata)
		{
			this.ignoreMetadata = ignoreMetadata;
			return this;
		}

		@Override
		public ImageWriteParam build(ImageWriteParam writeParam)
		{
			return writeParam;
		}
	}
}
