package com.planning.design.factory;

/**
 * concrete class
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle :: draw() method...");
    }
}
