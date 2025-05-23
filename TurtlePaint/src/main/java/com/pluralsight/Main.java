package com.pluralsight;

import com.pluralsight.model.*;
import com.pluralsight.model.Shape;
import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;
import org.javatuples.Pair;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        World world = new World(1000, 1000);
        Turtle turtle = new Turtle(world);

        Circle circle = new Circle(turtle, new Pair<>(0.0, 0.0), Color.RED, 1.0, 100);
        circle.paint();

        Square square = new Square(turtle, new Pair<>(0.0, 0.0), Color.BLUE, 1.0, 100);
        square.paint();

        Triangle triangle = new Triangle(turtle, new Pair<>(0.0, 0.0), Color.GREEN, 1.0, 150);
        triangle.paint();

        Hexagon hexagon = new Hexagon(turtle, new Pair<>(0.0, 0.0), Color.RED, 1.0, 100);
        hexagon.paint();

        turtle.penUp();
        turtle.goTo(0, 0);
    }
}