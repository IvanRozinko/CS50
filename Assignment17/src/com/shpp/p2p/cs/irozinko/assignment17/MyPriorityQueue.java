package com.shpp.p2p.cs.irozinko.assignment17;



/**
 * This class is simple imitation of standard java PriorityQueue. After adding elements to this class sorting
 * using compareTo method. The smallest element of collection is always on the top of queue. Class can compare only
 * objects of classes extended Comparable interface.
 * @param <T> parametrized type
 */
@SuppressWarnings("unchecked")
public class MyPriorityQueue<T extends Comparable<T> > implements IMyPriorityQueue<T> {
    // contains all elements of collection in array
    private Node<T> [] values;
    private int size;
    private int capacity = 16;
    private double limit;

    public MyPriorityQueue (){
        this.values = new Node [capacity];
        this.size = 0;
        this.limit = capacity * 0.75;
    }

    @Override
    public boolean add(T element) {
        if (size + 1 >= limit){
            arrayDoubling();
        }
        if (element == null){
            return false;
        }
        Node newElement = new Node(element);
        values[size] = newElement;
        size++;
        swapWithSmallerParents();
        return true;
    }

    /**
     * Method swapping new element with it parents until exists parent elements bigger than it.
     */
    private void swapWithSmallerParents() {
        int i = size - 1;
        int parent = (i - 1) / 2;
        while (i > 0){
            if (values[parent].compareTo(values[i]) > 0) {
                Node temp = values[i];
                values[i] = values[parent];
                values[parent] = temp;
            }
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    /**
     * Doubling array if size of collection exceed the limit
     */
    private void arrayDoubling() {
        Node[] temp = values;
        limit *= 2;
        values = new Node[values.length * 2];
        System.arraycopy(temp,0,values,0,size);
    }

    @Override
    public boolean remove(T element) {
        if ( size == 0){
            return false;
        }
        for(int i = 0 ; i < size; i++){
            if (values[i].value.compareTo(element) == 0){
                values[i] = values[size-1];
                values[size-1] = null;
                size--;
                makeHeap(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T peek() {
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        return values[size-1].value;
    }

    @Override
    public T poll() {
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        T result = values[0].value;
        values[0] = values[size-1];
        values[size-1] = null;
        size--;
        makeHeap(0);
        return result;
    }

    /**
     * Building binary heap, after some elements were removed or polled. It compare parent element with 2 children
     * and swap it if one of children smaller than parent, to keep smaller element at the top of binary heap.
     * @param i index of element in array
     */
    private void makeHeap(int i) {
        int firstChild;
        int secondChild;
        int smallestChild;

        while (true){
            firstChild = 2 * i + 1;
            secondChild = 2 * i + 2;
            smallestChild = i;

            if (firstChild < size && (values[firstChild].compareTo(values[smallestChild])) < 0) {
                smallestChild = firstChild;
            }

            if (secondChild < size && (values[secondChild].compareTo(values[smallestChild])) < 0) {
                smallestChild = secondChild;
            }

            if (smallestChild == i) {
                break;
            }
            Node temp = values[i];
            values[i] = values[smallestChild];
            values[smallestChild] = temp;
            i = smallestChild;
        }

    }

    @Override
    public boolean contains(T element) {
        for(Node node : values){
            if (node.value.equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        values = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }


    /**
     * This class is basic structure to build MyPriorityQueue. It stores value of element.
     * @param <T> - should extend Comparable, otherwise to compare 2 values, compareTo() method need to be redefined.
     */
    private class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
        private T value;
        Node(T value)
        {
            this.value = value;
        }
        @Override
        public int compareTo(Node<T> anotherNode) {
            return value.compareTo(anotherNode.value);
        }

    }
}
