import java.util.Arrays;

public class Brute {

	public static void main(String[] args) {

		double start = System.currentTimeMillis();
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		// read in the input
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
			points[i].draw();
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < N; k++) {
					if (points[i].slopeTo(points[j]) != points[j].slopeTo(points[k]))
						continue;
					for (int m = k + 1; m < N; m++) {
						if (points[i].slopeTo(points[j]) != points[j].slopeTo(points[k])
								|| points[j].slopeTo(points[k]) != points[k].slopeTo(points[m]))
							continue;
						Point[] collinear = { points[i], points[j], points[k], points[m] };
						Arrays.sort(collinear);

						StdOut.print(
								collinear[0] + " -> " + collinear[1] + " -> " + collinear[2] + " -> " + collinear[3]);
						StdOut.println();
						collinear[0].drawTo(collinear[3]);

					}
				}
			}
		}

		// display to screen all at once
		StdDraw.show(0);

		// reset the pen radius
		StdDraw.setPenRadius();
		double end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}