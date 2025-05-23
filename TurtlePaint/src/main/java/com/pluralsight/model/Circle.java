package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import org.javatuples.Pair;

import java.awt.*;

public class Circle extends Shape {
    private static final double TWO_PI = Math.PI * 2;
    private final double radius;

    public Circle(Turtle turtle, Pair<Double, Double> location, Color color, double borderWidth, double radius) {
        super(turtle, location, color, borderWidth);
        this.radius = radius;
    }

    @Override
    public void paint() {
        setCenterOfCircle();
        int steps = 360;
        double stepLength = TWO_PI * radius / steps;
        double turn = 360.0 / steps;

        for (int i = 0; i < steps; i++) {
            this.getTurtle().forward(stepLength);
            this.getTurtle().turnLeft(turn);
        }
        this.getTurtle().penUp();
    }

    private void setCenterOfCircle() {
        Turtle t = getTurtle();
        getTurtle().penUp();
        t.goTo(getLocation().getValue0(), getLocation().getValue1() - radius);
        getTurtle().penDown();
    }
}
