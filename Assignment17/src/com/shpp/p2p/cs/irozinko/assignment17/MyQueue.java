package com.shpp.p2p.cs.irozinko.assignment17;

/**Class is simple analog of queue implements principe First In First Out
 * @param <T>
 */
public class MyQueue<T> implements IMyQueue<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public MyQueue(){
        this.last = new Node<> (null, first, null);
        this.first = new Node<>(null, null, last);
        this.last.previous = first;
    }

    @Override
    public void add(T elem) {
        Node<T> newElem = last;
        newElem.value = elem;
        last = new Node<>(null, newElem, null);
        newElem.next = last;
        size++;
    }

    @Override
    public T peek() {
        if(size < 1){
            throw new IndexOutOfBoundsException();
        }
        return first.next.value;
    }

    @Override
    public T poll() {
        if(size < 1){
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = first.next;
        temp.next.previous = first;
        first.next = temp.next;
        size--;
        return temp.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }
    /**Basic structure for MyQueue. Contains value of element and references to next and previous elements
     * @param <T> parametrized type
     */
    class Node <T> {
        T value;
        Node<T> previous;
        Node<T> next;

        Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
