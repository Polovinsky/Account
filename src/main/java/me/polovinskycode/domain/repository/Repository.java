package me.polovinskycode.domain.repository;

public interface Repository<T> {

    T findById(Long id);

    void save(T t);

    void update(T t);

    void delete(T t);
}
