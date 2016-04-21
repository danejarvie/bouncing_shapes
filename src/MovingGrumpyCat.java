import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MovingGrumpyCat extends MovingImage {

	public MovingGrumpyCat(){
		super();
	}
	
	public MovingGrumpyCat(int x, int y, int pw, int w, int h,  int mw, int mh, Color bc, int pathType) {
		super(x ,y , pw, w, h ,mw ,mh ,bc, pathType);
		try {
			img = ImageIO.read(new File("C:\\Users\\Dane\\Documents\\UNI\\Java\\Projects\\CS230 Asst2\\src\\grumpycat2.png"));
			//img = new ImageIcon("C:\\Users\\Dane\\Documents\\UNI\\Java\\Projects\\CS230 Asst2\\src\\CHDancing.gif").getImage();
		}
		
		catch (IOException e){
		 
			e.printStackTrace();
		}
		
	}
	
	
}
