package com.shpp.p2p.cs.irozinko.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

/**
 * Class create optical illusion. Draws black squares divided by white spaces, so it's make person which
 * looking at it see the black points on the crossing of white spaces.
 */
public class Assignment2Part5 extends WindowProgram {
    /*  set the number of rows and columns of black squares at the picture*/
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* set size of each black square*/
    private static final double BOX_SIZE = 40;

    /* set the size of spaces between black squares*/
    private static final double BOX_SPACING = 10;

    /* set dimensions of picture */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

   /* variables "startX()" and "startY()" use to get the location of left top corner of  illusion*/
    private double startX, startY;

    public void run(){
        findTopLeftCorner();
        drawIllusion();
    }
    /* Method calculate the coordinates of left top corner of all illusion. It is same
     * size to all black squares added to spaces between them.  */
    private void findTopLeftCorner() {
        double rectangleWidth = BOX_SIZE * NUM_COLS + BOX_SPACING * (NUM_COLS - 1 );
        double rectangleHeight = BOX_SIZE * NUM_ROWS + BOX_SPACING * (NUM_ROWS - 1 );
        startX = (getWidth() - rectangleWidth)  / 2;
        startY = (getHeight() - rectangleHeight) / 2;
    }
    /*
     * draws black squares, divided with spaces. First cycle fills the columns, and 2nd cycle fills rows
     * Variables "startX()" and "startY()" use to get the location of left top corner of  illusion, and to start fill it
     * with black squares.
     */
    private void drawIllusion() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                drawBlackSquare(startX + (BOX_SIZE  +  BOX_SPACING ) * j,
                        startY + (BOX_SIZE  + BOX_SPACING ) * i);
            }
        }
    }
    /* draw black square with top left corner at location (x,y).
     * parameter x - position of left top  corner on x axis
     * parameter y - position of left top  corner on y axis
     */
    private void drawBlackSquare(double x, double y){
        GRect blackSquare = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        blackSquare.setFilled(true);
        blackSquare.setFillColor(Color.BLACK);
        add(blackSquare);
    }
}
