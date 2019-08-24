package com.shpp.p2p.cs.dzubak.assignment15;

import com.shpp.p2p.cs.dzubak.assignment17.MyHashMap;
import com.shpp.p2p.cs.dzubak.assignment17.MyPriorityQueue;

import java.io.*;
import java.util.BitSet;

/**
 * Class performs
 * data archiving
 */
class Archive implements Serializable {
    /**
     * Directory for incoming and outgoing files
     */
    private static final String PART = "assets/archive/";
    /**
     * File to archive
     */
    private static FileInputStream fileInArch;
    /**
     * File which is archived
     */
    private static FileOutputStream fileOutArch;
    /**
     * String to determine the name of the archived file
     */
    private static String fileName;
    /**
     * HashMap shows byte and its number of occurrences
     */
    private static MyHashMap <Byte, Integer> tableByteCount;
    /**
     * Incoming byte array
     */
    private static byte[] buffer;
    /**
     * Incoming byte array size
     */
    private static long sizeIn;
    /**
     * Encoded string
     */
    private static String stringOfEncodedValues;
    /**
     * Havman Root Tree Node
     */
    private static Node tree;
    /**
     * Map of the value of the character and its code in string representation
     */
    private static MyHashMap<Byte, String> tableOfSymbols;
    /**
     * Array set of string
     */
    private static BitSet bitset;
    /**
     * Object to transfer
     */
    private static Object object;


    /**
     * The class that is responsible for archiving the file
     *
     * @param fileIn  incoming file
     * @param fileOut output file
     * @throws IOException
     */
    Archive(String fileIn, String fileOut) throws IOException {
        fileName = fileOut;
        Archive.fileInArch = new FileInputStream(PART + fileIn);
        Archive.fileOutArch = new FileOutputStream(PART + fileOut);

        createAnArrayOfValues();
        countingUniqueCharacters();
        createATreeHafman();
        createEncodingTable();
        encodeCharacters();
        translateStringIntoBits();
        createObject();
        writeToOutputFile();
    }

    /**
     * The method creates an array of values ​​reading data from the incoming file.
     *
     * @throws IOException
     */
    private void createAnArrayOfValues() throws IOException {
        buffer = new byte[fileInArch.available()];
        sizeIn = fileInArch.available();
        fileInArch.read(buffer);
        fileInArch.close();
    }

    /**
     * The method counts unique bytes and their number.
     */
    private void countingUniqueCharacters() {

        tableByteCount = new MyHashMap <>();
        for (int i = 0; i < sizeIn; i++) {
            Byte key = buffer[i];

            if (tableByteCount.containsKey(key)) {
                int count = tableByteCount.get(key);
                tableByteCount.put(key, count + 1);
            } else {
                tableByteCount.put(key, 1);
            }
        }
    }

    /**
     * The method creates a Huffman tree using the
     * priority queue and writes the root of the tree into a variable.
     */
    private void createATreeHafman() {
        MyPriorityQueue<Node> PriorityQueueNode = new MyPriorityQueue<>();
        for (Byte key : tableByteCount) {
            if (key!=null){
                Node node = new Node(key, tableByteCount.get(key), null, null);
                PriorityQueueNode.add(node);
            }

        }

        while (PriorityQueueNode.size() > 1) {
            Node right = PriorityQueueNode.poll();
            Node left = PriorityQueueNode.poll();

            Node sumRightLeft = new Node((byte) 0, (right.numberOfEntries + left.numberOfEntries), left, right);
            PriorityQueueNode.add(sumRightLeft);
        }
        tree = PriorityQueueNode.peek();
    }

    /**
     * The method creates a table of bytes and their unique codes (lines)
     * for each byte that is in the input file
     */
    private void createEncodingTable() {
        tableOfSymbols = new MyHashMap <>();
        if (tableByteCount.size() > 1) {
            assignCodeFromTheTree(tree, "");
        } else if (tableByteCount.size() == 1) {
            tableOfSymbols.put(buffer[0], "0");
        }
    }

    /**
     * The method takes the root of the Havman
     * tree as input and creates prefix codes in the reverse order.
     *
     * @param tree Havman tree
     * @param s    line for new code
     */
    private void assignCodeFromTheTree(Node tree, String s) {
        if (tree.checkTreeLeaf()) {
            assignCodeFromTheTree(tree.left, s + "0");
            assignCodeFromTheTree(tree.right, s + "1");
        } else {
            tableOfSymbols.put(tree.uniqueByte, s);
        }
    }

    /**
     * The method creates a new encoded
     * line according to the new codes.
     */
    private void encodeCharacters() {
        StringBuilder line = new StringBuilder();
        for (byte b : buffer) {
            String str = tableOfSymbols.get(b);
            line.append(str);
        }
        stringOfEncodedValues = line.toString();
    }

    /**
     * The method translates the encoded line into the bit array.
     */
    private void translateStringIntoBits() {
        bitset = new BitSet();
        for (int i = 0; i < stringOfEncodedValues.length(); i++) {
            if (stringOfEncodedValues.charAt(i) == '1') {
                bitset.set(i);
            }
        }
    }

    /**
     * The method creates an object to transfer and write to the file.
     */
    private void createObject() {
        object = new Object(tree, bitset, tableByteCount.size(), stringOfEncodedValues.length(), sizeIn);
    }

    /**
     * The method writes the created object to the file.
     *
     * @throws IOException
     */
    private void writeToOutputFile() throws IOException {
        try (ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutArch)) {
            objectOutput.writeObject(object);
            fileOutArch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns the size of the incoming file.
     *
     * @return size in bytes
     */
    public long getBytesArchivedFile() {

        return sizeIn;
    }

    /**
     * The method returns the size of the output file.
     *
     * @return size in bytes
     */
    public long getBytesArchivedFileOut() {
        File file = new File(PART + fileName);
        long len = file.length();
        return len;
    }
}
