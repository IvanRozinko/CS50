package com.shpp.p2p.cs.dzubak.assignment13;

import acm.graphics.GImage;
import com.shpp.p2p.cs.dzubak.assignment17.MyQueue;

/**
 * The program displays the number
 * of silhouettes in the image, with the file type * .jpg
 */
public class Silhouette {
    //  Directory for incoming and outgoing files
    private static final String PART = "assets/";
    //Two-dimensional array which describes the presence of the image
    private static int[][] pixels;
    //Two-dimensional array which is used to check the passage of the cycle
    private static boolean[][] pixelsBoolean;
    //Background color, red component
    private static int red;
    //Background color, green component
    private static int green;
    //Background color, blue component
    private static int blue;
    //Image counter
    private static int numberSilhouettes;
    //Color difference interval
    private static int COLORDIFFERENCE = 50;
    //Percentage for discarding trash from an image
    private static double TRASHPERCENT = 1.0;
    //Number of all image pixels
    private static int totalPixels;
    //Number of pixels of each image
    private static int imagePixels;

    /**
     * The program displays the number of silhouettes in the image,
     * the image is set in the launch parameters and must be in the
     * root directory next to the src folder and have the file type * .jpg
     *
     * @param args program launch
     */
    public static void main(String args[]) {
        try {
            String filename;
            if (args.length == 0) {
                filename = "test.jpg";
            } else {
                filename = args[0];
            }
            GImage image = new GImage(PART + filename);
            pixels = image.getPixelArray();

            defineBackground();
            disassembleImage();
            findSilhouettes();

            System.out.println("Your amount : " + numberSilhouettes);
            System.out.println("Your pixel : " + totalPixels);
            System.out.println("Background color is defined as, " + "red: " + red + " , green: " + green + " , blue: " + blue);

        } catch (acm.util.ErrorException e) {
            System.out.println("The specified file was not found...");
        } catch (NegativeArraySizeException e) {
            System.out.println("Invalid file type...");
        }
    }

    /**
     * The method determines the background of the image
     * and decomposes it into color components: red, green, blue.
     */
    private static void defineBackground() {

        for (int i = 0; i < pixels[0].length; i++) {
            red += GImage.getRed(pixels[0][i]);
            red += GImage.getRed(pixels[pixels.length - 1][i]);
            green += GImage.getGreen(pixels[0][i]);
            green += GImage.getGreen(pixels[pixels.length - 1][i]);
            blue += GImage.getBlue(pixels[0][i]);
            blue += GImage.getBlue(pixels[pixels.length - 1][i]);
        }
        red /= (pixels[0].length * 2);
        green /= (pixels[0].length * 2);
        blue /= (pixels[0].length * 2);
    }

    /**
     * Image search using the "Search in width" method.
     * This is done according to the matrix and verified.
     * if this pixel was used to check or not.
     * Small images are clipped.
     */
    private static void findSilhouettes() {
        numberSilhouettes = 0;

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++)
                if (pixels[row][col] == 1 && pixelsBoolean[row][col]) {
                    breadthFirstSearch(row, col);

                    if ((double) imagePixels / (double) totalPixels * 100 >= TRASHPERCENT) {
                        numberSilhouettes++;
                    }
                    //System.out.println("The number of pixels in the image: " + imagePixels);

                } else if (pixels[row][col] == 0 && pixelsBoolean[row][col]) {
                    pixelsBoolean[row][col] = false;
                }
        }
    }

    /**
     * The method searches for an image wide. A queue is used and every pixel
     * and its children are checked.
     * The method counts the number of pixels in the image.
     *
     * @param x a variable that is responsible for the row in which the pixel is located
     * @param y variable which is responsible for the column in which the pixel is located
     */
    private static void breadthFirstSearch(int x, int y) {

        MyQueue<Integer> imageArray = new MyQueue<>();
        imagePixels = 0;

        if (pixels[x][y] == 1 && pixelsBoolean[x][y]) {
            pixelsBoolean[x][y] = false;
            imageArray.add(x);
            imageArray.add(y);

            while (!imageArray.isEmpty()) {
                int newX = imageArray.pop();
                int newY = imageArray.pop();
                imagePixels++;

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (newX + i >= 0 && newX + i <= pixels.length - 1 && newY + j >= 0 && newY + j <= pixels[0].length - 1) {
                            if (pixels[newX + i][newY + j] == 1 && pixelsBoolean[newX + i][newY + j]) {
                                pixelsBoolean[newX + i][newY + j] = false;
                                imageArray.add(newX + i);
                                imageArray.add(newY + j);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * A method that checks the presence of an image by comparing the background
     * and image colors and fills a two-dimensional array of 0 and 1.
     * If there is an image, then 1 if not then 0.
     * And it also fills the two-dimensional array with true values
     * The method counts the number of all pixels in the image.
     */
    private static void disassembleImage() {
        totalPixels = 0;
        pixelsBoolean = new boolean[pixels.length][pixels[0].length];

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                pixelsBoolean[row][col] = true;
                int redImage = GImage.getRed(pixels[row][col]);
                int greeenImage = GImage.getGreen(pixels[row][col]);
                int blueImage = GImage.getBlue(pixels[row][col]);
                if (redImage > red - COLORDIFFERENCE && redImage < red + COLORDIFFERENCE &&
                        greeenImage > green - COLORDIFFERENCE && greeenImage < green + COLORDIFFERENCE &&
                        blueImage > blue - COLORDIFFERENCE && blueImage < blue + COLORDIFFERENCE) {
                    pixels[row][col] = 0;

                } else {
                    pixels[row][col] = 1;
                    totalPixels++;
                }
            }
        }
    }

}
