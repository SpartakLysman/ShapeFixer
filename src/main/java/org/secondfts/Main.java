package org.secondfts;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape2D problematicShape = new Shape2D(Arrays.asList(
                new Point2D(0, 0), new Point2D(2, 0),
                new Point2D(2, 0), new Point2D(4, 0), // Duplicate
                new Point2D(4, 2), new Point2D(2, 2),
                new Point2D(0, 0)
        ));

        Shape2D invalidShape = new Shape2D(Arrays.asList(
                new Point2D(1, 0), new Point2D(2, 0),
                new Point2D(2, 0), new Point2D(4, 0),
                new Point2D(4, 2), new Point2D(2, 2),
                new Point2D(0, 0)
        ));
        TheShapeFixer shapeFixer = new TheShapeFixer();

        // after
        Shape2D repairedShape = shapeFixer.repair(problematicShape);
        System.out.println("Repaired shape points:");
        for (Point2D point : repairedShape.getPoints()) {
            System.out.println(point);
        }

        System.out.println("Is the repaired shape valid? " + shapeFixer.isValid(repairedShape));

        System.out.println("Is the invalid shape valid? " + shapeFixer.isValid(invalidShape));
    }
}