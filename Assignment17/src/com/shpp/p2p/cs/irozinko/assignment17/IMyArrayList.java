package com.shpp.p2p.cs.irozinko.assignment17;


/**Interface include main methods for implementation
 * @param <T> parametrized type
 */
public interface IMyArrayList <T> {

    /**
     * Creates new array which size bigger on 1 element, adds element t to last position of array
     * @param elem new element to be added
     */
    void add (T elem);

    /**
     * Removes element with index number from array
     * @param index of element to be removed
     * @return removed element
     */
    T remove (int index);

    /**
     * Get element by index
     * @param index of element to get
     * @return element
     */
    T  get(int index);

    /**
     * Sets values T of element with index
     * @param index of element in array
     * @param elem new values of element
     */
    void set(int index, T elem);

    /**Return size of array
     * @return array length
     */
    int size();
}
