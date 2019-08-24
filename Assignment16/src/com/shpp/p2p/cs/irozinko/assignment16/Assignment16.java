package com.shpp.p2p.cs.irozinko.assignment16;



/**
 * This class testing all MY collections. Better to test class by class, for better output info.
 */
public class Assignment16 {
    private  final static int MAX_SIZE = 1000;

    public static void main(String[] args) {


        testMyArrayList();
//        testMyLinkedList();
//        testMyQueue();
//        testMyStack();

    }

    /**
     * Testing MyStack
     */
    private static void testMyStack() {

        MyStack<Integer> stack = new MyStack<>();
        System.out.println("Initial size: [" + stack.size() + "]");

        for (int i = 0; i < MAX_SIZE ; i ++){
            stack.push(i);
        }
        System.out.println("Size after pushing elements: [" + stack.size() + "]");
        while (!stack.isEmpty()) {
            stack.pop();
        }
        System.out.println("Size after polling elements: [" + stack.size() + "]");
        stack.push(MAX_SIZE);
        System.out.print("Push element: [" + stack.peek() + "].");
        System.out.println("  Last pushed element: [" + stack.pop() + "].");

    }

    /**
     * Testing MyQueue
     */
    private static void testMyQueue() {

        MyQueue<Integer> queue = new MyQueue<>();
        System.out.println("Initial size: [" + queue.size() + "]");

        for (int i = 0; i < MAX_SIZE ; i ++){
            queue.push(i);
        }
        System.out.println("Size after pushing elements: [" + queue.size() + "]");
        while (!queue.isEmpty()) {
            queue.poll();
        }
        System.out.println("Size after polling elements: [" + queue.size() + "]");
        queue.push(MAX_SIZE);
        queue.push(MAX_SIZE/2);
        System.out.print("Push element: [" + queue.peek() + "].");
        queue.poll();
        System.out.println("  Last pushed element: [" + queue.poll() + "].");

    }


    /**
     * Testing MyLinkedList
     */
    private static void testMyLinkedList() {

        MyLinkedList<Integer> list = new MyLinkedList<>();
        System.out.println("Initial size: [" + list.size() + "]");

        for (int i = 0; i < MAX_SIZE ; i ++){
            list.addLast(i);
        }
        System.out.println("Size after adding elements: [" + list.size() + "]");
        for(int i = 0; i < MAX_SIZE/2; i++){
            list.remove(0);
        }
        System.out.println("Size after removing elements: [" + list.size() + "]");
        for(int i = 0; i < 2; i ++){
            System.out.print("Got value of " + i + " element: [" + list.get(i) + "].");
            list.set(i, (i + 10) * 10 );
            System.out.println("  New value of " + i + " element: [" + list.get(i) + "].");
        }
    }


    /**
     * Testing MyArrayList
     */
    private static void testMyArrayList() {
        MyArrayList<String> array = new MyArrayList<>();
        System.out.println("Initial size: [" + array.size() + "]");
        for (int i = 0; i < MAX_SIZE; i ++){
            array.add("value " + i);
        }
        System.out.println("Size after adding elements: [" + array.size() + "]");
        for(int i = 0; i < MAX_SIZE/2; i++){
            array.remove(0);
        }
        System.out.println("Size after removing elements: [" + array.size() + "]");
        for(int i = 0; i < 2; i ++){
            System.out.print("Got value of " + i + " element: [" + array.get(i) + "].");
            array.set(i, "NEW" + i);
            System.out.println("  New value of " + i + " element: [" + array.get(i) + "].");
        }
    }
}
