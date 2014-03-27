package model.shape;

public abstract class Shape {
	
	public static final double ACCEPTABLE_DEVIATION = 0.000001;
	
	/**
	 * 
	 * @param p the point of the eye
	 * @param angle the angle of the eye in radians
	 * @return -1 if you don't see the shape otherwise the distance.
	 */
	public abstract double distanceTo(Point p, double angle);
}
