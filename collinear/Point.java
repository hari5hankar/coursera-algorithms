import java.util.Comparator;

public class Point implements Comparable<Point> {
	public final Comparator<Point> SLOPE_ORDER; // compare points by slope to
												// this point

	// construct the point (x, y)
	public Point(int x, int y) {
		
	}

	// draw this point
	public void draw() {

	}

	// draw the line segment from this point to that point
	public void drawTo(Point that) {

	}

	// string representation
	public String toString() {

	}

	// is this point lexicographically smaller than that point?
	public int compareTo(Point that) {

	}

	// the slope between this point and that point
	public double slopeTo(Point that) {

	}
}