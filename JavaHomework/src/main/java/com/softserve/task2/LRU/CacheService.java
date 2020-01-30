package com.softserve.task2.LRU;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.softserve.task2.LFU.ICacheService;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@ToString
public class CacheService implements ICacheService<CacheEntry> {
    private static final int INITIAL_CAPACITY = 100000;
    private static final int TIME_TO_LIVE = 5;

    private static Logger logger = LoggerFactory.getLogger(CacheService.class);

    private LoadingCache<String, CacheEntry> cache = CacheBuilder.newBuilder()
            .maximumSize(INITIAL_CAPACITY)
            .expireAfterAccess(TIME_TO_LIVE, TimeUnit.SECONDS)
            .removalListener((RemovalListener<String, CacheEntry>) removalNotification ->
                    logger.info(String.format("[deleted] %s", removalNotification.getValue())))
            .recordStats()
            .build(
                    new CacheLoader<String, CacheEntry>() {
                        @Override
                        public CacheEntry load(String key) throws ExecutionException {
                            return cache.get(key);
                        }
                    });


    @Override
    public synchronized void put(String key, CacheEntry cacheEntry) {
        cache.put(key, cacheEntry);
    }

    @Override
    public CacheEntry get(String key) throws ExecutionException {
        return cache.get(key);
    }

    public double getAvgTimeToLoad(){
       return cache.stats().averageLoadPenalty();
    }

    public long getTotalEvictions () {
       return cache.stats().evictionCount();
    }

    public long size() {
        return cache.size();
    }
}
