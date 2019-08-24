package com.shpp.p2p.cs.dzubak.assignment11;

import com.shpp.p2p.cs.dzubak.assignment17.MyArrayList;
import com.shpp.p2p.cs.dzubak.assignment17.MyHashMap;
import com.shpp.p2p.cs.dzubak.assignment17.MyStack;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The program of counting values ​​according to the formula.
 * The program takes as input the arguments
 * for starting the program and parses the input
 * data for the space, then creates a HashMap
 * from the list of variable values ​​and a formula.
 * The value is considered the method of the reverse
 * Polish notation with the output of the value to the console.
 */

public class Calculator {

    private static String formula;
    private static String variables;
    private static MyHashMap<String, Double> dataBaseVariables;
    private static Stack<String> stackForm;
    private static MyStack<Double> stackDouble;
    private static MyArrayList<String> reverseRecording;

    private static Pattern variableForm = Pattern.compile("[a-zA-Z]+");
    private static Pattern numeralForm = Pattern.compile("\\d+\\.?\\d{0,20}");
    private static Pattern signForm = Pattern.compile("[-+*/^()]");
    private static Pattern functionForm = Pattern.compile("(sin||cos||tan||ctan||log10||log2||sqrt)");


    /**
     * A method that divides start-up parameters by spaces and,
     * depending on the type of record, divides it into a formula
     * and value. When starting, the user is prompted to enter values ​​of variables..
     *
     * @param args space separated input string parameters
     * @throws Exception are thrown when executing methods.
     *                   If no variable is specified there will be an exception
     */
    public static void main(String args[])  {



        try {
            stringParsing(args);
            while (true){
                Scanner sc = new Scanner(System.in);
                System.out.print("Do you want to change the values of variables? If not, click Enter:");
                variables = sc.nextLine();
                stringParsingInput(variables);
                breakIntoComponents(formula);
                calculate();

            }

/*
            for (int i = 0; i < reverseRecording.size(); i++) {
                System.out.print(reverseRecording.get(i) + " ");
            }
*/

        } catch (NumberFormatException e) {
            System.out.println("Variable does not exist");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Not correct variable value entered");
        } catch (NullPointerException e) {
            System.out.println("Not correct formula introduced");
        } catch (EmptyStackException e) {
            System.out.println("Check brackets for correctness or mathematical signs");
        }
    }

    /**
     * A method that separates the value of a variable by spaces and writes the value to the HashMap
     *
     * @param s space separated string
     */
    private static void stringParsingInput(String s) {
        if (s.isEmpty()) {
            return;
        } else {
            String[] variablesString = s.split(" ");
            for (String aVariablesString : variablesString) {
                String variable = aVariablesString.substring(0, aVariablesString.indexOf('='));
                String str = aVariablesString.substring(aVariablesString.indexOf('=') + 1);
                double var = Double.parseDouble(str);
                dataBaseVariables.put(variable, var);
            }
        }
    }

    /**
     * Parsit array method and splits data into formulas and values.
     * The formula is passed as a string and variables as HashMap with
     * a key equal to the value of the variable.
     *
     * @param args arguments that are passed from the main method
     */
    private static void stringParsing(String[] args) {
        dataBaseVariables = new MyHashMap<>();
        for (String s : args) {
            Pattern patternVar = Pattern.compile("[a-zA-Z]+=[-]?\\d+\\.?\\d{0,20}");
            Pattern patternForm = Pattern.compile("[a-zA-Z\\d-+/*^().]+[^=]");
            Matcher matcherForm = patternForm.matcher(s);
            Matcher matcherVariable = patternVar.matcher(s);
            if (matcherForm.matches()) {
                formula = s;
            }
            if (matcherVariable.matches()) {
                String variable = s.substring(0, s.indexOf('='));
                String str = s.substring(s.indexOf('=') + 1);
                double var = Double.parseDouble(str);
                dataBaseVariables.put(variable, var);
            }
        }

    }

    /**
     * Method that calculates the value according
     * to the formula. At the input takes ArrayList of
     * the reverse Polish record and the value of HashMap values ​​of variables.
     * For calculations, the class Stack is used which,
     * after performing operations, has one element which is the result.
     */
    private static void calculate() {
        stackDouble = new MyStack<>();

        for (int i =0; i<reverseRecording.size();i++) {

            if (variableForm.matcher(reverseRecording.get(i)).matches() && !functionForm.matcher(reverseRecording.get(i)).matches()) {
                stackDouble.push(Double.parseDouble(String.valueOf(dataBaseVariables.get(reverseRecording.get(i)))));

            } else if (numeralForm.matcher(reverseRecording.get(i)).matches()) {
                stackDouble.push(Double.parseDouble(reverseRecording.get(i)));

            } else if (signForm.matcher(reverseRecording.get(i)).matches()) {

                double operand1 = stackDouble.pop();
                double operand2 = stackDouble.pop();

                switch (reverseRecording.get(i)) {
                    case "+":
                        stackDouble.push(operand2 + operand1);
                        break;
                    case "-":
                        stackDouble.push(operand2 - operand1);
                        break;
                    case "*":
                        stackDouble.push(operand2 * operand1);
                        break;
                    case "/":
                        stackDouble.push(operand2 / operand1);
                        break;
                    case "^":
                        stackDouble.push(Math.pow(operand2, operand1));
                        break;
                }

            } else if (functionForm.matcher(reverseRecording.get(i)).matches()) {

                double operand3 = stackDouble.pop();

                switch (reverseRecording.get(i)) {
                    case "sin":
                        stackDouble.push(Math.sin(Math.toRadians(operand3)));
                        break;

                    case "cos":
                        stackDouble.push(Math.cos(Math.toRadians(operand3)));
                        break;

                    case "tan":
                        stackDouble.push(Math.tan(Math.toRadians(operand3)));
                        break;

                    case "ctan":
                        stackDouble.push(1 / (Math.tan(Math.toRadians(operand3))));
                        break;

                    case "log10":
                        stackDouble.push(Math.log10(operand3));
                        break;

                    case "log2":
                        stackDouble.push(Math.log10(operand3) / Math.log10(2));
                        break;

                    case "sqrt":
                        stackDouble.push(Math.sqrt(operand3));
                        break;
                }
            }
        }
        System.out.println("Your result " + formula + " = " + stackDouble.peek());

    }

    /**
     * The method breaks the formula into components,
     * then from the indicated components creates a
     * reverse Polish notation which it writes to ArrayList.
     *
     * @param arg the string value is the specified formula
     */
    private static void breakIntoComponents(String arg) {
        stackForm = new Stack<>();
        reverseRecording = new MyArrayList<>();

        StringTokenizer formNew = new StringTokenizer(arg, "-+*/^()", true);
        while (formNew.hasMoreTokens()) {
            String value = formNew.nextToken();

            if (variableForm.matcher(value).matches() && !functionForm.matcher(value).matches()) {
                reverseRecording.add(value);

            } else if (functionForm.matcher(value).matches()) {
                stackForm.push(value);

            } else if (numeralForm.matcher(value).matches()) {
                reverseRecording.add(value);

            } else if (signForm.matcher(value).matches() && stackForm.empty()) {
                stackForm.push(value);
                continue;

            } else if (signForm.matcher(value).matches() && !stackForm.empty()) {

                if (!value.equals(")") && !value.equals("(") && ((checkTheCharacter(value) > checkTheCharacter(stackForm.peek()))
                        || (checkTheCharacter(value) == checkTheCharacter(stackForm.peek())))) {

                    reverseRecording.add(stackForm.pop());

                    stackForm.push(value);

                } else if (value.equals("(")) {
                    stackForm.push(value);

                } else if (value.equals(")")) {
                    int var = stackForm.search("(");
                    int var2 = stackForm.search("(sin||cos||tan||ctan||log10||log2||sqrt)");

                    for (int i = 0; i < var - 1; i++) {
                        reverseRecording.add(stackForm.pop());
                    }
                    stackForm.pop();
                    if (var - var2 == 1) {
                        reverseRecording.add(stackForm.pop());
                    }

                } else {
                    stackForm.push(value);
                }
            }
        }
        while (!stackForm.empty()) {
            reverseRecording.add(stackForm.pop());
        }
    }

    /**
     * A method that returns a priority value for mathematical operations/
     *
     * @param value The value of the string that is equal to the mathematical operation
     * @return priority value of a mathematical operation
     */
    private static int checkTheCharacter(String value) {
        switch (value) {
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 1;
            case "(":
                return 4;
        }
        return 3;
    }

}
