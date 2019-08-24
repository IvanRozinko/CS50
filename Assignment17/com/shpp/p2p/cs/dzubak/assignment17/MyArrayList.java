package com.shpp.p2p.cs.dzubak.assignment17;

/**
 * Its class that implements the work with a variable array.
 * The size of the array does not have to be set at initialization. The class is based on an array.
 *
 * @param <M> the specific data type the class will work with
 */
public class MyArrayList<M> {
    /**
     * A variable that creates an initial array of the specified size.
     */
    private static final int INITIAL_SIZE = 16;
    /**
     * A variable that indicates the size of the MyArrayList
     */
    private int size;
    /**
     * An array that is created during initialization and then changed depending on the methods
     */
    private M[] array;

    /**
     * The constructor of the class that assigns the size of
     * the MyArrayList to 0 and creates the nom array of the specified size INITIAL_SIZE
     */
    public MyArrayList() {
        size = 0;
        array = (M[]) new Object[INITIAL_SIZE];
    }

    /**
     * Constructor class which assigns the size MyArrayList currentSize and creates a nom array of size currentSize
     *
     * @param currentSize specified size
     */
    public MyArrayList(Integer currentSize) {
        this.size = currentSize;
        array = (M[]) new Object[size];
    }

    private boolean check(int element) {
        return element >= 0 && element < size;
    }

    /**
     * A method that adds a new element to the MyArrayList
     *
     * @param element to add
     */
    public void add(M element) {

        if (size == array.length) {
            M[] arrayNew = (M[]) new Object[size * 2];
            System.arraycopy(array, 0, arrayNew, 0, array.length);
            array = arrayNew;
        }
        array[size] = element;
        size++;
    }

    /**
     * Method that adds a new item in MyArrayList to the specified position
     *
     * @param element to add
     * @param number  item number to add
     */
    public void add(M element, int number) {
        if (!check(number)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        if (number > size - 1) {
            M[] arrayNew = (M[]) new Object[(size + 1)];
            System.arraycopy(array, 0, arrayNew, 0, size);
            arrayNew[size] = element;
            array = arrayNew;
            size++;

        } else {
            M[] arrayNew = (M[]) new Object[size + 1];
            System.arraycopy(array, 0, arrayNew, 0, number);
            arrayNew[number] = element;
            System.arraycopy(array, number, arrayNew, (number + 1), size - number);
            array = arrayNew;
            size++;
        }
    }

    /**
     * The method that replaces the element with a new element according to the specified position
     *
     * @param element to be inserted
     * @param index   item number to add
     */
    public void set(M element, int index) {
        if (!check(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }

        if (index >= 0 && index < size)
            array[index] = element;
    }

    /**
     * The method that removes the item according to the specified position
     *
     * @param index position number to be deleted
     */
    public void remove(int index) {
        if (!check(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        M[] arrayNew = (M[]) new Object[size - 1];
        System.arraycopy(array, 0, arrayNew, 0, index);
        System.arraycopy(array, index + 1, arrayNew, index, size - index - 1);
        size = arrayNew.length;
        array = arrayNew;
    }

    /**
     * The method that removes the first element found
     *
     * @param element to be removed
     */
    public void remove(M element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * The method that returns an element according to the specified position
     *
     * @param index position to search
     * @return desired element
     */
    public M get(int index) {
        if (!check(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        return array[index];
    }

    /**
     * Method that indicates the index of the first item found
     *
     * @param element the element to be found
     * @return position number
     */
    public int indexOf(M element) {
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method that returns the size of the current MyArrayList
     *
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * The method that removes all the elements of our MyArrayList
     */
    public void removeAll() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

}

