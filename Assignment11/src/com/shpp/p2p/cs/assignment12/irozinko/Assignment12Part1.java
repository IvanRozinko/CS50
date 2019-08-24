package com.shpp.p2p.cs.assignment12.irozinko;

import acm.graphics.GImage;


/**
 * This class counting silhouettes at the image. Name of image file user inputting through command line
 * Background and objects of silhouettes should be differ a lot.
 * Silhouettes should be bigger enough to be detected, you can set min size of object.
 */
public class Assignment12Part1{
    /* define size of histogram array*/
    private static final int HISTOGRAM_SIZE = 256;
    /* variable in percent of image size, detecting only objects bigger than 0.5 % of image */
    private static final double MIN_SIZE_OF_OBJECT = 0.5;
    /* name of file with image*/
    private static final String FILE_NAME = "4orange.jpg";
    /* difference between pixels num of background and foreground in percent*/
    private static final int BACKGROUND_DIFFER = 1;
    /* array to check if current element been checked already*/
    private static  boolean [][] checked;
    /* array with pixels of image*/
    private static int [][] pixels;
    /* threshold to separate background from silhouettes*/
    private static int threshold;
    /* counter of pixels in silhouette*/
    private static int pixelsInObject;
    /* image histogram */
    private static int [] histogram;


    public static void main(String[] args) {
        /* analyzing image*/
        GImage image;                                           //add try catch..
        if (args.length == 0){
             image = new GImage(FILE_NAME);
        }
        else {
            image = new GImage(args[0]);
        }
        System.out.println("I found "+countSilhouettes(image)+" silhouettes at this image ");
    }


    /** Method receiving image and returning number of silhouettes on it.
     * @param image object of image
     * @return int number of silhouettes
     */
    private static int countSilhouettes(GImage image) {
        int result = 0;
        /* convert image to gray scale */
        pixels = toGrayscale(image);
        checked = new boolean[pixels.length][pixels[0].length];
        /* count size og image */
        int imageSize = pixels.length * pixels[0].length;
        /* calculate threshold */
        threshold = findThreshold(imageSize);
        /* check if image has dark background */
        boolean isDark = isDarkBackground();

        for (int row = 0; row < pixels.length; row++){
            for (int col = 0; col < pixels[row].length - 1; col++){
                int luminance = getLuminance(row, col);
                /* if dark background and find unchecked object and it luminance more or equal threshold*/
                if ( isDark && luminance >= threshold && !checked[row][col]){
                    DFSDark(row, col);
                }
                /* if light background and find unchecked object and it luminance less than threshold*/
                if (!isDark && luminance < threshold && !checked[row][col]){
                    DFSLight(row, col);
                }
                    /* if size of silhouette bigger than min count it*/
                if (pixelsInObject * 100.0 / imageSize  > MIN_SIZE_OF_OBJECT) {
                    result++;
                    System.out.format("№ "+ result + " silhouette. Size: %.1f%n ",pixelsInObject * 100.0/ imageSize);
                    pixelsInObject = 0;
                }
            }
        }
        return result;
    }


    /**Method checks if image is dark, and difference between dark and light pixels more than constant,
     *  based on image histogram
     * @return boolean value
     */
    private static boolean isDarkBackground() {
        double lightSum = 0;
        double darkSum = 0;
        for (int i = 0; i < HISTOGRAM_SIZE; i++){
            if (i <= threshold){
                darkSum += histogram[i];
            }
            else {
                lightSum += histogram[i];
            }
        }
            return (darkSum - lightSum)/(darkSum + lightSum) * 100 > BACKGROUND_DIFFER;
    }

    /** Method checks dark images, using depth first search to find all pixels of silhouette.
     * Checking 4 pixels around current starting
     * with left.
     * @param row of array with pixels
     * @param col of array with pixels
     */
    private static void DFSDark(int row, int col) {
        pixelsInObject ++;
        checked[row][col] = true;
        /* checking pixel left side from current*/
        if (col - 1 > 0 && getLuminance(row, col - 1) >= threshold && !checked[row][col - 1]) {
            DFSDark((row), (col - 1));
        } /* checking pixel under current*/
        if (row + 1 < pixels.length && getLuminance(row + 1, col) >= threshold && !checked[row + 1][col]) {
            DFSDark((row + 1), (col));
        }/* checking pixel right side from current*/
        if (col + 1 < pixels[row].length && getLuminance(row, col + 1) >= threshold && !checked[row][col + 1]) {
            DFSDark((row), (col + 1));
        }/* checking pixel upper current*/
        if (row - 1 > 0 && getLuminance(row - 1, col) >= threshold && !checked[row - 1][col]) {
            DFSDark((row - 1), (col));

        }
    }


    /** Method checks light images using depth first search to find all pixels of silhouette.
     *  Checking 4 pixels around current starting
     * with left.
     * @param row of array with pixels
     * @param col of array with pixels
     */
    private static void DFSLight(int row, int col) {
        pixelsInObject ++;
        checked[row][col] = true;
       /* checking pixel left side from current*/
        if (col - 1 > 0 && getLuminance(row, col - 1) < threshold && !checked[row][col - 1]) {
            DFSLight((row), (col - 1));
        } /* checking pixel under current*/
        if (row + 1 < pixels.length && getLuminance(row + 1, col) < threshold && !checked[row + 1][col]) {
            DFSLight((row + 1), (col));
        }/* checking pixel right side from current*/
        if (col + 1 < pixels[row].length && getLuminance(row, col + 1) < threshold && !checked[row][col + 1]) {
            DFSLight((row), (col + 1));
        }/* checking pixel upper current*/
        if (row - 1 > 0 && getLuminance(row - 1, col) < threshold && !checked[row - 1][col]) {
            DFSLight((row - 1), (col));
        }
    }


    /** Getting  luminance value of current pixel if it is inside of image if not returning white value
     * @param row of pixel in array
     * @param col of pixel in array
     * @return value of luminance of pixel[row][col]
     */
    private static int getLuminance(int row, int col) {
          return GImage.getRed(pixels[row][col]);
    }


    /** Searching threshold of gray scale image to separate background from silhouettes. Here used Otsu algorithm which
     * converting gray scale image to monochrome. Aim  is to find the threshold value where the sum of foreground
     * and background spreads is at its minimum.
     * http://www.labbookpages.co.uk/software/imgProc/otsuThreshold.html - link to original
     * @param imageSize in pixels size of image
     * @return value of threshold
     */
    private static int findThreshold(int imageSize) {
        /*getting histogram of image */
        histogram = histogramFor(imageToLuminances(pixels));

        int sum = 0;
        /* calculate sum of all intensities in image*/
        for (int i = 0; i < HISTOGRAM_SIZE; i++){
            sum += i * histogram[i];
        }

        double sumB = 0.0;
        double weightB = 0;
        double weightF;
        double max = 0.0;
        int threshold = 0;

        for(int i = 0; i < HISTOGRAM_SIZE; i++){
            /* weight background */
            weightB += histogram[i];
            if(weightB == 0) {
                continue;
            }
            /*weight foreground*/
            weightF = imageSize - weightB;
            if (weightF == 0){
                break;
            }
            sumB += (double) (i * histogram[i]);
            /* mean background */
            double meanBackground =  sumB / weightB;
            /* mean foreground */
            double meanForeground = (sum - sumB)/ weightF;
            /* calculate between class variance */
            double varBetween = weightB * weightF *
                    (meanBackground - meanForeground) *
                    (meanBackground - meanForeground);
            if (varBetween > max){
                max  = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }


    /** Converting image to grayscale image using
     * @param image inputted by user
     * @return array of pixels converted to gray scale
     */
    private static int[][] toGrayscale(GImage image) {
        int[][] pixels = image.getPixelArray();
        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                int intensity = (int) (0.299 * (double) GImage.getRed(pixels[row][col]) +
                                       0.587 * (double) GImage.getGreen(pixels[row][col]) +
                                       0.114 * (double) GImage.getBlue(pixels[row][col]));
                pixels[row][col] = GImage.createRGBPixel(intensity, intensity, intensity);
            }
        }
        return pixels;
    }

    /**Creating array of luminance's of each pixel in image
     * @param pixels of image
     * @return two dimensional array of luminance's
     */
    private static int[][] imageToLuminances(int[][] pixels) {
        int[][] luminances = new int[pixels.length][pixels[0].length];
        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                luminances[row][col] = GImage.getRed(pixels[row][col]);
            }
        }

        return luminances;
    }

    /**Creating histogram for image based on it luminance's
     * @param luminances array of luminance's
     * @return histogram of image
     */
    private static int[] histogramFor(int[][] luminances) {
        int[] histogram = new int[HISTOGRAM_SIZE];
        /* Fills the histogram array. */
        for (int[] row : luminances) {
            for (int luminance : row) {
                ++histogram[luminance];
            }
        }
        return histogram;
    }

}
