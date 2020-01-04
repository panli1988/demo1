package com.example.demo1.repository.ds2;

import com.example.demo1.repository.BaseRepository;
import com.example.demo1.repository.ds2.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface StudentRepository extends BaseRepository<Student,Long> {
}
