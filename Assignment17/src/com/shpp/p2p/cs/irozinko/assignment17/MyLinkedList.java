package com.shpp.p2p.cs.irozinko.assignment17;


import java.util.Iterator;

/**This class imitates simple analog of LinkedList. Basic structure is class Node object.
 * @param <T> parametrized class
 */
class MyLinkedList<T> implements IMyLinkedList<T>, Iterable<T> {
    private int size;
    private Node<T> last;
    private Node<T> first;

    MyLinkedList(){
        this.last = new Node<> (null, first, null);
        this.first = new Node<> (null, null, last);
        this.last.previous = first;
    }

    @Override
    public void add(int index, T elem) {
        if(index <= size) {
            Node<T> current = last;
            last = new Node<>(null, current, null);
            current.next = last;
            size++;
            current.index = size - 1;
            while (current.index != index) {
                current.value = current.previous.value;
                current = current.previous;
            }
            current.value = elem;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void addLast(T element) {
        Node<T> newElem = last;
        newElem.value = element;
        last = new Node<>(null, newElem, null);
        newElem.next = last;
        size++;
        newElem.index = size -1;
    }

    @Override
    public T remove(int index) {
        Node<T> current = getNodeByIndex(index);
        T removed = current.value;
        while (current.index < size -1 ){
            current.value = current.next.value;
            current = current.next;
        }
        last = last.previous;
        size--;
        return removed;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
    }

    @Override
    public void set(int index, T elem) {
        getNodeByIndex(index).value = elem;
    }

    @Override
    public int size() { return size;}


    /**Getting element by index
     * @param index of element to get
     * @return Node object of element by index
     */
    private Node<T> getNodeByIndex(int index) {
        if (index < size) {
            Node<T> current = first.next;
            for (int i = 0; i < index; i++) {
               current = current.next;
            }
            return current;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                return getNodeByIndex(counter++).value;
            }
        };
    }

    /**Basic structure for MyLinkedList. Contains index, value of element and references to next and previous elements
     * @param <T> parametrized type
     */
    class Node <T>{
        int index;
        T value;
        Node<T> previous;
        Node<T> next;

        Node(T value, Node<T> previous, Node<T> next){
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}




