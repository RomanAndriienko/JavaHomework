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

        if (isFull()) {
            Optional<Map.Entry<String, CacheEntry>> first = cache.entrySet().stream().findFirst();

            first.ifPresent(value -> logger.info(String.format("Deleted %s", cache.remove(value.getKey()))));
            cacheEviction++;
        }
        cache.put(key, cacheEntry);
        counter++;


        long end = System.nanoTime();
        getSpentTime(start, end);
    }

    @Override
    public CacheEntry get(String key) {
//        Optional<CacheEntry> cacheEntry = Optional.ofNullable(cache.get(key));
//
//        cacheEntry
//                .map(entry -> {
//                    cache.remove(key);
//                    cache.put(key, entry);
//                    return entry; })
//                .orElseThrow(IllegalArgumentException::new);

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
