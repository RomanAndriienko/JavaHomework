package com.softserve.task2.LFU;


import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class CacheService implements ICacheService<CacheEntry> {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    public static long cacheEviction = 0;

    private static AtomicLong totalTime = new AtomicLong(0);
    private static AtomicLong counter = new AtomicLong(0);
    private static final long TIME_TO_LIVE = 5000L;
    private static final int INITIAL_CAPACITY = 100000;
    private static ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>(INITIAL_CAPACITY);


    @Override
    public synchronized void put(String key, CacheEntry cacheEntry) {
        long start = System.currentTimeMillis();

        counter.getAndIncrement();

        for (Map.Entry<String, CacheEntry> entry : cache.entrySet()) {
            if (entry.getValue().getExpirationTime() < System.currentTimeMillis()) {
                removeFromCache(entry.getKey());
            }
        }

        if (!isFull()) {
            cache.put(key, cacheEntry);
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
        totalTime.addAndGet(spentTime);
    }

    public long getAvgTime() {
        return totalTime.get() / counter.get();
    }

    public int size() {
        return cache.size();
    }
}
