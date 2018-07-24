package com.aaron.demo.lock;

public class DeadLockTest {
    static String lockA = "lockA";
    static String lockB = "lockB";

    public static void main(String[] args){
        new Thread(new TaskA()).start();
        new Thread(new TaskB()).start();
    }


    static class TaskA implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    synchronized (DeadLockTest.lockA){
                        System.out.println("Task A get lockA");
                        Thread.sleep(10);

                        synchronized (DeadLockTest.lockB){
                            System.out.println("Task A get lockB");
                            Thread.sleep(30);
                        }
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TaskB implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    synchronized (DeadLockTest.lockB){
                        System.out.println("Task B get lockB");
                        Thread.sleep(10);

                        synchronized (DeadLockTest.lockA){
                            System.out.println("Task B get lockA");
                            Thread.sleep(30);
                        }
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
