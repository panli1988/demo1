package com.example.demo1.service.impl;

import com.example.demo1.repository.BaseRepository;
import com.example.demo1.repository.ds2.StudentRepository;
import com.example.demo1.repository.ds2.entity.Student;
import com.example.demo1.service.BaseService;
import com.example.demo1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class StudentServiceImpl<Student,Long> extends BaseServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public BaseRepository getBaseRepository() {
        return studentRepository;
    }
}
