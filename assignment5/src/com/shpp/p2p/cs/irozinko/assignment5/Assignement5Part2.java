package com.shpp.p2p.cs.irozinko.assignment5;

import com.shpp.cs.a.console.TextProgram;


/**
 * This class adding two numeric strings (strings consisting purely of digits) representing two
 * numbers n1 and n2, returns a numeric string representing their sum. The input
 * strings don't have to be the same length, but each will represent a nonnegative
 * integer.
 */
public class Assignement5Part2 extends TextProgram {

    /**
     * Runs the program in a loop asking user to inptu 2 numbers
     */
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of not negative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        String result = "";
        int decadePart = 0;
        int num1, num2;
        String longerNumber = getLongerNumber(n1, n2);
        String shorterNumber = getShorterNumber(n1, n2);
        int longerLength = longerNumber.length() - 1;
        int shorterLength = shorterNumber.length() - 1;
        for (int i = 0; i <= longerLength; i++) {
            if (i > shorterLength) {
                num1 = longerNumber.charAt(longerLength - i) - '0';
                num2 = 0;
            } else {
                num1 = longerNumber.charAt(longerLength - i) - '0';
                num2 = shorterNumber.charAt(shorterLength - i) - '0';
            }
            int sum = num1 + num2 + decadePart;
            decadePart = 0;
            if (sum > 9) {
                decadePart = sum / 10;
                sum = sum % 10;
                if (i == longerLength) {
                    return result = decadePart + (sum + result);
                }
            }
            result = sum + result;
        }

        return result;
    }

    /**Taking 2 numbers in String format and returning shorter of them
     * @param n1 number in string format
     * @param n2 number in string format
     * @return shorter number in string format
     */
    private String getShorterNumber(String n1, String n2) {
        return n1.length() < n2.length() ? n1 : n2;
    }

    /**Taking 2 numbers in String format and returning longer of them
     * @param n1 number in string format
     * @param n2 number in string format
     * @return longer number in string format
     */
    private String getLongerNumber(String n1, String n2) {
        return n1.length() >= n2.length() ? n1 : n2;
    }


}
