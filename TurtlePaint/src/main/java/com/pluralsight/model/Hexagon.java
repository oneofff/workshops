package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import org.javatuples.Pair;

import java.awt.*;

public class Hexagon extends Shape {
    private final double sideLength;

    public Hexagon(Turtle turtle, Pair<Double, Double> location, Color color, double borderWidth, double sideLength) {
        super(turtle, location, color, borderWidth);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        setCenterOfHexagon();
        getTurtle().penDown();
        getTurtle().setHeading(120);
        for (int i = 0; i < 6; i++) {
            getTurtle().forward(sideLength);
            getTurtle().turnRight(60);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void setCenterOfHexagon() {
        double l = sideLength;
        double h = Math.sqrt(3) * l;
        double cx = l / 2;
        double cy = h / 2;

        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getValue0() - cx, getLocation().getValue1() - cy);
        t.penDown();
    }
}
