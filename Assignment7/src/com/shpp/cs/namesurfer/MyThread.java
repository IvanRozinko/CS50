package com.shpp.cs.namesurfer;

public class MyThread implements Runnable {
    Thread thrd;

    MyThread(String name){
        thrd = new Thread(this, name);
        thrd.start();
    }
    @Override
    public void run() {
        System.out.println(thrd.getName() + " - run");
        try{
            for(int count = 0; count < 10; count++){
                Thread.sleep(400);
                System.out.println("In " + thrd.getName() +
                        " , counter: " + count);
            }
        }
        catch (InterruptedException exc) {
            System.out.println(thrd.getName() + " - breaking");
        }
        System.out.println(thrd.getName() + " end of thread");
    }
}
class UseThreadsImproved {
    public static void main(String[] args) {
        System.out.println("Start main thread");

        MyThread mt = new MyThread("Child #1");

        for (int i = 0; i < 50; i ++){
            System.out.print(".");
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException exc){
                System.out.println("Breaking of main thread");
            }
        }
        System.out.println("End of main thread");
    }

}
