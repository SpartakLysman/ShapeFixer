package org.secondfts;

import java.util.List;

public class Shape2D {

    List<Point2D> points;

    public Shape2D(List<Point2D> points) {
        this.points = points;
    }

    public List<Point2D> getPoints() {
        return points;
    }
}