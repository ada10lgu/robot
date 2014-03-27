package model.shape;

public class Line extends Shape {

	private double k,m;
	private Point p1,p2;
	
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		
		double dy = p1.y - p2.y;
		double dx = p1.x - p2.x;
		
		
		k = dy/dx;
		
		m = p1.y - p1.x*k;
		
	}
	
	public Line(double x1, double y1, double x2, double y2) {
		this(new Point(x1,y1),new Point(x2,y2));
	}
	
	private Line(Point p, double angle) {
		p1 = p;
		p2 = p;
		
		double x2 = Math.cos(angle);
		double y2 = Math.sin(angle);
		
		double dy = p.y - y2;
		double dx = p.x - x2;
		
		k = dy/dx;
		
		m = p1.y - p.x*k;
	}
	
	
	@Override
	public double distanceTo(Point p, double angle) {
		Line l = new Line(p,angle);
		
		Point collision = intersection(this, l);
		if (collision == null)
			return -1;
		
		if (!onLine(collision))
			return -1;
		
		if(Math.abs(p.angle(collision) - angle) > ACCEPTABLE_DEVIATION)
			return -1;
		
		return p.distanceTo(collision);
	}
	
	private boolean onLine(Point p) {
		double d = p1.distanceTo(p2);
		if (p.distanceTo(p1) > d)
			return false;
		if (p.distanceTo(p2) > d)
			return false;
		return true;
	}

	private Point intersection(Line l1, Line l2) {
		
		double deltaK = l1.k - l2.k;
		double deltaM = l2.m - l1.m;
		
		if (deltaK == 0)
			return null;
		
		double x = deltaM/deltaK;
		double y = l1.k*x+l1.m;
		
		return new Point(x,y);
		
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("y=").append(k).append("x+").append(m);
		
		return sb.toString();
	}
	
}
