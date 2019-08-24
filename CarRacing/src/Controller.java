
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


import java.awt.event.KeyEvent;


public class Controller extends WindowProgram implements GameConstants{
    //setting size of canvas
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 900;

    private Car car;
   private int width;
   private int height;



    public void run() {
        addKeyListeners();
        width = getWidth();
        height = getHeight();

        Graphic graph = new Graphic(width, height);
        add(graph);

        GameAnimation anim = new GameAnimation(width, height);
        add(anim);

        car = new Car(Color.RED);
        car.setX(100);
        car.setY(100);

        add(car);


        int timer = 0;
        Car enemy = null;

        while(true) {
            timer++;

            if (timer > 50 && enemy == null){
                enemy = new Car( Color.WHITE);
                enemy.setX(RandomGenerator.getInstance().nextInt(0, width));
                enemy.setY(RandomGenerator.getInstance().nextInt(0, height));
                add(enemy);

            }
            if (timer > 60){
                enemy.moveCarX(RandomGenerator.getInstance().nextInt(-4,4));
                enemy.moveCarY(RandomGenerator.getInstance().nextInt(-4,4));

            }
            anim.moveBackGround();
            pause(20);
        }

    }


    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            if ( car.getX() >= width / GRASS_SCALE ) {
                car.moveCarX(-HORIZONTAL);     //move left
            }
        }
        if (key == KeyEvent.VK_RIGHT){
            if( car.getX() <= width - (double)width/GRASS_SCALE - car.getCarWidth() ) {
                car.moveCarX(HORIZONTAL);     //move right
            }
        }
        if (key == KeyEvent.VK_UP){
            if ( car.getY() >= 0) {
                car.moveCarY(-VERTICAL);      //move up
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            if  ( car.getY() <=  height - car.getCarHeight() ) {
                car.moveCarY(VERTICAL);        //move down
            }
        }
    }








}
