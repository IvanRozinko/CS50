package com.shpp.p2p.cs.irozinko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * This class extracts a column from  *.csv file
 */
public class Assignment5Part4 extends TextProgram {
    //Here set the path to file
    private static final String CSVFILE = "newTest.csv";

    /**
     * Starts the program. Taking a number from user and printing result
     */
    public void run() {
        try {
            int columnIndex = readInt("Please enter column number: ");
            ArrayList<String> result = extractColumn(CSVFILE, columnIndex);
            printResult(result);
        } catch (InputMismatchException e){
            println("Please input a NUMBER");
        }
    }

    /**
     * Printing Arraylist each element at new string if it's not a null
     * @param result is a Arraylist of strings which has resulting column from file
     */
    private void printResult(ArrayList<String> result) {
        if (result != null) {
            for (String element : result) {
                println("[" + element + "]");
            }
        }
        else {
            println("File not found");
        }
    }

    /**
     * Method copying every string receiving from array allStrings  to two-dimensional array
     * list and after extracting column from it.
     * @param filename name of input file
     * @param columnIndex number of column to extract
     * @return Arraylist, which contains column choose by user
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex){
        //Making two-dimensional array
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> allStrings = getAllStrings(filename);
        if (allStrings == null){
            return null;
        }
        else {
            //if array not null, put every string of allStrings to list
            for (String string : allStrings) {
                list.add(fieldsIn(string));
            }
        }
        return  getColumn(list, columnIndex);
    }

    /**
     * method takes a number of column from two dimensional array
     * @param list is a two dimensional array with strings
     * @param columnIndex number of column to extract
     * @return array which contains column
     */
    private ArrayList<String> getColumn(ArrayList<ArrayList<String>> list, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        for(ArrayList<String> element : list){
            result.add(element.get(columnIndex));
        }
        return result;
    }

    /**
     * takes a string from file and separates it to elements, divided by commas or quotes
     * @param string taking whole string from file
     * @return string divided to array of elements
     */
    private ArrayList<String> fieldsIn(String string) {
        ArrayList<String> result = new ArrayList<>();
        String cell;
        int startIndex = 0;
        int endIndex = 0;
        int quotesIndex;
        while (endIndex != string.length()){
            quotesIndex = string.indexOf("\"", startIndex);
            endIndex = string.indexOf(",", startIndex);
            //if string ends
            if (endIndex == -1){
                endIndex = string.length();
            }
            //if string contain quotes
            if (quotesIndex >= startIndex && quotesIndex < endIndex){
                endIndex = string.indexOf("\"", quotesIndex + 1);
                cell = string.substring(quotesIndex + 1, endIndex);
                endIndex += 1;
            }
            else {
                cell = string.substring(startIndex, endIndex);
            }
            startIndex = endIndex + 1;
            result.add(cell);
        }
        return result;
    }

    /**
     * method take every string from file and putting it to array one by one
     * @param filename name of file
     * @return array of strings
     */
    private ArrayList<String> getAllStrings(String filename) {
       ArrayList<String> result =  new ArrayList<>();
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null){
                result.add(line);
            }
            reader.close();
        } catch (IOException e) {
           result = null;
        }
        return result;
    }
}
