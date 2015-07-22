import java.util.Arrays;

public class Fast {

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

		Point[] aux = new Point[points.length];
		System.arraycopy(points, 0, aux, 0, points.length);

		for (int i = 0; i < N; i++) {
			Arrays.sort(aux, points[i].SLOPE_ORDER);
			for (int j = 1; j < N - 2; j++) {
				if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[j + 1])
						&& points[i].slopeTo(points[j + 1]) == points[i].slopeTo(points[j + 2])) {

					Point[] collinear = { points[i], points[j], points[j + 1], points[j + 2] };
					Arrays.sort(collinear);

					StdOut.print(collinear[0] + " -> " + collinear[1] + " -> " + collinear[2] + " -> " + collinear[3]);
					StdOut.println();
					collinear[0].drawTo(collinear[3]);
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