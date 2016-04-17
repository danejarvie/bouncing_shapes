/*
 *	===============================================================================
 *	MovingShape.java : The superclass of all shapes.
 *	A shape has a point (top-left corner).
 *	A shape defines various properties, including selected, colour, width and height.
 *	===============================================================================
 */

import java.awt.*;
public abstract class MovingShape {

	public int marginWidth, marginHeight; // the margin of the animation panel area
	protected Point p; 					// the top left coner of shapes
	protected int width;				// the width of shapes
	protected int height;				// the height of shapes
	protected MovingPath path;			// the moving path of shapes
	protected Color borderColor;		// the border colour of shapes
	protected boolean selected = false;	// draw handles if selected
	protected int penWidth;				// the pen width of shapes
	

	/** constuctor to create a shape with default values
	 */
	public MovingShape() {
		this(0, 0, 1, 20, 20, 500, 500, Color.blue, 0); // the default properties
	}

	/** constuctor to create a shape
	 * @param x 		the x-coordinate of the new shape
	 * @param y		the y-coordinate of the new shape
	 * @param w 		the width of the new shape
	 * @param h 		the height of the new shape
	 * @param mw 		the margin width of the animation panel
	 * @param mh		the margin height of the animation panel
	 * @param c		the colour of the new shape
	 * @param typeOfPath 		the path of the new shape
	 */
	public MovingShape(int x, int y, int pw, int w, int h, int mw, int mh, Color c,  int pathType) {
		p = new Point(x,y);
		penWidth = pw;
		marginWidth = mw;
		marginHeight = mh;
		width = w;
		height = h;
		borderColor = c;
		setPath (pathType);
	}

	/** Return the x-coordinate of the shape.
	 * @return the x coordinate
	 */
	public int getX() { return p.x; }

	/** Return the y-coordinate of the shape.
	 * @return the y coordinate
	 */
	public int getY() { return p.y;}
	
	/** Set the penWidth of the shape
	 * @param pw	the penWidth value.
	 */
	public void setPenWidth(int pw) {penWidth = pw; }

	/** Return the selected property of the shape.
	 * @return the selected property
	 */
	public boolean isSelected() { return selected; }

	/** Set the selected property of the shape.
	 *	When the shape is selected, its handles are shown.
	 * @param s 	the selected value
	 */
	public void setSelected(boolean s) { selected = s; }

	/** Set the width of the shape.
	 * @param w 	the width value
	 */
	public void setWidth(int w) { width = w; }

	/** Set the height of the shape.
	 * @param h 	the height value
	 */
	public void setHeight(int h) { height = h; }

	/** Set the border colour of the shape.
	 * @param c 	the border colour
	 */
	public void setBorderColor(Color c) { borderColor = c; }

	/**
	 * Return a string representation of the shape, containing
	 * the String representation of each element.
	 */
	public String toString() {
		return "[" + this.getClass().getName() + "," + p.x + "," + p.y + "]";
	}

	/** Draw the handles of the shape
	 * @param g 	the Graphics control
	 */
	public void drawHandles(Graphics g) {
		// if the shape is selected, then draw the handles
		if (isSelected()) {
			g.setColor(Color.black);
			g.fillRect(p.x -2, p.y-2, 4, 4);
			g.fillRect(p.x + width -2, p.y + height -2, 4, 4);
			g.fillRect(p.x -2, p.y + height -2, 4, 4);
			g.fillRect(p.x + width -2, p.y-2, 4, 4);
		}
	}

	/** Reset the margin for the shape
	 * @param w 	the margin width
	 * @param h 	the margin height
	 */
	public void setMarginSize(int w, int h) {
		marginWidth = w;
		marginHeight = h;
	}

	/** abstract contains method
	 * Returns whether the point p is inside the shape or not.
	 * @param p	the mouse point
	 */
	public abstract boolean contains(Point p);

	/** abstract draw method
	 * draw the shape
	 * @param g 	the Graphics control
	 */
	public abstract void draw(Graphics g);

	/** Set the path of the shape.
	 * @param pathID 	the integer value of the path
	 *	MovingPath.FALLING is the falling path
	 */
	public void setPath(int pathID) {
		switch (pathID) {
			case MovingPath.FALLING : {
				path = new FallingPath();
				break;
			}
		}
	}

	/** move the shape by the path
	 */
	public void move() {
		path.move();
	}

	// Inner class ===================================================================== Inner class

	/*
	 *	===============================================================================
	 *	MovingPath : The superclass of all paths. It is an inner class.
	 *	A path can change the current position of the shape.
	 *	===============================================================================
	 */

	public abstract class MovingPath {
		public static final int FALLING = 0; // The Id of the moving path
		protected int deltaX, deltaY; // moving distance

		/** constructor
		 */
		public MovingPath() { }

		/** abstract move method
		* move the shape according to the path
		*/
		public abstract void move();
	}

	/*
	 *	===============================================================================
	 *	FallingPath : A falling path.
	 *	===============================================================================
	 */
	public class FallingPath extends MovingPath {
		private double am = 0, stx =0, sinDeltax = 0;

		/** constructor to initialise values for a falling path
		 */
		public FallingPath() {
			am = Math.random() * 20; //set amplitude variables
			stx = 0.5; //set step variables
			deltaY = 5;
			sinDeltax = 0;
		}

		/** move the shape
		 */
		public void move() {
			sinDeltax = sinDeltax + stx;
			p.x = (int) Math.round(p.x + am * Math.sin(sinDeltax));
			p.y = p.y + deltaY;
			if (p.y > marginHeight) // if it reaches the bottom of the frame, start again from the top
				p.y = 0;
			}
	}

}