package org.secondfts;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println();

        Shape2D validShape = new Shape2D(Arrays.asList(
                new Point2D(0, 0), new Point2D(1, 0),
                new Point2D(1, 1), new Point2D(0, 1),
                new Point2D(0, 0)
        ));

        Shape2D invalidShapeWithDuplicatePoints = new Shape2D(Arrays.asList(
                new Point2D(0, 0), new Point2D(1, 0),
                new Point2D(1, 1), new Point2D(0, 1),
                new Point2D(0, 0), new Point2D(0, 1)
        ));

        Shape2D invalidOpenShape = new Shape2D(Arrays.asList(
                new Point2D(0, 0), new Point2D(1, 0),
                new Point2D(1, 1), new Point2D(0, 1)
        ));
        TheShapeFixer shapeFixer = new TheShapeFixer();

        // 1
        System.out.println("Is the valid shape valid? " + shapeFixer.isValid(validShape));

        // 2
        System.out.println("Is the shape with duplicate points valid? " + shapeFixer.isValid(invalidShapeWithDuplicatePoints));

        // 3
        System.out.println("Is the open shape valid? " + shapeFixer.isValid(invalidOpenShape));

        // Repair shape with duplicate
        Shape2D repairedShape = shapeFixer.repair(invalidShapeWithDuplicatePoints);
        System.out.println("Repaired shape: ");
        for (Point2D point : repairedShape.getPoints()) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }

        // Repair open shape
        Shape2D repairedOpenShape = shapeFixer.repair(invalidOpenShape);
        System.out.println("Repaired open shape: ");
        for (Point2D point : repairedOpenShape.getPoints()) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
    }
}