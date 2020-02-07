package com.softserve.task2.LFU;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "expirationTime")
@EqualsAndHashCode(exclude = "expirationTime")
@Getter
@Setter
public class CacheEntry {
    private static final long TIME_TO_LIVE = 5000L;
    private String name;
    private long expirationTime;

    public CacheEntry(String name) {
        this.name = name;
        expirationTime = System.currentTimeMillis() + TIME_TO_LIVE;
    }
}
