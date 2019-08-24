package com.shpp.p2p.cs.irozinko.assignment2;


import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {
    /**
     * Method solves the quadratic equation. Takes 3 numbers from user
     * and displays roots of quadratic equation.
     */
    private double d, a, b, c;
    public void run() {
        enterNumbers();
        calculateDiscriminant();
        findRoots();
    }
    /* ask user to enter 3 numbers and pass it to variables a,b,c*/
    private void enterNumbers() {
        a = readInt("Please enter a: ");
        b = readInt("Please enter b: ");
        c = readInt("Please enter c: ");
    }
    /* calculating discriminant of square equation*/
    private void calculateDiscriminant() {
        d = Math.pow(b,2) - 4 * (a * c);
    }
    /* find roots of square equation depends of it value*/
    private void findRoots() {
        double root1, root2;
        if (d > 0) {                                        // Checking if discriminant d > 0
            root1 =  ((- b) + Math.sqrt(d)) / (2 * a);
            root2 = ((- b) - Math.sqrt(d)) / (2 * a);
            println("There are two roots: " + root1 + " and " + root2);
        }
        if (d == 0) {                                       // Checking if discriminant d == 0
            root1 = ((- b) + Math.sqrt(d)) / (2 * a);
            println("There is one root: " + root1);
        }
        if (d < 0) {                                        // Checking if discriminant d == 0
            println("There are no real roots");
        }
    }
}
