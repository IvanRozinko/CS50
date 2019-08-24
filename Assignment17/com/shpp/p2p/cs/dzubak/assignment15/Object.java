package com.shpp.p2p.cs.dzubak.assignment15;

import java.io.Serializable;
import java.util.BitSet;

/**
 * Class is used to transfer data to a file
 */
public class Object implements Serializable {
    private Node node;
    private BitSet bit;
    private int lengthHash;
    private long sizeString;
    private long sizeByte;

    /**
     * @param node       root tree huffman
     * @param bit        data transfer array BitSet
     * @param lengthHash unique character map size
     * @param sizeString encoded string size
     * @param sizeByte   size of the original array
     */
    public Object(Node node, BitSet bit, int lengthHash, long sizeString, long sizeByte) {
        this.node = node;
        this.bit = bit;
        this.lengthHash = lengthHash;
        this.sizeString = sizeString;
        this.sizeByte = sizeByte;
    }

    /**
     * The method returns the root node Huffman
     *
     * @return node
     */
    Node getNodeValue() {
        return node;
    }

    /**
     * Method returns an array BitSet
     *
     * @return array BitSet
     */
    BitSet getTheValueOfAbit() {
        return bit;
    }

    /**
     * The method returns the size HashMap
     *
     * @return size HashMap
     */
    int getTheLengthOfHashMap() {
        return lengthHash;
    }

    /**
     * The method returns the size  String
     *
     * @return size  String
     */
    long getRowSize() {
        return sizeString;
    }

    /**
     * The method returns the size of the array bytes
     *
     * @return size of array bytes
     */
    long getIncomingBytesSize() {
        return sizeByte;
    }


}
