package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Square extends Shape {
    private double sideLength;

    public Square(double sideLength) {
        super(new Turtle(new World(200, 200)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.sideLength = sideLength;
    }

    @Override
    protected void paint() {
        getTurtle().backward();
    }
}
