package com.pluralsight;

import com.pluralsight.model.Circle;
import com.pluralsight.model.Shape;
import com.pluralsight.model.Square;
import com.pluralsight.model.Triangle;
import com.pluralsight.paint.Turtle;
import com.pluralsight.paint.World;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        // Create a world 600x600 with white background
//        World world = new World(600, 600);
//
//        // Create a turtle in the center
//        Turtle t = new Turtle(world);
//        t.setColor(Color.BLACK);
//        // Make it draw quickly
//        t.setDelay(0);
//
//        // Circle parameters
//        double radius = 150;          // pixels
//        int    steps  = 360;          // number of segments
//        double step   = 2 * Math.PI * radius / steps;   // length of each forward move
//        double turn   = 360.0 / steps;                  // angle to turn after each step
//
//        // Move to the circle’s starting point (top of the circle)
//        t.penUp();
//        t.setHeading(0);      // face right
//        t.penDown();
//
//        // Draw the circle
//        for (int i = 0; i < steps; i++) {
//            t.forward(step);
//            t.turnRight(turn);
//        }

        Circle circle = new Circle(250);
        circle.paint();

        Square square = new Square(100);
        square.paint();

        Triangle triangle = new Triangle(100);
        triangle.paint();
    }
}