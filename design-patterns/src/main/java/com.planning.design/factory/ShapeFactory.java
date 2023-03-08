package com.planning.design.factory;

/**
 * create a Factory to generate object of concrete class based on given information
 */
public class ShapeFactory {

    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }

        if(shapeType.equalsIgnoreCase("circle")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("rectangle")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("square")){
            return new Square();
        }

        return null;
    }
}
