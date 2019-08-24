package com.shpp.p2p.cs.irozinko.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draws 4 equal black circles at the corners of window, and white rectangle over them
 * with 4 corners at the circles centers
 */

public class Assignment2Part2 extends WindowProgram {
    private static final int circleDiameter = 100;   // set circle's diameter
    public  static final int APPLICATION_WIDTH = 300;  // set window width
    public static final int APPLICATION_HEIGHT = 300; //  set window height
    /*
     * Draws 4 circles and rectangle over
     */
    public void run() {
        drawCircle(0,0);
        drawCircle((getWidth() - circleDiameter),0);
        drawCircle((getWidth() - circleDiameter),(getHeight() - circleDiameter));
        drawCircle(0,(getHeight() - circleDiameter));
        drawRectangle();
    }
    /* draws circle with left top corner at (x,y) location, with diameter equal circleDiameter constant,
     * filled with black color
     */
    private void drawCircle ( int x, int y){
        GOval circle = new GOval(x, y, circleDiameter, circleDiameter);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
    }
    /*
     * Draws  white colored rectangle over circles. Corners of rectangle located at the center of each
     * black circle
     */
    private void drawRectangle() {
        GRect rectangle = new GRect(circleDiameter /2.0, circleDiameter /2.0, (getWidth() - circleDiameter), (getHeight() - circleDiameter));
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.WHITE);
        rectangle.setColor(Color.WHITE);
        add(rectangle);
    }
}
