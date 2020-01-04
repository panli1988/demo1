package com.example.demo1.repository.ds1;

import com.example.demo1.repository.BaseRepository;
import com.example.demo1.repository.ds1.entity.Person;
import org.springframework.stereotype.Repository;


/**
 * @author Administrator
 */
@Repository
public interface PersonRepository extends BaseRepository<Person,Long> {

}
