/*	Dane Jarvie
 * 	UPI: djar004
 * 	ID: 2521969
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class MovingPlus extends MovingPolygon {

	public MovingPlus(){
		super();
	}
	
	public MovingPlus(int x, int y, float pw, int w, int h,  int mw, int mh, Color bc, int pathType){
		super(x , y, pw, w, h, mw, mh, bc, pathType);
	}
	
	public void createPolygon(int x, int y, int w, int h){
		int scaleWidth = w / 4;
		int scaleHeight = h / 4;
		polygon = new GeneralPath();
		polygon.moveTo(x+scaleWidth, y); //Starting point
		polygon.lineTo(x+(3*scaleWidth), y);  //Top line
		polygon.lineTo(x+(3*scaleWidth), y+scaleHeight); //Top right corner
		polygon.lineTo(x+w, y+scaleHeight); //Top right corner
		polygon.lineTo(x+w, y+(3*scaleHeight));  //Right side
		polygon.lineTo(x+(3*scaleWidth), y+(3*scaleHeight)); //Bottom right corner
		polygon.lineTo(x+(3*scaleWidth), y+h);
		polygon.lineTo(x+scaleWidth, y+h);
		polygon.lineTo(x+scaleWidth, y+(3*scaleHeight));
		polygon.lineTo(x, y+(3*scaleHeight));
		polygon.lineTo(x, y+scaleHeight);
		polygon.lineTo(x+scaleWidth, y+scaleHeight);
		polygon.lineTo(x+scaleWidth, y);
		polygon.closePath();
	}
	

}
