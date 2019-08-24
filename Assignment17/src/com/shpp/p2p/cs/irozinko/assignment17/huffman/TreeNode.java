package com.shpp.p2p.cs.irozinko.assignment17.huffman;

/**
 * Class extends class Node. Objects of this class has 2 "kids"
 */
 class TreeNode extends Node{
    private Node first;
    private Node second;

    TreeNode (Node first, Node second){
        this.first = first;
        this.second = second;
        super.quantity = first.quantity + second.quantity;
    }

     @Override
     void makeBitSequence(String bitSequence) {
         super.makeBitSequence(bitSequence);
         first.makeBitSequence(bitSequence + "0");
         second.makeBitSequence(bitSequence + "1");
     }
 }
