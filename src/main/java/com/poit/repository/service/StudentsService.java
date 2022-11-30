package com.poit.repository.service;

import com.poit.repository.entity.Student;

import java.util.List;

public interface StudentsService {

    List<Student> findAll();

    void add(Student student);

    void update(Student student);
}
