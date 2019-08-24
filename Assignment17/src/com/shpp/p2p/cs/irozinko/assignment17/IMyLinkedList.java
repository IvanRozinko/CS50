package com.shpp.p2p.cs.irozinko.assignment17;

/**Interface contain main methods to implement MyLinkedList
 * @param <T>
 */
public interface IMyLinkedList<T> {

    /**
     * Adds element by index
     * @param index of new element
     * @param elem value of new element
     */
    void add(int index, T elem);

    /**
     * Add element at the end of list
     * @param elem of new element
     */
    void addLast(T elem);

    /**
     * Removes element by index
     * @param index of removing element
     * @return value of removed element
     */
    T remove (int index);

    /**
     * Getting element by index
     * @param index of element to get
     * @return value of getting element
     */
    T  get(int index);

    /**
     * Setting element by index
     * @param index of element to reset value
     * @param elem new value
     */
    void set(int index, T elem);

    /**
     * Return size of linkedList
     */
    int size();

}
