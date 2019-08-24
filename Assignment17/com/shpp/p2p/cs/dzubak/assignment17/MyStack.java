package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * The class that stores the elements of the
 * list and works according to the principle first went first out. Stack (FIFO)
 *
 * @param <M>
 */

public class MyStack<M> {
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
    public MyStack() {
        firstNode = new Node(null, null, endNode);
        endNode = new Node(firstNode, null, null);
    }

    /**
     * Method that adds items to the list
     *
     * @param element to add
     */
    public void push(M element) {
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
        Node<M> nodeForOutput = endNode;
        endNode = endNode.getPreviousNode();
        size--;
        return nodeForOutput.getCurrent();
    }

    /**
     * Method that gives the value of the last element
     *
     * @return element
     */
    public M peek() {
        return endNode.getCurrent();
    }

    /**
     * The method that performs the search for the element
     * and returns the number of steps to reach the specified element.
     *
     * @param element who need to find
     * @return number of steps to reach the element
     */
    public int search(M element) {
        Node<M> nodeForOutput = endNode;
        int i = 0;
        while (i < size) {
            if (nodeForOutput.getCurrent().equals(element)) {
                return i;
            } else {
                Node<M> temporaryNode = nodeForOutput.getPreviousNode();
                nodeForOutput = temporaryNode;
            }
            i++;
        }
        return -1;
    }

    /**
     * A method that returns true when the list is empty
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method that returns the size of the list
     *
     * @return
     */
    public int size() {
        return size;
    }


}
