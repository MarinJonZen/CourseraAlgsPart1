import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by jmarin on 12/16/16.
 */
public class FastCollinearPoints {
    private ArrayList<LineSegment> segs;

    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
        if (points == null) {
            throw new NullPointerException();
        }
        segs = new ArrayList<>();

        // Starting at each point
        for (int i = 0; i < points.length; i += 1) {

            int offset = i+1;
            Comparator<Point> slopeOrd = points[i].slopeOrder();
            // Sort sub array by slope
            Arrays.sort(points, offset, points.length, slopeOrd);
            // Find all groupings by slope
            int beg = offset;
            int end = offset;
            for (int j = offset+1; j < points.length; j += 1) {

                // For each grouping, find min/max and create line segment
                if (points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                } else if (points[i].slopeTo(points[beg]) == points[i].slopeTo(points[j])) {
                    end = j;
                } else {
                    if ((end - beg + 1) >= 3) {
                        segs.add(toLineSegment(points, i, beg, end));
                    }
                    beg = j;
                }
            }
            if ((end - beg + 1) >= 3) {
                segs.add(toLineSegment(points, i, beg, end));
            }

        }

    }

    private LineSegment toLineSegment(Point[] points, int ref, int beg, int end) {
        Point min = points[ref];
        Point max = points[ref];
        for (int i = beg; i <= end; i += 1) {
            if (min.compareTo(points[i]) == 1) {
                min = points[i];
            }

            if (max.compareTo(points[i]) == -1) {
                max = points[i];
            }
        }

        return new LineSegment(min, max);
    }

    public           int numberOfSegments() {       // the number of line segments
        return segs.size();
    }
    public LineSegment[] segments() {               // the line segments
        return segs.toArray(new LineSegment[segs.size()]);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
