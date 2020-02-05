package com.softserve.task2.LFU2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        CacheService cacheService = new CacheService();

        for (int i = 0; i < 250000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }


        logger.info(String.format("Total evictions  %s", CacheService.cacheEviction));
        logger.info(String.format("Avg time %s", cacheService.getAvgTime()));
        logger.info(String.format("Cache size %s", cacheService.size()));
    }
}
