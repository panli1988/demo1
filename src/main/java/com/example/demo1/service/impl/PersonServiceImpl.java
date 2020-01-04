package com.example.demo1.service.impl;

import com.example.demo1.repository.BaseRepository;
import com.example.demo1.repository.ds1.PersonRepository;
import com.example.demo1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class PersonServiceImpl<Person,Long> extends BaseServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public BaseRepository getBaseRepository() {
        return personRepository;
    }
}
