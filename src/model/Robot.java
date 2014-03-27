package model;

import java.util.ArrayList;

import model.shape.Point;
import model.shape.Shape;

public class Robot {

	private ArrayList<Shape> shapes;

	public Robot() {
		shapes = new ArrayList<>();
	}

	public void addShape(Shape s) {
		shapes.add(s);
	}

	public Point distanceTo(Point p, double angle) {
		double distance = Double.POSITIVE_INFINITY;

		for (Shape s : shapes) {
			double d = s.distanceTo(p, angle);
			System.out.println(d);
			if (d != -1 && d < distance)
				distance = d;
		}
		
		if (distance == Double.POSITIVE_INFINITY)
			return null;
		
		double x = Math.cos(angle) * distance;
		double y = Math.sin(angle) * distance;

		return new Point(x,y);
	}

}
