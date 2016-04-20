import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class MovingRotatingSquare extends MovingSquare{
	
	private AffineTransform tx;
	Shape[] rotatingSquare = new Shape[10];

	public MovingRotatingSquare() {
		super();
	}

	public MovingRotatingSquare(int x, int y, int pw, int w, int h, int mw, int mh, Color bc, int pathType) {
		super(x, y, pw, w, h, mw, mh, bc, pathType);
		tx = new AffineTransform();
	}
	
	public Shape[] createRotatingSquare(int x, int y, int sideLen, AffineTransform tx){
		rotatingSquare[0] = new Rectangle(x, y, sideLen, sideLen); //Create the initial square shape
		tx.setToRotation(30, x+(sideLen/2), y+(sideLen/2)); //Set the transformation to a rotation on the centre of the shape
		for (int i = 1; i < rotatingSquare.length; i++){
			//Fill the shape array with square shapes each rotated via the transformation
			rotatingSquare[i] = tx.createTransformedShape(rotatingSquare[i-1]);
		}
		return rotatingSquare;
	}
	
	/** draw the rectangle with the fill colour
	 *	If it is selected, draw the handles
	 *	@param g	the Graphics control
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(penWidth));
		g2d.setPaint(borderColor);
		createRotatingSquare(p.x, p.y, width, tx);
		for (int i = 0; i < rotatingSquare.length; i++){
			g2d.draw(rotatingSquare[i]);
		}
		drawHandles(g);
	}

}