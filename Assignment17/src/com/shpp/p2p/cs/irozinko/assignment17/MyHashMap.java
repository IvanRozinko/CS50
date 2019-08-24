package com.shpp.p2p.cs.irozinko.assignment17;

import java.util.Iterator;

/**
 * Class imitating simple analog of java collection HashMap. It has main methods of HashMap.
 * Do not support adding of null key or value
 * @param <K> parametrized type of Key
 * @param <V> parametrized type of value
 */
@SuppressWarnings("unchecked")
public class MyHashMap<K,V> implements IMyHashMap<K,V>{
    //array of nodes
    private Node<K,V>[] table;
    private int size;
    //when value of size reach limit, size is doubling
    private double limit;
    final private int BASIC_CAPACITY = 16;

    public MyHashMap() {
        this.table = new Node[BASIC_CAPACITY];
        this.limit = table.length * 0.75;
        this.size = 0;
    }

    @Override
    public boolean put(K key, V value) {
        //doubling array
        if (size + 1 >=  limit){
            limit *= 2;
            raiseArraySize();
        }
        if (key == null){
            return false;
        }
        Node <K,V> newElem = new Node<>(key, value);
        int index = getIndex(newElem.key);
        if (table[index] == null){
            addElement(newElem, index);
            return true;
        }

        MyLinkedList<Node<K,V>> list = table[index].list;
        for (Node<K,V> current : list){

            if(newElem.key.equals(current.key) && !(newElem.value.equals(current.value))) {
                current.value = newElem.value;
                return true;
            }

            else if (!(newElem.key.equals(current.key)) && !(newElem.value.equals(current.value))) {
                list.addLast(newElem);
                size++;
                return true;
            }
        }

        return false;
    }


    @Override
    public V getValue(K key) {
       int index = getIndex(key);
       if (index < table.length && table[index] != null) {
           for (Node<K, V> node : table[index].list) {
               if (key.equals(node.key)) {
                   return node.value;
               }
           }
       }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        if (index < table.length && table[index] != null) {
            for (Node<K, V> node : table[index].list) {
                if (key.equals(node.key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(Node<K,V> element : table){
            if(element != null) {
                for (Node<K, V> node : element.list) {
                    if (node.value.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        if (key == null){
            return null;
        }
        int index = getIndex(key);
        if (index < table.length && table[index] != null) {
            int counter = 0;
            for(Node<K,V> node : table[index].list){
                if (key.equals(node.key)){
                    V value = node.value;
                    table[index].list.remove(counter);
                    size--;
                    return value;
                }
                counter++;
            }
        }
        return null;
    }

    @Override
    public MyArrayList<K> keySet() {
        MyArrayList<K> keyset = new MyArrayList<>();
        for(Node<K,V> current : table){
            if (current != null){
                for(Node<K,V> node : current.list ){
                    keyset.add(node.key);
                }
            }
        }
        return keyset;
    }

    @Override
    public MyArrayList<V> values() {
        MyArrayList<V> values = new MyArrayList<>();
        for(Node<K,V> current : table){
            if (current != null){
                for(Node<K,V> node : current.list ){
                    values.add(node.value);
                }
            }
        }
        return values;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (!containsKey(key)){

            put(key, value);
            return value;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        table = new Node[BASIC_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    /**
     * Doubling size of array to store elements, if size > limit value
     */
    private void raiseArraySize() {
        Node<K,V>[] buffer = table;
        table =  new Node[buffer.length * 2];
        size = 0;
        for(Node<K,V> current : buffer){
            if (current != null) {
                for(Node<K,V> node : current.list){
                    put(node.key, node.value);
                }
            }
        }
    }


    /**
     * Adds new element to node list
     * @param newElem new element Node object
     * @param index of array where to add new element
     */
    private void addElement(Node<K, V> newElem, int index) {
        table[index] = new Node<>(null, null);
        table[index].list.addLast(newElem);
        size++;
    }

    /**
     * Computing index of array to add new element based on key hashcode
     * @param key of new element
     * @return index
     */
    private int getIndex(K key) {
        return hashcode(key) % table.length < 0 ? Math.abs( hashcode(key) % table.length): hashcode(key) % table.length;
    }


    /**
     * Calculating hashcode based on key hashcode
     * @param key of element
     * @return hashcode
     */
    private int hashcode(K key) {
        int hash = 31;
        return hash * 17 + key.hashCode();
    }


    /**
     * Method returns entry object with key and value. Used to implement iterator inside this class
     * @return entry object
     */
    public Entry<K,V> entry(){
        return new Entry();
    }

    /**
     * Class created to implement iterator. Stores inside methods to iterate between Entry elements.
     */
    public  class Entry<Kn, Vn> implements Iterable<Entry<Kn, Vn>>{

        private Kn key;
        private Vn value;
        private MyArrayList<Entry<Kn, Vn>> entries;

        Entry() {
            this.entries = new MyArrayList<>();
            for (Node<K,V> current : table) {
                if (current != null) {
                    for (Node<K,V> node : current.list) {
                        if (node != null) {
                            Entry entry = new Entry(node.key, node.value);
                            entries.add(entry);
                        }
                    }
                }
            }
        }
        Entry (Kn key, Vn value){
            this.key = key;
            this.value = value;
        }
        public Kn getKey(){
            return key;
        }
        public Vn getValue(){
            return value;
        }

        @Override
        public Iterator<Entry<Kn, Vn>> iterator() {
            return new Iterator<Entry<Kn, Vn>>() {
                int counter = 0;
                @Override
                public boolean hasNext() {
                    return counter < entries.size();
                }

                @Override
                public Entry next() {
                    return entries.get(counter++);
                }
            };
        }

    }


    /**
     * Basic structure of MyHashMap. It store inside MyLinkedList with nodes and actual values of elements
     * @param <K>
     * @param <V>
     */
   private class Node<K,V>{
        private K key;
        private V value;
        private MyLinkedList<Node<K,V>> list;

        Node (K key, V value){
            this.key = key;
            this.value = value;
            this.list = new MyLinkedList<>();
        }

    }

}
