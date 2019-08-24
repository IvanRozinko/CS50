package com.shpp.p2p.cs.irozinko.assignment15;

/**
 * Class contains node of binary tree. This is parent class, which contains bit sequence of current node and
 * frequency of it appearance in inputted file
 */
 class Node {
    int quantity;
    String bitSequence;

    /**Sets a value of bits sequence
     * @param bitSequence value of bits sequence
     */
   void makeBitSequence(String bitSequence){
        this.bitSequence = bitSequence;
    }
}
