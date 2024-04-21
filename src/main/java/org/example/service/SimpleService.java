package org.example.service;

import java.util.List;

public interface SimpleService<T,K> {

    void save(T obj);

    T findById(K id);

    boolean deleteById(K id);

    List<T> findAll();
}
