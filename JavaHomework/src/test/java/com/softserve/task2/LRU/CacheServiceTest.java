package com.softserve.task2.LRU;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class CacheServiceTest {
    private static final int INITIAL_CAPACITY = 100000;
    private ConcurrentHashMap<String, CacheEntry> actualCache;
    private CacheService cacheService;
    private String key = String.valueOf(555);

    @Before
    public void setUp() {
        cacheService = new CacheService();
        actualCache = new ConcurrentHashMap<>(INITIAL_CAPACITY);
        fillActualCache();
        fillExpectedCache();
    }


    @Test
    public void getEntries() throws ExecutionException {
        CacheEntry actual = actualCache.get(String.valueOf(key));
        CacheEntry expected = cacheService.get(String.valueOf(key));

        Assert.assertEquals(actual.getData(), expected.getData());
    }


    @Test
    public void sizeTest() {
        long actual = actualCache.size();
        long expected = cacheService.size();

        Assert.assertEquals(expected, actual);
    }


    private void fillExpectedCache() {
        for (int i = 0; i < 1000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }
    }

    private void fillActualCache() {
        for (int i = 0; i < 1000; i++) {
            actualCache.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }
    }
}