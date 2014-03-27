package manualGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Robot;
import model.shape.Line;
import model.shape.Point;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private Robot model;
	private ArrayList<Point> points;
	private Point person;

	public GUI(Robot model) {
		this.model = model;
		points = new ArrayList<>();
		person = new Point(0,0);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		add(new Grid(this));

		new Spyglass(this).start();


		setVisible(true);
		repaint();

	}

	private class Spyglass extends Thread {

		GUI frame;

		public Spyglass(GUI frame) {
			this.frame = frame;
		}

		@Override
		public void run() {
			try {
				int n = 10000;
				while (true) {

					double dt = 2 * Math.PI / n;

					for (int i = 0; i < n; i++) {
						double angle = i*dt;
						Point p = model.distanceTo(person, angle);
						if (p != null) {
							synchronized (frame) {
								System.out.println(p);
								points.add(p);
							}
							repaint();
						}
						Thread.sleep(1);
					}
					System.out.println("One loop done");
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private class Grid extends JPanel {

		Point origo;
		GUI frame;

		public Grid(GUI frame) {
			this.frame = frame;
		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;

			BufferedImage canvas = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			{
				int x = getWidth() / 2;
				int y = getHeight() / 2;

				origo = new Point(x, y);
			}
			synchronized (frame) {

				for (Point p : points) {
					int x = (int) (origo.x + p.x);
					int y = (int) (origo.y - p.y);
					canvas.setRGB(x, y, Color.BLACK.getRGB());
				}

			}
			g2.drawImage(canvas, null, null);
		}
	}

	public static void main(String[] args) {
		Robot r = new Robot();

		r.addShape(new Line(1, 1, 5, 5));
		r.addShape(new Line(5, 5, 5, 10));

		new GUI(r);
	}

}
