package com.shpp.p2p.cs.irozinko.assignment16;

/**Interface contains main methods to implement
 * @param <T> parametrised type
 */
public interface IMyQueue<T> {
    void push(T elem);
    T peek();
    T poll();
    int size();
    boolean isEmpty();
}
