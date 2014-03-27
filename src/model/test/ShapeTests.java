package model.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import model.shape.Line;
import model.shape.Point;
import model.shape.Shape;

import org.junit.Test;

public class ShapeTests {

	@Test
	public void testToCreateAPoint() {
		Random r = new Random();
		double x = r.nextDouble();
		double y = r.nextDouble();
		
		Point p = new Point(x,y);
		
		assertEquals(x,p.x,0);
		assertEquals(y,p.y,0);
		
	}
	
	@Test
	public void testToChangePoint() {
		Random r = new Random();
		double x = r.nextDouble();
		double y = r.nextDouble();
		
		Point p = new Point(0,0);
		p.x = x;
		p.y = y;
		assertEquals(x,p.x,0);
		assertEquals(y,p.y,0);
	}

	@Test
	public void TestPointSeesLine() {
		Point p1 = new Point(-10,1);
		Point p2 = new Point(10,1);
		Line l = new Line(p1,p2);
		
		Point eye = new Point(0,0);
		double angle = Math.toRadians(45);
		
		double distance = l.distanceTo(eye, angle);
		
		double correct = Math.sqrt(2);
		
		assertEquals(correct, distance,0);
		
	}
	
	@Test
	public void TestPointAngles() {
		Point p1 = new Point(0,0);
		Point p2 = new Point(1,0);
		Point p3 = new Point(1,1);
		Point p4 = new Point(0,1);
		
		double correct11 = 0;
		double correct22 = 0;
		double correct33 = 0;
		double correct44 = 0;
		double correct12 = 0;
		double correct13 = Math.toRadians(45);
		double correct14 = Math.PI/2;
		
		assertEquals(correct11, p1.angle(p1),0);
		assertEquals(correct22, p2.angle(p2),0);
		assertEquals(correct33, p3.angle(p3),0);
		assertEquals(correct44, p4.angle(p4),0);
		
		assertEquals(correct12, p1.angle(p2),0);
		assertEquals(correct13, p1.angle(p3), Shape.ACCEPTABLE_DEVIATION);
		assertEquals(correct14, p1.angle(p4), Shape.ACCEPTABLE_DEVIATION);
		
		
		
	}
	
	@Test
	public void TestPointDontSeesLine() {
		Point p1 = new Point(-10,1);
		Point p2 = new Point(10,1);
		Line l = new Line(p1,p2);
		
		Point eye = new Point(0,0);
		double angle = Math.toRadians(-45);
		
		double distance = l.distanceTo(eye, angle);
		
		double correct = -1;
		
		assertEquals(correct, distance,0);
		
	}
	
}
