package com.shpp.p2p.cs.irozinko.assignment17.huffman;

/**
 * Class used to store leaves of binary tree, which don't have "kids". Actually it is unique symbols from
 * inputted file.
 */
 class TreeLeaf extends Node{
    private byte symbolValue;
    //constructor
     TreeLeaf(byte symbolValue, int quantity){
        this.symbolValue = symbolValue;
        super.quantity = quantity;
    }
}
