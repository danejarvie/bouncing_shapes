import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MovingImage extends MovingShape{
	
	protected BufferedImage img = null;

	public MovingImage(){
		super();
	}
	
	public MovingImage(int x, int y, int pw, int w, int h,  int mw, int mh, Color bc, int pathType) {
		super(x ,y , pw, w, h ,mw ,mh ,bc, pathType);
		try {
			img = ImageIO.read(new File("C:\\Users\\Dane\\Documents\\UNI\\Java\\Projects\\CS230 Asst2\\src\\GrumpyCat.jpg"));
		}
		catch (IOException e){
			//e.printStackTrace();
		}
	}
	
	@Override
	public boolean contains(Point mousePt) {
		return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)	&&	p.y <= mousePt.y && mousePt.y <= (p.y + height + 1));

	}

	@Override
	/** draw the rectangle with the fill colour
	 *	If it is selected, draw the handles
	 *	@param g	the Graphics control
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(penWidth));
		g2d.setPaint(borderColor);
		g2d.drawImage(img, p.x, p.y, width, height,null);
		drawHandles(g);
	}

}
