package com.shpp.p2p.cs.irozinko.assignment16;

import java.util.Stack;

/**Class is simple analog of queue implements principe First In Last Out
 * @param <T>
 */
public class MyStack<T> implements IStack<T> {
    private int size ;
    private Node<T> first;
    private  Node<T> last;

    MyStack(){
        this.last = new Node<T>(null, first, null);
        this.first = new Node <T> (null, null, last);
        this.last.previous = first;
    }

    /**Adds element at the top of stack
     * @param elem value
     */
    @Override
    public void push(T elem) {
        Node<T> newElem = last;
        newElem.value = elem;
        last = new Node<>(null, newElem, null);
        newElem.next = last;
        size++;
    }

    /**Removed top element of stack and return it value
     * @return value of top element
     */
    @Override
    public T pop() {
        Node<T> temp = last.previous;
        temp.previous.next = last;
        last.previous = temp.previous;
        size--;
        return temp.value;
    }

    /**
     * @return Get top element of stack
     */
    @Override
    public T peek() {
        return last.previous.value;
    }

    /**
     * @return boolean value is stack empty
     */
    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    /**
     * @return size of stack
     */
    @Override
    public int size() {
        return size;
    }
}
