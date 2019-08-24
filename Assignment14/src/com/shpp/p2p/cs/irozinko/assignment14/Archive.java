package com.shpp.p2p.cs.irozinko.assignment14;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class pack inputted file to archive, using char table.
 * If inputted file contain more than 256 unique bytes file can't be archived.
 */
 class Archive {

    /**Method adds to archive nput file to output file
     * @param input name file
     * @param output name of file
     */
    public static void archive(String input, String output) {
        long startTime = System.currentTimeMillis();

        Set<Byte> charsUnique = getUniqueChars(input);
        //using to write bytes and bits sequence to table
        HashMap<Byte, Byte> charTable = makeCharTable(charsUnique);
        //size of inputted file
        long sizeOfInputFile = new File(input).length();
        int bitLength = Integer.toBinaryString(charsUnique.size() - 1).length();

        try (FileInputStream fis = new FileInputStream(input); FileOutputStream fos = new FileOutputStream(output);
            BufferedInputStream bufferReader = new BufferedInputStream(fis)){

            //read all file to byte array\
            byte[] buffer =  new byte[bufferReader.available()];

            bufferReader.read(buffer);
            //write size of table to output file
            int sizeOfTable = charTable.size() * 2;
            fos.write(intToByteArray(sizeOfTable));
            //write size of data of inputted file
            fos.write(longToByteArray(sizeOfInputFile));
            //write table to output file
            for (Map.Entry<Byte, Byte> entrySet : charTable.entrySet()) {
                fos.write(entrySet.getKey());
                fos.write(entrySet.getValue());
            }
            //replace all chars in buffer array to binary sequences
            archiveBuffer(buffer, charTable, fos,bitLength);


            long stopTime = System.currentTimeMillis();
            long archiveTime = stopTime - startTime;
            long sizeOfOutputFile = new File(output).length();
            showStatistics(input, output, sizeOfInputFile,sizeOfOutputFile, archiveTime);


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**Shows info statics about file extracting
     * @param input input file name
     * @param output archived file name
     * @param sizeOfInputFile size of input file
     * @param sizeOfOutputFile size of archived file
     * @param archiveTime time taken to archive file
     */
    private static void showStatistics(String input, String output, long sizeOfInputFile, long sizeOfOutputFile, long archiveTime) {
        System.out.println("Input file: [" + input + "]");
        System.out.println("Output file: [" + output + "]");
        System.out.println("Size of inputted file: [" + sizeOfInputFile + "] bytes");
        System.out.println("Size of outputted file: [" + sizeOfOutputFile + "] bytes");
        System.out.println("Compression efficiency: [" + Math.floor((double)sizeOfOutputFile / sizeOfInputFile * 100) + "]%");
        System.out.println("Time to pack file is: [" + archiveTime / 1000.0 + "] sec" );
    }

    /**Making char table for each unique char (key) it unique bit sequence(value)
     * @param charsUnique Set of  bytes contains unique symbols in file
     * @return charTable as a char table
     */
    private static HashMap<Byte, Byte> makeCharTable(Set<Byte> charsUnique) {
        HashMap<Byte, Byte> charTable= new HashMap<>();
        int i = 0;
        for (Byte current : charsUnique){
            charTable.put(current, getBitSequence(i));
            i++;
        }
        return charTable;
    }

    /**Converting long to byte array
     * @param i number in long format
     * @return array of bytes
     */
    private static byte[] longToByteArray(long i) {
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
    private static byte[] intToByteArray(int i) {
        return new byte[] {
                (byte) (i >>> 24),
                (byte) (i >>> 16),
                (byte) (i >>> 8),
                (byte) i
        };
    }

    /**Replacing all bytes in buffer array with bits sequences getting from char table
     * @param buffer array of bytes from inputted file
     * @param charTable hashmap with chars and its bits sequence
     */
    private static void archiveBuffer(byte[] buffer, HashMap<Byte, Byte> charTable, FileOutputStream fos, int bitLength) throws IOException {
        StringBuilder string = new StringBuilder();
        int byteLength = 8;
        //replace all bytes in buffer to value of "0" and "1"
        for (byte current : buffer) {
            StringBuilder bitSequence = new StringBuilder(Integer.toBinaryString(charTable.get(current)));
            while(bitSequence.length() < bitLength){
                bitSequence.insert(0,"0");
            }
            string.append(bitSequence);
            if (string.length() > byteLength){
                String oneByte = string.substring(0, byteLength);
                fos.write((byte)Integer.parseInt(oneByte,2));

                string.delete(0,byteLength);
            }
        }
        //write last byte to file
        fos.write((byte)Integer.parseInt(string.toString(),2));
    }


    /** Making  bits sequence for each symbol in charsUnique array
     * @param numOfChar number of unique byte in unique
     * @return byte
     */
    private static Byte getBitSequence(int numOfChar) {
        return (byte)Integer.parseInt(Integer.toBinaryString(numOfChar),2);
    }


    /** Method selecting unique symbols in text file and make byte array of them
     * @param input name of inputted text file
     * @return byte array of unique symbols
     */
    private static Set<Byte> getUniqueChars(String input) {
        Set<Byte> chars = new HashSet<>();
        try {
            FileInputStream fis = new FileInputStream(input);
            int current;
            while ((current = fis.read()) != -1) {
                chars.add((byte)current);
            }
            fis.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return chars;
    }

}
