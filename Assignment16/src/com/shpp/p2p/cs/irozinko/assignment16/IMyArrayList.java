package com.shpp.p2p.cs.irozinko.assignment16;


/**Interface include main methods for implementation
 * @param <T> parametrized type
 */
public interface IMyArrayList <T> {
    void add (T elem);
    T remove (int index);
    T  get(int index);
    void set(int index, T elem);
    int size();
}
