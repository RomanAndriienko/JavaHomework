package com.softserve.task2.LFU;

import java.util.concurrent.ExecutionException;

public interface ICacheService<E> {

    void put(String key, E e);

    E get(String key) throws ExecutionException;
}
