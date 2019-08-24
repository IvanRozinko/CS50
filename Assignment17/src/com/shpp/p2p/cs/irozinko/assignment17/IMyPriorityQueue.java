package com.shpp.p2p.cs.irozinko.assignment17;

/**
 * Interface contains all main methods to be implement in MyPriorityQueue class.
 * @param <T> parametrized type
 */
public interface IMyPriorityQueue<T> {

    /**
     * Adds new element to collection. After adding new element swapping with it parents until it smaller
     * than it parent
     * @param element to be added
     * @return boolean value if adding was successful
     */
    boolean add(T element);


    /**
     * Removes element from collection, by it value. After removing heap rebuilding
     * @param element to be removed
     * @return boolean value if removing was successful
     */
    boolean remove (T element);


    /**
     * Peeking element from the top of collection ( always min)
     * @return peeked element
     */
    T peek();


    /**
     * Polling element from the top of collection (always min), means element after will be removed
     * @return polled element
     */
    T poll();


    /**
     * Showing if collection contains element
     * @param element to search
     * @return boolean value is it true or false
     */
    boolean contains (T element);


    /**
     * Removing all elements from collection
     */
    void clear();


    /**
     * Shows size of collection
     * @return size of collection
     */
    int size();


    /**
     * @return boolean answer is empty?
     */
    boolean isEmpty();

}
