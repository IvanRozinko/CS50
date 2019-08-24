package com.shpp.p2p.cs.irozinko.assignment4;




import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;


/**
 * This class create a classical arcade game "BREAKOUT". You using a paddle and  ball can break the bricks
 * at the top part of canvas. Game continuing till last brick at the stage, or till ball touches the ground.
 *  Player has 3 rounds, each round bricks redraws at canvas;
 */
public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 15;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 3;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Game freezing before new round
     */
    private static final int FREEZE_TIME = 1500;
    /**
     * Pause between each move of ball
     */
    private static final int PAUSE_TIME = 10;

    /**
     * Ball speed
     */
    private static final double BALL_SPEED = 3.0;

    /**
     * This variable contains link to paddle
     */
    private GRect paddle = null;

    /**
     * This variables contain position of ball by X and Y axis
     */
    private double vx, vy;


    /**
     * Runs the program. addMouseListeners() - adding mouse cursor to program,
     */
    public void run() {
        addMouseListeners();
        playGame();
    }

    /**
     * Method getting mouse  position and setting position of paddle depend of it
     * @param e using to get mouse position
     */
    public void mouseMoved (MouseEvent e){
        int mousePositionX = e.getX();
        int paddleX = mousePositionX - PADDLE_WIDTH / 2;
        int paddleY = (getHeight() - PADDLE_Y_OFFSET);
        movePaddle(paddleX, paddleY, mousePositionX);
    }

    /**
     * Method attached center of paddle to cursor and make it move together at horizontal direction only at
     * inside the canvas limits
     * @param paddleX position of paddle by X axis
     * @param paddleY position of paddle by Y axis
     * @param mousePositionX position of cursor by X axis
     */
    private void movePaddle(int paddleX, int paddleY, int mousePositionX) {
        if (mousePositionX < PADDLE_WIDTH / 2){
            paddle.setLocation(0,paddleY);
        }
        else if (mousePositionX > getWidth() - PADDLE_WIDTH / 2){
            paddle.setLocation(getWidth() - PADDLE_WIDTH, paddleY);
        }
        else {
            paddle.setLocation(paddleX, paddleY);
        }
    }

    /**
     * Sets the speed of the ball in vertical direction and randomly change the speed  and direction of the ball in
     * horizontal direction
     */
    private void setBallSpeed() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = - vx;
        }
        vy = BALL_SPEED;
    }

    /**
     * Set the number of rounds per game, draws starting stage, make the ball move after use click mouse
     */
    private void playGame() {
        drawBricks();
        for (int i = 1; i <= NTURNS; i++) {
            setBallSpeed();
            GOval ball = drawBall();
            paddle = drawPaddle();
            showLabel("ROUND " + i, Color.ORANGE);
            waitForClick();
            playRound(ball);
        }
        showLabel("GAME OVER", Color.BLACK);
    }

    /**
     * Method makes to play one round of game
     * @param ball object ball
     */
    private void playRound(GOval ball) {
        int bricksOnStage = NBRICK_ROWS * NBRICKS_PER_ROW;

        while(true) {
            bricksOnStage = collideObject(ball, bricksOnStage);
            collideStageLimits(ball);
            if (hasMissPaddle(ball)) {
                showLabel("BUSTED", Color.BLACK);
                clearStage(ball);
                break;
            }
            ball.move(vx, vy);
            //println(vx + " " + vy);
            if (isBricksLeft(bricksOnStage)){
                showLabel("YOU WON!", Color.red);
                clearStage(ball);
                break;
            }
            pause(PAUSE_TIME);
        }
    }


    /**
     * Method checking if ball collides any object on his way and changed ball direction depends of conditions
     * @param ball object ball
     * @param bricksOnStage variable counts how many bricks left on stage
     * @return  int how many bricks left on stage
     */
    private int collideObject(GOval ball, int bricksOnStage) {
        GObject collider = getCollidingObject(ball);
        if (isObjectAPaddle(collider) && vy > 0){
            vy = - vy;
        }
        else if (isObjectABrick(collider)){
            remove(collider);
            vy = - vy;
            bricksOnStage -= 1;
        }
        return bricksOnStage;
    }

    /**
     * Draws bricks of different color at the top of stage which can be broken if ball collides it.
     */
    private void drawBricks() {
        int topLeftX = (getWidth() - (BRICK_WIDTH * NBRICKS_PER_ROW + BRICK_SEP * (NBRICKS_PER_ROW - 1))) / 2;
        int topLeftY = (BRICK_Y_OFFSET);
        Color [] bricksColor ={Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                                Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN};

        for (int i = 0; i < NBRICK_ROWS; i++){
            for (int j = 0; j <NBRICKS_PER_ROW; j++){
                drawBrick(topLeftX + (BRICK_WIDTH + BRICK_SEP) * j, topLeftY + (BRICK_HEIGHT + BRICK_SEP) * i, bricksColor[i % 10]);
            }
        }
    }

    /**
     * Method checks if ball collides any object on his way
     * @param ball object ball
     * @return object  of GObject collided by ball or null
     */
    private GObject getCollidingObject(GOval ball) {
        GObject result = null;
        GPoint[] borderPoints = createArrayOfBallBorders(ball);
        for (GPoint someBorderPoint : borderPoints) {
            result = getElementAt(someBorderPoint);
            if (result != null){
                break;
            }
        }
        return result;
    }

    /**
     * Draw a singe brick with set position and color
     * @param x position of brick on X axis
     * @param y position of brick on y axis
     * @param color set the color of brick
     */
    private void drawBrick(int x, int y, Color color) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(color);
        add(brick);
    }

    /**
     * Draws a paddle, which is black rectangle dimensions set by constants
     * @return returning object of GRect
     */
    private GRect drawPaddle() {
        int paddleX = (getWidth() - PADDLE_WIDTH) / 2;
        int paddleY = (getHeight() - PADDLE_Y_OFFSET );
        GRect rect = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
        rect.setFilled(true);
        rect.setColor(Color.BLACK);
        add(rect);
        return rect;
    }

    /**Draw a gray ball which is GOval object
     * @return GOval object
     */
    private GOval drawBall(){
        GOval ball = new GOval(getWidth() / 2.0 - BALL_RADIUS, getHeight() - PADDLE_Y_OFFSET - 2 * BALL_RADIUS,
                            2 * BALL_RADIUS, 2 * BALL_RADIUS);
        ball.setFilled(true);
        ball.setColor(Color.gray);
        add(ball);
        return ball;
    }

    /**
     * Checking if there are bricks left on the stage
     * @param bricksOnStage receiving number of bricks and compare it to "0"
     * @return boolean answer
     */
    private boolean isBricksLeft(int bricksOnStage) {
        return bricksOnStage == 0;
    }

    /**
     * Stop the round show result label and remove ball and paddle after it
     * @param ball ball object
     */
    private void clearStage(GOval ball) {
        remove(ball);
        remove((paddle));
    }

    /**
     * Draw label at the center of canvas with set color and text
     * @param text receive text of label
     * @param color set the color of label
     */
    private void showLabel(String text, Color color) {
        GLabel result = new GLabel(text);
        result.setFont("Verdana-60");
        result.setColor(color);
        result.sendToFront();
        add(result, (getWidth() - result.getWidth()) /2, (getHeight() - result.getHeight()) / 2 );
        pause(FREEZE_TIME);
        remove(result);
    }

    /**
     * Check if collided object is a paddle
     * @param collider object GObject
     * @return  boolean answer
     */
    private boolean isObjectAPaddle(GObject collider) {
        return (collider == paddle);
    }

    /**
     * Check if collided object is a brick
     * @param collider object GObject
     * @return boolean answer
     */
    private boolean isObjectABrick(GObject collider) {
        return collider != null;
    }

    /**
     * Method checks if ball collides stage limits and changing reflect direction of ball
     * @param ball object GOval
     */
    private void collideStageLimits(GOval ball) {
        if (isBallCollideCelling(ball)){
            vy = - vy;
        }
        if (isBallCollideRightWall(ball) || isBallCollideLeftWall(ball)){
            vx = - vx;
        }
    }

    /**
     * Method creating array of GPoints which located at 4 corners of square ball inscribed.
     * @param ball object GOval ball
     * @return array of GPoints which located at 4 corners of square ball inscribed
     */
    private GPoint [] createArrayOfBallBorders(GOval ball) {
        GPoint [] borderPoints = new GPoint[4];
        borderPoints[0] = new GPoint(ball.getX(), ball.getY());
        borderPoints[1] = new GPoint(ball.getX() + 2 * BALL_RADIUS, ball.getY());
        borderPoints[2] = new GPoint(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
        borderPoints[3] = new GPoint(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
        return borderPoints;
    }

    /**
     * Check if ball collide left limit of stage
     * @param ball object GOval ball
     * @return boolean answer
     */
    private boolean isBallCollideLeftWall(GOval ball) {
        return ball.getX() <= 0;
    }

    /**
     * Check if ball collide right limit of stage
     * @param ball object GOval ball
     * @return boolean answer
     */
    private boolean isBallCollideRightWall(GOval ball) {
        return ball.getX() + 2 * BALL_RADIUS >= getWidth();
    }

    /**
     * Check if ball collide celling of stage
     * @param ball object GOval ball
     * @return boolean answer
     */
    private boolean isBallCollideCelling(GOval ball) {
        return ball.getY() <= 0;
    }

    /**
     * Check if ball hit the floor
     * @param ball object GOval ball
     * @return boolean answer
     */
    private boolean hasMissPaddle(GOval ball){
        return ball.getY() + 2 * BALL_RADIUS >= getHeight();
    }


}
