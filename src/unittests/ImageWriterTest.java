package unittests;

import renderer.ImageWriter;
import java.awt.*;
import org.junit.Test;

public class ImageWriterTest {

    @Test
    public void ImageWiterWriteToImageTest() {
        ImageWriter imageWriter = new ImageWriter("AvitalTal", 1600, 1000, 800, 500);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.WHITE);
                } else {
                    imageWriter.writePixel(j, i, Color.RED);
                }
            }
        }
        imageWriter.writeToImage();
    }
}