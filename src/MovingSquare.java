/*	Dane Jarvie
 * 	UPI: djar004
 * 	ID: 2521969
 *
 *	===============================================================================
 *	MovingSquare.java : An extension of MovingRectangle. The AnimationPanel.java sets
 *	the width and height of a MovingSquare to both be equal to the minimum of the current
 *	values for width and height.
 *	===============================================================================
 */
import java.awt.Color;

public class MovingSquare extends MovingRectangle{

	/** constuctor to create a square with default values
	 */
	public MovingSquare() {
		super();
	}

	/** constuctor to create a square shape
	 */
	public MovingSquare(int x, int y, float pw, int w, int h,  int mw, int mh, Color bc, int pathType) {
		super(x ,y , pw, w, h ,mw ,mh ,bc, pathType);
	}
}
