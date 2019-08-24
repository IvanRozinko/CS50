package com.shpp.p2p.cs.irozinko.assignment17;

/**Interface contains main methods to implement
 * @param <T> parametrised type
 */
public interface IMyQueue<T> {

    /**
     * Adds element at the end of queue
     * @param elem value
     */
    void add(T elem);

    /**
     * @return Get first element of queue
     */
    T peek();


    /**
     * Removed first element of queue and return it value
     * @return value of first element
     */
    T poll();


    /**
     * @return size of queue
     */
    int size();


    /**
     * @return boolean value is queue empty
     */
    boolean isEmpty();
}
