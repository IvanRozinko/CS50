package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * An additional class of a unidirectional queue that has
 * nodes HashNode<T, M>  in itself and is able to extract and write elements into a queue.
 *
 * @param <T> generic type T
 * @param <M> generic type M
 */
public class MyQueu<T, M> {
    /**
     * Queue size
     */
    private int size = 0;
    /**
     * Pointer to the first node
     */
    private HashNode<T, M> firstNode;
    /**
     * Pointer to the last node
     */
    private HashNode<T, M> endNode;

    /**
     * An additional class of a unidirectional queue that has
     * nodes HashNode<T, M>. During initialization, store an empty list and a pointer to the next node.
     */

    MyQueu() {
        firstNode = new HashNode(0, null, null, endNode);
        endNode = new HashNode(0, null, null, null);
    }

    /**
     * A method that adds an element to the end of the queue.
     *
     * @param key   value
     * @param value
     */
    public void add(T key, M value) {
        HashNode<T, M> temporaryNode = new HashNode(key.hashCode(), key, value, null);
        if (size == 0) {
            firstNode = temporaryNode;
            firstNode.setNext(endNode);
        }
        endNode.setNext(temporaryNode);
        endNode = temporaryNode;
        size++;
    }

    /**
     * The method that checks for the presence of an element in the current queue and if such an element
     * finds the method changes its value and returns true if there is no such element.
     *
     * @param hash  value
     * @param key   value
     * @param value
     * @return returns true if the method is executed or false if not executed.
     */
    public boolean checkTheAvailabilityOfTheItem(int hash, T key, M value) {
        HashNode<T, M> temporaryNode = firstNode;

        int i = size;
        while (i > 0) {
            if (temporaryNode.getHash() == hash && temporaryNode.getKey().equals(key)) {
                temporaryNode.setValue(value);
                return true;
            }
            temporaryNode = temporaryNode.getNext();
            i--;
        }
        return false;
    }

    /**
     * The method that is looking for this item in the queue.
     *
     * @param key
     * @return returns true if the item is found or false if not
     */
    public HashNode<T, M> findAnElement(T key) {
        HashNode<T, M> nodeForOutput = firstNode;
        for (int i = 0; i < size; i++) {
            if (nodeForOutput.getKey().equals(key)) {
                return nodeForOutput;
            }
            nodeForOutput = nodeForOutput.getNext();

        }
        return null;
    }

    /**
     * Removes an item from the queue head and deletes it.
     *
     * @return first item in line
     */
    public HashNode<T, M> pop() {
        HashNode<T, M> nodeForOutput = firstNode;
        firstNode = firstNode.getNext();

        size--;
        return nodeForOutput;
    }

    /**
     * Returns an item from the beginning of the queue but does not remove it.
     *
     * @return first item in line
     */
    public HashNode<T, M> peek() {
        return firstNode;
    }

    /**
     * Method returns true if queue size = 0
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method returns queue size
     *
     * @return
     */
    public int size() {
        return size;
    }

}
