import acm.graphics.GCompound;
import acm.graphics.GRoundRect;

import java.awt.*;

public class Car extends GCompound implements GameConstants  {

        private int x;
        private int y;
        private double carWidth, carHeight;




    Car(Color color) {                          //constructor of class
        //draw car body
        GRoundRect carBody = new GRoundRect(CAR_WIDTH, CAR_HEIGHT);
        carBody.setFilled(true);
        carBody.setFillColor(color);
        this.add(carBody, x, y);
        //draw front window
        GRoundRect frontWindow = new GRoundRect(CAR_WIDTH, 0.2 * CAR_HEIGHT);
        frontWindow.setFilled(true);
        frontWindow.setFillColor(Color.BLACK);
        this.add(frontWindow, x, y + 0.22 * CAR_HEIGHT);
        //draw back window
        GRoundRect backWindow = new GRoundRect(CAR_WIDTH, 0.15 * CAR_HEIGHT);
        backWindow.setFilled(true);
        backWindow.setFillColor(Color.BLACK);
        this.add(backWindow, x, y + 0.7 * CAR_HEIGHT);

        this.carWidth = this.getWidth();
        this.carHeight = this.getHeight();
    }


    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

     double getCarWidth() {
        return carWidth;
    }
     double getCarHeight() {
        return carHeight;
    }


     void setX(int x) {
        this.x = x;
    }

     void setY(int y) {
        this.y = y;
    }


     void moveCarX(int dx){
        x += dx;
        this.setLocation(x,y);
    }
     void moveCarY(int dy) {
        y += dy;
        this.setLocation(x, y);
    }


}
