package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * Class that stores items
 * list and works on the principle of first entering last out. Queue (LIFO)
 *
 * @param <M>
 */
public class MyQueue<M> {
    /**
     * The size of our list
     */
    private int size = 0;
    /**
     * Starting node
     */
    private Node<M> firstNode;
    /**
     * End node
     */
    private Node<M> endNode;

    /**
     * Constructor class which initializes 2 nodes
     * that point at each other and are empty elements.
     */
    public MyQueue() {
        firstNode = new Node(null, null, endNode);
        endNode = new Node(firstNode, null, null);
    }

    /**
     * Method that adds items to the list
     *
     * @param element to add
     */
    public void add(M element) {
        Node<M> temporaryNode = new Node(null, element, null);
        if (size == 0)
            firstNode = temporaryNode;
        else {
            endNode.setNextNode(temporaryNode);
            temporaryNode.setPreviousNode(endNode);
        }
        endNode = temporaryNode;
        size++;
    }



    /**
     * A method that returns the value of the last element and deletes the element itself.
     *
     * @return element
     */
    public M pop() {
        Node<M> nodeForOutput = firstNode;
        firstNode = firstNode.getNextNode();
        size--;
        return nodeForOutput.getCurrent();
    }

    /**
     * Method that gives the value of the last element
     *
     * @return element
     */
    public M peek() {
        return firstNode.getCurrent();
    }

    /**
     * A method that returns true if the list is empty
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method that returns the size of the list
     *
     * @return size
     */
    public int size() {
        return size;
    }


}
