package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

public class Circle extends Shape {
    private static final double TWO_PI = Math.PI * 2;
    private final double radius;

    public Circle(double radius) {
        super(new Turtle(new World(1000, 1000)), new Pair<>(25.0, 15.0), "PINK", 1.0);
        this.radius = radius;
    }

    @Override
    public void paint() {
        setCenterOfCircle();
        int steps = 45;
        double stepLength = TWO_PI * radius / steps;
        double turn = 360.0 / steps;

        for (int i = 0; i < steps; i++) {
            this.getTurtle().forward(stepLength);
            this.getTurtle().turnLeft(turn);
        }
        this.getTurtle().penUp();
    }

    private void setCenterOfCircle() {
        getTurtle().penUp();
        getTurtle().goTo(0, -radius);
        getTurtle().penDown();
    }
}
