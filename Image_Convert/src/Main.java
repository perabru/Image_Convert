
import java.awt.Component;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Main extends Component {

	// Global variable that are responsible to get the position X and Y
	// https://www.mobilefish.com/services/record_mouse_coordinates/record_mouse_coordinates.php
	private int i;
	private int j;
	ArrayList<Integer> arrGrade = new ArrayList<Integer>();
	// Importing the CompareFeedBack class
	CompareFeedback cfb = new CompareFeedback();

	private boolean flag = false;

	// Initializes the program
	public static void main(String[] Args) {
		// Method responsible to start all things
		new Main();

	}
	// --------------------------------------------------------------------------------

	// Method that prints the pixel in the screen
	public void printPixelARGB(int pixel) {
		// The memory has 4 slots so I had to move the pixel to fit

		// Alpha defines the opacity of the pixel, as closer of 255 closer of white
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;

		System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);

		// Filtering the pixels I want
		// ---------------------------------------------------------------------------------------
		// Doing some unicorn magic here

		if (red <= 0 && green <= 0 && blue <= 0) {

			WriteBlackPixelsTxt(j, i, alpha, red, green, blue);

			arrGrade.add(j);
			arrGrade.add(i);

			i = i + 50;
			if (i >= 470) {
				i = 469;
			}

		}

	}

	// ----------------------------------------------------------------------------

	// Method responsible to run through all image
	private void marchThroughImage(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		// Width and Height - not that important
		System.out.println("width, height: " + w + ", " + h);

		// For to walk for all image Width and Height
		for (i = 0; i < h; i++) {
			for (j = 0; j < w; j++) {
				// X and Y position - IMPORTANT
				System.out.println("x,y: " + j + ", " + i);
				int pixel = image.getRGB(j, i);

				// Method to print ARGB
				printPixelARGB(pixel);
				System.out.println("");
			}
		}
	}
	// ---------------------------------------------------------------------

	public Main() {

		while (flag != true) {
			try {

				// get the BufferedImage, using the ImageIO class
				BufferedImage image = ImageIO.read(this.getClass().getResource("gabarito1.png"));
				marchThroughImage(image);

			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			flag = true;
		}

		// get the BufferedImage, using the ImageIO class
		BufferedImage image;
		try {
			image = ImageIO.read(this.getClass().getResource("gabarito2.png"));
			marchThroughImage(image);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * for (int z = 0; z < arrGrade.size(); z++) {
		 * System.out.println(arrGrade.get(z)); // JOptionPane.showMessageDialog(null,
		 * arrGrade.size() + " TOTAL " + z); }
		 */
		cfb.compareAns(arrGrade);
	}

	// ------------------------------------------------------------
	// If I print the arraylist here it will be 40 elements
	/*
	 * for(int z = 0; z < arrGrade.size(); z++) {
	 * //System.out.println(arrGrade.get(z)); JOptionPane.showMessageDialog(null,
	 * arrGrade.size()); }
	 */

	// -----------------------------------------------------------------------------------------------------

	// Write all elements into txt file slowdow the software.
	public void WriteBlackPixelsTxt(int x, int y, int alpha, int red, int green, int blue) {
		try {

			if (flag == false) {
				// Stored the image in this path
				BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\output\\grade.txt", true));

				// Write the pixels and position I want in a text flie
				bw.write("Posicao XY:" + String.valueOf(x) + " " + String.valueOf(y) + " argb: " + String.valueOf(alpha)
						+ ", " + String.valueOf(red) + ", " + String.valueOf(green) + ", " + String.valueOf(blue)
						+ "  ");

				bw.flush();
				bw.close();

				// Matlab ploting - not working properly tururu ;-;
				/*
				 * bw.write( String.valueOf(x) + "," + String.valueOf(y)+")"); bw.newLine();
				 * bw.write("hold on"); bw.newLine(); bw.flush(); bw.close();
				 */

			} else if (flag == true) {
				// Stored the image in this path
				BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\output\\studentgrade.txt", true));

				// Write the pixels and position I want in a text flie
				bw.write("Posicao XY:" + String.valueOf(x) + " " + String.valueOf(y) + " argb: " + String.valueOf(alpha)
						+ ", " + String.valueOf(red) + ", " + String.valueOf(green) + ", " + String.valueOf(blue)
						+ "  ");
				bw.flush();
				bw.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.out.println("ARQUIVO GERADO");

	}

}
