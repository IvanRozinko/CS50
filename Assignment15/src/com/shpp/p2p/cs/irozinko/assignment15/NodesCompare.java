package com.shpp.p2p.cs.irozinko.assignment15;

import java.util.Comparator;


/**
 * Class using interface Comparator to sort objects of Node class. If first object has less quantity than second,
 * than first object inserts in priority queue before second object. And so on.
 */
 class NodesCompare implements Comparator<Node> {

     @Override
     public int compare(Node first, Node second) {
         if (first.quantity < second.quantity) {
             return -1;
         }
         else if (first.quantity > second.quantity){
             return 1;
         }
         return 0;
     }
 }
