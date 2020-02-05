package com.softserve.task2.LFU;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DemoLFU {
    private static final Logger logger = LoggerFactory.getLogger(DemoLFU.class);

    public static void main(String[] args) {
        CacheService cacheService = new CacheService();

        for (int i = 0; i < 25000; i++) {
            CacheEntry cacheEntry = new CacheEntry(UUID.randomUUID().toString());
            cacheService.put(String.valueOf(i), cacheEntry);
        }


        logger.info(String.format("Total evictions  %s", CacheService.cacheEviction));
        logger.info(String.format("Avg time %s", cacheService.getAvgTime()));
        logger.info(String.format("Cache size %s", cacheService.size()));
    }
}
