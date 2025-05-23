package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import org.javatuples.Pair;

import java.awt.*;

public class Square extends Shape {
    private final double sideLength;

    public Square(Turtle turtle, Pair<Double, Double> location, Color color, double borderWidth, double sideLength) {
        super(turtle, location, color, borderWidth);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        setCenterOfSquare();
        getTurtle().penDown();
        for (int i = 0; i <= 4; i++) {
            getTurtle().forward(sideLength);
            getTurtle().turnLeft(90);
        }
        getTurtle().penUp();
    }

    @SuppressWarnings("DuplicatedCode")
    private void setCenterOfSquare() {
        double l = sideLength;
        double cx = l / 2;
        double cy = l / 2;

        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getValue0() - cx, getLocation().getValue1() - cy);
        t.penDown();
    }
}
