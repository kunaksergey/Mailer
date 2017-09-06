package ua.shield.service;

import ua.shield.entity.MailAddress;
import ua.shield.entity.User;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
public interface IService<T> {

    public T findById(int id);

    public Iterable<T> findAll();

    public T find(T entity);

    Set<T> findAllByOwner();

    public T add(T entity);

    public Iterable<T> addAll(Iterable<T> iterable);

    public T update(T entity);

    public Iterable<T> updateAll(Iterable<T> iterable);

    public void delete(T entity);

    public void deleteAll();

    public void deleteById(int id);

    public void deleteById(Iterable<T> iterable);
}
