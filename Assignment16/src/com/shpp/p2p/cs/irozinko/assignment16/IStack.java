package com.shpp.p2p.cs.irozinko.assignment16;

/**Interface contains main methods to implement
 * @param <T> parametrised type
 */
public interface IStack<T> {
    void push(T elem);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}
