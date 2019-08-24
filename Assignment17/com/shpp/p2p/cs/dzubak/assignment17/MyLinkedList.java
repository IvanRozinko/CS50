package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * A class that is based on the principle of a doubly linked list of elements.
 * The class deletes and adds elements that replace the elements.
 * To bypass the elements, the interface is iterated
 */

import java.util.Iterator;

public class MyLinkedList<M> implements Iterable<M> {
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
    public MyLinkedList() {
        firstNode = new Node(null, null, endNode);
        endNode = new Node(firstNode, null, null);
    }

    /**
     * The method that adds a new item
     *
     * @param element to add
     */
    public void add(M element) {
        addLast(element);
    }

    /**
     * The method that adds an element to the top of the list
     *
     * @param element to add
     */
    public void addFirst(M element) {
        Node<M> temporaryNode = new Node(null, element, null);
        temporaryNode.setNextNode(firstNode);
        firstNode.setPreviousNode(temporaryNode);
        firstNode = temporaryNode;
        size++;
    }

    /**
     * The method that adds an element to the end of the list
     *
     * @param element to add
     */
    public void addLast(M element) {
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
     * The method that adds the specified item to the specified item.
     *
     * @param index   position to add
     * @param element to add
     */
    public void add(int index, M element) {
        Node<M> temporaryRight = getNode(index);
        Node<M> temporaryLeft = temporaryRight.getPreviousNode();
        Node<M> addNode = new Node(null, element, null);
        temporaryLeft.setNextNode(addNode);
        temporaryRight.setPreviousNode(addNode);
        addNode.setPreviousNode(temporaryLeft);
        addNode.setNextNode(temporaryRight);
        size++;
    }

    /**
     * The method that returns the desired node according to the specified position
     *
     * @param index to find the node
     * @return
     */
    private Node<M> getNode(int index) {
        Node<M> nodeForOutput = firstNode;
        int i = index;
        while (i != 0) {
            Node<M> temporaryNode = nodeForOutput.getNextNode();
            nodeForOutput = temporaryNode;
            i--;
        }
        return nodeForOutput;
    }

    /**
     * Method that returns an element according to the specified position
     *
     * @param index to find the element
     * @return
     */
    public M get(int index) {
        Node<M> nodeForOutput = firstNode;
        int i = index;
        while (i != 0) {
            Node<M> temporaryNode = nodeForOutput.getNextNode();
            nodeForOutput = temporaryNode;
            i--;
        }
        return nodeForOutput.getCurrent();
    }

    /**
     * The method that removes the first item in the list
     */
    public void removeFirst() {
        firstNode = firstNode.getNextNode();
        size--;
    }

    /**
     * Method that removes the last item in the list
     */
    public void removeLast() {
        endNode = endNode.getPreviousNode();
        size--;
    }

    /**
     * The method that inserts the element according to the specified position
     *
     * @param index   in which to insert the element
     * @param element to be inserted
     */
    public void set(int index, M element) {
        getNode(index).setCurrent(element);
    }

    /**
     * The method that returns the first element
     *
     * @return element
     */
    public M getFirst() {
        return firstNode.getCurrent();
    }

    /**
     * Method that returns the last element
     *
     * @return element
     */
    public M getLast() {
        return endNode.getCurrent();
    }

    /**
     * Method that returns the size of the list
     *
     * @return size
     */
    public int size() {
        return size;
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
     * The method that implements finding the next
     * element and returns true if the next element is
     *
     * @return element
     */
    @Override
    public Iterator<M> iterator() {
        return new Iterator<M>() {
            Node<M> nodeForOutput = firstNode;
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public M next() {
                M element;
                if (position > 0) {
                    nodeForOutput = nodeForOutput.getNextNode();
                }
                element = nodeForOutput.getCurrent();
                position++;
                return element;
            }
        };
    }
}

