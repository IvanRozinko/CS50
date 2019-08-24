package com.shpp.p2p.cs.irozinko.assignment6.tm;



public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        for (int row = 0; row < toneMatrix.length; row++){
            //find which lamps is on
            if (toneMatrix[row][column]){
                //combine sound samples to one
                for (int i = 0; i < samples[row].length; i++){
                    result[i] = result[i] + samples[row][i];
                }
            }
        }
        //normalize sound wave [-1.0;1.0]
        result = normalizeWave(result);
        return result;
    }

    /**Method receiving array of numbers and scaling it be in range of values [-1.0;1.0]
     * @param sample sum array of samples
     * @return normalized array
     */
    private static double[] normalizeWave(double[] sample) {
        double max = 0.0;
        for (int i = 0; i < sample.length; i++){
            if (Math.abs(sample[i]) > Math.abs(max)){
                max = sample[i];
            }
        }
        if (max == 0){
            return new double[sample.length];
        }

        for (int i = 0; i < sample.length; i++) {
            sample[i] = sample[i] / max;
        }

     return sample;
    }
}
