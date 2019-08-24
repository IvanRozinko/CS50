import java.awt.*;

public interface GameConstants {


    //setting quantity and size of road marking lines
    double NUM_OF_LINES = 3.0;
    double LINE_WIDTH = 10;

    // setting color of grass and it width
    Color GREEN = new Color(34,139,34);
    int GRASS_SCALE = 10;

    //setting sizes of cars
    double CAR_WIDTH = 35;
    double CAR_HEIGHT = 70;

    //set duration of animation
    int ANIMATION_TIME = 6000 ;
    //set the step of each move
    double DELTA_Y = 10;
    //set delay of animation
    double PAUSE = 1000/50.0;
    //set spaces between cars
    double CARS_SPACE = 90;
    //set speed of red car
    double RED_CAR_DELTA = 1 ;
    //set speed of yellow car
    double YELLOW_CAR_DELTA = 1.5;
    //set speed of blue car
    double BLUE_CAR_DELTA = 0.5 ;
    //set number of lines in finish line
    int FINISH_HEIGHT = 3;
    //set size of squares in finish line
    double SQUARE_SIZE = 10 ;
    int HORIZONTAL = 10;
    int VERTICAL = 10;
    //initial variables of Car class
 //   private Car redCar, yellowCar, blueCar;
}
