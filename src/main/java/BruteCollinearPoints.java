import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by jmarin on 12/15/16.
 */
public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments;
    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points

        if (points == null) {
            throw new NullPointerException();
        }

        segments = new ArrayList<>();
        for (int i = 0; i < points.length-3; i +=  1) {

            for (int j = i+1; j < points.length-2; j +=  1) {

                double i2j = points[i].slopeTo(points[j]);

                ArrayList<Point> matching = new ArrayList<>();
                matching.add(points[i]);
                matching.add(points[j]);

                for (int k = j+1; k < points.length-1; k +=  1) {

                    double i2k = points[i].slopeTo(points[k]);
                    if (i2j ==  i2k) {
                        matching.add(points[k]);

                        for (int l = k + 1; l < points.length; l +=  1) {

                            double i2l = points[i].slopeTo(points[l]);
                            if (i2j ==  i2l) {
                                matching.add(points[l]);
                            }
                        }
                    }
                }

                if (matching.size() >=  4) {
                    Point min = matching.get(0);
                    Point max = matching.get(0);
                    for (Point p : matching) {
                        min = min(min, p);
                        max = max(max, p);
                    }

                    LineSegment ls = new LineSegment(min, max);
                    segments.add(ls);
                }
            }
        }
    }

    // Would prefer to use Java 8 features that eliminate much of the redundant code
    // but the course doesn't allow pulling in any class that is not in java.lang
    // or java.utl
    private static Point max(Point a, Point b) {
        if (a ==  null || b ==  null) {
            throw new IllegalArgumentException();
        }

        if (a.compareTo(b) > 0) {
            return a;
        } else {
            return b;
        }
    }

    private static Point min(Point a, Point b) {
        if (a ==  null || b ==  null) {
            throw new IllegalArgumentException();
        }

        if (a.compareTo(b) <=  0) {
            return a;
        } else {
            return b;
        }
    }

    public int numberOfSegments() {       // the number of line segments
        return segments.size();
    }

    public LineSegment[] segments() {                // the line segments
        return segments.toArray(new LineSegment[segments.size()]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }



}
