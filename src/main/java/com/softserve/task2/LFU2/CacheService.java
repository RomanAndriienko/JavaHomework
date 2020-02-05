package com.softserve.task2.LFU2;

import com.softserve.task2.LFU.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CacheService implements ICacheService<CacheEntry> {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    private static final int INITIAL_CAPACITY = 100000;

    public static long cacheEviction = 0;
    private long totalTime = 0L;
    private long counter = 0L;

    private static LinkedHashMap<String, CacheEntry> cache = new LinkedHashMap<>();

    @Override
    public void put(String key, CacheEntry cacheEntry) {
        long start = System.nanoTime();

        String firstKey = "";

        if (isFull()) {
            Optional<Map.Entry<String, CacheEntry>> first = cache.entrySet().stream().findFirst();
            if (first.isPresent()) {
                firstKey = first.get().getKey();
            }
            logger.info(String.format("Deleted %s", cache.remove(firstKey)));
            cacheEviction++;
        }
        cache.put(key, cacheEntry);
        counter++;


        long end = System.nanoTime();
        getSpentTime(start, end);
    }

    @Override
    public CacheEntry get(String key) {
        if (cache.containsKey(key)) {

            CacheEntry currentEntry = cache.get(key);
            cache.remove(key);
            cache.put(key, currentEntry);

            return cache.get(key);
        } else {
            throw new IllegalArgumentException("Sorry there is no such key");
        }
    }

    public long getAvgTime() {
        return totalTime / counter;
    }

    public int size() {
        return cache.size();
    }

    private boolean isFull() {
        return cache.size() == INITIAL_CAPACITY;
    }

    private void getSpentTime(long start, long end) {
        long spentTime = end - start;
        totalTime = totalTime + spentTime;
    }
}
