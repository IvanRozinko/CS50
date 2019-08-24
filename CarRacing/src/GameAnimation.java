import acm.graphics.GCompound;
import acm.graphics.GRect;

import java.awt.*;
                    /* Can move all this class to Graphic class*/

 class GameAnimation extends GCompound implements GameConstants {
    private int width, height;



     GameAnimation(int width, int height) {
        this.width = width;
        this.height = height;
        drawDivider() ;

    }

    public void moveBackGround() {
            this.move(0, DELTA_Y);
            if (this.getY() >= height / 2.0){
                this.setLocation(0,0);
            }

    }
    //draw 1st divider of road marking lines, which is rectangle same color with asphalt
    private void    drawDivider() {
        GRect block = new GRect(width / NUM_OF_LINES, 0, LINE_WIDTH, LINE_WIDTH);
        GRect block1 = new GRect( 2 * width / NUM_OF_LINES , 0, LINE_WIDTH, LINE_WIDTH);
        GRect block2 = new GRect(width / NUM_OF_LINES, height / 2.0, LINE_WIDTH, LINE_WIDTH);
        GRect block3 = new GRect(2 * width / NUM_OF_LINES, height / 2.0, LINE_WIDTH, LINE_WIDTH);
        block.setFilled(true);
        block1.setFilled(true);
        block2.setFilled(true);
        block3.setFilled(true);
        this.add(block);
        this.add(block1);
        this.add(block2);
        this.add(block3);
        this.setColor(Color.gray);
    }
}
