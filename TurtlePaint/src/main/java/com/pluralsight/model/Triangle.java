package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import org.javatuples.Pair;

import java.awt.*;

public class Triangle extends Shape {
    private final double sideLength;

    public Triangle(Turtle turtle, Pair<Double, Double> location, Color color, double borderWidth, double sideLength) {
        super(turtle, location, color, borderWidth);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        setCenterOfTriangle();
        Turtle t = getTurtle();
        t.setHeading(60);
        for (int k = 0; k < 3; k++) {
            t.forward(sideLength);
            t.turnRight(120);
        }
    }

    private void setCenterOfTriangle() {
        double l = sideLength;
        double h = Math.sqrt(3) * l / 2;
        double cx = l / 2;
        double cy = h / 3;

        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getValue0() - cx, getLocation().getValue1() - cy);
        t.penDown();
    }
}
