package com.example.accounting.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<K extends Number, T> {
    T get(K id);

    List<T> getAll();

    Optional<T> findByName(String name);

    void create(T obj);

    boolean delete(K id);

    void update(T obj);
}
