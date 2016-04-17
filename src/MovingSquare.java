import java.awt.Color;

public class MovingSquare extends MovingRectangle{

	/** constuctor to create a square with default values
	 */
	public MovingSquare() {
		super();
	}

	/** constuctor to create a square shape
	 */
	public MovingSquare(int x, int y, int pw, int w, int h,  int mw, int mh, Color bc, int pathType) {
		super(x ,y , pw, w, h ,mw ,mh ,bc, pathType);
	}
}
