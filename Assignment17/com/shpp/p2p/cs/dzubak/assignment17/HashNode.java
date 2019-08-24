package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * An additional class whose type is <<T, M>, which stores an element with
 * hash parameters, a key value, a value, and a link to the next element.
 * A class can extract the values ​​of its fields and also specify new values.
 *
 * @param <T> generic type T
 * @param <M> generic type M
 */
public class HashNode<T, M> {
    /**
     * The value of the type int, which stores the hash
     */
    private int hash;
    /**
     * A value whose type is T, which stores the key
     */
    private T key;
    /**
     * A value whose type is M, which stores the value
     */
    private M value;
    /**
     * Pointer to the next node
     */
    private HashNode<T, M> next;

    /**
     * An additional class whose type is <<T, M>, which stores an element with
     * hash parameters, a key value, a value, and a link to the next element.
     *
     * @param hash  value
     * @param key   value
     * @param value
     * @param next  element
     */
    HashNode(int hash, T key, M value, HashNode<T, M> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * A method that returns a hash code value.
     *
     * @return hash code
     */
    public int getHash() {
        return hash;
    }

    /**
     * Method that changes the value of the hash code
     *
     * @param hash hash code
     */
    public void setHash(int hash) {
        this.hash = hash;
    }

    /**
     * Method that returns key value
     *
     * @return key value
     */
    public T getKey() {
        return key;
    }

    /**
     * Method that changes key value
     *
     * @param key key value
     */
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * Method that returns the current value
     *
     * @return current value
     */
    public M getValue() {
        return value;
    }

    /**
     * A method that changes the current value
     *
     * @param value
     */
    public void setValue(M value) {
        this.value = value;
    }

    /**
     * Method that returns the next node
     *
     * @return next node
     */
    public HashNode<T, M> getNext() {
        return next;
    }

    /**
     * Method that changes the value of the following node
     *
     * @param hashNode
     */
    public void setNext(HashNode<T, M> hashNode) {
        next = hashNode;
    }


}
