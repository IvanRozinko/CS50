package com.shpp.cs.assignments.arrays.tm;

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

        for (int row = 0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]) {
                for (int rowResult = 0; rowResult < result.length; rowResult++) {
                    result[rowResult] += samples[row][rowResult];
                }
            }
        }

        return normalizeWaveSound(result);
    }

    /**
     * Normalize the sound wave for the range [-1.0; 1.0]
     * @param result - A sound, the intensity of the wave of which at each moment
     *              of time is equal to the sum of the intensities of all incoming sounds
     * @return - A sound sample corresponding to all notes currently being played after normalize
     */
    private static double[] normalizeWaveSound(double[] result) {
        //the wave with the greatest intensity
        double maxWave = result[0];

        for (int row = 0; row < result.length; row++) {                         ///foreach?
            if (Math.abs(result[row]) > Math.abs(maxWave)) {
                maxWave = result[row];
            }
        }

        if (maxWave == 0) {
            return result;
        } else if (Math.abs(maxWave) > 1.0){
            for (int row = 0; row < result.length; row++) {
                result[row] /= maxWave;
            }
        }

        return result;
    }
}

//mark 5