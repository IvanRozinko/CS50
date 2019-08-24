package com.shpp.p2p.cs.irozinko.assignment17;



public class test {
    final static private int MAX_SIZE = 1000;
    public static void main(String[] args) {

//        testMyHashMap();
        testMyPriorityQueue();

    }

    private static void testMyPriorityQueue() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
        System.out.println("Initial size of PriorityQueue: [" + queue.size() + "]\n isEmpty call: [" + queue.isEmpty() + "]");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_SIZE; i++ ){
            queue.add(i * 100);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to add " + MAX_SIZE + " elements to collection: [" + (endTime-startTime)/1000.0 + "] sec" );

        for (int i = 0; i < MAX_SIZE - 5; i++){
            queue.poll();
        }
        System.out.println("Poll last 5 elements from queue: ");
        while(!queue.isEmpty()){
            System.out.print(queue.poll() + " ");
        }

        for (int i = 0; i < 5; i ++){
            queue.add(i);
        }
        System.out.println("\nRemove element [4]: " + queue.remove(4));
        System.out.println( "Queue after removing 4: ");
        while(!queue.isEmpty()){
            queue.poll();
        }

        System.out.println("Queue size after polling all [" + queue.size()+ "]");




    }

    private static void testMyHashMap() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        System.out.println("Initial size of MyHashMap: [" + map.size() + "]\n isEmpty call: [" + map.isEmpty() + "]");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_SIZE; i++ ){
            map.put("key" + i, i * 100);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to add " + MAX_SIZE + " elements to collection: [" + (endTime-startTime)/1000.0 + "] sec" );
        System.out.println("Show getValue key100 [" + map.getValue("key500") + "]");
        for (int i = 0; i < MAX_SIZE - 5; i++){
            map.remove("key" + i);
        }
        System.out.println("Size after removing: [" + map.size()+"]" + "\n And entries left: ");
        for(MyHashMap.Entry entry : map.entry()){
            System.out.print("["+entry.getKey() + ":" + entry.getValue()+"] ");
        }
        System.out.println("\nCheck if map contains key key999: [" + map.containsKey("key999") +
                "] and value 0: [" + map.containsValue(0) + "]");
        System.out.println("keySet of MyHashMap: ");
        for (String key : map.keySet()){
            System.out.print("[" + key + "] ");
        }
        System.out.println("\nvalues of MyHashMap: ");
        for (Integer value : map.values()){
            System.out.print("[" + value + "] ");
        }
        System.out.println("\nRest size: [" + map.size()+ "]");
        map.clear();
        System.out.println("MyHashMap is empty?: [" + map.isEmpty() + "]");





    }
}
