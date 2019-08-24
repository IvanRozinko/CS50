
package com.shpp.p2p.cs.irozinko.assignment17;

import java.util.Iterator;

/**Class imitates simple analog of ArrayList based on array, implements MyArrayInterface.
 * @param <T> parametrized type
 */
@SuppressWarnings("unchecked")
public class MyArrayList<T> implements IMyArrayList<T>, Iterable<T>{
    //variable contains simple array
    private T[] values;

    public MyArrayList(){
        values = (T[]) new Object[0];
    }

    @Override
    public void add(T element) {
        T [] buffer = (T[])new Object[values.length + 1];
        for (int i = 0; i < buffer.length; i++){
            if(i == buffer.length - 1){
                buffer[i] = element;
            }else {
                buffer[i] = values[i];
            }
        }
        values = buffer;
    }

    @Override
    public T remove(int index) {
        T[] buffer = (T[]) new Object[values.length - 1];
        int j = 0;
        for(int i = 0; i < values.length; i++){
            if (i != index) {
                buffer[j] = values[i];
                j++;
            }
        }
        values = buffer;
        return values[index];
    }

    @Override
    public T get(int index) {
        return values[index];
    }

    @Override
    public void set(int index, T element) {
        values[index] = element;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < size();
            }

            @Override
            public T next() {
                return values[counter++];
            }
        };
    }
}
