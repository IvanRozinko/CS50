import acm.graphics.*;


import java.awt.*;



 class Graphic extends GCompound implements GameConstants {

   private int width, height;



     Graphic( int width, int height ) {
        this.width= width;
        this.height= height;
        drawBackGround();
    }


    private void drawBackGround() {
        removeAll();
        drawAsphalt();
        drawGrass(0,width / GRASS_SCALE, height);
        drawGrass(width - width / GRASS_SCALE,width / GRASS_SCALE, height);
        drawRoadMarking();

    }
    private void drawAsphalt() {
        GRect asphalt = new GRect(0,0,width,height);
        asphalt.setFilled(true);
        asphalt.setColor(Color.GRAY);
        this.add(asphalt);

    }
    //draw grass at the left side of road which is green rectangle
    private void drawGrass(int x, int width, int height) {
        GRect grass = new GRect(x, 0, width, height);
        grass.setFilled(true);
        grass.setColor(GREEN);
        this.add(grass);

    }
    //draw road marking lines which is white rectangles
    private void drawRoadMarking() {
        for (int i = 0; i < NUM_OF_LINES - 1; i++) {                                    //set the quantity of lines at road marking
            GRect roadMarking = new GRect(width / NUM_OF_LINES + i * width / NUM_OF_LINES,
                    0, LINE_WIDTH, height);
            roadMarking.setFilled(true);
            roadMarking.setColor(Color.WHITE);
            this.add(roadMarking);

        }
    }
}
