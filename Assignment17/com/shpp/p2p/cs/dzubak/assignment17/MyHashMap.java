package com.shpp.p2p.cs.dzubak.assignment17;


import java.util.Iterator;

/**
 * The class that stores items by the value of their keys.
 * Each key corresponds to its value. The class is based on calculating the hash
 * of each key value and writing the key and its value to the queue in the corresponding
 * cell of the expandable array.
 *
 * @param <T> generic type T
 * @param <M> generic type M
 */
public class MyHashMap<T, M> implements Iterable<T> {
    /**
     * The initial size of the empty array.
     */
    private static final int INITIAL_SIZE = 16;
    /**
     * Size of our MyHashMap
     */
    private int size;
    /**
     * Array of elements before expansion
     */
    private MyQueu<T, M>[] array;
    /**
     * Array of elements after expansion
     */
    private MyQueu<T, M>[] arrayNew;

    /**
     * The class that stores items by the value of their keys.
     * Each key corresponds to its value. The class is based on calculating the hash
     * of each key value and writing the key and its value to the queue in the corresponding
     * cell of the expandable array. Initial size = 0, the size of the empty array is INITIAL_SIZE
     */
    public MyHashMap() {
        size = 0;
        array = new MyQueu[INITIAL_SIZE];

    }

    /**
     * A method that adds an element and its value to an array
     *
     * @param key   value
     * @param value
     */
    public void put(T key, M value) {

        if (size == array.length) {
            newDistributionOfElements();
        }

        addElement(key, value, array);
        size++;

    }

    /**
     * The method that calculates the hash of the key and puts the
     * element and its value in the corresponding cell of the array that is specified.
     *
     * @param key    value
     * @param value
     * @param arrays in which the elements go
     */
    private void addElement(T key, M value, MyQueu<T, M>[] arrays) {
        int hash = key.hashCode();
        int number = findCellNumber(hash, arrays.length);
        HashNode<T, M> hashNode = new HashNode<>(hash, key, value, null);

        if (arrays[number] == null) {
            MyQueu<T, M> queu = new MyQueu<>();
            queu.add(hashNode.getKey(), hashNode.getValue());
            arrays[number] = queu;
        } else if (arrays[number] != null) {
            MyQueu<T, M> queu = arrays[number];
            if (!queu.checkTheAvailabilityOfTheItem(hashNode.getHash(), hashNode.getKey(), hashNode.getValue())) {
                queu.add(hashNode.getKey(), hashNode.getValue());
            } else if (queu.checkTheAvailabilityOfTheItem(hashNode.getHash(), hashNode.getKey(), hashNode.getValue())) {
                size--;
            }
        }

    }

    /**
     * The method that creates a new array is 2 times the old one and
     * writes all the elements of the old array into it.
     */
    private void newDistributionOfElements() {
        arrayNew = new MyQueu[size * 2];
        for (int i = 0; i < array.length; i++) {

            if (array[i] != null) {
                MyQueu<T, M> queu = array[i];
                while (!queu.isEmpty()) {
                    HashNode<T, M> node = queu.pop();
                    addElement(node.getKey(), node.getValue(), arrayNew);
                }
            }
        }
        array = arrayNew;

    }

    /**
     * The method which, based on the key entered, calculates
     * its hash and finds its value in the corresponding cell and displays it.
     *
     * @param key value
     * @return the value corresponding to the key
     */
    public M get(T key) {
        if (key == null) {
            return null;
        }
        int hash = key.hashCode();
        int number = findCellNumber(hash, array.length);

        if (array[number] != null) {
            MyQueu<T, M> queu = array[number];
            if (queu.findAnElement(key) != null) {
                HashNode<T, M> node = queu.findAnElement(key);
                return node.getValue();
            }
        }

        return null;
    }

    /**
     * A method that checks the presence of a key in the map.
     * And if the key is found, the output is true.
     *
     * @param key value
     * @return true if the key is found
     */
    public boolean containsKey(T key) {
        if (key == null) {
            return false;
        }
        int hash = key.hashCode();
        int number = findCellNumber(hash, array.length);

        if (array[number] != null) {
            MyQueu<T, M> queu = array[number];
            if (queu.findAnElement(key) != null) {
                return true;
            }
        }

        return false;
    }

    /**The method that calculates the number of the pump in which you want to put an element
     * @param hash value
     * @param length current array length
     * @return
     */
    private int findCellNumber(int hash, int length) {
        return Math.abs(hash) % length;
    }

    /** Method that returns the size of the map
     * @return size of the map
     */
    public int size() {
        return size;
    }

    /** Iterator with which you can get the key values ​​of all elements
     * @return key values
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            //Position associated with the size of the map
            private int position = 0;
            //Position associated with the length of the current array
            private int positionInTheArray = 0;
            // Extracted queue from masiv
            private MyQueu<T, M> queu;
            //Extracted element
            private HashNode<T, M> node;
            //Position in the queue
            private int queuePosition = 0;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public T next() {

                if (position < size) {
                    if (array[positionInTheArray] != null) {
                        queu = array[positionInTheArray];
                        if (queuePosition == 0) {
                            node = queu.peek();
                            position++;
                            queuePosition++;
                            return node.getKey();
                        } else if (queuePosition > 0 && queuePosition < queu.size()) {
                            node = node.getNext();
                            position++;
                            queuePosition++;
                            return node.getKey();
                        }
                        positionInTheArray++;
                        queuePosition = 0;
                    } else {
                        positionInTheArray++;
                    }
                }

                return null;

            }
        };
    }
}
