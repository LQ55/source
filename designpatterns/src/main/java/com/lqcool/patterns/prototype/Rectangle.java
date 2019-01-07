package com.lqcool.patterns.prototype;

/**
 * @Auther: lqcool
 * @Description:
 */
public class Rectangle extends Shape{
    public Rectangle(){
        this.type = "Rectangle";
    }
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
