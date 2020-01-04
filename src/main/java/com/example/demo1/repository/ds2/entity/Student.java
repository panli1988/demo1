package com.example.demo1.repository.ds2.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Entity
@Table(name = "d_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 7993941906610435818L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String studentNo;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
