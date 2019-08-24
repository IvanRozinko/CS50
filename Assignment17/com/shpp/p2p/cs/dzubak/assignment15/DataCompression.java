package com.shpp.p2p.cs.dzubak.assignment15;
/**
 * The program archives and
 * unzips files, depending on the parameters set at startup.
 */

import java.io.IOException;

/**
 * This class archives and unzips files
 * depending on the command line parameters set at startup.
 */
public class DataCompression {
    /**
     * Variable indicates maximum percentage
     */
    private final static int percent = 100;
    /**
     *Variable indicates the number of milliseconds per second
     */
    private final static int millisecond = 1000;
    /**
     *First command line parameter
     */
    private static String firstParameter;
    /**
     *Second command line parameter
     */
    private static String secondParameter;
    /**
     *Parameter fixes current time
     */
    private static double timeSpentOnWork;

    /**
     * The program takes command line data as input,
     * parses it and archives or unarchives it, depending on the incoming data.
     *
     * @param args command line
     * @throws IOException when file name is not found
     */
    public static void main(String[] args) throws IOException {
        try {
            stringParsing(args);

            System.out.println("firstParameter = " + firstParameter);
            System.out.println("secondParameter = " + secondParameter);
            System.out.println("");
            double start_Time = System.currentTimeMillis();
            defineArchiveOrUnzip();
            timeSpentOnWork = System.currentTimeMillis() - start_Time;
            System.out.println("Time spent on work = " + timeSpentOnWork / millisecond + " second");

        } catch (java.io.FileNotFoundException e) {
            System.out.println("The file name is incorrect or the file does not exist...");

        }

    }

    /**
     * The method of parsing the arguments and archives or unzips
     *
     * @throws IOException when working with files
     */
    private static void defineArchiveOrUnzip() throws IOException {

        if (firstParameter.matches("(.*)(.par)")) {
            uarchive();
        } else if (firstParameter.matches("(.*)(-u)")) {
            firstParameter = firstParameter.substring(0, firstParameter.indexOf("-u"));
            uarchive();
        } else if (firstParameter.matches("(.*)(-a)")) {
            firstParameter = firstParameter.substring(0, firstParameter.indexOf("-a"));
            archive();
        } else {
            archive();
        }
    }

    /**
     * The method unarchives
     * data creating a new class with incoming data
     *
     * @throws IOException when working with files
     */
    private static void uarchive() throws IOException {
        Unarchive unzip = new Unarchive(firstParameter, secondParameter);
        System.out.println("Unarchive : " + firstParameter);

    }

    /**
     * The method archives data creating a new class with incoming data
     * and displays the percentage of compression and expansion of the
     * incoming and outgoing file.
     *
     * @throws IOException when working with files
     */
    private static void archive() throws IOException {
        System.out.println("Archive : " + firstParameter);

        Archive arch = new Archive(firstParameter, secondParameter);

        double compressionPercentage = (percent - ((double) arch.getBytesArchivedFileOut() / (double) arch.getBytesArchivedFile()) * percent);
        System.out.println("Compression percentage = " + (int) compressionPercentage + "%");
        System.out.println("Incoming file size = " + arch.getBytesArchivedFile());
        System.out.println("Output file size = " + arch.getBytesArchivedFileOut());
    }

    /**
     * The method parses incoming arguments.
     * Parameters are calculated by a space.
     * Takes from 0 to 3 arguments
     *
     * @param args launch program
     */
    private static void stringParsing(String[] args) {

        if (args.length == 0) {
            firstParameter = "test.txt";
            secondParameter = firstParameter.substring(0, firstParameter.indexOf('.')) + ".par";

        } else if (args.length == 1) {
            firstParameter = args[0];
            if (firstParameter.substring(firstParameter.lastIndexOf('.') + 1).equals("par")) {
                secondParameter = firstParameter.substring(0, firstParameter.lastIndexOf(".par"));
            } else {
                secondParameter = firstParameter + ".par";
            }

        } else if (args.length == 2 && !args[0].equals("-u") && !args[0].equals("-a")) {
            firstParameter = args[0];
            secondParameter = args[1];

        } else {
            if (args.length == 3) {
                if (args[0].equals("-u")) {
                    firstParameter = args[1] + "-u";
                    secondParameter = args[2];

                } else if (args[0].equals("-a")) {
                    firstParameter = args[1] + "-a";
                    secondParameter = args[2];
                }

            } else if (args.length == 2) {
                if (args[0].equals("-u")) {
                    firstParameter = args[1] + "-u";
                    secondParameter = args[1] + ".uar";

                } else if (args[0].equals("-a")) {
                    firstParameter = args[1] + "-a";
                    secondParameter = args[1] + ".par";

                }
            }
        }
    }
}
