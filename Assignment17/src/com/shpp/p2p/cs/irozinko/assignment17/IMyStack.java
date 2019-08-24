package com.shpp.p2p.cs.irozinko.assignment17;

/**Interface contains main methods to implement
 * @param <T> parametrised type
 */
public interface IMyStack<T> {
    /**
     * Adds element at the top of stack
     * @param elem value
     */
    void push(T elem);

    /**
     * Removed top element of stack and return it value
     * @return value of top element
     */
    T pop();

    /**
     * @return Get top element of stack
     */
    T peek();

    /**
     * @return boolean value is stack empty
     */
    boolean isEmpty();

    /**
     * @return size of stack
     */
    int size();

    /**
     * Returns how many pop() operations to get element  from stack
     * @param element to search
     * @return int how many pop() operations to get element
     */
    int search(T element);
}
