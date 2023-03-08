package com.planning.design.factory;

/**
 * concrete class
 */
public class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Inside Circle :: draw() method...");
    }
}
