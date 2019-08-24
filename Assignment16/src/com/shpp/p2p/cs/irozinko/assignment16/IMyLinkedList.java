package com.shpp.p2p.cs.irozinko.assignment16;

/**Interface contain main methods to implement MyLinkedList
 * @param <T>
 */
public interface IMyLinkedList<T> {
    void add(int i, T elem);
    void addLast(T elem);
    T remove (int index);
    T  get(int index);
    void set(int index, T elem);
    int size();

}
