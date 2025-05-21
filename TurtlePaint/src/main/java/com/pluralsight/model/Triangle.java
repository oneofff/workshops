package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Triangle extends Shape {
    private final double sideLength;

    public Triangle(double sideLength) {
        super(new Turtle(new World(1000, 1000)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        Turtle t = getTurtle();
        t.setDelay(3);
        t.setHeading(60);
        for (int k = 0; k < 3; k++) {
            t.forward(sideLength);
            t.turnRight(120);
        }
    }
}
