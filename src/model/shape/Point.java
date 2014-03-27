package model.shape;

public class Point {
	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(Point p) {
		return Math.hypot(x - p.x, y - p.y);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("p(").append(x).append(",").append(y).append(")");

		return sb.toString();
	}

	public double angle(Point p) {
		if (equals(p))
			return 0;

		double dx = p.x-x;
		double hyp = distanceTo(p);
		
		return Math.acos(dx/hyp);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		return distanceTo((Point) obj) < Shape.ACCEPTABLE_DEVIATION;

	}
}
