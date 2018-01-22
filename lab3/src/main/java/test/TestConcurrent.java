package test;

import concurrent.DBLockedList;
import concurrent.DBLockedSet;
import dbList.DBSafeList;
import dbSet.DBSet;

public class TestConcurrent {
    public static void main(String[] args) {
        String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
        DBLockedList<String> set=new DBLockedList<>(new DBSafeList<>(adr,false));
        System.out.println("Start main");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    set.add("hello");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("First thread is over");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    set.contains("hello");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Second thread is over");
            }

        }).start();

        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    set.contains("hello");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Third thread is over");
            }
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    set.add("hello");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("Forth thread is over");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    set.add("hello");
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("Fifth thread is over");
            }
        }).start();

    }
}
