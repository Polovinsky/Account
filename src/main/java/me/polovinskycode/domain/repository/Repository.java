package me.polovinskycode.domain.repository;

public interface Repository<T> {

    T find(Long id);

    T save(T t);

    T update(T t);

    void delete(Long id);
}
