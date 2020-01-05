package com.example.demo1.controller;


import com.example.demo1.repository.ds2.entity.Student;
import com.example.demo1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/save")
    public String save(Student student){
        studentService.save(student);
        return "SUCCESS";
    }

    @GetMapping("/test")
    public String test(){
        return "SUCCESS";
    }
}
