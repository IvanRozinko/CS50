package com.shpp.p2p.cs.irozinko.assignment17.calculator;

import com.shpp.p2p.cs.irozinko.assignment17.*;


/**
 * This class imitating calculator, supported next commands: " +,-,*,/,^" including brackets "(",")" and
 * functions "sin, cos, tan, atan, log10, log2, sqrt".
 * User through command line entering an expression and getting result of it.
 * It's possible to input expression with variables. In that case, first argument of command line can be expression
 * to solve all next input must be variables and it values type of "a=2".
 */

public class Assignment10Part2 {
    /* Type of splitter in variable expression*/
    private static final char SPLITTER = '=';
    /* Regex of expression type  "name of variable" "sign '='" "value of variable" */
    private static final String VARIABLE_INIT = "^[a-z]=[-]?(\\d*[.])?\\d+";

    /* info about type of error */
    private static MyArrayList<String> errorMessage = new MyArrayList<>();

    public static void main(String[] args) {
        String formula;
        double result;
        MyHashMap<String, Double> var = new MyHashMap<>();
        /* this variable used to store formula inputted by user through command line */
        if (args.length != 0) {
            formula = args[0];
            /* check if user input more than 1 argument */
            if (args.length > 1) {
                /* this variable used to store name and value of variable inputted by user through command line */
                for (int i = 1; i < args.length; i++) {
                    String string = replaceSpaces(args[i]);

                    if (string.matches(VARIABLE_INIT)) {
                        var.put(getKey(string), getValue(string));
                    } else {
                        errorMessage.add("Wrong type of variable initiation");
                    }
                }
            }
            result = calculate(parseToRPN(formula), var);
            if (errorMessage.size() == 0) {
                System.out.println("Result of calculating = " + result);
            } else {
                for (String msg : errorMessage) {
                    System.out.println(msg);
                }
            }
        } else {
            System.out.println("You forget to enter formula, please try again");
        }
    }

    /**
     * Calculating in formula expressions in order with priority. Method receiving formula parsed to RPN,
     * inserts the values of variables and returning result of calculation
     *
     * @param string formula parsed to RPN
     * @return result of expression
     * @var values of variables
     */
    private static double calculate(MyArrayList<String> string, MyHashMap<String, Double> var) {
        /* inserting value of variables inputted by user */
        MyArrayList<String> formula = insertVariables(string, var);
        MyStack<String> stack = new MyStack<>();
        /* to store function names and instances of it classes */
        MyHashMap<String, IAction> functionMap = makeFunctionMap();

        for (String current : formula) {
            /* if is operand push it to stack */
            if (isNum(current)) {
                /* check for multiplying points in operand */
                if (current.indexOf(".") != current.lastIndexOf(".")) {
                    errorMessage.add("Probably you input multiplying points in operand: " + current);
                    return 0;
                } else {
                    stack.push(current);
                }
            }   /* if is variable, add error message */
            else if (isLetter(current) && current.length() == 1) {
                errorMessage.add("Probably you forget to init this variable: " + current);
                return 0;
            }   /* calculate value of function and push to stack */
            else if (isFunction(current)) {
                IAction function = functionMap.getValue(current);
                if (function != null) {
                    double result = function.calculate(Double.parseDouble(stack.pop()));
                    stack.push(String.valueOf(result));
                } else {
                    errorMessage.add("Unknown name of function: " + current);
                }
            }   /* if it's operator calculate result of operation and push it to stack */
            else if (isOperator(current)) {
                double second = Double.parseDouble(stack.pop());
                /* check if user input only 1 operand */
                if (!stack.isEmpty()) {
                    double first = Double.parseDouble(stack.pop());
                    stack.push(getResult(first, second, current));
                } else {
                    errorMessage.add("Probably you forget to input second operand");
                    return 0;
                }
            }

        }
        return Double.parseDouble(stack.pop());
    }


    /**
     * Method parsing inputted formula to RPN expression
     *
     * @param formula inputted by user
     * @return expression parsed to RPN
     */
    private static MyArrayList<String> parseToRPN(String formula) {
        MyArrayList<String> result = new MyArrayList<>();
        MyStack<String> stack = new MyStack<>();
        StringBuilder operand = new StringBuilder();
        /* analyze every char from formula */
        for (int i = 0; i < formula.length(); i++) {
            String current = formula.substring(i, i + 1);

            /* if it is number append it to operand and add to result*/
            if (isNum(current)) {
                int j = i;
                for (; j < formula.length(); j++) {
                    String nextNumber = formula.substring(j, j + 1);
                    if (isNum(nextNumber)) {
                        operand.append(nextNumber);
                    } else {
                        /* if  next char not a number  push operand to stack */
                        result.add(operand.toString());
                        operand = new StringBuilder();
                        break;
                    }
                }
                i = j - 1;
                /* if it is a variable add it to result */
            } else if (isVariable(current, i, formula)) {
                result.add(current);
            } else if (isOperator(current)) {
                /* if operand is unary "-" add "0" to operand before it */
                if (isUnary(current, i, formula)) {
                    operand.append("0");
                    result.add(operand.toString());
                    operand = new StringBuilder();
                }
                makeOperatorAction(current, result, stack);
            } else if (isBracket(current)) {
                makeBracketAction(current, stack, result);
            } else if (isFunction(current)) {
                StringBuilder function = new StringBuilder();
                for (int j = i; j < formula.length(); j++) {
                    String nextSymbol = formula.substring(j, j + 1);
                    if (!isBracket(nextSymbol)) {
                        function.append(nextSymbol);
                    } else {
                        stack.push(function.toString());
                        i = j - 1;
                        break;
                    }
                }
            } else {
                errorMessage.add("Unsupported operation: " + current);
            }
        }
        /* push rest of stack to result if it is not empty string*/
        if (!operand.toString().equals("")) {
            result.add(operand.toString());
        }
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                errorMessage.add("Formula contains unclosed bracket");
            }
            result.add(stack.pop());
        }
        return result;
    }


    /**
     * Method making operations if meets brackets in formula
     *
     * @param current analyzing symbol
     * @param stack   stack of operations
     * @param result  output values
     */
    private static void makeBracketAction(String current, MyStack<String> stack, MyArrayList<String> result) {
        /* if formula has brackets */
        if (current.equals("(")) {
            stack.push(current);
        }
        if (current.equals(")")) {
            while (!stack.peek().equals("(")) {
                if (stack.size() == 1) {
                    errorMessage.add("Open bracket missed");
                    break;
                }
                result.add(stack.pop());
            }
            stack.pop();
            if (!stack.isEmpty() && isFunction(stack.peek())) {
                result.add(stack.pop());
            }

        }
    }


    /**
     * Method making operations if meets operator in formula
     *
     * @param current analyzing symbol
     * @param stack   stack of operations
     * @param result  output values
     */
    private static void makeOperatorAction(String current, MyArrayList<String> result, MyStack<String> stack) {
        /* if stack not empty push current to stack, or depend of priority put it to result*/
        if (!stack.isEmpty()) {
            while (!stack.isEmpty() && isOperator(stack.peek()) &&
                    isLeftAssociative(current) && getPriority(stack.peek()) >= getPriority(current) ||
                    isRightAssociative(current) && getPriority(current) < getPriority(stack.peek())) {
                result.add(stack.pop());
            }
            stack.push(current);
        }
        /* if stack empty put current in it*/
        if (stack.isEmpty() || stack.peek().equals("(")) {
            stack.push(current);
        }

    }


    /**
     * Puts to map names of functions as keys and instances of it classes as values
     *
     * @return filled HashMap
     */
    private static MyHashMap<String, IAction> makeFunctionMap() {
        MyHashMap<String, IAction> map = new MyHashMap<>();
        map.put("sin", new Sin());
        map.put("cos", new Cos());
        map.put("tan", new Tan());
        map.put("atan", new Atan());
        map.put("log10", new Log10());
        map.put("log2", new Log2());
        map.put("sqrt", new Sqrt());
        return map;
    }

    /**
     * Method calculating result of small expression, which is a parts of formula
     *
     * @param current contains type of command to calculate
     * @param first   first member of expression
     * @param second  second member of expression
     * @return result of calculating
     */
    private static String getResult(double first, double second, String current) {
        switch (current) {
            case "^": {
                return String.valueOf(Math.pow(first, second));
            }
            case "*": {
                return String.valueOf(first * second);
            }
            case "/": {
                /* if user truing to divide by "0" write a message */
                if (second == 0) {
                    errorMessage.add("Arithmetical mistake, you trying to divide by '0'");
                    return "0";
                } else {
                    return String.valueOf(first / second);
                }
            }
            case "-": {
                return String.valueOf(first - second);
            }
            case "+": {
                return String.valueOf(first + second);
            }
        }
        return "0";
    }

    /**
     * Check if symbol is bracket
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isBracket(String current) {
        return current.equals("(") || current.equals(")");
    }

    /**
     * Check is operation left associative
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isLeftAssociative(String current) {
        return current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/");
    }

    /**
     * Check is operation right associative
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isRightAssociative(String current) {
        return current.equals("^");
    }

    /**
     * Check if current symbol is unary
     *
     * @param current analyzing symbol
     * @param i       number of analyzing symbol
     * @param formula analyzing formula
     * @return boolean answer
     */
    private static boolean isUnary(String current, int i, String formula) {
        return i == 0 && current.equals("-") ||
                i != 0 && current.equals("-") && isOperator(formula.substring(i - 1, i)) ||
                i != 0 && current.equals("-") && formula.substring(i - 1, i).equals("(");
    }

    /**
     * Returning priority of operation
     *
     * @param current analyzing symbol
     * @return rank of operation
     */
    private static int getPriority(String current) {
        if (current.equals("+") || current.equals("-")) {
            return 1;
        }
        if (current.equals("*") || current.equals("/")) {
            return 2;
        }
        if (current.equals("^")) {
            return 3;
        }
        return -1;
    }

    /**
     * Check is analyzing symbol is variable
     *
     * @param current analyzing symbol
     * @param i       number of analyzing symbol
     * @param formula analyzing formula
     * @return boolean answer
     */
    private static boolean isVariable(String current, int i, String formula) {
        return isLetter(current) && i == formula.length() - 1 ||
                isLetter(current) && !isLetter(formula.substring(i + 1, i + 2));
    }

    /**
     * Check is analyzing symbol is operator
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isOperator(String current) {
        return current.length() == 1 && current.equals("+") ||
                current.equals("-") || current.equals("*") ||
                current.equals("/") || current.equals("^");
    }

    /**
     * Check is analyzing symbol is number
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isNum(String current) {
        if (current.length() == 1) {
            for (int i = 0; i < current.length(); i++) {
                char c = current.charAt(i);
                if (!(c >= '0' && c <= '9' || c == '.')) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < current.length(); i++) {
                char c = current.charAt(i);
                if (!(c >= '0' && c <= '9' || c == '.' || c == '-')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check is analyzing symbol is function
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isFunction(String current) {
        return isLetter(current);
    }

    /**
     * Check is analyzing symbol is letter
     *
     * @param current analyzing symbol
     * @return boolean answer
     */
    private static boolean isLetter(String current) {
        return current.charAt(0) >= 'a' && current.charAt(0) <= 'z';
    }

    /**
     * Method get all arguments from formula and replace it with actual values
     *
     * @param formula inputted by user
     * @return modified formula
     */
    private static MyArrayList<String> insertVariables(MyArrayList<String> formula, MyHashMap<String, Double> var) {
        if (!var.isEmpty()) {
            for (MyHashMap.Entry entry : var.entry()) {
                String key = (String)entry.getKey();
                String value = entry.getValue().toString();
                for (int i = 0; i < formula.size(); i++) {
                    if (formula.get(i).equals(key)) {
                        formula.set(i, value);
                    }
                }
            }
        }
        return formula;
    }

    /**
     * Getting value of variable from string
     *
     * @param string string inputted by user
     * @return value parsed to double
     */
    private static double getValue(String string) {
        if (string.length() - string.indexOf(SPLITTER) > 1) {
            return Double.parseDouble(string.substring(string.indexOf(SPLITTER) + 1));
        }
        /* check if user forget to input value of variable */
        errorMessage.add("You forget to init variable(s)");
        return 0;
    }

    /**
     * Getting key from string
     *
     * @param string string inputted by user
     * @return key
     */
    private static String getKey(String string) {
        int splitter = string.indexOf(SPLITTER);
        if (splitter != -1) {
            return string.substring(0, splitter);
        }
        errorMessage.add("Sign'=' not found ");
        return null;
    }

    /**
     * Replace all spaces from string
     *
     * @param string inputted string
     * @return string without spaces
     */
    private static String replaceSpaces(String string) {
        return string.replaceAll(" ", "");
    }
}
