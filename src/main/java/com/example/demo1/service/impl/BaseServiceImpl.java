package com.example.demo1.service.impl;

import com.example.demo1.repository.BaseRepository;
import com.example.demo1.service.BaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
public abstract class BaseServiceImpl<T, ID> implements BaseService<T,ID> {

    public abstract BaseRepository getBaseRepository();


    @Override
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getBaseRepository().findAll(sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return getBaseRepository().findAllById(ids);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return getBaseRepository().saveAll(entities);
    }

    @Override
    public void flush() {
        getBaseRepository().flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return (S) getBaseRepository().saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        getBaseRepository().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        getBaseRepository().deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return (T) getBaseRepository().getOne(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return getBaseRepository().findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getBaseRepository().findAll(example,sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseRepository().findAll(pageable);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return getBaseRepository().findOne(example);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getBaseRepository().findAll(example,pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return getBaseRepository().count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return getBaseRepository().exists(example);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return getBaseRepository().findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return getBaseRepository().findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getBaseRepository().findAll(spec,pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return getBaseRepository().findAll(spec,sort);
    }

    @Override
    public long count(Specification<T> spec) {
        return getBaseRepository().count(spec);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getBaseRepository().findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getBaseRepository().existsById(id);
    }

    @Override
    public long count() {
        return getBaseRepository().count();
    }

    @Override
    public void deleteById(ID id) {
        getBaseRepository().deleteById(id);
    }

    @Override
    public void delete(T entity) {
        getBaseRepository().delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        getBaseRepository().deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        getBaseRepository().deleteAll();
    }

    @Override
    public <S extends T> S save(S entity) {
        return (S) getBaseRepository().save(entity);
    }
}
