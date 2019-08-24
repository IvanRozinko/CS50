package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * An additional class that stores an element
 * and a pointer to the previous element and the next one.
 *
 * @param <M> the specific data type the class will work with
 */
public class Node<M> {
    private Node<M> previous;
    private M current;
    private Node<M> next;

    /**
     * The class that stores the element and the pointer to the previous element and the next
     *
     * @param previous element
     * @param current  present value
     * @param next     element
     */
    public Node(Node<M> previous, M current, Node<M> next) {
        this.previous = previous;
        this.current = current;
        this.next = next;
    }

    /**
     * Method that returns the previous node
     *
     * @return Node
     */
    public Node getPreviousNode() {
        return previous;
    }

    /**
     * The method that replaces the previous node
     *
     * @param element Node
     */
    public void setPreviousNode(Node<M> element) {
        this.previous = element;
    }

    /**
     * Method that gives the value of the element
     *
     * @return element
     */
    public M getCurrent() {
        return current;
    }

    /**
     * A method that changes the value of an element
     *
     * @param element
     */
    public void setCurrent(M element) {
        this.current = element;
    }

    /**
     * Method that returns the next node
     *
     * @return Node
     */
    public Node getNextNode() {
        return next;
    }

    /**
     * The method that replaces the next node
     *
     * @param element Node
     */
    public void setNextNode(Node<M> element) {
        this.next = element;
    }


}
