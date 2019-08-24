package com.shpp.cs.assignments.arrays.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;
    private static final int ArraySize = 256;                           ///can use MAX +1 ???

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {

        int [] histogramImage = new int[ArraySize];

        for (int row = 0; row < luminances.length; row++) {             //can use foreach
            for (int col = 0; col < luminances[row].length; col++) {

                int luminanceValue = luminances[row][col];

                for (int i = 0; i < histogramImage.length; i++) {               /// is it not tooooooo hard????
                    if (luminanceValue == i) {                                   ///  histogramImage(luminances[row][col])++
                        histogramImage[i]++;
                    }
                }
            }
        }

        return histogramImage;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {

        int [] cumulativeHistogram = new int[ArraySize];
        int frequencySum = 0;

        for (int i = 0; i < histogram.length; i++) {
            frequencySum += histogram[i];
            cumulativeHistogram[i] = frequencySum;
        }

        return cumulativeHistogram;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        return luminances.length * luminances[0].length ;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        int [] histogramImage = histogramFor(luminances);
        int [] cumulativeHistogram = cumulativeSumFor(histogramImage);
        int totalPixels = totalPixelsIn(luminances);

        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {

                int pixelInArray = luminances[row][col];
                double fractionSmaller = (double)cumulativeHistogram[pixelInArray] / totalPixels;
                int newLuminance = (int)(MAX_LUMINANCE * fractionSmaller);
                luminances[row][col] = newLuminance;
            }
        }

        return luminances;
    }
}

//mark 5