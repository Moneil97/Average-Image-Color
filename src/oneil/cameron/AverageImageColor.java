package oneil.cameron;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class AverageImageColor {
	
	public AverageImageColor() {
		
		Image image = getImage();
		
	}
	
	private Image getImage(){
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			return ImageIO.read(chooser.getSelectedFile());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		new AverageImageColor();
	}

}
