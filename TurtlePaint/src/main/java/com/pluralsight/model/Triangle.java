package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Triangle extends Shape {
    private double sideLength;

    public Triangle(double sideLength) {
        super(new Turtle(new World(200, 200)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.sideLength = sideLength;
    }

    @Override
    protected void paint() {
        getTurtle().forward(sideLength);
        getTurtle().turnRight(120);
        getTurtle().forward(sideLength);
        getTurtle().turnRight(120);
        getTurtle().forward(sideLength);
        getTurtle().turnRight(120);
    }
}
