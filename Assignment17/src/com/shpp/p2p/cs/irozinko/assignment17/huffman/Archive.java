package com.shpp.p2p.cs.irozinko.assignment17.huffman;

import com.shpp.p2p.cs.irozinko.assignment17.*;

import java.io.*;



/**
 * Class pack inputted file to archive, using char table based on Huffman algorithm
 *
 */
 class Archive {

    /**Class constructor
     * @param input name of input file
     * @param output name of output file
     */
     Archive(String input, String output){
        archive(input,output);
    }


    /**Method adds to archive input file to output file
     * @param input name file
     * @param output name of file
     */
      private void archive(String input, String output) {
        long startTime = System.currentTimeMillis();
        //Getting unique symbols from file and frequency of each
        MyHashMap<Byte, Integer> charsUnique = getUniqueChars(input);
        //using to write bytes and nodes of binary tree
        MyHashMap<Byte, Node> charTable = makeTree(charsUnique);
        long sizeOfInputFile = new File(input).length();

        try (FileInputStream fis = new FileInputStream(input); FileOutputStream fos = new FileOutputStream(output);
             BufferedInputStream bufferReader = new BufferedInputStream(fis);
             BufferedOutputStream bufferWriter = new BufferedOutputStream(fos)){

            //write table to output file
            archiveTable(bufferWriter, charsUnique, sizeOfInputFile);
            archiveData(charTable, bufferWriter, bufferReader);

            long stopTime = System.currentTimeMillis();
            long archiveTime = stopTime - startTime;
            long sizeOfOutputFile = new File(output).length();
            showStatistics(input, output, sizeOfInputFile, sizeOfOutputFile, archiveTime);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**Write to output file table of chars and it frequency in inputted file
     * @param bufferWriter stream of bytes to write in  output file
     * @param charsUnique   hashmap contains unique symbols and frequency of it in inputted file
     * @throws IOException stream can throw exception
     */
    private void archiveTable(BufferedOutputStream bufferWriter, MyHashMap<Byte,
                                Integer> charsUnique, long sizeOfInputFile) throws IOException {
        //write size of table to output file
        int sizeOfTable = getCharTableSize(charsUnique);
        bufferWriter.write(intToByteArray(sizeOfTable));
        //write size of data of inputted file
        bufferWriter.write(longToByteArray(sizeOfInputFile));

        for (MyHashMap.Entry entrySet : charsUnique.entry()) {
            bufferWriter.write((Byte)entrySet.getKey());
            bufferWriter.write(intToByteArray((Integer) entrySet.getValue()));
        }
    }


    /**Counting size of char table
     * @param charTable hashmap with byte as key and node as value
     * @return size of table in bytes
     */
    private int getCharTableSize(MyHashMap<Byte, Integer> charTable) {
        int numOfBytesInInteger = 4;
        return charTable.size() + charTable.size() * numOfBytesInInteger ;
    }


    /**Shows info statics about file extracting
     * @param input input file name
     * @param output archived file name
     * @param sizeInput size of input file
     * @param sizeOutput size of archived file
     * @param archiveTime time taken to archive file
     */
    private void showStatistics(String input, String output, long sizeInput, long sizeOutput, long archiveTime) {
        System.out.println("Input file: [" + input + "]");
        System.out.println("Output file: [" + output + "]");
        System.out.println("Size of inputted file: [" + sizeInput + "] bytes");
        System.out.println("Size of outputted file: [" + sizeOutput + "] bytes");
        System.out.println("Compression efficiency: [" + Math.floor((double)sizeOutput / sizeInput * 100) + "]%");
        System.out.println("Time to pack file is: [" + archiveTime / 1000.0 + "] sec" );
    }


    /**Creates a binary tree using Huffman algorithm. And save result to HashMap.
     * Used internet source https://stepik.org/lesson/16968/step/4?unit=4499
     * @param charsUnique Set of  bytes contains unique symbols in file
     * @return charTable with byte and node which store unique bit sequence of it
     */
    private MyHashMap<Byte, Node> makeTree(MyHashMap<Byte, Integer> charsUnique) {
        MyHashMap<Byte, Node> charTable = new MyHashMap<>();
        MyPriorityQueue<Node> tree = new MyPriorityQueue<>();
        for (MyHashMap.Entry entry : charsUnique.entry()){
            TreeLeaf leaf = new TreeLeaf((Byte)entry.getKey(), (Integer)entry.getValue());
            charTable.put((Byte)entry.getKey(), leaf);
            tree.add(leaf);
        }

        while(tree.size() > 1){
            Node first = tree.poll();
            Node second = tree.poll();
            tree.add(new TreeNode(first, second));
        }
        Node root = tree.poll();
        if (charTable.size() == 1){
            root.makeBitSequence("0");
        }else {
            root.makeBitSequence("");
        }
        return charTable;
    }

    /**Converting long to byte array
     * @param i number in long format
     * @return array of bytes
     */
    private byte[] longToByteArray(long i) {
        return new byte[]{
                (byte) (i >>> 56),
                (byte) (i >>> 48),
                (byte) (i >>> 40),
                (byte) (i >>> 32),
                (byte) (i >>> 24),
                (byte) (i >>> 16),
                (byte) (i >>> 8),
                (byte) i
        };
    }


    /**Converting int to byte array
     * @param i number in int format
     * @return array of bytes
     */
    private byte[] intToByteArray(int i) {
        return new byte[] {
                (byte) (i >>> 24),
                (byte) (i >>> 16),
                (byte) (i >>> 8),
                (byte) i
        };
    }


    /**Replacing all bytes in buffer array with bits sequences getting from char table
     * @param bufferReader reading stream
     * @param bufferWriter writing stream
     * @param charTable Hashmap with chars and its bits sequence
     */
    private void archiveData(MyHashMap<Byte, Node> charTable, BufferedOutputStream bufferWriter,          //hashmap
                               BufferedInputStream bufferReader) throws IOException {
        int dataLength = bufferReader.available();
        StringBuilder string = new StringBuilder();
        int byteLength = 8;

        for (int i = 0; i < dataLength; i++) {
            byte current = (byte)bufferReader.read();
            StringBuilder bitSequence = new StringBuilder(charTable.getValue(current).bitSequence);
            string.append(bitSequence);
            while (string.length() > byteLength){
                String oneByte = string.substring(0, byteLength);
                bufferWriter.write((byte)Integer.parseInt(oneByte,2));
                string.delete(0, byteLength);
            }
        }
        //write last byte to file
        if(!string.toString().equals("")) {
            bufferWriter.write((byte) Integer.parseInt(string.toString(), 2));
        }
        bufferWriter.flush();
    }



    /** Method selecting unique symbols in text file and and count how many times each of it repeats in file
     * @param input name of inputted text file
     * @return byte hashmap of unique symbols and quantity of repeats
     */
    private MyHashMap<Byte, Integer> getUniqueChars(String input) {
        MyHashMap<Byte, Integer> chars = new MyHashMap<>();
        try (FileInputStream fis = new FileInputStream(input)){
            int current;
            while ((current = fis.read()) != -1) {
                byte symbol = (byte)current;
                if (chars.containsKey(symbol)){
                    chars.put(symbol, (chars.getValue(symbol)+1));
                }  else {
                    chars.put(symbol, 1);
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return chars;
    }

}
