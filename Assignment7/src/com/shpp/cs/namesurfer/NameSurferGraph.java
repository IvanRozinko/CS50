package com.shpp.cs.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private ArrayList<NameSurferEntry> entryList = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        update();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
          entryList.clear();
          update();
    }

	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if ( entry != null) {
            entryList.add(entry);
        }
        else {
            System.out.println("Try another name");
        }
        update();
    }

    /**
     *Delete graph for chosen name
     * @param name value to delete
     */
    public void deleteName(String name){
        for (int i = 0; i < entryList.size(); i++){
            if (name.equals(entryList.get(i).getName().toLowerCase())){
            entryList.remove(entryList.get(i));
            update();
            break;
            }
        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawHorizontalLine(GRAPH_MARGIN_SIZE);
        drawHorizontalLine( getHeight() - GRAPH_MARGIN_SIZE);
        drawVerticalLines();
        if (entryList.size() != 0) {
            drawGraph(entryList);
        }
    }

    /**
     * Method draws dependency graph for names and it's rant at different decades
     * @param entryList is arraylist of NameSurferEntry which need to draw at graphs
     */
    private void drawGraph(ArrayList<NameSurferEntry> entryList) {
        //step of graphic by X axis
        int stepX = getWidth() / NDECADES;
        // scaling coefficient for Y axis
        double scaleY = (double)(getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK ;

        for (int i = 0; i < entryList.size(); i ++){
            NameSurferEntry current = entryList.get(i);

            for (int j = 0; j < NDECADES; j++){
                int rank = (isZero(current.getRank(j)));
                int startOffset = GRAPH_MARGIN_SIZE;

                if (j != 0) {
                    //draw line between 2 points and setting color
                    int previousRank = (isZero(current.getRank(j -1)));
                    GLine line = new GLine(stepX * (j - 1), (startOffset + previousRank * scaleY),
                                        stepX * j, (startOffset + rank * scaleY));
                    chooseColor(line, i);
                    add(line);
                }
                //draw labels on each decade
                GLabel rankText = addLabel((rank == MAX_RANK ? "*" : " " + rank), i);
                GLabel nameText = addLabel( current.getName(), i);

                add(rankText, stepX * j, (startOffset + rank * scaleY));
                add(nameText, stepX * j, (startOffset + rank * scaleY) + rankText.getHeight());

                //draw point on each decade
                GOval point = new GOval(stepX * j - POINT_DIAMETER / 2,
                        (startOffset + rank * scaleY) - POINT_DIAMETER / 2,
                        POINT_DIAMETER, POINT_DIAMETER);
                point.setFilled(true);
                chooseColor(point, i);
                add(point);
            }
        }
    }

    /**
     * Draw label with name and value on each decade of graph
     * @param text the value of what to display
     * @param i need to set color of label
     * @return GLabel object
     */
    private GLabel addLabel(String text, int i) {
        double fontScale =  ((double) getWidth() * getHeight()) /  (APPLICATION_HEIGHT * APPLICATION_WIDTH);  //scale font to canvas size
        GLabel label = new GLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, (int)(13 * fontScale)));
        chooseColor(label, i);
        label.sendToFront();
        return label;
    }

    /**
     * checking if rank equal 0 than makes it value MAX_RANK
     * @param rank of name at decade
     * @return new value of rank if it's 0
     */
    private int isZero(int rank) {
        return rank == 0 ?  MAX_RANK : rank;
    }

    /**
     * Setting color of object, between values in array
     * @param obj object which taking color
     * @param i makes to choose color in array bounds
     */
    private void chooseColor(GObject obj, int i) {
        Color [] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK };
        obj.setColor(colors[i % colors.length]);
    }

    /**
     * Draws vertical lines of graph through all canvas
     */
    private void drawVerticalLines() {

        int width = getWidth()/ NDECADES;
        for ( int i = 0; i < NDECADES; i++){
            int startDecade = START_DECADE + DECADE_STEP * i;
            String decadeText = "  " + startDecade;
            GLabel label = new GLabel(decadeText);
            label.setFont("Arial-18");
            GLine line = new GLine(width * i, 0,width * i, getHeight());
            add(line);
            add(label, width * i, getHeight() - label.getDescent());

        }
    }

    /**Draws horizontal line through all canvas
     * @param height set margin
     */
    public void drawHorizontalLine(double height){
        GLine line = new GLine (0, height, getWidth(), height);
        add(line);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
