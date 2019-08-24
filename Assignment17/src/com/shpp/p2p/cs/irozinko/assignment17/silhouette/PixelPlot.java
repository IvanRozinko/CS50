package com.shpp.p2p.cs.irozinko.assignment17.silhouette;

/**
 * Contains 2 integers which means the row and column of pixel in two-dimensional array
 */
class PixelPlot {
    /* variables of pixel position */
    private int row;
    private int col;
    /* class constructor */
    PixelPlot (int row, int col){
        this.row = row;
        this.col = col;
    }

    /* getter of row */
    int getRow() {
        return row;
    }
    /* getter of col */
    int getCol() {
        return col;
    }
}
