package com.shpp.p2p.cs.irozinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class taking from user 3 letters and returning all words from given file which contains this letters
 * in same order.
 */
public class Assignment5Part3 extends TextProgram {
    private static final String DICTIONARY = "C:/Users/ivanr/IdeaProjects/assignment5/src/com/shpp/p2p/cs/irozinko/" +
                                             "assignment5/en-dictionary.txt";

    /**
     * Starting program, and in loop asking user to input 3 letters. Printing as result all words have, match
     * input letters
     */
    public void run() {
        while(true){
            String input = readLine("Please enter 3 letters");
            ArrayList<String> allMatches = findAllMatches(input);
            if (allMatches.size() == 0){
                println("There are no matches founded");
            }
            else {
                for (String matchWord : allMatches) {
                    println("[" + matchWord + "]");
                }
                println("We found " + allMatches.size() + " matches: ");
            }
        }
    }

    /**
     * Receiving string of letters converting it to lower case char array, checking if there are words matches in file
     * @param input string of letters inputted by user
     * @return array of words which matches the inputted letters
     */
    private ArrayList<String> findAllMatches(String input) {
        char inputLetters [] = input.toLowerCase().toCharArray();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> allWords = makeArrayOfAllWords();
        for (String word : allWords) {
            if (isThisWordMatches(word, inputLetters)){
                result.add(word);
            }
        }
        return result;
    }

    /**
     * This method checking if word from file contains inputted by user letters and returns true or false
     * @param word string from  file
     * @param inputLetters inputted letters by user
     * @return boolean matches or not
     */
    private boolean isThisWordMatches(String word, char [] inputLetters) {
        boolean result = false;
        int previousIndex = -1;
        int count = 0;
        for (char letter : inputLetters) {
            int index = word.indexOf(letter);
            //checking if users inputted letters repeating ('aaa' or 'abb')
            if (index <= previousIndex && previousIndex != word.length()){
                index = word.indexOf(letter, previousIndex + 1);
            }
            //if index of letter bigger than index of previous letter increment counter
            if (index > previousIndex) {
                previousIndex = index;
                count ++;
                if (inputLetters.length == count) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Putting every string from file to array
     * @return array every element of it is a string of file
     */
    private ArrayList<String> makeArrayOfAllWords() {
        ArrayList<String> allWords = new  ArrayList<>();
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(DICTIONARY));
            while ((line = reader.readLine()) != null){
                allWords.add(line);
            }
            reader.close();
        }catch (IOException e) {
            println("File not found");
        }
        return allWords;
    }
}
