package com.softserve.task2.LRU;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DemoLRU {
    private static Logger logger = LoggerFactory.getLogger(DemoLRU.class);

    public static void main(String[] args) throws InterruptedException {
        CacheService cacheService = new CacheService();

        Thread thread1 = new Thread(() -> simulate(cacheService));
        Thread thread2 = new Thread(() -> simulate(cacheService));
        Thread thread3 = new Thread(() -> simulate(cacheService));
        Thread thread4 = new Thread(() -> simulate(cacheService));
        Thread thread5 = new Thread(() -> simulate(cacheService));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        logger.info(String.format("Cache size %s", cacheService.size()));
        logger.info((String.format("Total evictions %s", cacheService.getTotalEvictions())));
        logger.info(String.format("Avg time %s", cacheService.getAvgTimeToLoad()));
    }

    private static void simulate(CacheService cacheService) {
        for (int i = 0; i < 100000; i++) {
            CacheEntry cacheEntry = new CacheEntry(UUID.randomUUID().toString());
            cacheService.put(String.valueOf(i), cacheEntry);
        }
    }
}
