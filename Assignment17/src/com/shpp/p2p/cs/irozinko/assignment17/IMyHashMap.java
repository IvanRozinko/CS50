package com.shpp.p2p.cs.irozinko.assignment17;

public interface IMyHashMap<K,V> {
    /**
     * Method adds new element with key and value. If quantity of elements exceeds the limit, size of array doubling.
     * @param key of element
     * @param value of element
     * @return boolean value: true if element putted successfully otherwise - false
     */
    boolean put(K key, V value);

    /**
     * Returning value of element by key. If key didn't found returns null
     * @param key of element
     * @return value
     */
    V getValue(K key);

    /**
     * Return true, if element with key presented in MyHashMap, otherwise - false
     * @param key of element
     * @return boolean value
     */
    boolean containsKey (K key );

    /**
     * Return true, if element with value presented in MyHashMap, otherwise - false
     * @param value of element
     * @return boolean answer
     */
    boolean containsValue (V value);

    /**
     * Removes element by key
     * @param key of element
     * @return value of removed element or null if key not found
     */
    V remove (K key);

    /**
     * Returns collection of all keys from MyHashMap
     * @return MyArrayList collection of all keys from MyHashMap
     */
    MyArrayList<K> keySet();

    /**
     * Returns collection of all values from MyHashMap
     * @return MyArrayList collection of all values from MyHashMap
     */
    MyArrayList<V> values();

    /**
     * Adds element to MyHashMap if eky of element id not present inside it
     * @param key of added element
     * @param value of added element
     * @return value of added element or null if operation was failed
     */
    V putIfAbsent(K key ,V value);

    /**
     * Returns size of MyHashMap
     * @return size
     */
    int size();

    /**
     * Removes all elements from MyHashMap
     */
    void clear();

    /**
     * Returns boolean answer is collection empty
     */
    boolean isEmpty();


}
