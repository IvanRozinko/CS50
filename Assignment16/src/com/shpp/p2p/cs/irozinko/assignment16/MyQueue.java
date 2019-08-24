package com.shpp.p2p.cs.irozinko.assignment16;

/**Class is simple analog of queue implements principe First In First Out
 * @param <T>
 */
public class MyQueue<T> implements IMyQueue<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    MyQueue (){
        this.last = new Node<> (null, first, null);
        this.first = new Node<>(null, null, last);
        this.last.previous = first;
    }

    /**Adds element at the end of queue
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


    /**
     * @return Get first element of queue
     */
    @Override
    public T peek() {
        return first.next.value;
    }


    /**Removed first element of queue and return it value
     * @return value of first element
     */
    @Override
    public T poll() {
        Node<T> temp = first.next;
        temp.next.previous = first;
        first.next = temp.next;
        size--;
        return temp.value;
    }


    /**
     * @return size of queue
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * @return boolean value is queue empty
     */
    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }
}
