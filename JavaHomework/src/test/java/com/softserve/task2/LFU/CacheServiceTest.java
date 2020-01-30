package com.softserve.task2.LFU;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceTest {
    private static final int INITIAL_CAPACITY = 100000;
    private ConcurrentHashMap<String, CacheEntry> actualCache;
    private CacheService cacheService;
    private String key = String.valueOf(555);

    @Before
    public void setUp() {
        cacheService = new CacheService();
        actualCache = new ConcurrentHashMap<>(INITIAL_CAPACITY);
        fillExpectedCache();
        fillActualCache();
    }


    @Test
    public void sizeTest() {

        int actual = actualCache.size();
        int expected = cacheService.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntriesTest() {

        CacheEntry actual = actualCache.get(String.valueOf(key));
        CacheEntry expected = cacheService.get(String.valueOf(key));

        Assert.assertEquals(expected.getName(), actual.getName());
    }


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void IllegalArgumentExceptionTest(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Sorry there is no such key");

        cacheService.get("12345");
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