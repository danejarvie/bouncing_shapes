/*	Dane Jarvie
 * 	UPI: djar004
 * 	ID: 2521969
 *
 *	===============================================================================
 *	MovingSquare.java : An extension of MovingRectangle. The AnimationPanel.java sets
 *	the width and height of a MovingSquare to both be equal to the minimum of the current
 *	values for width and height upon creation of a new MovingSquare.
 *	To ensure the MovingSquare remains a square shape after creation, the setHeight
 *	setWidth methods of MovingShape are over-ridden to set the side length to the
 *	minimum of the currentWidth and currentHeight inputs.
 *	===============================================================================
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MovingSquare extends MovingRectangle{
	public int side_len;
	
	/** constuctor to create a square with default values
	 */
	public MovingSquare() {
		super();
	}

	/** constuctor to create a square shape
	 */
	public MovingSquare(int x, int y, float pw, int w, int h,  int mw, int mh, Color bc, int pathType) {
		super(x ,y , pw, w, h ,mw ,mh ,bc, pathType);
		side_len = Math.min(w, h);
	}
	
	public void setWidth(int w){
		width = w;
		side_len = Math.min(w, height);
	}
	
	public void setHeight(int h){
		height = h;
		side_len = Math.min(h, width);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(penWidth));
		g2d.setPaint(borderColor);
		g2d.drawRect(p.x, p.y, side_len, side_len);
		drawHandles(g);
	}
	
	public void drawHandles(Graphics g) {
		// if the shape is selected, then draw the handles
		if (isSelected()) {
			g.setColor(Color.black);
			g.fillRect(p.x -2, p.y-2, 4, 4);
			g.fillRect(p.x + side_len -2, p.y + side_len -2, 4, 4);
			g.fillRect(p.x -2, p.y + side_len -2, 4, 4);
			g.fillRect(p.x + side_len -2, p.y-2, 4, 4);
		}
	}
}
