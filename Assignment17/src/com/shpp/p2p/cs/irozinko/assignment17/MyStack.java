package com.shpp.p2p.cs.irozinko.assignment17;

import java.util.EmptyStackException;

/**Class is simple analog of queue implements principe First In Last Out
 * @param <T>
 */
public class MyStack<T> implements IMyStack<T> {
    private int size ;
    private  Node<T> last = new Node<>(null,null);


    @Override
    public void push(T elem) {
        Node<T> newElem = last;
        newElem.value = elem;
        last = new Node<>(null, newElem);
        size++;
    }

    @Override
    public T pop() {
        Node<T> temp = last.previous;
        if (temp == null) {
            throw new EmptyStackException();
        }
        last.previous = temp.previous;
        size--;
        return temp.value;
    }

    @Override
    public T peek() {
        if (size < 1){
            throw new EmptyStackException();
        }
        return last.previous.value;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int search(T element) {
        Node<T> temp = last.previous;
       int counter = 0;
       while (counter < size){
           if ( temp.value.equals(element)){
               return counter;
           }
           else {
               temp = temp.previous;
               counter++;
           }
       }
        return -1;
    }

    /**Basic structure for MyStack. Contains  value of element and reference to previous element
     * @param <T> parametrized type
     */
    class Node <T> {
        T value;
        Node<T> previous;

        Node(T value, Node<T> previous) {
            this.value = value;
            this.previous = previous;
        }
    }
}
