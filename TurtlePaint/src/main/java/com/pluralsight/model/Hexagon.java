package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Hexagon extends Shape {
    private double sideLength;

    public Hexagon(double sideLength) {
        super(new Turtle(new World(1000, 1000)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        getTurtle().setHeading(90);
        for (int i = 0; i < 6; i++) {
            getTurtle().forward(sideLength);
            getTurtle().turnRight(60);
        }
    }
}
