package me.polovinskycode.domain.repository;

import java.util.List;

public interface Repository<T> {

    T findById(Long id);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
