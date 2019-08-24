package com.shpp.p2p.cs.irozinko.assignment5;

import acm.graphics.GImage;
import com.shpp.cs.a.console.TextProgram;

public class test extends TextProgram {

    public void run() {
    GImage source = new GImage("C:\\Users\\ivanr\\IdeaProjects\\Assignment6\\src\\com\\shpp\\cs\\assignments\\assets\\steganography\\Stegosaurus.png") ;
    findMessage(source);

    }
    public static boolean[][] findMessage(GImage source) {
        int[][] pixels = source.getPixelArray();
        int numRows = pixels.length;
        int numCols = pixels[0].length;
        boolean[][] message = new boolean[numRows][numCols];
        for (int row = 0; row < pixels.length; row++){
            for (int col = 0; col < pixels[row].length; col++){
                int red = GImage.getRed(pixels[row][col]);
                if (isOdd(red)){
                    message[row][col] = true;
                }
            }
        }
        for (boolean[] e : message){
            System.out.println();
            for(boolean elem : e){
                System.out.print(elem + ",");
            }
        }
        return message;
    }
    private static boolean isOdd(int red) {
        return (red % 2) != 0;
    }
}
