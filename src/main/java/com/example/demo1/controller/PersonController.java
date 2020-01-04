package com.example.demo1.controller;

import com.example.demo1.repository.ds1.entity.Person;
import com.example.demo1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/save")
    public String save(Person person){
        personService.save(person);
        return "SUCCESS";
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
