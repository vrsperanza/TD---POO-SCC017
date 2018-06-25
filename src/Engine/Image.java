package Engine;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

import javax.swing.GrayFilter;

/**
 * Image helper methods
 */
public class Image {
	// Generates a BufferedImage from an image
	public static BufferedImage toBufferedImage(java.awt.Image img)
	{
	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	// Turns the given image to its grayscale equivalent
	public static BufferedImage toGray(BufferedImage colorImage){
		ImageFilter filter = new GrayFilter(true, 50);  
		ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);  
		java.awt.Image mage = Toolkit.getDefaultToolkit().createImage(producer);
		return toBufferedImage(mage);
	}
}
