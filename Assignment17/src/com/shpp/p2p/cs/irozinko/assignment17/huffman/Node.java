package com.shpp.p2p.cs.irozinko.assignment17.huffman;

/**
 * Class contains node of binary tree. This is parent class, which contains bit sequence of current node and
 * frequency of it appearance in inputted file
 */
 class Node implements Comparable<Node> {
    Integer quantity;
    String bitSequence;

    /**Sets a value of bits sequence
     * @param bitSequence value of bits sequence
     */
   void makeBitSequence(String bitSequence){
        this.bitSequence = bitSequence;
    }


    @Override
    public int compareTo(Node anotherNode) {
        return this.quantity.compareTo(anotherNode.quantity);
    }
}
