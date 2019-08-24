package com.shpp.p2p.cs.dzubak.assignment17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class that tests the output
 * values ​​of my created classes and original classes.
 */

public class test {


    MyHashMap<Integer, String> myHash = new MyHashMap<>();
    HashMap<Integer, String> hash = new HashMap<>();

    MyPriorityQueue<Integer> myPriorInt = new MyPriorityQueue<>();
    PriorityQueue<Integer> priorInt = new PriorityQueue<>();

    MyPriorityQueue<String> myPriorStr = new MyPriorityQueue<>();
    PriorityQueue<String> priorStr = new PriorityQueue<>();

    MyPriorityQueue<Integer> myComp = new MyPriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(Math.abs(o1), Math.abs(o2));
        }
    });
    PriorityQueue<Integer> comp = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(Math.abs(o1), Math.abs(o2));
        }
    });

    /**
     * The method fills different classes with identical values.
     */
    @BeforeEach
    void test() {
        for (int i = 0; i < 3000; i++) {
            myHash.put(i, "0" + i);
            hash.put(i, "0" + i);

            priorInt.add(i);
            myPriorInt.add(i);

            myPriorStr.add("test" + i);
            priorStr.add("test" + i);

            myComp.add(i * (-1));
            comp.add(i * (-1));
        }

        myHash.put(100, "My");
        hash.put(100, "My");

        priorInt.add(-10);
        myPriorInt.add(-10);

    }

    /**
     * The method outputs and compares the specified values ​​of my classes and the original class.
     */
    @Test
    void get() {
        assertEquals(myHash.get(2500), hash.get(2500));
        assertEquals(myHash.get(100), hash.get(100));
        assertEquals(myHash.get(0), hash.get(0));

        assertEquals(myPriorInt.poll(), priorInt.poll());
        assertEquals(myPriorInt.poll(), priorInt.poll());
        assertEquals(myPriorInt.peek(), priorInt.peek());

        assertEquals(myPriorStr.poll(), priorStr.poll());
        assertEquals(myPriorStr.peek(), priorStr.peek());

        assertEquals(myComp.poll(), comp.poll());
        assertEquals(myComp.peek(), comp.peek());

    }
}
