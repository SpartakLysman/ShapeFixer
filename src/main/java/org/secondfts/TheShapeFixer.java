package org.secondfts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TheShapeFixer {

    private boolean doIntersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Handle collinear
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    private int orientation(Point2D p, Point2D q, Point2D r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    private boolean onSegment(Point2D p, Point2D q, Point2D r) {
        return (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y));
    }

    public boolean isValid(Shape2D shape) {
        List<Point2D> points = shape.getPoints();

        if (!points.get(0).equals(points.get(points.size() - 1))) {
            return false;
        }

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 2; j < points.size() - 1; j++) {
                if (i == 0 && j == points.size() - 2) {
                    continue;
                }

                Point2D p1 = points.get(i);
                Point2D q1 = points.get(i + 1);
                Point2D p2 = points.get(j);
                Point2D q2 = points.get(j + 1);

                if (doIntersect(p1, q1, p2, q2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Shape2D repair(Shape2D shape) {
        List<Point2D> points = new ArrayList<>(shape.getPoints());

        // Remove sub-contours
        List<Point2D> filteredPoints = removeSubContours(points);

        // Closing the shape properly
        if (!filteredPoints.get(0).equals(filteredPoints.get(filteredPoints.size() - 1))) {
            filteredPoints.add(filteredPoints.get(0));
        }

        return new Shape2D(filteredPoints);
    }

    private List<Point2D> removeSubContours(List<Point2D> points) {
        List<Point2D> filteredPoints = new ArrayList<>();
        Set<Point2D> uniquePoints = new HashSet<>();

        filteredPoints.add(points.get(0));
        uniquePoints.add(points.get(0));

        for (int i = 1; i < points.size() - 1; i++) {
            Point2D current = points.get(i);

            if (!uniquePoints.contains(current)) {
                if (!isInteriorPoint(current, filteredPoints)) {
                    filteredPoints.add(current);
                    uniquePoints.add(current);
                }
            }
        }
        return filteredPoints;
    }

    private boolean isInteriorPoint(Point2D point, List<Point2D> filteredPoints) {
        for (int i = 0; i < filteredPoints.size() - 1; i++) {
            Point2D p1 = filteredPoints.get(i);
            Point2D p2 = filteredPoints.get(i + 1);
            if (doIntersect(p1, p2, filteredPoints.get(0), point)) {
                return true;
            }
        }
        return false;
    }
}