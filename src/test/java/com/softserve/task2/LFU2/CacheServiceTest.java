package com.softserve.task2.LFU2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CacheServiceTest {
    private CacheService cacheService;

    @Before
    public void setUp() {
        cacheService = new CacheService();
    }

    @Test
    public void putIfCacheNotFullTest() {

        cacheService.put("111", new CacheEntry("msg"));

        CacheEntry actual = new CacheEntry("msg");
        CacheEntry expected = cacheService.get("111");


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void putIfCacheFullTest() {

        for (int i = 0; i < 150000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }

        CacheEntry actual = new CacheEntry(String.valueOf(125000));
        CacheEntry expected = cacheService.get("125000");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get() {

        for (int i = 0; i < 150000; i++) {
            if (i == 75000){
                cacheService.get("1");
            }
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }

        CacheEntry actual = new CacheEntry(String.valueOf(1));
        CacheEntry expected = cacheService.get(String.valueOf(1));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void size() {

        for (int i = 0; i < 150000; i++) {
            cacheService.put(String.valueOf(i), new CacheEntry(String.valueOf(i)));
        }

        int expected = cacheService.size();
        int actual = 100000;

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