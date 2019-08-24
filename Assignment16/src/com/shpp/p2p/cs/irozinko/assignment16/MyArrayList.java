package com.shpp.p2p.cs.irozinko.assignment16;



/**Class imitates simple analog of ArrayList based on array, implements MyArrayInterface.
 * @param <T> parametrized type
 */
class MyArrayList<T> implements IMyArrayList<T>{
    //variable contains simple array
    private T[] value;

    MyArrayList(){
        value = (T[]) new Object[0];
    }

    /**Creates new array which size bigger on 1 element, adds element t to last position of array
     * @param t new element to be added
     */
    @Override
    public void add(T t) {
        T [] buffer = (T[])new Object[value.length + 1];
        for (int i = 0; i < buffer.length; i++){
            if(i == buffer.length - 1){
                buffer[i] = t;
            }else {
                buffer[i] = value[i];
            }
        }
        value = buffer;
    }

    /**Removes element with index number from array
     * @param index of element to be removed
     * @return removed element
     */
    @Override
    public T remove(int index) {
        T[] buffer = (T[]) new Object[value.length - 1];
        int j = 0;
        for(int i = 0; i < value.length; i++){
            if (i != index) {
                buffer[j] = value[i];
                j++;
            }
        }
        value = buffer;
        return value[index];
    }

    /**Get element by index
     * @param index of element to get
     * @return element
     */
    @Override
    public T get(int index) {
        return value[index];
    }

    /**Sets value T of element with index
     * @param index of element in array
     * @param t new value of element
     */
    @Override
    public void set(int index, T t) {
        value[index] = t;
    }

    /**Return size of array
     * @return array length
     */
    @Override
    public int size() {
        return value.length;
    }
}
