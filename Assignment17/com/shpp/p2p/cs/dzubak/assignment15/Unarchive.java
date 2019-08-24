package com.shpp.p2p.cs.dzubak.assignment15;

import java.io.*;
import java.util.BitSet;

/**
 * The class that is responsible for unarchiving the file
 */
class Unarchive implements Serializable {
    /**
     * Directory for incoming and outgoing files
     */
    private static final String PART = "assets/archive/";
    /**
     * Unzip file
     */
    private static FileInputStream fileInUnarch;
    /**
     * The file that was unzipped
     */
    private static FileOutputStream fileOutUnarch;
    /**
     * Array of new bytes to write to file
     */
    private static byte[] buffer;
    /**
     * transferred object
     */
    private static Object objectIn;
    /**
     * Bit array
     */
    private static BitSet bitsetIn;
    /**
     * The length of the array of unique characters
     */
    private static int lengthHash;
    /**
     * The length of the array of bytes written
     */
    private static long sizeByte;
    /**
     * Encoded string length
     */
    private static long sizeString;
    /**
     * This encoded string
     */
    private static String encodedString;
    /**
     * Root of the tree hafman
     */
    private static Node treeIn;


    /**
     * CA class that takes a file as input and unzips it.
     *
     * @param fileIn  incoming file
     * @param fileOut output file
     * @throws IOException
     */
    Unarchive(String fileIn, String fileOut) throws IOException {

        Unarchive.fileInUnarch = new FileInputStream(PART + fileIn);
        Unarchive.fileOutUnarch = new FileOutputStream(PART + fileOut);

        readObjectFromFilee();
        readAndSaveDataFromTheObject();
        translateBitMapToString();
        decodeString();
        writeToFile();
    }

    /**
     * The method reads the incoming file and creates a written object from it.
     *
     * @throws IOException
     */
    private void readObjectFromFilee() throws IOException {
        ObjectInputStream input = new ObjectInputStream(fileInUnarch);
        try {
            objectIn = (Object) input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The data necessary for unarchiving
     * is pulled out and written out of the object.
     */
    private void readAndSaveDataFromTheObject() {
        treeIn = objectIn.getNodeValue();
        bitsetIn = objectIn.getTheValueOfAbit();
        lengthHash = objectIn.getTheLengthOfHashMap();
        sizeString = objectIn.getRowSize();
        sizeByte = objectIn.getIncomingBytesSize();
    }

    /**
     * The method maps the incoming
     * Bitset array to the encoded string.
     */
    private void translateBitMapToString() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < sizeString; i++) {
            if (bitsetIn.get(i)) {
                line.append("1");
            } else {
                line.append("0");
            }
        }
        encodedString = line.toString();
    }

    /**
     * The method decodes the encoded string according to
     * the root node of the Huffman tree and writes data
     * into the byte array.
     */
    private void decodeString() {
        buffer = new byte[(int) sizeByte];
        Node node = treeIn;
        if (lengthHash == 1) {
            byte b = node.uniqueByte;
            for (int i = 0; i < sizeByte; i++) {
                buffer[i] = b;
            }

        } else {
            int value = 0;
            int i = 0;
            while (encodedString.length() > value) {
                while (node.checkTreeLeaf()) {
                    char ch = encodedString.charAt(value);
                    if (ch == '1') {
                        node = node.right;
                    } else if (ch == '0') {
                        node = node.left;
                    }
                    value++;
                }
                buffer[i] = node.uniqueByte;
                i++;
                node = treeIn;
            }
        }
    }

    /**
     * Method writes unencrypted byte array to file
     *
     * @throws IOException
     */
    private void writeToFile() throws IOException {
        fileOutUnarch.write(buffer);
        fileOutUnarch.close();
    }
}
