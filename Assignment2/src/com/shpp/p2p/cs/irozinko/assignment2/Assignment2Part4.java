package com.shpp.p2p.cs.irozinko.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;
/**
 * This class draws country flag, composed of three vertical equal stripes
 * Colors of stripes set by user.
 */
public class Assignment2Part4   extends WindowProgram {
    /* this constants are setting dimensions of application */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;
    /* here you can set flag dimension */
    private static final double FLAG_WIDTH = 300;
    private static final double FLAG_HEIGHT = 300;
    /* here you can set width of flag stripes and create new color for them */
    private static final double STRIPE_WIDTH = FLAG_WIDTH / 3;
    private static final Color DARK_BLUE = new Color (50,23,180);
    /**
     * Method draws flag with 3 stripes located at the center of application
     */
    public void run() {
        drawFlag((getWidth() - FLAG_WIDTH) / 2, (getHeight() - FLAG_HEIGHT) / 2);
    }
    /*
     * Method draws flag composed of 3 stripes,  color of each stripe can be
     * set by certain parameter
     */
    private void drawFlag(double x, double y) {
        drawStripe(x,y ,DARK_BLUE);
        drawStripe(x + STRIPE_WIDTH,y,Color.ORANGE);
        drawStripe(x+ 2 * STRIPE_WIDTH,y ,Color.RED);
        drawLabel();
    }
    /* Draws one stripe of flag, it's colored filled rectangle with left top corner at location (x;y).
     * Color of stripe sets by parameter "color"
     */
    private void drawStripe(double x, double y, Color color){
        GRect stripe = new GRect(x, y, STRIPE_WIDTH, FLAG_HEIGHT);
        stripe.setFilled(true);
        stripe.setFillColor(color);
        stripe.setColor(color);
        add(stripe);              /* adds objects to application */
    }
    /* draw label at the right down corner  of application*/
    private void drawLabel() {
        GLabel label = new GLabel("Flag of Romania");
        label.setColor(Color.GRAY);
        label.setFont("Times-40");
        label.setLocation(getWidth() - label.getWidth(), getHeight()- label.getDescent());
        add(label);                 /* adds objects to application */
    }
}
