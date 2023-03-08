package com.planning.design.factory;

public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // get an object of Circle and call its draw method
        Shape circle = shapeFactory.getShape("circle");
        circle.draw();

        Shape square = shapeFactory.getShape("square");
        square.draw();

        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();
    }
}
