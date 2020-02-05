package com.softserve.task2.LFU;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CacheServiceTest {
    private CacheService cacheService;
    private String key = String.valueOf(555);

    @Before
    public void setUp() {
        cacheService = new CacheService();
    }

    @Test
    public void sizeTest() {

        for (int i = 0; i < 1000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }

        int actual = 1000;
        int expected = cacheService.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntriesTest() {

        for (int i = 0; i < 1000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }

        CacheEntry actual = new CacheEntry(key);
        CacheEntry expected = cacheService.get(String.valueOf(key));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void putEntriesTest() {

        cacheService.put("123", new CacheEntry("msg"));

        CacheEntry actual = new CacheEntry("msg");
        CacheEntry expected = cacheService.get("123");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeFromCacheTest() throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }
        Thread.sleep(6000);

        cacheService.put("111", new CacheEntry("111"));

        int actual = 1;
        int expected = cacheService.size();


        Assert.assertEquals(expected, actual);

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void IllegalArgumentExceptionTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Sorry there is no such key");

        cacheService.get("12345");
    }
}