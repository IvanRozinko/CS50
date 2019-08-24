package com.shpp.p2p.cs.irozinko.assignment2;

/* This class draws 2 paw prints at window you can set location of each by changing " x " and " y " coordinate
 */
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the paw print.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the paw print.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;
     /* Sets the dimension of drawing window where objects located */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }
    /**
     * Draws a paw print. The parameters should specify the upper-left corner of the
     * bounding box containing that paw print.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {

        drawHeel(HEEL_OFFSET_X + x, HEEL_OFFSET_Y + y);
        /* draws first toe */
        drawToe(FIRST_TOE_OFFSET_X + x, FIRST_TOE_OFFSET_Y + y);
        /* draws second toe  */
        drawToe(SECOND_TOE_OFFSET_X + x, SECOND_TOE_OFFSET_Y + y);
        /* draws third toe */
        drawToe(THIRD_TOE_OFFSET_X + x, THIRD_TOE_OFFSET_Y + y);
    }
    /* draws heel it's a oval, with left top at (x,y) location  filled with black color*/
    private void drawHeel (double x, double y){
        GOval heel = new GOval(x, y, HEEL_WIDTH, HEEL_HEIGHT);
        heel.setFilled(true);
        heel.setFillColor(Color.BLACK);
        add(heel);
    }
    /* draws first toe it's oval  with left top at (x,y) location  filled with black color*/
    private void drawToe(double x, double y) {
        GOval toe = new GOval(x, y, TOE_WIDTH, TOE_HEIGHT);
        toe.setFilled(true);
        toe.setFillColor(Color.BLACK);
        add(toe);
    }


}
