
package com.shpp.p2p.cs.irozinko.assignment17.huffman;


/**
 * Class is a simple archive taking a  file as input and packing file to output file archive.
 * Archive using a Huffman algorithm
 */
public class Assignment15 {
    //variable for file names
    private static String inputFile, outputFile;
    //extension of archived file
    private static String archExtension = ".par";

    public static void main(String[] args) {

        if (isFlagPresent(args)) {
            String archFlag = "-a";
            inputFile = args[1];
            outputFile = args[2];
            if (args[0].equals(archFlag)) {
                new Archive(inputFile, outputFile);
            } else {
                new Unarchive(inputFile, outputFile);
            }
        } else {
            setFilesNames(args);
            if (inputFile.contains(archExtension)) {
                new Unarchive(inputFile, outputFile);
            } else {
                new Archive(inputFile, outputFile);
            }
        }
    }

    /**Sets the names of inputted and outputted files depends of conditions
     * @param args array of arguments
     */
    private static void setFilesNames(String[] args) {
        int arguments = args.length;
        //if no parameters entered than inputted file is default: test.txt
        if (arguments == 0) {
            inputFile = "test.txt";
            arguments = 1;
        } //if received 1 argument
        if (arguments == 1) {
            if (args.length != 0){
                inputFile = args[0];
            }  //if inputted file has no extension
            if (!inputFile.contains(".")){
                outputFile = inputFile + ".uar";
            } else if (isArchive()) {
                outputFile = inputFile.replace(archExtension, "");
            } else if(!isArchive()) {
                outputFile = inputFile + archExtension;
            }
        }
        //if received 2 arguments
        if (arguments == 2) {
            inputFile = args[0];
            outputFile = args[1];
        }
    }

    /**Check if user input flag
     * @param args arguments array
     * @return boolean answer
     */
    private static boolean isFlagPresent(String[] args) {
        return (args.length == 3);
    }


    /**Check is file a archive
     * @return boolean answer
     */
    private static boolean isArchive() {
        return inputFile.indexOf(archExtension,inputFile.length() - archExtension.length()) != -1;
    }
}
