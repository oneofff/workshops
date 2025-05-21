package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Square extends Shape {
    private final double sideLength;

    public Square(double sideLength) {
        super(new Turtle(new World(1000, 1000)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        getTurtle().penDown();
        for (int i = 0; i <= 4; i++) {
            getTurtle().forward(sideLength);
            getTurtle().turnLeft(90);
        }
        getTurtle().penUp();
    }
}
