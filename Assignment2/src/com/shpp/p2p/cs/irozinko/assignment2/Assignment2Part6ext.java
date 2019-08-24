package com.shpp.p2p.cs.irozinko.assignment2;


import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;



/**
 * Draws caterpillar on the screen, made of circles. User can change the size and number
 * of parts of caterpillar.
 */
public class Assignment2Part6ext extends WindowProgram {
    /* Set the size of application window*/
    public static final double APPLICATION_WIDTH = 600;
    public static final double APPLICATION_HEIGHT = 300;
    /* Set number of circles which making caterpillar and their size*/
    private static final double NUM_CIRCLES = 6;
    private static final double BODYPART_SIZE = 80;
    /* Set spacing between top-left corners of circles which making caterpillar*/
    private static final double SPACING =BODYPART_SIZE / 3;
    /* Set color of circles which making caterpillar*/
    private static final Color DARK_GREEN = new Color (0, 204 ,0);

    /* This variable using to count number of circles already created*/
    private static  int countBodyParts = 0;
    /* This variables used to draw face of caterpillar. "GFace face" - variable of class "GFace" -
     * made to compound all components of caterpillar face, for easy way to manage it.
     * "faceX", "faceY" - used to set location of face by "X" and "Y" axis.
     */
    private static  GFace face;
    private static double  faceX;
    private static double  faceY;

    private static ArrayList<GOval> list = new ArrayList<>();
    private static final double PAUSE_TIME = 3000 ;
    private static final double DELTA_X = 20 ;

    /* Method draws caterpillar at left down corner of the screen, made of set number of circles
   color of body sets by user. Caterpillar faces to east.
     */
    public void run() {

        while ( countBodyParts < NUM_CIRCLES ) {
            if (countBodyParts % 2 == 0) {
                drawDownRow();
            } else {
                drawUpRow();
            }
            countBodyParts++;
        }
        /* Making new object class "GFace" and pass it adress to variable "face", adding "face"
        to the screen and setting it location
         */
        face = new GFace();
        add(face, faceX, faceY - SPACING);
        /*make caterpillar move to  end of screen*/
        moveCaterpillar();
    }
    /* Draw down row of body parts which composed of circles*/
    private void drawDownRow() {
        drawCircle((BODYPART_SIZE + SPACING) * countBodyParts / 2, getHeight() - BODYPART_SIZE);
    }
    /* Draw UP row of body parts which composed of circles*/
    private void drawUpRow() {
        drawCircle((BODYPART_SIZE + SPACING) * countBodyParts / 2, getHeight() -  (BODYPART_SIZE + SPACING));
    }
    /* Draw circle with top left corner located on "x:y" and diameter*/
    private  void drawCircle (double x, double y) {
        GOval circle = new GOval(x, y, BODYPART_SIZE, BODYPART_SIZE);
        circle.setColor(Color.BLACK);
        circle.setFilled(true);
        circle.setFillColor(DARK_GREEN);
        add(circle);
        list.add(circle);
        /*Getting position of last circle to assign location of caterpillars face*/
        faceX = circle.getX();
        faceY = circle.getY();
    }
    /* Make caterpillar move along X axis till end of window, and same time change location of each body part
     * along Y axis
     */
    private void moveCaterpillar() {

        while (list.get(0).getX() < getWidth()) {
            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 0) {
                    list.get(i).move(DELTA_X, - SPACING);
                }
                else {
                    list.get(i).move(DELTA_X, SPACING);
                }
            }
            face.move(DELTA_X, SPACING);
            pause(PAUSE_TIME);
            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 0) {
                    list.get(i).move(DELTA_X, SPACING);
                }
                else {
                    list.get(i).move(DELTA_X, - SPACING);
                }
            }
            face.move(DELTA_X, - SPACING);
           pause(PAUSE_TIME);
        }
    }
}





