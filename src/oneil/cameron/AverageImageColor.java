package oneil.cameron;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.DataBufferByte;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class AverageImageColor{
	
	public AverageImageColor() {
		
		BufferedImage image = (BufferedImage) getImage();
		
		int[][] pixels = convertTo2DWithoutUsingGetRGB(image);
		
		int reds=0, greens=0, blues=0;
		
		for (int[] row : pixels)
			for (int pix : row){
				Color c = new Color(pix);
				reds += c.getRed();
				greens += c.getGreen();
				blues += c.getBlue();
			}
				
		reds/= pixels.length*pixels[0].length;
		greens/= pixels.length*pixels[0].length;
		blues/= pixels.length*pixels[0].length;
		say(reds + " " + greens + " " + blues);
		
	}
	
	private void say(Object o) {
		System.out.println(o);
	}

	private Image getImage(){
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			say(chooser.getSelectedFile());
			return ImageIO.read(chooser.getSelectedFile());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//http://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image
	private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

	      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	      final int width = image.getWidth();
	      final int height = image.getHeight();
	      final boolean hasAlphaChannel = image.getAlphaRaster() != null;

	      int[][] result = new int[height][width];
	      if (hasAlphaChannel) {
	         final int pixelLength = 4;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	            result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      } else {
	         final int pixelLength = 3;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += -16777216; // 255 alpha
	            argb += ((int) pixels[pixel] & 0xff); // blue
	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	            result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      }

	      return result;
	   }


	public static void main(String[] args) {
		new AverageImageColor();
	}

}
