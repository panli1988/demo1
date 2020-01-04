package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author Administrator
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>{
}
