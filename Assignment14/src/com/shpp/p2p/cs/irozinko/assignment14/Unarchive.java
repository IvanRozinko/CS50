package com.shpp.p2p.cs.irozinko.assignment14;

import java.io.*;
import java.util.HashMap;

/**
 * Class unarchive file
 */
class Unarchive {
    /**
     *Method extracting inputted file to output.
     * @param input name of inputted file
     * @param output name of outputted file
     */
    static void unarchive(String input, String output) {
        long startTime = System.currentTimeMillis();

        try(FileInputStream fis = new FileInputStream(input); FileOutputStream fos = new FileOutputStream(output);
        BufferedInputStream bufferReader = new BufferedInputStream(fis); BufferedOutputStream bufferWriter = new BufferedOutputStream(fos)){

            //get size of table in bytes
            byte[] sizeOfTableBytes = new byte[4];
            byte[] sizeOfDataBytes = new byte[8];
            //get size of table in bytes
            bufferReader.read(sizeOfTableBytes);
            int sizeOfTable = byteArrayToInt(sizeOfTableBytes);
            //get size of data in bytes
            bufferReader.read(sizeOfDataBytes);
            //get table of bits and
            HashMap<Byte, Byte> charTable = makeCharTable(bufferReader, sizeOfTable);

            extractData(bufferReader, charTable, bufferWriter);

            long endTime = System.currentTimeMillis();
            long operationTime = endTime - startTime;
            showStatistics(input, output, operationTime);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**Shows info statics about file extracting
     * @param input archive file name
     * @param output extracted file name
     * @param operationTime time taken to extract file
     */
    private static void showStatistics(String input, String output, long operationTime) {
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
    private static void extractData(BufferedInputStream bufferReader, HashMap<Byte, Byte> charTable,
                                    BufferedOutputStream bufferWriter) throws IOException {
        // get length of bit sequence
        int bitLength = Integer.toBinaryString(charTable.size() - 1).length();
        //use to store bit current sequence
        StringBuilder bitSequence ;
        //stores rest residue of bit sequence which was not flush to file writer
        String residue = "";
        //length of data to unpack
        int dataLength = bufferReader.available();

        for (int i = 0; i <dataLength; i ++){
            byte b = (byte) bufferReader.read();
            //condition to write last byte of stream
            if (i == dataLength - 1){
                //converting last byte of input stream  to bit sequence
                bitSequence = new StringBuilder(Integer.toBinaryString((b + 256) % 256));
                while ((residue.length() + bitSequence.length()) % bitLength != 0  ){
                    bitSequence.insert(0,"0");
                }
                bitSequence.insert(0, residue);
            } else {   //converting each byte of input stream (even negative ) to bit sequence
                bitSequence = new StringBuilder(String.format("%8s",
                        Integer.toBinaryString((b + 256) % 256)).replace(' ', '0'));
                bitSequence.insert(0,residue);
            } //convert bit sequence to original symbols and push it to stream writer
            while (bitSequence.length() >= bitLength){
                String oneByte = bitSequence.substring(0, bitLength);
                bufferWriter.write(charTable.get((byte)Integer.parseInt(oneByte,2)));
                bitSequence.delete(0,bitLength);
            }
            residue = bitSequence.toString();
        }
    }

    /**Gets char table from input stream. First byte put to value second byte - to key. It's opposite to
     * archive table
     * @param sizeOfTable contains size of char table in bytes
     * @param bufferReader contains buffered input stream
     * @return charTable - hashMap with keys - bytes (bits sequence) and values - bytes(symbols of file)
     */
    private static HashMap<Byte, Byte> makeCharTable(BufferedInputStream bufferReader, int sizeOfTable) throws IOException {
       HashMap<Byte, Byte> charTable = new HashMap<>();
       while (sizeOfTable > 0) {
           Byte value = (byte)bufferReader.read();
           Byte key = (byte)bufferReader.read();
           charTable.put(key, value);
           sizeOfTable -= 2;
       }
        return charTable;
    }

    /**Converting byte array to integer
     * @param array  of bytes
     * @return integer stored inside array
     */
    private static int byteArrayToInt(byte[] array ) {
        int result = 0;
        for (byte b : array) {
            result = result << 8;
            result = result|(b & 255);
        }
        return result;
    }


}
