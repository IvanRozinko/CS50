package com.shpp.p2p.cs.dzubak.assignment15;



import java.io.Serializable;

/**
 * Class describing the construction of the tree according to khafman
 */
class Node implements Serializable, Comparable <Node> {
    byte uniqueByte;
    int numberOfEntries;
    Node left;
    Node right;

    /**
     * The class describing the construction of the tree according to the khafman,
     * at the entrance we take a unique byte the number of occurrences of the byte and its descendants
     *
     * @param b     unique byte
     * @param value number of byte entries
     * @param left  child
     * @param right child
     */
    Node(byte b, int value, Node left, Node right) {
        this.uniqueByte = b;
        this.numberOfEntries = value;
        this.left = left;
        this.right = right;
    }

    /**
     * The method checks this node or sheet
     *
     * @return true if it is not a leaf
     */
    boolean checkTreeLeaf() {
        return (this.left != null && this.right != null);
    }

    /**
     * Method returns unique byte
     *
     * @return unique byte
     */
    int getNumberOfEntries() {
        return numberOfEntries;
    }

    /**
     * The method returns the number of occurrences of the unique byte.
     *
     * @return number of occurrences of the unique byte
     */
    byte outputBytes() {
        return uniqueByte;
    }

    /**
     * Overrides the method for the priority queue.
     * The smallest element with the number of entries will be in the head of the queue
     *
     * @param o new node
     * @return node with the least occurrence
     */
    @Override
    public int compareTo( Node o) {
        int compare = Integer.compare(numberOfEntries, o.numberOfEntries);
        return compare;
    }
}

