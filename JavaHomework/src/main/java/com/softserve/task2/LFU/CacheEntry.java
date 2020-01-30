package com.softserve.task2.LFU;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@ToString(exclude = "expirationTime")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheEntry that = (CacheEntry) o;
        return expirationTime == that.expirationTime &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expirationTime);
    }
}
