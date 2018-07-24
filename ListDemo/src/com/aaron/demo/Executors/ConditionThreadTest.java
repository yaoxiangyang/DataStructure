package com.aaron.demo.Executors;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionThreadTest {

    public static void main(String args[]){
        Business business = new Business();
        new Thread(() -> {
            business.sub1();
        }).start();

        new Thread(() -> {
            business.main();
        }).start();

        new Thread(() -> {
            business.sub2();
        }).start();

    }


    static class Business{

        Lock lock = new ReentrantLock();
        private int flag = 3;
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        public void sub1(){
            lock.lock();
            try {
                if(flag != 1){
                    condition1.await();
                }

                for(int i=1; i<=10; i++){
                    System.out.println("sub1 thread execute "+ i + "times");
                }
                flag = 2;
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }

        public void sub2(){
                lock.lock();
                try {
                    if(flag != 2){
                        condition2.await();
                    }

                    for(int i=1; i<=10; i++){
                        System.out.println("sub2 thread execute "+ i + "times");
                    }

                    flag = 3;
                    condition3.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

        }

        public void main() {

            lock.lock();
            try {
                if(flag != 3){
                    condition3.await();
                }

                for(int i=1; i<=50; i++){
                    System.out.println("main thread execute "+ i +" times");
                }
                flag = 1;
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

}
