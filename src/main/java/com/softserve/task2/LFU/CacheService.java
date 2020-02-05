package com.softserve.task2.LFU;


import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class CacheService implements ICacheService<CacheEntry> {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    private static final long TIME_TO_LIVE = 5000L;
    private static final int INITIAL_CAPACITY = 100000;

    public static long cacheEviction = 0;
    private long totalTime = 0L;
    private long counter = 0L;

    private static ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>(INITIAL_CAPACITY);

    @Override
    public void put(String key, CacheEntry cacheEntry) {
        long start = System.currentTimeMillis();

        for (Map.Entry<String, CacheEntry> entry : cache.entrySet()) {
            if (entry.getValue().getExpirationTime() < System.currentTimeMillis()) {
                removeFromCache(entry.getKey());
            }
        }

        if (!isFull()) {
            cache.put(key, cacheEntry);
            counter++;
        } else {
            logger.info("Sorry cache is full");
        }

        long end = System.currentTimeMillis();
        getSpentTime(start, end);
    }

    @Override
    public CacheEntry get(String key) {
        if (cache.containsKey(key)) {
            CacheEntry entry = cache.get(key);
            if (entry.getExpirationTime() > System.currentTimeMillis()) {
                updateExpirationTime(key);
                return entry;
            }
        }
        throw new IllegalArgumentException("Sorry there is no such key");
    }


    private void updateExpirationTime(String key) {
        CacheEntry entry = cache.get(key);
        entry.setExpirationTime(System.currentTimeMillis() + TIME_TO_LIVE);
    }

    private void removeFromCache(String key) {
        logger.info(String.format("[Deleted] entry %s", cache.remove(key)));
        cacheEviction++;
    }

    private static boolean isFull() {
        return cache.size() == INITIAL_CAPACITY;
    }

    private void getSpentTime(long start, long end) {
        long spentTime = end - start;
        totalTime = totalTime + spentTime;
    }

    public long getAvgTime() {
        return totalTime / counter;
    }

    public int size() {
        return cache.size();
    }
}
