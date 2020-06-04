package unittests;

import renderer.ImageWriter;
import java.awt.*;
import org.junit.Test;

public class ImageWriterTest {

	/**
	 * Test method for
     * {@link renderer.ImageWriter#all functions}.
	 */
	@Test
	public void testWriteToImage() {
		
			ImageWriter imageWriter = new ImageWriter("Testing Image Writing", 1600, 1000, 800, 500); //create an image
			
	        int Nx = imageWriter.getNx();
	        int Ny = imageWriter.getNy();
	        
	        for (int i = 0; i < Ny; i++)
	        {
	            for (int j = 0; j < Nx; j++)
	            {
	                if (i % 50 == 0 || j % 50 == 0)
	                {
	                    imageWriter.writePixel(j, i, Color.BLACK); //drawing black strips in regular intervals
	                }
	                else
	                {
	                    imageWriter.writePixel(j, i, Color.GREEN);
	                }
	            }
	        }
	        imageWriter.writeToImage(); //finally make this image
	}

}