package com.softserve.task1.semaphore;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

@AllArgsConstructor
public class MyThread extends Thread {
    private Semaphore semaphore;
    private String threadName;

    @Override
    public void run() {
        if (threadName.equals("first")) {
            try {
                semaphore.acquire();

                System.out.println("Now working " + threadName);

                for (int i = 1; i <= 10; i++) {
                    System.out.println(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Something goes wrong " + e);
            }
            semaphore.release();
        }

        if (threadName.equals("second")) {
            try {
                semaphore.acquire();

                System.out.println("Now working " + threadName);

                for (int i = 1; i <= 10; i++) {
                    System.out.println(i);
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }
    }
}
