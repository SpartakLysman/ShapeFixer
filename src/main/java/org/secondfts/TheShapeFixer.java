package org.secondfts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheShapeFixer {

    public boolean isValid(Shape2D shape) {
        List<Point2D> points = shape.getPoints();

        // Check if closed or not
        if (!points.get(0).equals(points.get(points.size() - 1))) {
            return false;
        }

        // Check for duplicates
        Set<Point2D> pointSet = new HashSet<>();
        for (int i = 0; i < points.size() - 1; i++) {
            if (!pointSet.add(points.get(i))) {
                return false;
            }
        }

        return true;
    }

    public Shape2D repair(Shape2D shape) {
        List<Point2D> points = shape.getPoints();
        List<Point2D> repairedPoints = new ArrayList<>();
        Set<Point2D> pointSet = new HashSet<>();

        for (int i = 0; i < points.size(); i++) {
            Point2D currentPoint = points.get(i);

            if (!pointSet.contains(currentPoint)) {
                repairedPoints.add(currentPoint);
                pointSet.add(currentPoint);
            }
        }
        if (!repairedPoints.get(0).equals(repairedPoints.get(repairedPoints.size() - 1))) {
            repairedPoints.add(repairedPoints.get(0));
        }

        return new Shape2D(repairedPoints);
    }
}