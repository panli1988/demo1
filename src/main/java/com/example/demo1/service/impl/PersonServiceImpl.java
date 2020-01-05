package com.example.demo1.service.impl;

import com.example.demo1.repository.BaseRepository;
import com.example.demo1.repository.ds1.PersonRepository;
import com.example.demo1.repository.ds1.entity.Person;
import com.example.demo1.repository.ds2.StudentRepository;
import com.example.demo1.repository.ds2.entity.Student;
import com.example.demo1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Administrator
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public BaseRepository getBaseRepository() {
        return personRepository;
    }

    @Transactional
    @Override
    public void testTransaction() {
        Person person = new Person();
        person.setName("A");
        personRepository.save(person);
        Student student = new Student();
        student.setName("zs");
        student.setStudentNo("1001");
        studentRepository.save(student);


    }
}
