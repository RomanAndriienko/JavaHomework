package com.softserve.task1.semaphore;

import java.util.concurrent.Semaphore;

public class Demo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        MyThread thread1 = new MyThread(semaphore, "first");
        MyThread thread2 = new MyThread(semaphore, "second");
        thread1.start();
        thread2.start();
    }
}

