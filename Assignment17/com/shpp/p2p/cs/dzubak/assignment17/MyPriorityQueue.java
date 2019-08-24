package com.shpp.p2p.cs.dzubak.assignment17;

import java.util.Comparator;

/**
 * The priority queue class based on the expandable array.
 * The elements of the array are arranged in descending order.
 * The last element has the smallest value.
 *
 * @param <T> generic type T which is inherited from Comparable and can be compared with
 *            another element of this type.
 */
public class MyPriorityQueue<T extends Comparable<T>> {
    /**
     * A variable that creates an initial array of the specified size.
     */
    private static final int INITIAL_SIZE = 16;
    /**
     * A variable that indicates the size of the MyArrayList
     */
    private int size;
    /**
     * An array that is created during initialization and then changed depending on the methods
     */
    private T[] array;
    /**
     * Comparator which can be specified when creating a priority queue.
     */
    private Comparator<T> comparator;


    /**
     * A constructor that creates a priority queue without
     * specifying comparator, the size of which is 0, and the size of the array = INITIAL_SIZE
     */
    public MyPriorityQueue() {
        comparator = null;
        size = 0;
        array = (T[]) new Comparable[INITIAL_SIZE];

    }

    /**
     * A constructor that creates a priority queue indicating comparator,
     * the size of which is 0, and the size of the array = INITIAL_SIZE
     *
     * @param comparator
     */
    public MyPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
        size = 0;
        array = (T[]) new Comparable[INITIAL_SIZE];
    }

    /**
     * A method that adds an element to an array and immediately sorts
     * the current array, leaving at the end the element with the lowest value.
     *
     * @param element to add
     */
    public void add(T element) {

        if (size == array.length) {
            T[] arrayNew = (T[]) new Comparable[size * 2 + 1];
            System.arraycopy(array, 0, arrayNew, 0, array.length);
            array = arrayNew;
        }
        array[size] = element;
        size++;
        if (size > 1) {
            sort(array);
        }
    }

    /**
     * A method that sorts the added element depending on whether a comparator
     * is specified or not. The element with the highest value is at the beginning of the array.
     *
     * @param array returns sorted array
     */
    private void sort(T[] array) {
        int i = size - 1;
        while (i > 0) {
            T first = array[i];
            T second = array[i - 1];
            if (comparator == null) {
                if (first.compareTo(second) > 0) {
                    array[i] = second;
                    array[i - 1] = first;
                }
            } else {
                if (comparator.compare(first, second) > 0) {
                    array[i] = second;
                    array[i - 1] = first;
                }
            }
            i--;
        }
    }

    /**
     * The method that returns the last value in the array deletes it and
     * reduces the size of the priority queue. This element will have the lowest value.
     *
     * @return element
     */
    public T poll() {
        T element = array[size - 1];
        T[] arrayNew = (T[]) new Comparable[size - 1];
        System.arraycopy(array, 0, arrayNew, 0, (size - 1));
        array = arrayNew;
        size--;

        return element;
    }

    /**
     * Method that returns the last value in the array. This element will have the lowest value.
     *
     * @return element
     */
    public T peek() {
        T element = array[size - 1];
        return element;
    }

    /**
     * A method that returns true if the queue is empty.
     *
     * @return true if size = 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method returns the queue size
     *
     * @return size
     */
    public int size() {
        return size;
    }


}
