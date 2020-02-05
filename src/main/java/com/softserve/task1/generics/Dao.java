package com.softserve.task1.generics;

import java.util.List;
import java.util.UUID;

public interface Dao<E> {
    void add(E e);

    void delete(E e);

    void update(E e);

    E getById(UUID id);

    List<E> getAll();

}
