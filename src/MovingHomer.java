/*	Dane Jarvie
 * 	UPI: djar004
 * 	ID: 2521969
 * 
 * ======================================================================
 *	MovingHomer.java : An extension of MovingImage.  Implements an image of Homer Simpson.
 *	There is provision in AnimationPanel.java to play a random sound clip (of 3) each
 *	time a new MovingHomer object is created.
 *	======================================================================
 */

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MovingHomer extends MovingImage {

	public MovingHomer(){
		super();
	}
	
	public MovingHomer(int x, int y, float pw, int w, int h,  int mw, int mh, Color bc, int pathType) {
		super(x ,y , pw, w, h ,mw ,mh ,bc, pathType);
		try {
			img = ImageIO.read(new File("src\\homer.png"));
			//img = new ImageIcon("C:\\Users\\Dane\\Documents\\UNI\\Java\\Projects\\CS230 Asst2\\src\\CHDancing.gif").getImage();
		}
		
		catch (IOException e){
		 
			e.printStackTrace();
		}
	}
}
