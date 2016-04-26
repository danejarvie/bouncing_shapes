/*	Dane Jarvie
 * 	UPI: djar004
 * 	ID: 2521969
 */

import java.awt.*;
import java.awt.geom.GeneralPath;

public class MovingPolygon extends MovingRectangle {
	
	protected GeneralPath polygon;
	
	
	public MovingPolygon(){
		super();
	}
	
	public MovingPolygon(int x, int y, float pw, int w, int h,  int mw, int mh, Color bc, int pathType){
		super(x , y, pw, w, h, mw, mh, bc, pathType);
		
	}
	
	public boolean contains(Point mousePt){
		return polygon.contains(mousePt);
	}
	
	public void createPolygon(int x, int y, int w, int h){}
	
	/** draw the polygon with the fill colour
	 *	If it is selected, draw the handles
	 *	@param g	the Graphics control
	 */
	public void draw(Graphics g) {
		    
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(penWidth));
		g2d.setPaint(borderColor);
		createPolygon(p.x, p.y, width, height);
		g2d.draw(polygon);
		drawHandles(g);
	}
}
