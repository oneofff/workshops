package com.pluralsight.model;

import com.pluralsight.paint.Turtle;
import lombok.AccessLevel;
import lombok.Getter;
import org.javatuples.Pair;

import java.awt.*;

@Getter(AccessLevel.PROTECTED)
public abstract class Shape {
    private final Turtle turtle;
    private final Pair<Double, Double> location;
    private final String color;
    private final double borderWidth;

    public Shape(Turtle turtle, Pair<Double, Double> location, String color, double borderWidth) {
        this.turtle = turtle;
        this.location = location;
        this.color = color;
        this.borderWidth = borderWidth;
        setEnvironment();
    }

    protected abstract void paint();

    protected void setEnvironment() {
        this.turtle.penUp();
        this.turtle.setColor(Color.PINK);
        this.turtle.setDelay(0);
        this.turtle.goTo(location.getValue0(), location.getValue1());
        this.turtle.penDown();

    }
}
