package com.poit.repository.service.impl;

import com.poit.repository.entity.Student;
import com.poit.repository.service.StudentsService;

import java.util.List;

public class StudentsServiceImpl implements StudentsService {
    private final com.poit.repository.StudentsRepository studentsRepository;

    public StudentsServiceImpl(com.poit.repository.StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public void add(Student student) {
        studentsRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentsRepository.update(student);
    }
}
