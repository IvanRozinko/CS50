package com.shpp.p2p.cs.irozinko.assignment17.huffman;

import com.shpp.p2p.cs.irozinko.assignment17.*;
import java.io.*;


/**
 * Class unarchive file
 */
class Unarchive {

    /**Class constructor
     * @param input name of input file
     * @param output name of output file
     */
    Unarchive(String input, String output){
        unarchive(input,output);
    }
    /**
     *Method extracting inputted file to output.
     * @param input name of inputted file
     * @param output name of outputted file
     */
    private void unarchive(String input, String output) {
        long startTime = System.currentTimeMillis();

        try(FileInputStream fis = new FileInputStream(input); FileOutputStream fos = new FileOutputStream(output);
            BufferedInputStream bufferReader = new BufferedInputStream(fis);
            BufferedOutputStream bufferWriter = new BufferedOutputStream(fos)){

            //get size of table in bytes
            byte[] sizeOfTableBytes = new byte[4];
            byte[] sizeOfDataBytes = new byte[8];
            //get size of table in bytes
            bufferReader.read(sizeOfTableBytes);
            int sizeOfTable = byteArrayToInt(sizeOfTableBytes);
            //get size of data in bytes
            bufferReader.read(sizeOfDataBytes);
            byteArrayToInt(sizeOfDataBytes);
            //get table of bits and
            MyHashMap<Byte, Integer> charsUnique = makeCharTable(bufferReader, sizeOfTable);
            MyHashMap<Byte, Node> charTable = makeTree(charsUnique);

            extractData(bufferReader, charTable, bufferWriter);

            long endTime = System.currentTimeMillis();
            long operationTime = endTime - startTime;
            showStatistics(input, output, operationTime);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**Making char table for each unique char (key) it unique bit sequence(value)
     * @param charsUnique Set of  bytes contains unique symbols in file
     * @return charTable as a char table
     */
    private MyHashMap<Byte, Node> makeTree(MyHashMap<Byte, Integer> charsUnique) {
        MyHashMap<Byte, Node> charTable = new MyHashMap<>();
        MyPriorityQueue<Node> tree = new MyPriorityQueue<>();
        for (MyHashMap.Entry entry: charsUnique.entry()){
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


    /**Shows info statics about file extracting
     * @param input archive file name
     * @param output extracted file name
     * @param operationTime time taken to extract file
     */
    private void showStatistics(String input, String output, long operationTime) {
        System.out.println("Input file: [" + input + "]");
        System.out.println("Output file: [" + output + "]");
        System.out.println("Time to extract file is: [" + operationTime / 1000.0 + "] sec" );
    }


    /**
     *Method replacing bytes of input stream extract it original values and flush to writer stream
     * @param bufferReader inputted stream of bytes
     * @param bufferWriter outputted stream of bytes
     * @param charTable char table keys - bytes(bits sequence) values - original symbols of inputted file
     */
    private void extractData(BufferedInputStream bufferReader, MyHashMap<Byte, Node> charTable,
                                    BufferedOutputStream bufferWriter) throws IOException {
        MyHashMap<String, Byte> chars = new MyHashMap<>();
        for (MyHashMap.Entry entry : charTable.entry()){
            chars.put(((Node)entry.getValue()).bitSequence, (Byte)entry.getKey());
        }
        //use to store bit current sequence
        StringBuilder sb = new StringBuilder();
        StringBuilder bitSequence = new StringBuilder();
        //length of data to unpack
        int dataLength = bufferReader.available();
        String current;
        for (int i = 0; i < dataLength; i++ ){
            byte b = (byte)bufferReader.read();
            if (i == dataLength - 1){
                current = Integer.toBinaryString((b + 256) % 256);
            }
            else {
                current = String.format("%8s",
                        Integer.toBinaryString((b + 256) % 256)).replace(' ', '0');
            }
            sb.append(current);
            for (int j = 0; j < sb.length(); j++){
                bitSequence.append(sb.charAt(j));
                if(chars.containsKey(bitSequence.toString())) {
                    bufferWriter.write(chars.getValue(bitSequence.toString()));
                    bitSequence = new StringBuilder();
                }
            }
            sb = new StringBuilder();
        }
    }

    /**Gets char table from input stream. First byte put to value second byte - to key. It's opposite to
     * archive table
     * @param sizeOfTable contains size of char table in bytes
     * @param bufferReader contains buffered input stream
     * @return charTable - hashMap with keys - bytes (bits sequence) and values - bytes(symbols of file)
     */
    private MyHashMap<Byte, Integer> makeCharTable(BufferedInputStream bufferReader, int sizeOfTable) throws IOException {
        int tableBlockSize = 5;
        int bytesInInteger = 4;
        MyHashMap<Byte, Integer> charTable = new MyHashMap<>();
         while (sizeOfTable > 0){
            Byte key = (byte)bufferReader.read();
            byte[] arr = new byte[bytesInInteger];
            for(int i = 0; i < bytesInInteger; i++){
                arr[i] = (byte) bufferReader.read();
            }
            int value = byteArrayToInt(arr);
            charTable.put(key, value);
            sizeOfTable -= tableBlockSize;
        }
       
        return charTable;
    }

    /**Converting byte array to integer
     * @param array  of bytes
     * @return integer stored inside array
     */
    private int byteArrayToInt(byte[] array ) {
        int result = 0;
        for (byte b : array) {
            result = result << 8;
            result = result|(b & 255);
        }
        return result;
    }


}
