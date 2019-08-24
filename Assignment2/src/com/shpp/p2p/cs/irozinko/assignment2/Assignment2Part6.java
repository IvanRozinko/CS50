package com.shpp.p2p.cs.irozinko.assignment2;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

/**
 * Draws caterpillar on the screen, made of circles. User can change the size and number
 * of parts of caterpillar.
 */
public class Assignment2Part6 extends WindowProgram {
    /* Set the size of application window*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 300;
    /* Set number of circles which making caterpillar and their size*/
    private static final double NUM_BODYPARTS = 6;
    private static final double BODYPART_SIZE = 80;
    /* Set spacing between top-left corners of circles which making caterpillar*/
    private static final double SPACING = BODYPART_SIZE / 3;
    /* Set color of circles which making caterpillar*/
    private static final Color DARK_GREEN = new Color (0, 204 ,0);

    /* This variable using to count number of bodyParts already created*/
    private static  int countBodyParts = 0;
    private static double  faceX;
    private static double  faceY;


    public void run() {
        drawCaterpillar();
    }
    /* Method draws caterpillar at left down corner of the screen, made of set number of circles
     * color of body sets by user. Caterpillar faces to east.
     */
    private void drawCaterpillar() {
        while ( countBodyParts < NUM_BODYPARTS ) {
            if (countBodyParts % 2 == 0) {
                drawDownRow();
            } else {
                drawUpRow();
            }
            countBodyParts++;
        }
        /* Making new object class "GFace" and pass it address to variable "face", adding "face"
         * to the screen and setting it location.
         * This variables used to draw face of caterpillar. "GFace face" - variable of class "GFace" -
         * made to compound all components of caterpillar face, for easy way to manage it.
         * "faceX", "faceY" - used to set location of face by "X" and "Y" axis.
         */
        GFace face = new GFace();
        add(face, faceX, faceY - SPACING);
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
    /*Getting position of last circle to assign location of caterpillars face*/
        faceX = circle.getX();
        faceY = circle.getY();
    }
}
/**
 * This class made to draw caterpillar face. It composed different parts of face to one object, to  manage it
 * more easy. Composed of head,  2 eyes, nose and horns.
 */
 class GFace extends GCompound {
    GFace() {
        /*draw head of caterpillar*/
        GOval head = new GOval(FACE_SIZE, FACE_SIZE);
        head.setColor(Color.BLACK);
        head.setFilled(true);
        head.setFillColor(Color.ORANGE);
        /*draw left eye of caterpillar*/
        GOval leftEye = new GOval(EYE_WIDTH, EYE_HEIGHT);
        leftEye.setFilled(true);
        leftEye.setColor(Color.white);
        /*draw left eye pupil of caterpillar*/
        GOval leftPupil = new GOval(EYE_WIDTH, EYE_WIDTH );
        leftPupil.setColor(Color.black);
        leftPupil.setFilled(true);
        /*draw right eye of caterpillar*/
        GOval rightEye = new GOval(EYE_WIDTH, EYE_HEIGHT );
        rightEye.setFilled(true);
        rightEye.setColor(Color.white);
        /*draw right eye pupil of caterpillar*/
        GOval rightPupil = new GOval(EYE_WIDTH, EYE_WIDTH);
        rightPupil.setColor(Color.black);
        rightPupil.setFilled(true);
        /*draw nose of caterpillar*/
        GOval nose = new GOval(NOSE_WIDTH, NOSE_HEIGHT);
        nose.setFilled(true);
        nose.setFillColor(Color.RED);
        GArc smile = new GArc(SMILE_WIDTH , SMILE_HEIGHT, SMILE_START, SMILE_SWEEP);
        /*draw left horn of caterpillar*/
        GArc leftHorn = new GArc(FACE_SIZE / 3, FACE_SIZE, LEFT_HORN_START, LEFT_HORN_SWEEP);
        /*draw left horn of caterpillar*/
        GArc rightHorn = new GArc(FACE_SIZE / 3, FACE_SIZE, RIGHT_HORN_START, RIGHT_HORN_SWEEP);

        add(head, 0, 0);
        add(leftEye, (FACE_SIZE / 2 - EYE_WIDTH ) / 2,
                (FACE_SIZE / 2 - EYE_HEIGHT ) / 2);
        add(leftPupil, (FACE_SIZE / 2 - EYE_WIDTH ) / 2,
                (FACE_SIZE / 2 - EYE_HEIGHT ) / 2 + (EYE_HEIGHT  - EYE_WIDTH ));
        add(rightEye, (FACE_SIZE / 2 * 3 - EYE_WIDTH ) / 2,
                (FACE_SIZE / 2 - EYE_HEIGHT ) / 2);
        add(rightPupil, (FACE_SIZE / 2 * 3 - EYE_WIDTH) / 2,
                (FACE_SIZE / 2 - EYE_HEIGHT) / 2 + (EYE_HEIGHT  - EYE_WIDTH ));
        add(nose, (FACE_SIZE - NOSE_WIDTH ) / 2, (FACE_SIZE - NOSE_HEIGHT ) / 2);
        add(smile, (FACE_SIZE - SMILE_WIDTH ) / 2, (FACE_SIZE * 3 / 2 - SMILE_HEIGHT) / 2);
        add(leftHorn, 0, - FACE_SIZE / 2);
        add(rightHorn, FACE_SIZE * 2 / 3, - FACE_SIZE / 2);
    }
    /* constants describing dimensions of caterpillars face and how other parts of face dependent on of it*/
    private static final double FACE_SIZE = 80;
    private static final double EYE_WIDTH = 0.15 * FACE_SIZE;
    private static final double EYE_HEIGHT = 0.25 * FACE_SIZE;
    private static final double NOSE_WIDTH = 0.15 * FACE_SIZE;
    private static final double NOSE_HEIGHT = 0.10 * FACE_SIZE;
    private static final double SMILE_WIDTH = 0.5 * FACE_SIZE;
    private static final double SMILE_HEIGHT = 0.25 * FACE_SIZE;
    private static final double SMILE_START = 0;
    private static final double SMILE_SWEEP = -180;
    private static final double LEFT_HORN_START = 0;
    private static final double LEFT_HORN_SWEEP = 145;
    private static final double RIGHT_HORN_START = 180;
    private static final double RIGHT_HORN_SWEEP = -145;

}


