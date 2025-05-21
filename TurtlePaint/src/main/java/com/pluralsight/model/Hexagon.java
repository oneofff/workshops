package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Hexagon extends Shape {
    private double sideLength;

    public Hexagon(double sideLength) {
        super(new Turtle(new World(200, 200)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.sideLength = sideLength;
    }

    @Override
    protected void paint() {
        for (int i = 0; i < 6; i++) {
            getTurtle().forward(sideLength);
            getTurtle().turnRight(60);
        }
    }
}
